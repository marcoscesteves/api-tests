# Spring Boot Test API - API02

1) Nesta API a ideia é receber o nome do usuário através da URL e imprimir na tela. Sendo uma versão que nos permitirá aprender um pouco mais além do que fizemos na primeira API.

2) Na primeira API construída, nós somente "subimos" o servidor. Neste exercício, agora, vamos conhecer melhor o que estava rodando por trás dos panos.

3) No exercício 01 (api01) obtivemos o seguinte resultado em tela:

    ![Janela do navegador exibindo localhost:8080/hello com a mensagem "Hello! I am your first API built using Spring Boot!" mostrada em texto preto sobre fundo branco](../api01/imagem2.jpg)

4) Esta tela inicial padrão é realizar através do seguinte código (/api_tests/api01/src/main/java/com/example/api01/ApiController.java):

    ```
    package com.example.api01;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class ApiController {
        
        @GetMapping("hello")
        public String hello() {
            return "Hello! I am your first API built using Spring Boot! ";
        } 
        
    }
    ```

5) Quais melhorias faremos nesta versão? Repare nas linhas ressaltadas abaixo: 

    ```diff
    package com.example.api02;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    + import org.springframework.web.bind.annotation.PathVariable;

    @RestController
    public class ApiController {
    

    +   @GetMapping("hello/{name}")
    +   public String hello(@PathVariable String name) {
    +        return "Hello, " + name + "! I am your Second API built using Spring Boot! ";
        } 
        
    }
    ```

6) Para executar o projeto sempre verifique se você está na pasta que contém o arquivo pom.xml. Em nosso projeto, este arquivo se encontra na pasta api02. Faça a execução através deste comando:
    ```bash
    mvn clean package
    mvn spring-boot:run
    ```

7) Teste o servidor
    ![Janela do navegador exibindo a URL localhost:8080/hello/Marcos com a mensagem Hello, Marcos! I am your Second API built using Spring Boot! em texto preto sobre fundo branco, demonstrando o funcionamento da API com parâmetro de nome dinâmico na URL](imagem1.jpg)

8) O que aprendemos neste exercício:

    **i) @GetMapping("hello/{name}")**
    - Mapeia um endpoint HTTP GET para o caminho `hello/{name}`
    - `{name}` é um **segmento variável** da URL (placeholder)
    - Permite URLs dinâmicas como `/hello/João`, `/hello/Maria`, etc.
    - Diferente da API01 que tinha rota fixa `/hello`

    **ii) @PathVariable String name**
    - Anotação que captura o valor da variável na URL
    - "Pegue o valor que está em `{name}` na URL e coloque neste parâmetro"
    - Spring automaticamente faz essa conversão
    - Nome do parâmetro deve corresponder ao nome na URL (ou usar `@PathVariable("name")`)

    **iii) Como funciona na prática:**
    - URL acessada: `http://localhost:8080/hello/Marcos`
    - Spring extrai `"Marcos"` da URL
    - Injeta no parâmetro `name` do método
    - Método executa: `"Hello, Marcos! I am your Second API..."`
    - Resposta retorna ao navegador

9) Feche o servidor aberto e vá para exercício 03 (api_testes/api03)




