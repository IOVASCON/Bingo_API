package com.example.bingo.service;

import com.example.bingo.model.BingoCard;
import com.example.bingo.repository.BingoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BingoService {

    private final BingoCardRepository bingoCardRepository;
    private final Set<Integer> drawnNumbers = new HashSet<>();

    @Autowired
    public BingoService(BingoCardRepository bingoCardRepository) {
        this.bingoCardRepository = bingoCardRepository;
    }

    public Flux<BingoCard> generateBingoCards(int numberOfCards) {
        return Flux.range(0, numberOfCards)
                .map(i -> generateUniqueBingoCard())
                .flatMap(bingoCardRepository::save);
    }

    private BingoCard generateUniqueBingoCard() {
        Random random = new Random();
        Set<Integer> cardNumbers;

        do {
            cardNumbers = random.ints(1, 76)
                    .distinct()
                    .limit(20)
                    .boxed()
                    .collect(Collectors.toSet());
        } while (!isUniqueCard(cardNumbers));

        return new BingoCard(cardNumbers);
    }

    private boolean isUniqueCard(Set<Integer> cardNumbers) {
        long commonNumbersThreshold = 5; // 1/4th of 20 numbers

        return bingoCardRepository.findAll()
                .filter(existingCard -> existingCard.getNumbers().stream()
                        .filter(cardNumbers::contains)
                        .count() <= commonNumbersThreshold)
                .hasElements()
                .blockOptional()
                .orElse(true);
    }

    public Flux<BingoCard> getAllBingoCards() {
        return bingoCardRepository.findAll();
    }

    public Mono<BingoCard> drawNumber() {
        Random random = new Random();
        int number;

        do {
            number = random.nextInt(75) + 1;
        } while (drawnNumbers.contains(number));

        drawnNumbers.add(number);
        return Mono.just(new BingoCard(drawnNumbers));
    }

    public Mono<BingoCard> assignCardToPlayer(String cardId, String playerId) {
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
