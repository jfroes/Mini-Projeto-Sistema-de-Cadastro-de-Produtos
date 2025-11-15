package entities;

public class Produto {
    private static Integer GENERATED_ID = 1;
    private Integer id = 0;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public Produto(){
    }

    public Produto(String nome, Double preco, Integer quantidade) {
        this.id = GENERATED_ID++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }

    public void atualizarPreco(double novoPreco){
        this.setPreco(novoPreco);
    }

    public void reporEstoque(int qtd){
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade nao pode ser zero");
        }else if(qtd <= this.quantidade){
            throw new IllegalArgumentException("Quantidade nao pode menor que o estoque atual (" + this.quantidade + ")");
        }else {
            this.quantidade += qtd;
        }
    }

    public void vender(int qtd){
        if (this.quantidade - qtd <=0 ){
            throw new IllegalArgumentException("Estoque insuficente para verder " + qtd);
        }else{
            this.quantidade -= qtd;
        }
    }

}
