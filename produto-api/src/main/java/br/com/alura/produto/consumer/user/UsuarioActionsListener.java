package br.com.alura.produto.consumer.user;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.alura.produto.service.EventoAuthService;




@Component
public class UsuarioActionsListener {

    @Autowired
    private EventoAuthService eventoAuthService;

    @RabbitListener(queues = "user.login")
    public void recebeMensagem(RequestUsuarioAuthEvent event){
        try {
            String mensagem = """
                    [EVENTO][USUARIO] %s, usuário = %s, horário = %s
                    """.formatted(event.getTipo(),event.getUsuario(),event.getTimestamp());
            System.out.println(mensagem);
        } catch (IllegalArgumentException e) {
            throw new AmqpRejectAndDontRequeueException("Erro de validação: " + e.getMessage(), e);
        }catch (Exception e) {
            throw e;
        }
        eventoAuthService.cadastrar(event.toEventoAuth());
    }

    public UsuarioActionsListener() {
    }
}
