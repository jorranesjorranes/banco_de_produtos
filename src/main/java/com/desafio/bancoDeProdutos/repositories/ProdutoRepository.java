package com.desafio.bancoDeProdutos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.bancoDeProdutos.entities.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

}
