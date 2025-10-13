package br.com.alura.produto.auth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthClient {

    private final RestClient client;

    public AuthClient(RestClient.Builder builder,
                      @Value("${gateway.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public ResponseTokenValidation validateToken(String bearerToken) {
        ResponseTokenValidation ans =  client.post()
                .uri("usuario-api/login/token/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new RequestTokenValidation(bearerToken))
                .retrieve()
                .body(ResponseTokenValidation.class);
        return ans;
    }

}