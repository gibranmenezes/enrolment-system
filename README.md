# Projeto Ernolment System

Project developed in Alura Code Challenge.

For more information about the specifications : `https://drive.google.com/file/d/1SyeaazHMBT0CjLYJufntRccCY_9YlLj_/view?usp=drive_link`


## Project Configuration

Java 22
Docker
Mysql
SpringBoot 3.2.4

## Execução

### Via Docker:
 - Abra o diretório Docker que está na raiz do projeto. Abra no terminal e execute o comando: docker compose  up -d --build
 - Após todo o processo de criação das imagens a aplicação estará pronta.

 ### Via IDE:
  - Abra o projeto na sua IDE, mas garanta que o MONGODB esteja executando antes de subir a aplicação. Após o MONGODB ativo, executar o run da IDE.

### Via CodeSpace do GitHub:
 - Na página do repositório no GitHub, click kem CODE e em seguida em CodeSpace. Crie um CodeSpace e siga os passo da execução via Docker.

## Utilizando a aplicação
 - A aplicação tem suporte a API do swagger. Após execução da aplicação, acesse o link `http://localhost:8080/swagger-ui/index.html`. Siga os padrões de requisição.

## Casos de uso

#### Ao inicializar a aplicação uma carga de usuário no banco de dados. 

#### Para cadastrar um shipping é necessário primeiro realizar a consulta de usuários e escolher um userId.

#### Com esse userId o usuário pode escolher quais informações deseja salvar(CRUD dinâmico)
        id, code, postalCode, sentDate, deliveryDate, type, weight, volume, packAmount e Status

#### Id e Code são gerados automaticamente

#### Status recebe o valor "PREPARING" que pode ser atualizado: 
        "PREPARING", "READY", "SENT", "DELIVERED"

#### sentDate e deliveryDate são geradas no momento em que o Status é atualizado.

#### Type:  
    "DEFAULT" ou "EXPRESS"

### Restrições:

#### BUSCANDO:
    O sistema tem uma busca paginada que retorna todos os shipping para um userID

#### Atualizando Status:
    O status pode ser atualizado usando o endpoint `/shippings/status` no swagger. 
    Se o status for SENT ele só pode ser atualizado para Delivered.
    Se o status for Delivered ele não pode ser atualizado.    

#### Atualizando informações do shipping:
 - Os campos  postalCode, type, weight, volume, packAmount só podem ser atualizados se o status for "PREPARING" ou "READY".

 #### Excluindo frete:
  - Um shipping não pode ser excluído se já tiver saído para entrega: Status "SENT"

## Tecnologias e ferramentas


Intellij
ApiSwagger


