package com.desafio.bancoDeProdutos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.desafio.bancoDeProdutos.entities.ProdutoModel;
import com.desafio.bancoDeProdutos.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public ProdutoModel save(ProdutoModel produto) {
		return produtoRepository.save(produto);
	}

	public List<ProdutoModel> findAll() {
		return produtoRepository.findAll();
	}

	public Optional<ProdutoModel> findById(Integer id) {
		return produtoRepository.findById(id);
	}

	public ProdutoModel update(Integer id, ProdutoModel obj) {
		ProdutoModel entity = produtoRepository.getReferenceById(id);
		updateData(entity, obj);
		return produtoRepository.save(entity);
	}

	private void updateData(ProdutoModel entity, ProdutoModel obj) {
		if (obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		if (obj.getDescricao() != null) {
			entity.setDescricao(obj.getDescricao());
		}
		if (obj.getPreco() != null) {
			entity.setPreco(obj.getPreco());
		}
	}

	@Transactional
	public void delete(ProdutoModel produto) {
		produtoRepository.delete(produto);
	}

}
