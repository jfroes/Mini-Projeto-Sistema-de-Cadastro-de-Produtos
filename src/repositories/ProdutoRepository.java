package repositories;

import entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private static List<Produto> produtos = new ArrayList<>();

    public Produto save(Produto produto){

        produtos.add(produto);

        return produto;
    }
}
