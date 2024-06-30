package com.example.bingo.controller;

import com.example.bingo.model.BingoCard;
import com.example.bingo.service.BingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bingo")
public class BingoController {

    private final BingoService bingoService;

    @Autowired
    public BingoController(BingoService bingoService) {
        this.bingoService = bingoService;
    }

    @PostMapping("/generate")
    public Flux<BingoCard> generateBingoCards(@RequestParam int numberOfCards) {
        return bingoService.generateBingoCards(numberOfCards);
    }

    @GetMapping("/cards")
    public Flux<BingoCard> getAllBingoCards() {
        return bingoService.getAllBingoCards();
    }

    @PostMapping("/draw")
    public Mono<BingoCard> drawNumber() {
        return bingoService.drawNumber();
    }

    @PostMapping("/assign")
    public Mono<BingoCard> assignCardToPlayer(@RequestParam String cardId, @RequestParam String playerId) {
        return bingoService.assignCardToPlayer(cardId, playerId);
    }
}
