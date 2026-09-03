public class Funcionario {


    private int idFuncionario;
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;


    public Funcionario(String nome, String cpf, String telefone, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public int getIdFuncionario() {return idFuncionario;} public void setIdFuncionario(int idFuncionario) {this.idFuncionario = idFuncionario;}
    public String getNome() {return nome;} public void setNome(String nome) {this.nome = nome;}
    public String getCpf() {return cpf;} public void setCpf(String cpf) {this.cpf = cpf;}
    public String getTelefone() {return telefone;} public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getCargo() {return cargo;} public void setCargo(String cargo) {this.cargo = cargo;}
}