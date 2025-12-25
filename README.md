# api-tests

Repositório de estudos para **praticar a construção de APIs REST simples com Spring Boot**, consolidar fundamentos, testar boas práticas e evoluir continuamente habilidades de desenvolvimento.

---

## Estrutura do repositório

Este repositório é organizado em **múltiplos projetos Spring Boot**, cada um dentro de sua própria pasta (ex.: `api01/`, `api02/`, `api03/` etc.).

Cada pasta é um projeto independente (com seu próprio build e execução). A ideia é que cada diretório represente um conjunto de exercícios/versões incrementais.

> Dica: mantenha um `README.md` dentro de cada pasta (`api01`, `api02`, etc.) descrevendo o objetivo e endpoints daquele exercício.

---

## Pré-requisitos

- **Java** (recomendado: 17+)
- **Maven** (ou Maven Wrapper, se existir no projeto)
- Opcional: Postman/Insomnia/cURL para testar os endpoints

Verifique se está tudo ok:

```bash
java -version
mvn -version
```

---

## Como executar um dos projetos

Entre na pasta do exercício e rode:

```bash
cd api01
mvn clean package
mvn spring-boot:run
```

Repita para `api02` / `api03` trocando o diretório.

---

## Rodando em outra porta

Se a porta padrão (8080) estiver ocupada:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Ou, alternativamente, configure no `application.properties` do projeto:

```properties
server.port=8081
```

## Licença

Este repositório está sob a licença **GPL-3.0** (ver arquivo `LICENSE`, se disponível).
