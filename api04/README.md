# Spring Boot Test API - API04

1) Nesta API vamos aprender a trabalhar com **Query Parameters** (parâmetros de consulta na URL). Diferente do `@PathVariable` que vimos antes, os query parameters usam o formato `?chave=valor` após o caminho da URL.

2) Como estava nosso código na terceira API:

    ```java
    package com.example.api03;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    @RestController
    @RequestMapping("api")
    public class ApiController {
        
        @GetMapping("hello/{name}")
        public Greeting hello(@PathVariable String name) {
            return new Greeting(
                name,
                "Hello, " + name + "! I am your third API built using Spring Boot!"
            );
        } 

        public record Greeting(String name, String message) {}
        
    }
    ```

3) Quais alterações vamos fazer?

    ```diff
    package com.example.api04;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    + import org.springframework.web.bind.annotation.RequestParam;

    @RestController
    @RequestMapping("api")
    public class ApiController {
        
    +    @GetMapping("buscar")
    +    public SearchResult buscar
    +    (
    +            @RequestParam String nome,
    +            @RequestParam(required = false) Integer idade
    +    ) 
    +   {
    +        
    +        String mensagem;
    +        if (idade != null) {
    +            mensagem = String.format("Buscando por: %s, %d anos", nome, idade);
    +        } else {
    +            mensagem = String.format("Buscando por: %s", nome);
    +        }
    +        
    +        return new SearchResult(nome, idade, mensagem);
    +    }
    +    
    +    @GetMapping("filtrar")
    +    public FilterResult filtrar
    +    (
    +       @RequestParam(required = false, defaultValue = "todos") 
    +       String categoria,
    +       @RequestParam(required = false, defaultValue = "10") 
    +       int limite,
    +       @RequestParam(required = false, defaultValue = "false") 
    +       boolean ativo
    +    ) 
    +    {
    +        
    +        return new FilterResult(categoria, limite, ativo, 
    +            String.format("Filtrando %d itens da categoria '%s' (ativo: %s)", 
    +                limite, categoria, ativo));
    +    }

    +    public record SearchResult(String nome, Integer idade, String mensagem) {}
    +    public record FilterResult(String categoria, int limite, boolean ativo, String resultado) {}
        
    }
    ```

4) Para executar o projeto sempre verifique se você está na pasta que contém o arquivo pom.xml. Em nosso projeto, este arquivo se encontra na pasta api04. Faça a execução através deste comando:
    ```bash
    mvn clean package
    mvn spring-boot:run
    ```

5) Teste o servidor com diferentes URLs:

    **Exemplo 1 - Parâmetro obrigatório:**
    ```
    http://localhost:8080/api/buscar?nome=João
    ```
    Retorna: `{"nome":"João","idade":null,"mensagem":"Buscando por: João"}`

    **Exemplo 2 - Múltiplos parâmetros:**
    ```
    http://localhost:8080/api/buscar?nome=Maria&idade=25
    ```
    Retorna: `{"nome":"Maria","idade":25,"mensagem":"Buscando por: Maria, 25 anos"}`

    **Exemplo 3 - Parâmetros com valores padrão:**
    ```
    http://localhost:8080/api/filtrar
    ```
    Retorna: `{"categoria":"todos","limite":10,"ativo":false,"resultado":"Filtrando 10 itens da categoria 'todos' (ativo: false)"}`

    **Exemplo 4 - Customizando filtros:**
    ```
    http://localhost:8080/api/filtrar?categoria=livros&limite=5&ativo=true
    ```
    Retorna: `{"categoria":"livros","limite":5,"ativo":true,"resultado":"Filtrando 5 itens da categoria 'livros' (ativo: true)"}`

6) O que aprendemos neste exercício:

    **i) Entendendo tipos de retorno e nomes de métodos**
    ```java
    public SearchResult buscar(...) {
    //     ↑            ↑
    //   TIPO         NOME DO MÉTODO
        return new SearchResult(...);  // retorna um objeto do tipo SearchResult (record)
    }
    ```
    - **Nome do método**: `buscar` (verbo, ação que o método realiza)
    - **Tipo de retorno**: `SearchResult` (substantivo, tipo do objeto retornado)
    - O método `buscar` **retorna** um objeto do tipo `SearchResult` (nosso record)
    - É comum em Java métodos retornarem objetos do mesmo tipo que aparece no nome

    **ii) @RequestParam vs @PathVariable**
    - `@PathVariable`: pega valores do **caminho** da URL → `/hello/{nome}`
    - `@RequestParam`: pega valores dos **parâmetros de consulta** → `/buscar?nome=João&idade=25`

    **iii) @RequestParam String nome**
    - Por padrão, o parâmetro é **obrigatório**
    - Se não for enviado, retorna erro 400 (Bad Request)

    **iv) @RequestParam(required = false)**
    - Torna o parâmetro **opcional**
    - Se não for enviado, o valor será `null`
    - Útil quando queremos filtros ou buscas flexíveis

    **v) @RequestParam(defaultValue = "valor")**
    - Define um **valor padrão** se o parâmetro não for enviado
    - Elimina a necessidade de verificar se é `null`
    - Sempre use com parâmetros opcionais para melhor experiência

    **vi) Query Parameters na URL**
    - Formato: `?parametro1=valor1&parametro2=valor2`
    - Primeiro parâmetro usa `?`, os demais usam `&`
    - Perfeito para filtros, buscas e paginação

7) Diferenças práticas:

    | Característica | PathVariable | RequestParam |
    |----------------|--------------|--------------|
    | **Sintaxe** | `/hello/João` | `/buscar?nome=João` |
    | **Uso típico** | Identificadores | Filtros e opções |
    | **Obrigatório** | Sim (parte da rota) | Configurável |
    | **Múltiplos valores** | Difícil | Fácil (`?a=1&b=2`) |

8) 💡 **Dica importante:** Query Parameters são ideais quando você tem:
    - Parâmetros opcionais
    - Múltiplos filtros
    - Configurações de paginação
    - Opções de ordenação

9) Feche o servidor em execução e vá para o exercício 05 (quando disponível).
