package repositories;

import entities.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    private static Integer GENERATED_ID = 1;

    private static List<Produto> produtos = new ArrayList<>();


    public Produto save(Produto produto) {
        produto.setId(GENERATED_ID++);
        produtos.add(produto);
        return produto;
    }

    public List<Produto> findAll() {
        return produtos;
    }

    public Optional<Produto> findById(Integer id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }

    public Produto update(Produto produto) {
        Produto existente = produtos.get(produto.getId());

        existente.setNome(produto.getNome());
        existente.setQuantidade(produto.getQuantidade());
        existente.setPreco(produto.getPreco());

        return existente;
    }

    public boolean delete(Integer id) {
        return produtos.remove(id);
    }
}