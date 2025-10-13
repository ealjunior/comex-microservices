package br.com.alura.produto.service;

import br.com.alura.produto.model.EventoAuth;
import br.com.alura.produto.repository.EventoAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventoAuthService {

    @Autowired
    private EventoAuthRepository repository;

    public void cadastrar(EventoAuth EventoAuth) {
        repository.save(EventoAuth);
    }

    public Optional<EventoAuth> findById(Long idEventoAuth) {
        return repository.findById(idEventoAuth);
    }
}
