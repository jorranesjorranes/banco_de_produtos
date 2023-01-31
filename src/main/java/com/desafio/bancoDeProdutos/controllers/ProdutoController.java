package com.desafio.bancoDeProdutos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.bancoDeProdutos.entities.ProdutoModel;
import com.desafio.bancoDeProdutos.services.ProdutoService;

@RestController
@RequestMapping("/api-produto")
public class ProdutoController {

	private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodos() {
    	return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody ProdutoModel produto) {  	
    	return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto)); 		
    }    	      
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoModel> update(@PathVariable Integer id, @RequestBody ProdutoModel obj) {
		obj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
    	Optional<ProdutoModel> produtoOptional = produtoService.findById(id);
    	if (!produtoOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
    	}
    	
		produtoService.delete(produtoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
	}
}
