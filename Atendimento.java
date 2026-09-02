public class Atendimento {

    private int idAtendimento;
    private int idPet;
    private int idFuncionario;
    private String tipoAtendimento;
    private double valor;
    private String dataAtendimento;

    public Atendimento(int idPet, int idFuncionario, String tipoAtendimento, double valor, String dataAtendimento) {
        this.idPet = idPet;
        this.idFuncionario = idFuncionario;
        this.tipoAtendimento = tipoAtendimento;
        this.valor = valor;
        this.dataAtendimento = dataAtendimento;
    }

    public int getIdAtendimento() {return idAtendimento;}public void setIdAtendimento(int idAtendimento) {this.idAtendimento = idAtendimento;}
    public int getIdPet() {return idPet;}public void setIdPet(int idPet) {this.idPet = idPet;}
    public int getIdFuncionario() {return idFuncionario;}public void setIdFuncionario(int idFuncionario) {this.idFuncionario = idFuncionario;}
    public String getTipoAtendimento() {return tipoAtendimento;}public void setTipoAtendimento(String tipoAtendimento) {this.tipoAtendimento = tipoAtendimento;}
    public double getValor() {return valor;}public void setValor(double valor) {this.valor = valor;}
    public String getDataAtendimento() {return dataAtendimento;}public void setDataAtendimento(String dataAtendimento) {this.dataAtendimento = dataAtendimento;}
}