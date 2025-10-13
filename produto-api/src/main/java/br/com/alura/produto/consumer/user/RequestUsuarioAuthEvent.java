package br.com.alura.produto.consumer.user;

import java.time.Instant;
import br.com.alura.produto.model.EventoAuth;

public class RequestUsuarioAuthEvent {
    private String token;
    private String usuario;
    private Boolean ativo;
    private String tipo;
    private Instant timestamp;

    public RequestUsuarioAuthEvent()
    {

    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public EventoAuth toEventoAuth()
    {
        return new EventoAuth(usuario,ativo,tipo,timestamp,token);
    }
}
