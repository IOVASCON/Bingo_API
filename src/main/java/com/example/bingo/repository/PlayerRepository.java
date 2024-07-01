// Pacote onde a interface está localizada.
package com.example.bingo.repository;

// Importa a classe Player do pacote model.
import com.example.bingo.model.Player;
// Importa a interface ReactiveMongoRepository para fornecer operações de repositório reativo.
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

// Interface que estende ReactiveMongoRepository para fornecer operações CRUD reativas para Player.
public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    // A interface ReactiveMongoRepository já fornece métodos CRUD padrão como save,
    // findById, findAll, deleteById, etc.
}
