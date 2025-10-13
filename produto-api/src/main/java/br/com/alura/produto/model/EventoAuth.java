package br.com.alura.produto.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class EventoAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Boolean ativo;
    private String tipo;
    private Instant timestamp;

    @Column(name = "nome", nullable = false, length = 50)
    private String usuario;

    public EventoAuth() {
    }

    public EventoAuth(String usuario, Boolean ativo, String tipo, Instant timestamp, String token) {
        this.usuario = usuario;
        this.ativo =  ativo;
        this.tipo = tipo;
        this.timestamp = timestamp;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }




}