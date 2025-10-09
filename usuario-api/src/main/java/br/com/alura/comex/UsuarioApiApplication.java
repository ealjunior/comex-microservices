package br.com.alura.comex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class UsuarioApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }
}