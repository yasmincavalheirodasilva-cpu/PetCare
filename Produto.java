public class Produto {
    private int idProduto;
    private String nome;
    private String categoria;
    private Double preco;
    private int quantidade;

    public Produto(String nome, String categoria, Double preco, int quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getIdProduto() {return idProduto; }
    public void setIdProduto(int id_produto) { this.idProduto = id_produto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() {return categoria;}
    public void setCategoria(String  categoria) { this.categoria = categoria; }

    public Double getPreco() {return preco; }
    public void setPreco(Double preco) {this.preco = preco; }

    public int getQuantidade() {return quantidade; }
    public void setQuantidade(int quantidade) {this.quantidade = quantidade; }
}
