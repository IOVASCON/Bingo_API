// Pacote onde a classe está localizada.
package com.example.bingo.controller;

// Importa a classe BingoCard do pacote model.
import com.example.bingo.model.BingoCard;
// Importa a classe BingoService do pacote service.
import com.example.bingo.service.BingoService;
// Importa a anotação @Autowired para injeção de dependências.
import org.springframework.beans.factory.annotation.Autowired;
// Importa as anotações para definir os endpoints REST.
import org.springframework.web.bind.annotation.*;
// Importa a classe Flux do Project Reactor para operações reativas que retornam múltiplos itens.
import reactor.core.publisher.Flux;
// Importa a classe Mono do Project Reactor para operações reativas que retornam um único item.
import reactor.core.publisher.Mono;

// Anotação que indica que esta classe é um controlador REST, responsável por lidar com requisições HTTP.
@RestController
// Define a URL base para todos os endpoints dentro deste controlador.
@RequestMapping("/bingo")
public class BingoController {

    // Dependência do serviço BingoService, que será injetada pelo Spring.
    private final BingoService bingoService;

    // Construtor que usa injeção de dependência para inicializar o serviço
    // BingoService.
    @Autowired
    public BingoController(BingoService bingoService) {
        this.bingoService = bingoService;
    }

    // Endpoint para gerar um número especificado de cartelas de bingo.
    // Método HTTP POST e URL /bingo/generate.
    @PostMapping("/generate")
    public Flux<BingoCard> generateBingoCards(@RequestParam int numberOfCards) {
        // Chama o serviço para gerar as cartelas de bingo.
        return bingoService.generateBingoCards(numberOfCards);
    }

    // Endpoint para obter todas as cartelas de bingo.
    // Método HTTP GET e URL /bingo/cards.
    @GetMapping("/cards")
    public Flux<BingoCard> getAllBingoCards() {
        // Chama o serviço para obter todas as cartelas de bingo.
        return bingoService.getAllBingoCards();
    }

    // Endpoint para sortear um número.
    // Método HTTP POST e URL /bingo/draw.
    @PostMapping("/draw")
    public Mono<BingoCard> drawNumber() {
        // Chama o serviço para sortear um número.
        return bingoService.drawNumber();
    }

    // Endpoint para vincular uma cartela a um jogador.
    // Método HTTP POST e URL /bingo/assign.
    @PostMapping("/assign")
    public Mono<BingoCard> assignCardToPlayer(@RequestParam String cardId, @RequestParam String playerId) {
        // Chama o serviço para vincular a cartela ao jogador.
        return bingoService.assignCardToPlayer(cardId, playerId);
    }
}
