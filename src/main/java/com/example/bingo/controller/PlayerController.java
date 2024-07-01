// Pacote onde a classe está localizada.
package com.example.bingo.controller;

// Importa a classe Player do pacote model.
import com.example.bingo.model.Player;
// Importa a classe PlayerService do pacote service.
import com.example.bingo.service.PlayerService;
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
@RequestMapping("/players")
public class PlayerController {

    // Dependência do serviço PlayerService, que será injetada pelo Spring.
    @Autowired
    private PlayerService playerService;

    // Endpoint para obter todos os jogadores.
    // Método HTTP GET e URL /players.
    @GetMapping
    public Flux<Player> getAllPlayers() {
        // Chama o serviço para obter todos os jogadores.
        return playerService.findAll();
    }

    // Endpoint para obter um jogador pelo ID.
    // Método HTTP GET e URL /players/{id}.
    @GetMapping("/{id}")
    public Mono<Player> getPlayerById(@PathVariable String id) {
        // Chama o serviço para obter um jogador pelo ID.
        return playerService.findById(id);
    }

    // Endpoint para criar um novo jogador.
    // Método HTTP POST e URL /players.
    @PostMapping
    public Mono<Player> createPlayer(@RequestBody Player player) {
        // Chama o serviço para salvar um novo jogador.
        return playerService.save(player);
    }

    // Endpoint para atualizar um jogador existente pelo ID.
    // Método HTTP PUT e URL /players/{id}.
    @PutMapping("/{id}")
    public Mono<Player> updatePlayer(@PathVariable String id, @RequestBody Player player) {
        // Chama o serviço para atualizar um jogador existente.
        return playerService.update(id, player);
    }

    // Endpoint para deletar um jogador pelo ID.
    // Método HTTP DELETE e URL /players/{id}.
    @DeleteMapping("/{id}")
    public Mono<Void> deletePlayer(@PathVariable String id) {
        // Chama o serviço para deletar um jogador pelo ID.
        return playerService.deleteById(id);
    }
}
