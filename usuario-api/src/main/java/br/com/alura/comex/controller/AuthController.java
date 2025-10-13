package br.com.alura.comex.controller;

import java.time.Instant;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.security.DadosTokenJWT;
import br.com.alura.comex.service.TokenService;
import br.com.alura.comex.service.UsuarioService;
import jakarta.validation.Valid;
import br.com.alura.comex.publisher.user.RabbitPublisherConfig;
import br.com.alura.comex.publisher.user.RequestUsuarioAuthEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid RequestAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        RequestUsuarioAuthEvent event = new RequestUsuarioAuthEvent();
        Authentication authentication = null;
        event.setTimestamp(Instant.now());
        try {
           authentication = manager.authenticate(authenticationToken);
        } catch (Exception e) {
            event.setUsuario(dados.login());
            event.setTipo("BLOCK");
            event.setAtivo(false);
            event.setToken(null);
            rabbitTemplate.convertAndSend("user.login",event);
        }
        
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(usuario);

        event.setUsuario(usuario.getLogin());
        event.setTipo("AUTH");
        event.setAtivo(usuario.isEnabled());
        event.setTimestamp(Instant.now());
        event.setToken(tokenJWT);


        rabbitTemplate.convertAndSend("user.login",event);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/token/validate")
    public ResponseEntity<Object> validarToken(@RequestBody RequestTokenValidation dados) {
        
        String subject = tokenService.getSubject(dados.getToken());

        if(subject != null)
        {
            return ResponseEntity.ok(new ResponseTokenValidation(true));
        }

        return ResponseEntity.status(403).body(new ResponseTokenValidation(false));

        // enviar o evento

        
    }

    @PostMapping("/token/test")
    public HttpStatus teste(@RequestBody RequestTokenValidation dados) {

        return HttpStatus.OK;
    }

    @PostMapping("/init")
    public HttpStatus inicializaNovoUsuario() {

        String login = "admin";
        String senha = "admin123";
        usuarioService.save(login, senha);

        return HttpStatus.CREATED;
    }
}
