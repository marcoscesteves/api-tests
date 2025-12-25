# 🧪 Spring Boot Test API

Este repositório foi criado para **aprender e praticar Spring Boot**, servindo como um espaço pessoal para explorar como funcionam as APIs REST.

---

## 🚀 Objetivo

Aprender os conceitos básicos de **Spring Boot**:
- Criar e executar uma API local  
- Definir endpoints com `@RestController`  
- Retornar respostas em texto e JSON  
- Entender a estrutura do projeto e o uso do Maven  

---

## 🏗️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3.x**  
- **Maven**  
- **IDE:** VS Code  

---

## ▶️ Como Executar o Projeto

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/marcoscesteves/api-tests.git
   cd api_tests/api01
   ```

2. **Execute o servidor:**
   ```bash
   mvn spring-boot:run
   ```

   O log abaixo avisará que a aplicação está disponível.  
   ![Saída do console da aplicação Spring Boot mostrando a inicialização bem-sucedida do servidor Tomcat na porta 8080 com logs de inicialização](imagem1.jpg)


3. **Abra a aplicação no navegador:**
   ```
   http://localhost:8080/hello
   ```

   Uma mensagem de confirmação será exibida confirmando que a API está funcional.  
   ![Janela do navegador exibindo localhost:8080/hello com a mensagem "Hello! I am your first API built using Spring Boot!" mostrada em texto preto sobre fundo branco](imagem2.jpg)

4. ** Interrompa o serviço: **
   Interrompa a execução do servidor e vá para o execício 02 (api_tests/api02)
