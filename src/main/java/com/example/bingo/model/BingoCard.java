package com.example.bingo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "bingoCards")
public class BingoCard {
    @Id
    private String id;
    private Set<Integer> numbers;
    private String playerId; // Adicionando referência ao jogador

    public BingoCard(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getId() {
        return id;
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
