// Pacote onde a classe está localizada.
package com.example.bingo.service;

// Importa a classe Player do pacote model.
import com.example.bingo.model.Player;
// Importa a interface PlayerRepository para operações de repositório reativo.
import com.example.bingo.repository.PlayerRepository;
// Importa a anotação @Autowired para injeção de dependência.
import org.springframework.beans.factory.annotation.Autowired;
// Importa a anotação @Service para marcar esta classe como um serviço Spring.
import org.springframework.stereotype.Service;
// Importa a classe Flux do Project Reactor para operações reativas que retornam múltiplos itens.
import reactor.core.publisher.Flux;
// Importa a classe Mono do Project Reactor para operações reativas que retornam um único item.
import reactor.core.publisher.Mono;

// Anotação que indica que esta classe é um serviço do Spring.
@Service
public class PlayerService {

    // Repositório de jogadores.
    @Autowired
    private PlayerRepository playerRepository;

    // Método para buscar todos os jogadores.
    public Flux<Player> findAll() {
        return playerRepository.findAll();
    }

    // Método para buscar um jogador pelo ID.
    public Mono<Player> findById(String id) {
        return playerRepository.findById(id);
    }

    // Método para salvar um novo jogador.
    public Mono<Player> save(Player player) {
        return playerRepository.save(player);
    }

    // Método para atualizar um jogador existente.
    public Mono<Player> update(String id, Player player) {
        return playerRepository.findById(id)
                .flatMap(existingPlayer -> {
                    existingPlayer.setName(player.getName());
                    existingPlayer.setEmail(player.getEmail());
                    return playerRepository.save(existingPlayer);
                });
    }

    // Método para deletar um jogador pelo ID.
    public Mono<Void> deleteById(String id) {
        return playerRepository.deleteById(id);
    }
}
