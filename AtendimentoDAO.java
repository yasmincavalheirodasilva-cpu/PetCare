import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {

    // insere um novo atendimento no banco
    public void inserir(Connection conn, Atendimento atendimento) {
        String sql = "INSERT INTO atendimento (id_cliente, id_pet, id_funcionario, descricao, data_atendimento) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, atendimento.getIdCliente());
            stmt.setInt(2, atendimento.getIdPet());
            stmt.setInt(3, atendimento.getIdFuncionario());
            stmt.setString(4, atendimento.getDescricao());
            stmt.setTimestamp(5, Timestamp.valueOf(atendimento.getDataAtendimento()));

            stmt.executeUpdate();
            System.out.println("Atendimento registrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar atendimento: " + e.getMessage());
        }
    }

    // lista todos os atendimentos cadastrados
    public List<Atendimento> listar(Connection conn) {

        List<Atendimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM atendimento";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Atendimento a = new Atendimento(
                    rs.getInt("id_cliente"),
                    rs.getInt("id_pet"),
                    rs.getInt("id_funcionario"),
                    rs.getString("descricao"),
                    rs.getTimestamp("data_atendimento").toLocalDateTime()
                );

                a.setIdAtendimento(rs.getInt("id_atendimento"));
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar atendimentos: " + e.getMessage());
        }

        return lista;
    }

    // atualiza os dados de um atendimento
    public void atualizar(Connection conn, Atendimento atendimento) {

        String sql = "UPDATE atendimento SET id_cliente = ?, id_pet = ?, id_funcionario = ?, descricao = ?, data_atendimento = ? WHERE id_atendimento = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, atendimento.getIdCliente());
            stmt.setInt(2, atendimento.getIdPet());
            stmt.setInt(3, atendimento.getIdFuncionario());
            stmt.setString(4, atendimento.getDescricao());
            stmt.setTimestamp(5, Timestamp.valueOf(atendimento.getDataAtendimento()));
            stmt.setInt(6, atendimento.getIdAtendimento());

            stmt.executeUpdate();
            System.out.println("Atendimento atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar atendimento: " + e.getMessage());
        }
    }

    // exclui um atendimento pelo ID
    public void excluir(Connection conn, int idAtendimento) {

        String sql = "DELETE FROM atendimento WHERE id_atendimento = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAtendimento);

            stmt.executeUpdate();
            System.out.println("Atendimento excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir atendimento: " + e.getMessage());
        }
    }
}