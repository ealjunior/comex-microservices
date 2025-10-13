package br.com.alura.comex.controller;

public class RequestTokenValidation{

    private String token;

    public RequestTokenValidation(String bearerToken) {
        this.token = bearerToken;
    }

    public RequestTokenValidation() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}