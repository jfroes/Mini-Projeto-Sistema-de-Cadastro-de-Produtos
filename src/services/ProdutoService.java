package services;

import dto.ProdutoDTO;
import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public ProdutoDTO insert(ProdutoDTO dto){
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        produto = repository.save(produto);

        return new ProdutoDTO(produto);
    }
}
