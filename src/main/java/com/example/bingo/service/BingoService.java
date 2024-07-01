// Pacote onde a classe está localizada.
package com.example.bingo.service;

// Importa a classe BingoCard do pacote model.
import com.example.bingo.model.BingoCard;
// Importa a interface BingoCardRepository para operações de repositório reativo.
import com.example.bingo.repository.BingoCardRepository;
// Importa a anotação @Autowired para injeção de dependência.
import org.springframework.beans.factory.annotation.Autowired;
// Importa a anotação @Service para marcar esta classe como um serviço Spring.
import org.springframework.stereotype.Service;
// Importa a classe Flux do Project Reactor para operações reativas que retornam múltiplos itens.
import reactor.core.publisher.Flux;
// Importa a classe Mono do Project Reactor para operações reativas que retornam um único item.
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

// Anotação que indica que esta classe é um serviço do Spring.
@Service
public class BingoService {

    // Repositório de cartelas de bingo.
    private final BingoCardRepository bingoCardRepository;
    // Conjunto para armazenar os números já sorteados.
    private final Set<Integer> drawnNumbers = new HashSet<>();

    // Construtor que utiliza a injeção de dependência para inicializar o
    // repositório de cartelas de bingo.
    @Autowired
    public BingoService(BingoCardRepository bingoCardRepository) {
        this.bingoCardRepository = bingoCardRepository;
    }

    // Método para gerar um determinado número de cartelas de bingo.
    public Flux<BingoCard> generateBingoCards(int numberOfCards) {
        return Flux.range(0, numberOfCards) // Gera um intervalo de números.
                .map(i -> generateUniqueBingoCard()) // Mapeia cada número para uma nova cartela de bingo única.
                .flatMap(bingoCardRepository::save); // Salva cada cartela no repositório.
    }

    // Método privado para gerar uma cartela de bingo única.
    private BingoCard generateUniqueBingoCard() {
        Random random = new Random();
        Set<Integer> cardNumbers;

        // Gera números aleatórios únicos até que a cartela seja única.
        do {
            cardNumbers = random.ints(1, 76)
                    .distinct()
                    .limit(20)
                    .boxed()
                    .collect(Collectors.toSet());
        } while (!isUniqueCard(cardNumbers));

        // Retorna uma nova cartela de bingo com os números gerados.
        return new BingoCard(cardNumbers);
    }

    // Método privado para verificar se uma cartela é única.
    private boolean isUniqueCard(Set<Integer> cardNumbers) {
        long commonNumbersThreshold = 5; // 1/4 de 20 números.

        // Verifica se a cartela gerada possui no máximo 1/4 dos mesmos números de
        // qualquer outra cartela existente.
        return bingoCardRepository.findAll()
                .filter(existingCard -> existingCard.getNumbers().stream()
                        .filter(cardNumbers::contains)
                        .count() <= commonNumbersThreshold)
                .hasElements()
                .blockOptional()
                .orElse(true);
    }

    // Método para obter todas as cartelas de bingo.
    public Flux<BingoCard> getAllBingoCards() {
        return bingoCardRepository.findAll();
    }

    // Método para sortear um número.
    public Mono<BingoCard> drawNumber() {
        Random random = new Random();
        int number;

        // Sorteia um número que ainda não foi sorteado.
        do {
            number = random.nextInt(75) + 1;
        } while (drawnNumbers.contains(number));

        // Adiciona o número sorteado ao conjunto de números sorteados.
        drawnNumbers.add(number);
        return Mono.just(new BingoCard(drawnNumbers));
    }

    // Método para vincular uma cartela a um jogador.
    public Mono<BingoCard> assignCardToPlayer(String cardId, String playerId) {
        // Verifica se o jogador já possui uma cartela para esta rodada.
        return bingoCardRepository.findAllByPlayerId(playerId)
                .hasElements()
                .flatMap(hasCard -> {
                    if (hasCard) {
                        return Mono.error(new RuntimeException("Player already has a card assigned for this round."));
                    } else {
                        return bingoCardRepository.findById(cardId)
                                .flatMap(card -> {
                                    card.setPlayerId(playerId);
                                    return bingoCardRepository.save(card);
                                });
                    }
                });
    }
}
