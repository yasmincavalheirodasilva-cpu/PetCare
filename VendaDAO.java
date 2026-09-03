import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public void cadastrar(Connection conn, Venda venda) {

        String sqlVenda =
            "INSERT INTO venda " +
            "(id_cliente, id_produto, quantidade, valor_unitario, data_venda) " +
            "VALUES (?, ?, ?, ?, ?)";

        String sqlEstoque =
            "UPDATE produto " +
            "SET quantidade_estoque = quantidade_estoque - ? " +
            "WHERE id_produto = ?";

        try {

            String sqlConsulta =
                "SELECT quantidade_estoque FROM produto WHERE id_produto = ?";

            try (PreparedStatement consulta =
                     conn.prepareStatement(sqlConsulta)) {

                consulta.setInt(1, venda.getIdProduto());

                ResultSet rs = consulta.executeQuery();

                if (!rs.next()) {
                    System.out.println(
                        "Produto não encontrado."
                    );
                    return;
                }

                int estoqueAtual =
                    rs.getInt("quantidade_estoque");

                if (estoqueAtual < venda.getQuantidade()) {

                    System.out.println(
                        "Estoque insuficiente."
                    );

                    return;
                }
            }

            conn.setAutoCommit(false);

            try (PreparedStatement stmt =
                     conn.prepareStatement(sqlVenda)) {

                stmt.setInt(1, venda.getIdCliente());
                stmt.setInt(2, venda.getIdProduto());
                stmt.setInt(3, venda.getQuantidade());
                stmt.setDouble(4, venda.getValorUnitario());

                stmt.setTimestamp(
                    5,
                    java.sql.Timestamp.valueOf(
                        venda.getDataVenda()
                    )
                );

                stmt.executeUpdate();
            }

            try (PreparedStatement stmt =
                     conn.prepareStatement(sqlEstoque)) {

                stmt.setInt(1, venda.getQuantidade());
                stmt.setInt(2, venda.getIdProduto());

                stmt.executeUpdate();
            }

            conn.commit();

            System.out.println(
                "Venda cadastrada e estoque atualizado!"
            );

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception erroRollback) {
                System.out.println(
                    "Erro ao desfazer operação: "
                    + erroRollback.getMessage()
                );
            }

            System.out.println(
                "Erro ao cadastrar venda: "
                + e.getMessage()
            );

        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (Exception e) {
                System.out.println(
                    "Erro ao restaurar conexão."
                );
            }
        }
    }

    public List<Venda> listar(Connection conn) {

        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM venda";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql);
             ResultSet rs =
                 stmt.executeQuery()) {

            while (rs.next()) {

                Venda venda = new Venda(
                    rs.getInt("id_cliente"),
                    rs.getInt("id_produto"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valor_unitario"),
                    rs.getTimestamp("data_venda").toString()
                );

                venda.setIdVenda(
                    rs.getInt("id_venda")
                );

                vendas.add(venda);
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao listar vendas: "
                + e.getMessage()
            );
        }

        return vendas;
    }

    public void atualizar(Connection conn, Venda venda) {

        String sql =
            "UPDATE venda SET " +
            "id_cliente = ?, " +
            "id_produto = ?, " +
            "quantidade = ?, " +
            "valor_unitario = ?, " +
            "data_venda = ? " +
            "WHERE id_venda = ?";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql)) {

            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdProduto());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setDouble(4, venda.getValorUnitario());

            stmt.setTimestamp(
                5,
                java.sql.Timestamp.valueOf(
                    venda.getDataVenda()
                )
            );

            stmt.setInt(
                6,
                venda.getIdVenda()
            );

            int linhasAlteradas =
                stmt.executeUpdate();

            if (linhasAlteradas > 0) {

                System.out.println(
                    "Venda atualizada com sucesso!"
                );

            } else {

                System.out.println(
                    "Nenhuma venda encontrada com esse ID."
                );
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao atualizar venda: "
                + e.getMessage()
            );
        }
    }

    public void excluir(Connection conn, int idVenda) {

        String sql =
            "DELETE FROM venda WHERE id_venda = ?";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            int linhasExcluidas =
                stmt.executeUpdate();

            if (linhasExcluidas > 0) {

                System.out.println(
                    "Venda excluída com sucesso!"
                );

            } else {

                System.out.println(
                    "Nenhuma venda encontrada com esse ID."
                );
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao excluir venda: "
                + e.getMessage()
            );
        }
    }
}