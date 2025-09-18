package br.com.fiap.digitalbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return """
                Nome do projeto: digitalbank
                Integrantes: Gabriel Oliveira Rossi
                """;
    }
}
