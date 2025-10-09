package br.com.alura.produto.auth;

public class RequestTokenValidation{

    private String token;

    public RequestTokenValidation(String bearerToken) {
        this.token = bearerToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}