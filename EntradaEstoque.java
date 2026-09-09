public class EntradaEstoque {

    private int idEntrada;
    private int idProduto;
    private int idFuncionario;
    private int quantidade;
    private String dataEntrada;

    public EntradaEstoque(int idProduto, int idFuncionario,
                          int quantidade, String dataEntrada) {

        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
    }


    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}