package com.unibave.catalogoprodutos.controller;

import android.content.Context;
import android.database.Cursor;

import com.unibave.catalogoprodutos.model.Produto;
import com.unibave.catalogoprodutos.model.ProdutoDatabaseRepository;

import java.util.UUID;

public class ProdutoController {

    private ProdutoDatabaseRepository repository;

    public ProdutoController(Context context) {
        repository = new ProdutoDatabaseRepository(context);
    }

    public String create(String name, String fornecedor, String valor) {
        Produto domain = new Produto();
        domain.setId(UUID.randomUUID().toString());
        domain.setNome(name);
        domain.setFornecedor(fornecedor);
        domain.setValor(valor);

        validateName(domain);
        return repository.insert(domain);
    }

    public String update(String id, String name, String fornecedor, String valor) {
        Produto domain = new Produto();
        domain.setId(id);
        domain.setNome(name);
        domain.setFornecedor(fornecedor);
        domain.setValor(valor);

        validateName(domain);
        return repository.update(domain);
    }

    private void validateName(Produto domain) {
        if (domain.getNome() == null || domain.getNome().isEmpty() || domain.getNome().length() > 40) {
            throw new RuntimeException("Nome invalido!");
        }
    }

    public Cursor findAll() {
        return repository.findAll();
    }

    public Cursor findById(int id) {
        return repository.findById(id);
    }

    public String  remove(int id) {
        return repository.remove(id);
    }
}
