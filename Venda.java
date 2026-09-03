public class Venda {

    private int idVenda;
    private int idCliente;
    private int idProduto;
    private int quantidade;
    private double valorUnitario;
    private String dataVenda;

    public Venda(int idCliente, int idProduto, int quantidade, double valorUnitario, String dataVenda) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.dataVenda = dataVenda;
    }

    public int getIdVenda() {return idVenda;}public void setIdVenda(int idVenda) {this.idVenda = idVenda;}
    public int getIdCliente() {return idCliente;}public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
    public int getIdProduto() {return idProduto;}public void setIdProduto(int idProduto) {this.idProduto = idProduto;}
    public int getQuantidade() {return quantidade;}public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public double getValorUnitario() {return valorUnitario;}public void setValorUnitario(double valorUnitario) {this.valorUnitario = valorUnitario;}
    public String getDataVenda() {return dataVenda;} public void setDataVenda(String dataVenda) {this.dataVenda = dataVenda;}
}