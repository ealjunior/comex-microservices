package br.com.alura.produto.service;

import br.com.alura.produto.model.Categoria;
import br.com.alura.produto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.produto.producer.KafkaDispatcher;

import java.util.Optional;

@Component
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaService() {
        
    }

    public void cadastrar(Categoria categoria) {
        repository.save(categoria);
    }

    public boolean enviar(Categoria categoria){
        try (var dispatcher = new KafkaDispatcher<Categoria>()) {
            dispatcher.send("categoria-criada", categoria.getNome(),categoria);
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Optional<Categoria> findById(Long idCategoria) {
        return repository.findById(idCategoria);
    }
}
