# 🧪 Spring Boot Test API - PT 03

1) Nesta API a ideia é receber o nome do usuário através da URL e imprimir na tela o conteúdo recebido em formato JSON.

2) Como estava nosso código na segunda API:

```
package com.example.api02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    

   @GetMapping("hello/{name}")
   public String hello(@PathVariable String name) {
        return "Hello, " + name + "! I am your Second API built using Spring Boot! ";
    } 
    
}
```

3) Quais alterações vamos fazer?

```diff
package com.example.api03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
+ @RequestMapping("api")
public class ApiController {
    
    @GetMapping("hello/{name}")
+    public Greeting hello(@PathVariable String name) {
+        return new Greeting(
+            name,
+            "Hello, " + name + "! I am your third API built using Spring Boot!"
        );
    } 

+    public record Greeting(String name, String message) {}
    
}
```

4) Para executar o projeto sempre verifique se você está na pasta que contém o arquivo pom.xml. Em nosso projeto, este arquivo se encontra na pasta api03. Faça a execução através deste comando:
```bash
mvn clean package
mvn spring-boot:run
```

5) Importante agora temos de acessar o endereço http://localhost:8080/api/hello/ (ao invés de http://localhost:8080/hello/)

6) Repare nas alterações em relação à segunda API que fizemos e tente entender as diferenças:

- @RequestMapping("api") : define um prefixo de rota para todos os endpoints da classe. É da família das anotações do Spring MVC que mapeiam URLs. Deste modo, nosso ponto de acesso vai se iniciar em http://localhost:8080/api/ (ao inves de http://localhost:8080/).

- public record Greeting(String name, String message) {} : nesta versão da API vamos retornar um JSON. Então, vamos criar um record para auxiliar nesta etapa.

- Após criado o record Greeting, vamos retornar este objeto -> que aparecerá como um JSON pro usuário. 






