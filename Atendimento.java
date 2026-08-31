import java.time.LocalDateTime;

public class Atendimento {
    private int idAtendimento;
    private int idCliente;
    private int idPet;
    private int idFuncionario;
    private String descricao;
    private LocalDateTime dataAtendimento;

    public Atendimento(int idCliente, int idPet, int idFuncionario, String descricao, LocalDateTime dataAtendimento) {
        this.idCliente = idCliente;
        this.idPet = idPet;
        this.idFuncionario = idFuncionario;
        this.descricao = descricao;
        this.dataAtendimento = dataAtendimento;
    }

    public int getIdAtendimento() { return idAtendimento; }
    public void setIdAtendimento(int idAtendimento) { this.idAtendimento = idAtendimento; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdPet() { return idPet; }
    public void setIdPet(int idPet) { this.idPet = idPet; }

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataAtendimento() { return dataAtendimento; }
    public void setDataAtendimento(LocalDateTime dataAtendimento) { this.dataAtendimento = dataAtendimento; }
}