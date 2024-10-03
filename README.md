# Projeto API - Codificação para Back-End

Projeto foi criado para a finalização da matéria Codificação para Back-End em que foi introduzido o Framewok Springboot. O intuito dessa API é servir de cadastramento de clientes para lojas virtuais sendo a [Havan](https://github.com/Arthurdnl/projeto-api) - Front-End de testes, usada para demonstrá-la em uso.
Ela conta com listagem, busca por ID, cadastro, login, atualização e exclusão. Enquanto os produtos podem ser adicionados, buscados por ID e adicionar imagens localmente ou via link.

## Requisitos

- Java (21 ou superior)

## Instalação 

Clone o arquivo em algum repositório com:

```
https://github.com/WSgabri3l/projeto-api-back
```

Busque o arquivo <em>HavanApplication.java</em> e rode na sua máquina.

Lembrando que esta é uma aplicação local, logo, você deve utlizar <em>http://localhost:8080</em> na sua URL para utilizá-la.

## Endpoints



### Client



Esses Endpoints referem-se aos Clientes.


1. POST



Rota: POST | http://localhost:8080/client/v1

Descrição: Adiciona um cliente ao banco de dados.


```
{
  "name" : "pedrinho",
  "email" : "aaaa@gmail.com",
  "password" : "abc123",
  "cpf" : "782.481.580-39",
  "cep" : "000000-000"
}
```


2. GET



Rota: GET | http://localhost:8080/client/v1

Descrição: Retorna todos os clientes presentes no banco.



Rota: GET | http://localhost:8080/client/v1/{id}

Descrição: Retorna o cliente referente àquele ID.


3. PUT



Rota: PUT | http://localhost:8080/client/v1/{id}

Descrição: Atualiza os dados do cliente encontrado a partir do ID.


```
{
  "name" : "pedrinho",
  "email" : "aaaa@gmail.com",
  "cpf" : "782.481.580-39",
  "cep" : "000000-000"
}
```



Rota: PUT | http://localhost:8080/client/v1/login

Descrição: Retorna os dados do usuário a partir da confirmação de seu CPF e senha.


```
{
  "cpf" : "782.481.580-39",
  "password" : "abc123"
}
```



4. DELETE



Rota: DELETE | http://localhost:8080/client/v1/{id}
Descrição: Deleta o cliente do banco de dados a partir de seu ID.



### Product



Esses Endpoints referem-se aos Produtos.


1. POST



Rota: POST | http://localhost:8080/product/v1

Descrição: Adiciona um cliente ao banco de dados.



```
{
  "name" : "queijo",
  "code" : "001",
  "price" : 3.99,
  "imagePath" : ROTA ou LINK
}
```



Rota: POST | http://localhost:8080/products/files


Descrição: Transfere imagens localmente para dentro da aplicação.


Como Usar: 


Estando dentro dentro de uma pasta, ecreva "cmd" e abra a linha de comando.


Em seguida, digite:



```
curl -X POST -F "file=@NOME_DO_ARQUIVO" http://localhost:8080/products/files/upload
```



E, assim, a imagem estará presente na pasta Upload.



2. GET



Rota: GET | http://localhost:8080/product/v1

Descrição: Retorna todos os produtos catalogados.



Rota: GET | http://localhost:8080/product/v1/{id}

Descrição: Retorna o produto com base em seu ID.

#

*Desenvolvido por [Paulo Gabriel](https://github.com/WSgabri3l), [Arthur Davis](https://github.com/Arthurdnl), [Ryan Medeiros](https://github.com/Ryanmedeirosp), [José Bulhões](https://github.com/juneonju), [Dalton Alex](https://github.com/dalton4lex)*
