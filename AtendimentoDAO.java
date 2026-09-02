import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {

    public void cadastrar(Connection conn, Atendimento atendimento) {

        String sql = "INSERT INTO atendimento "+ "(id_pet, id_funcionario, tipo_atendimento, valor, data_atendimento) "+ "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, atendimento.getIdPet());
            stmt.setInt(2, atendimento.getIdFuncionario());
            stmt.setString(3, atendimento.getTipoAtendimento());
            stmt.setDouble(4, atendimento.getValor());

            // Converte a data para TIMESTAMP
            stmt.setTimestamp(
                5,
                java.sql.Timestamp.valueOf(
                    atendimento.getDataAtendimento()
                )
            );

            stmt.executeUpdate();

            System.out.println("Atendimento cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println(
                "Erro ao cadastrar atendimento: " + e.getMessage()
            );
        }
    }

    public List<Atendimento> listar(Connection conn) {

        List<Atendimento> atendimentos = new ArrayList<>();

        String sql = "SELECT * FROM atendimento";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Atendimento atendimento = new Atendimento(rs.getInt("id_pet"),rs.getInt("id_funcionario"),rs.getString("tipo_atendimento"),rs.getDouble("valor"),rs.getTimestamp("data_atendimento").toString());

                atendimento.setIdAtendimento(
                    rs.getInt("id_atendimento")
                );

                atendimentos.add(atendimento);
            }

        } catch (Exception e) {
            System.out.println(
                "Erro ao listar atendimentos: " + e.getMessage()
            );
        }

        return atendimentos;
    }

    public void atualizar(Connection conn, Atendimento atendimento) {

        String sql = "UPDATE atendimento SET "+ "id_pet = ?, "+ "id_funcionario = ?, "+ "tipo_atendimento = ?, "+ "valor = ?, "+ "data_atendimento = ? "+ "WHERE id_atendimento = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, atendimento.getIdPet());
            stmt.setInt(2, atendimento.getIdFuncionario());
            stmt.setString(3, atendimento.getTipoAtendimento());
            stmt.setDouble(4, atendimento.getValor());

            stmt.setTimestamp(
                5,
                java.sql.Timestamp.valueOf(
                    atendimento.getDataAtendimento()
                )
            );

            stmt.setInt(6, atendimento.getIdAtendimento());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas > 0) {
                System.out.println(
                    "Atendimento atualizado com sucesso!"
                );
            } else {
                System.out.println(
                    "Nenhum atendimento encontrado com esse ID."
                );
            }

        } catch (Exception e) {
            System.out.println(
                "Erro ao atualizar atendimento: " + e.getMessage()
            );
        }
    }

    public void excluir(Connection conn, int idAtendimento) {

        String sql = "DELETE FROM atendimento WHERE id_atendimento = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAtendimento);

            int linhasExcluidas = stmt.executeUpdate();

            if (linhasExcluidas > 0) {
                System.out.println(
                    "Atendimento excluído com sucesso!"
                );
            } else {
                System.out.println(
                    "Nenhum atendimento encontrado com esse ID."
                );
            }

        } catch (Exception e) {
            System.out.println(
                "Erro ao excluir atendimento: " + e.getMessage()
            );
        }
    }
}