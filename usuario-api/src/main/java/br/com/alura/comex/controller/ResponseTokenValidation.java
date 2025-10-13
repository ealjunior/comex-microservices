package br.com.alura.comex.controller;

public class ResponseTokenValidation {

    private Boolean valid;

    public ResponseTokenValidation(Boolean valid)
    {
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}