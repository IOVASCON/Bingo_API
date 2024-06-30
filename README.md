# Bingo API

Este projeto é uma API de jogo de Bingo desenvolvida utilizando as seguintes tecnologias:

- **Java**
- **MongoDB**
- **Spring WebFlux**
- **Docker (opcional)**

## Requisitos

### Funcionais

1. **Gerenciamento de Jogadores (CRUD)**
   - **Criar** um novo jogador.
   - **Recuperar** informações de um jogador.
   - **Atualizar** informações de um jogador.
   - **Deletar** um jogador.
   - **Find on demand**: Buscar jogadores sob demanda.

2. **Gerenciamento de Cartelas**
   - **Gerar Cartelas**: As cartelas devem ser geradas com números aleatórios seguindo as regras:
     - Todas as cartelas geradas devem ter quantidades iguais de números.
     - Cada cartela deve ter 20 números.
     - Uma cartela pode ter no máximo 1/4 dos mesmos números de outra cartela.
     - As cartelas da rodada só podem ser geradas antes de começar o sorteio dos números.

3. **Vinculação de Cartelas a Jogadores**
   - Um jogador pode ser vinculado a uma cartela por rodada.

## Endpoints

### Jogadores

- **Criar Jogador**
  - **URL**: `/players`
  - **Método**: `POST`
  - **Body**:

        {
        "name": "John Doe",

        "email": "john.doe@example.com"
        }

- **Obter Todos os Jogadores**

  - **URL**: `/players`
  - **Método**: `GET`

- **Obter Jogador por ID**
  - **URL**: `/players/{id}`
  - **Método**: `GET`

- **Atualizar Jogador**
  - **URL**: `/players/{id}`
  - **Método**: `PUT`
  - **Body**:

        {
        "name": "John Doe Updated",

        "email": "john.doe.updated@example.com"
        }

- **Deletar Jogador**
  - **URL**: `/players/{id}`
  - **Método**: `DELETE`

### Cartelas

- **Gerar Cartelas**
  - **URL**: `/bingo/cards`
  - **Método**: `POST`
  - **Body**:
    {
      "playerId": "player-id",
      "numberOfCards": 5
    }

- **Obter Cartelas por Jogador**
  - **URL**: `/bingo/cards/player/{playerId}`
  - **Método**: `GET`

## Instalação

### Pré-requisitos

- Java 11 ou superior
- Maven
- MongoDB

### Passos

1. Clone o repositório:

   git clone [https://github.com/IOVASCON/Bingo_API.git]
   cd Bingo_API/bingo-api

2. Compile o projeto:
    mvn clean install

3. Execute o projeto:
    mvn spring-boot:run

## Docker (Opcional)

Para executar o MongoDB com Docker:

    1. Baixe a imagem do MongoDB:

    docker pull mongo

    2. Execute o contêiner do MongoDB:

    docker run -d -p 27017:27017 --name bingo-mongo mongo

## Contribuição

    Fork o projeto.
    Crie uma nova branch (git checkout -b feature/nova-feature).
    Faça commit das suas mudanças (git commit -am 'Adiciona nova feature').
    Faça push para a branch (git push origin feature/nova-feature).
    Crie um novo Pull Request.
