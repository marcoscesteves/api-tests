# Spring Boot Test API - API06

1) Nesta API vamos aprender a **buscar um recurso específico por ID** e trabalhar com **tratamento de erros**. Vamos melhorar nossa API adicionando a capacidade de buscar um usuário individual e lidar com casos onde o usuário não existe.

2) Como estava nosso código na quinta API:

    ```java
    package com.example.api05;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("api/usuarios")
    public class ApiController {
        
        private List<Usuario> usuarios = new ArrayList<>();
        private Long proximoId = 1L;
        
        @PostMapping
        public Usuario criar(@RequestBody Usuario usuario) {
            usuario.setId(proximoId++);
            usuarios.add(usuario);
            return usuario;
        }
        
        @GetMapping
        public List<Usuario> listar() {
            return usuarios;
        }
    }
    ```

3) Quais alterações vamos fazer?

    ```diff
    package com.example.api06;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    + import org.springframework.web.bind.annotation.PathVariable;
    + import org.springframework.http.ResponseEntity;
    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("api/usuarios")
    public class ApiController {
        
        private List<Usuario> usuarios = new ArrayList<>();
        private Long proximoId = 1L;
        
        @PostMapping
        public Usuario criar(@RequestBody Usuario usuario) {
            usuario.setId(proximoId++);
            usuarios.add(usuario);
            return usuario;
        }
        
        @GetMapping
        public List<Usuario> listar() {
            return usuarios;
        }
        
    +    // GET - Buscar usuário por ID
    +    @GetMapping("/{id}")
    +    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
    +        for (Usuario usuario : usuarios) {
    +            if (usuario.getId().equals(id)) {
    +                return ResponseEntity.ok(usuario);
    +            }
    +        }
    +        return ResponseEntity.notFound().build();
    +    }
    }
    ```

4) **O que é ResponseEntity?**
   - É uma classe do Spring que nos permite controlar **toda a resposta HTTP**
   - Podemos definir o **código de status** (200, 404, etc.)
   - Podemos definir **headers** personalizados
   - Podemos retornar o **corpo da resposta**
   - É mais profissional do que só retornar o objeto

5) **Códigos de Status HTTP:**
   - **200 OK** - Sucesso! Recurso encontrado
   - **404 Not Found** - Recurso não encontrado
   - **201 Created** - Recurso criado com sucesso
   - **204 No Content** - Sucesso, mas sem conteúdo na resposta

6) **Estrutura completa do código:**

    ```java
    package com.example.api06;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.http.ResponseEntity;
    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("api/usuarios")
    public class ApiController {
        
        // Lista em memória para armazenar os usuários
        private List<Usuario> usuarios = new ArrayList<>();
        private Long proximoId = 1L;
        
        // POST - Criar um novo usuário
        @PostMapping
        public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
            usuario.setId(proximoId++);
            usuarios.add(usuario);
            return ResponseEntity.status(201).body(usuario);
        }
        
        // GET - Listar todos os usuários
        @GetMapping
        public List<Usuario> listar() {
            return usuarios;
        }
        
        // GET - Buscar usuário por ID
        @GetMapping("/{id}")
        public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
            for (Usuario usuario : usuarios) {
                if (usuario.getId().equals(id)) {
                    return ResponseEntity.ok(usuario);
                }
            }
            return ResponseEntity.notFound().build();
        }
    }
    ```

7) **Classe Usuario:**

    ```java
    package com.example.api06;

    public class Usuario {
        private Long id;
        private String nome;
        private String email;
        private Integer idade;

        // Construtores
        public Usuario() {}
        
        public Usuario(Long id, String nome, String email, Integer idade) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.idade = idade;
        }

        // Getters e Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getIdade() {
            return idade;
        }

        public void setIdade(Integer idade) {
            this.idade = idade;
        }
    }
    ```

---

## 🎯 O que você vai aprender

- ✅ Como buscar um recurso específico por ID
- ✅ Como usar `ResponseEntity` para controlar a resposta
- ✅ Como retornar diferentes códigos de status HTTP
- ✅ Como tratar o caso de "não encontrado" (404)
- ✅ Como melhorar a API com respostas mais profissionais

---

## ▶️ Como Executar o Projeto

1. **Entre na pasta do projeto:**
   ```bash
   cd api06
   ```

2. **Execute o projeto:**
   ```bash
   mvn spring-boot:run
   ```

3. **O servidor vai iniciar em:** `http://localhost:8080`

---

## 🧪 Como Testar os Endpoints

### 1️⃣ Criar alguns usuários primeiro (POST)

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Marcos",
    "email": "marcos@email.com",
    "idade": 25
  }'
