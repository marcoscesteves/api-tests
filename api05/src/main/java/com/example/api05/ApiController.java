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
    
    // Lista em memória para armazenar os usuários
    private List<Usuario> usuarios = new ArrayList<>();
    private Long proximoId = 1L;
    
    // POST - Criar um novo usuário
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.add(usuario);
        return usuario;
    }
    
    // GET - Listar todos os usuários
    @GetMapping
    public List<Usuario> listar() {
        return usuarios;
    }
}
