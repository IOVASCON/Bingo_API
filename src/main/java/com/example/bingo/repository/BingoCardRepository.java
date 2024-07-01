// Pacote onde a interface está localizada.
package com.example.bingo.repository;

// Importa a classe BingoCard do pacote model.
import com.example.bingo.model.BingoCard;
// Importa a interface ReactiveMongoRepository para fornecer operações de repositório reativo.
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
// Importa a classe Flux do Project Reactor para operações reativas que retornam múltiplos itens.
import reactor.core.publisher.Flux;

// Interface que estende ReactiveMongoRepository para fornecer operações CRUD reativas para BingoCard.
public interface BingoCardRepository extends ReactiveMongoRepository<BingoCard, String> {
    // Método personalizado para buscar todas as cartelas de bingo associadas a um
    // determinado jogador pelo playerId.
    Flux<BingoCard> findAllByPlayerId(String playerId);
}
