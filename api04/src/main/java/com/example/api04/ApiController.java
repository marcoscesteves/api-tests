package com.example.api04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api")
public class ApiController {
    
    @GetMapping("buscar")
    public SearchResult buscar
    (
            @RequestParam String nome,
            @RequestParam(required = false) Integer idade
    ) 
    {
        
        String mensagem;
        if (idade != null) {
            mensagem = String.format("Buscando por: %s, %d anos", nome, idade);
        } else {
            mensagem = String.format("Buscando por: %s", nome);
        }
        
        return new SearchResult(nome, idade, mensagem);
    }
    
    @GetMapping("filtrar")
    public FilterResult filtrar
    (
       @RequestParam(required = false, defaultValue = "todos") 
       String categoria,
       @RequestParam(required = false, defaultValue = "10") 
       int limite,
       @RequestParam(required = false, defaultValue = "false") 
       boolean ativo
    ) 
    {
        
        return new FilterResult(categoria, limite, ativo, 
            String.format("Filtrando %d itens da categoria '%s' (ativo: %s)", 
                limite, categoria, ativo));
    }

    public record SearchResult(String nome, Integer idade, String mensagem) {}
    public record FilterResult(String categoria, int limite, boolean ativo, String resultado) {}
    
}
