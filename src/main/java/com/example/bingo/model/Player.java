// Pacote onde a classe está localizada.
package com.example.bingo.model;

// Importa a anotação @Id para marcar o campo id como identificador.
import org.springframework.data.annotation.Id;
// Importa a anotação @Document para indicar que esta classe é um documento MongoDB.
import org.springframework.data.mongodb.core.mapping.Document;

// Anotação que indica que esta classe é um documento do MongoDB.
@Document
public class Player {
    // Anotação que indica que este campo é o identificador do documento.
    @Id
    private String id;

    // Nome do jogador.
    private String name;

    // Email do jogador.
    private String email;

    // Métodos getters e setters para acessar e modificar os campos privados.

    // Método para obter o identificador do jogador.
    public String getId() {
        return id;
    }

    // Método para definir o identificador do jogador.
    public void setId(String id) {
        this.id = id;
    }

    // Método para obter o nome do jogador.
    public String getName() {
        return name;
    }

    // Método para definir o nome do jogador.
    public void setName(String name) {
        this.name = name;
    }

    // Método para obter o email do jogador.
    public String getEmail() {
        return email;
    }

    // Método para definir o email do jogador.
    public void setEmail(String email) {
        this.email = email;
    }
}