```

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana",
    "email": "ana@email.com",
    "idade": 30
  }'
```

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Pedro",
    "email": "pedro@email.com",
    "idade": 28
  }'
```

### 2️⃣ Listar todos os usuários (GET)

```bash
curl http://localhost:8080/api/usuarios
```

**Resultado esperado:**
```json
[
  {
    "id": 1,
    "nome": "Marcos",
    "email": "marcos@email.com",
    "idade": 25
  },
  {
    "id": 2,
    "nome": "Ana",
    "email": "ana@email.com",
    "idade": 30
  },
  {
    "id": 3,
    "nome": "Pedro",
    "email": "pedro@email.com",
    "idade": 28
  }
]
```

### 3️⃣ Buscar usuário específico por ID (GET)

**Buscar o usuário com ID 1:**
```bash
curl http://localhost:8080/api/usuarios/1
```

**Resultado esperado:**
```json
{
  "id": 1,
  "nome": "Marcos",
  "email": "marcos@email.com",
  "idade": 25
}
```

**Buscar o usuário com ID 2:**
```bash
curl http://localhost:8080/api/usuarios/2
```

### 4️⃣ Testar o erro 404 (usuário não existe)

**Buscar um ID que não existe:**
```bash
curl -i http://localhost:8080/api/usuarios/999
```

**Resultado esperado:**
```
HTTP/1.1 404 
...
(corpo vazio)
```

A opção `-i` mostra os headers, onde você verá o status `404 Not Found`.

---

## 🧰 Testando com Postman/Insomnia

### POST - Criar usuário
- **Método:** POST
- **URL:** `http://localhost:8080/api/usuarios`
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**
  ```json
  {
    "nome": "Marcos",
    "email": "marcos@email.com",
    "idade": 25
  }
  ```

### GET - Listar todos
- **Método:** GET
- **URL:** `http://localhost:8080/api/usuarios`

### GET - Buscar por ID
- **Método:** GET
- **URL:** `http://localhost:8080/api/usuarios/1`
- **URL (teste 404):** `http://localhost:8080/api/usuarios/999`

---

## 📝 Conceitos Importantes

### ResponseEntity
Uma classe do Spring que representa toda a resposta HTTP:
```java
ResponseEntity.ok(usuario)              // 200 OK com corpo
ResponseEntity.notFound().build()       // 404 Not Found sem corpo
ResponseEntity.status(201).body(usuario) // 201 Created com corpo
```

### PathVariable com tipo específico
```java
@GetMapping("/{id}")
public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id)
```
O Spring converte automaticamente a String da URL para Long.

### Status HTTP 201 Created
Quando criamos um recurso, é mais correto retornar `201 Created` em vez de `200 OK`:
```java
return ResponseEntity.status(201).body(usuario);
```

### Busca em Lista
Usamos um loop `for` para percorrer a lista e encontrar o usuário:
```java
for (Usuario usuario : usuarios) {
    if (usuario.getId().equals(id)) {
        return ResponseEntity.ok(usuario);
    }
}
```

---

## 🎓 Próximos Passos

Na **API07** vamos:
- Aprender o método **PUT** para atualizar recursos
- Atualizar todos os dados de um usuário existente
- Trabalhar com validações mais complexas

---

## 📚 Dicas

- **Teste o 404:** É importante ver o que acontece quando busca algo que não existe
- **Use o -i no cURL:** Para ver os códigos de status HTTP
- **No Postman:** Olhe a aba "Status" para ver o código HTTP retornado
- **ResponseEntity é poderoso:** Permite controle total da resposta

---

## 💡 Desafio Extra (Opcional)

Tente adicionar um método para buscar usuários por nome:
```java
@GetMapping("/buscar-por-nome/{nome}")
public List<Usuario> buscarPorNome(@PathVariable String nome) {
    List<Usuario> resultado = new ArrayList<>();
    for (Usuario usuario : usuarios) {
        if (usuario.getNome().toLowerCase().contains(nome.toLowerCase())) {
            resultado.add(usuario);
        }
    }
    return resultado;
}
```

Teste: `http://localhost:8080/api/usuarios/buscar-por-nome/ana`

---

## 🔍 Comparação: Com e Sem ResponseEntity

**Sem ResponseEntity (simples):**
```java
@GetMapping("/{id}")
public Usuario buscarPorId(@PathVariable Long id) {
    // Retorna o objeto ou null
    // Sempre retorna 200 OK (mesmo se null)
}
```

**Com ResponseEntity (profissional):**
```java
@GetMapping("/{id}")
public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
    // Retorna 200 OK se encontrar
    // Retorna 404 Not Found se não encontrar
    // Controle total da resposta HTTP
}
```
