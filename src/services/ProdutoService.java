package services;

import dto.ProdutoDTO;
import entities.Produto;
import repositories.ProdutoRepository;
import services.exceptions.DuplicatedResourceException;
import services.exceptions.ResourceNotFoundExeception;

import java.util.List;
import java.util.NoSuchElementException;

public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public ProdutoDTO insert(ProdutoDTO dto){
        Produto produto = new Produto(null, dto.getNome(), dto.getPreco(), dto.getQuantidade());

        if (repository.findAll().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(dto.getNome()))){
            throw new DuplicatedResourceException("Já existe um recurso com este nome");
        }else {

            produto = repository.save(produto);
            return new ProdutoDTO(produto);
        }
    }

    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = repository.findAll();

        return produtos.stream().map(produto -> new ProdutoDTO(produto)).toList();
    }

    public ProdutoDTO findById(Integer id){
        try {
            Produto produto = repository.findById(id).get();

            return new ProdutoDTO(produto);
        }catch (IndexOutOfBoundsException | NoSuchElementException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado");
        }
    }

    public ProdutoDTO update(ProdutoDTO dto){
        try {
            if (repository.findAll().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(dto.getNome())) &&
                    repository.findAll().stream().noneMatch(p -> p.getId().equals(dto.getId()))
            ){
                throw  new DuplicatedResourceException("Já existe um recurso com este nome");
            }
            Produto produto = new Produto(dto.getId(), dto.getNome(), dto.getPreco(), dto.getQuantidade());

            produto = repository.update(produto);

            return new ProdutoDTO(produto);
        }catch(IndexOutOfBoundsException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado");
        }
    }

    public void delete(Integer id){
        try{
            repository.delete(id);
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundExeception("Recurso não encontrado");
        }
        repository.delete(id);
    }
}
