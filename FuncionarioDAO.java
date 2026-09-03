import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void inserir(Connection conn, Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, telefone, cargo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo()); 

            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public List<Funcionario> listar(Connection conn) { 

        List<Funcionario> funcionarios = new ArrayList<>(); 
        String sql = "SELECT * FROM funcionario";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario = new Funcionario(rs.getString("nome"),rs.getString("cpf"),rs.getString("telefone"),rs.getString("cargo"));

                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));

                funcionarios.add(funcionario); 
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar funcionários: " + e.getMessage());
        }

        return funcionarios; 
    }

    // atualiza os dados de um funcionário
    public void atualizar(Connection conn, Funcionario funcionario) {

        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, telefone = ?, cargo = ? WHERE id_funcionario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setInt(5, funcionario.getIdFuncionario()); 

            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // exclui um funcionário pelo ID
    public void excluir(Connection conn, int idFuncionario) { 

        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            stmt.executeUpdate();
            System.out.println("Funcionário excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}