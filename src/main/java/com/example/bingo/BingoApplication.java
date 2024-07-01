// Pacote onde a classe está localizada.
package com.example.bingo;

// Importa a classe SpringApplication para iniciar a aplicação Spring Boot.
import org.springframework.boot.SpringApplication;
// Importa a anotação @SpringBootApplication que configura a aplicação Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que indica que esta é a classe principal de uma aplicação Spring Boot.
// Combina as anotações @Configuration, @EnableAutoConfiguration e @ComponentScan.
@SpringBootApplication
public class BingoApplication {

    // Método principal que inicia a aplicação.
    public static void main(String[] args) {
        // Executa a aplicação Spring Boot.
        SpringApplication.run(BingoApplication.class, args);
    }
}
