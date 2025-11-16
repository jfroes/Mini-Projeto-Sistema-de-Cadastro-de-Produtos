package services;

import dto.ProdutoDTO;
import entities.Produto;
import repositories.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public ProdutoDTO insert(ProdutoDTO dto){
        Produto produto = new Produto(null, dto.getNome(), dto.getPreco(), dto.getQuantidade());
        produto = repository.save(produto);

        return new ProdutoDTO(produto);
    }

    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = repository.findAll();

        return produtos.stream().map(produto -> new ProdutoDTO(produto)).toList();
    }

    public ProdutoDTO findById(Integer id){
        Produto produto = repository.findById(id).get();

        return new ProdutoDTO(produto);
    }

    public ProdutoDTO update(ProdutoDTO dto){
        Produto produto = new Produto(dto.getId(), dto.getNome(), dto.getPreco(), dto.getQuantidade());
        System.out.println(produto);
        produto = repository.update(produto);

        return  new ProdutoDTO(produto);
    }
}
