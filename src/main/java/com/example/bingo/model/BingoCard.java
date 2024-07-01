// Pacote onde a classe está localizada.
package com.example.bingo.model;

// Importa a anotação @Id para marcar o campo id como identificador.
import org.springframework.data.annotation.Id;
// Importa a anotação @Document para indicar que esta classe é um documento MongoDB.
import org.springframework.data.mongodb.core.mapping.Document;

// Importa a classe Set do pacote util para trabalhar com conjuntos de números.
import java.util.Set;

// Anotação que indica que esta classe é um documento do MongoDB na coleção "bingoCards".
@Document(collection = "bingoCards")
public class BingoCard {
    // Anotação que indica que este campo é o identificador do documento.
    @Id
    private String id;

    // Conjunto de números da cartela de bingo.
    private Set<Integer> numbers;

    // Identificador do jogador associado a esta cartela.
    private String playerId;

    // Construtor que inicializa a cartela com um conjunto de números.
    public BingoCard(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    // Método para obter o identificador da cartela.
    public String getId() {
        return id;
    }

    // Método para obter os números da cartela.
    public Set<Integer> getNumbers() {
        return numbers;
    }

    // Método para obter o identificador do jogador associado.
    public String getPlayerId() {
        return playerId;
    }

    // Método para definir o identificador do jogador associado.
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
