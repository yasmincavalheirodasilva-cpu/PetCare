import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    // insere uma nova venda no banco
    public void inserir(Connection conn, Venda venda) {
        String sql = "INSERT INTO venda (id_cliente, id_produto, quantidade, valor_unitario, data_venda) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdProduto());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getValorUnitario());
            stmt.setTimestamp(5, Timestamp.valueOf(venda.getDataVenda()));

            stmt.executeUpdate();
            System.out.println("Venda registrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    // lista todas as vendas cadastradas
    public List<Venda> listar(Connection conn) {

        List<Venda> lista = new ArrayList<>();
        String sql = "SELECT * FROM venda";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda v = new Venda(
                    rs.getInt("id_cliente"),
                    rs.getInt("id_produto"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valor_unitario"),
                    rs.getTimestamp("data_venda").toLocalDateTime()
                );

                v.setIdVenda(rs.getInt("id_venda"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar vendas: " + e.getMessage());
        }

        return lista;
    }

    // atualiza os dados de uma venda
    public void atualizar(Connection conn, Venda venda) {

        String sql = "UPDATE venda SET id_cliente = ?, id_produto = ?, quantidade = ?, valor_unitario = ?, data_venda = ? WHERE id_venda = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdProduto());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getValorUnitario());
            stmt.setTimestamp(5, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setInt(6, venda.getIdVenda());

            stmt.executeUpdate();
            System.out.println("Venda atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    // exclui uma venda pelo ID
    public void excluir(Connection conn, int idVenda) {

        String sql = "DELETE FROM venda WHERE id_venda = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            stmt.executeUpdate();
            System.out.println("Venda excluída com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir venda: " + e.getMessage());
        }
    }
}
