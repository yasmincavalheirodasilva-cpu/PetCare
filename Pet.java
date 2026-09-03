public class Pet {

    private int idPet;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private int idCliente;

    public Pet(String nome, String especie, String raca, int idade, int idCliente) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.idCliente = idCliente;
    }

    public int getIdPet() {return idPet;}public void setIdPet(int idPet) {this.idPet = idPet;}
    public String getNome() {return nome;}public void setNome(String nome) {this.nome = nome;}
    public String getEspecie() {return especie;}public void setEspecie(String especie) {this.especie = especie;}
    public String getRaca() {return raca;}public void setRaca(String raca) {this.raca = raca;}
    public int getIdade() {return idade;}public void setIdade(int idade) {this.idade = idade;}
    public int getIdCliente() {return idCliente;}public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
}