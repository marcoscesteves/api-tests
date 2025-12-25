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
