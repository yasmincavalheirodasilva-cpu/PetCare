import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntradaEstoqueDAO {

    public void cadastrar(Connection conn, EntradaEstoque entrada) {

        String sqlEntrada =
            "INSERT INTO entrada_estoque " +
            "(id_produto, id_funcionario, quantidade, data_entrada) " +
            "VALUES (?, ?, ?, ?)";

        String sqlEstoque =
            "UPDATE produto " +
            "SET quantidade_estoque = quantidade_estoque + ? " +
            "WHERE id_produto = ?";

        try {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt =
                     conn.prepareStatement(sqlEntrada)) {

                stmt.setInt(1, entrada.getIdProduto());
                stmt.setInt(2, entrada.getIdFuncionario());
                stmt.setInt(3, entrada.getQuantidade());

                stmt.setTimestamp(
                    4,
                    java.sql.Timestamp.valueOf(
                        entrada.getDataEntrada()
                    )
                );

                stmt.executeUpdate();
            }

            try (PreparedStatement stmt =
                     conn.prepareStatement(sqlEstoque)) {

                stmt.setInt(1, entrada.getQuantidade());
                stmt.setInt(2, entrada.getIdProduto());

                stmt.executeUpdate();
            }

            conn.commit();

            System.out.println(
                "Entrada registrada e estoque atualizado!"
            );

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception erro) {
                System.out.println(
                    "Erro ao desfazer operação: "
                    + erro.getMessage()
                );
            }

            System.out.println(
                "Erro ao registrar entrada: "
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

    public List<EntradaEstoque> listar(Connection conn) {

        List<EntradaEstoque> entradas =
            new ArrayList<>();

        String sql =
            "SELECT * FROM entrada_estoque";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql);
             ResultSet rs =
                 stmt.executeQuery()) {

            while (rs.next()) {

                EntradaEstoque entrada =
                    new EntradaEstoque(
                        rs.getInt("id_produto"),
                        rs.getInt("id_funcionario"),
                        rs.getInt("quantidade"),
                        rs.getTimestamp(
                            "data_entrada"
                        ).toString()
                    );

                entrada.setIdEntrada(
                    rs.getInt("id_entrada")
                );

                entradas.add(entrada);
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao listar entradas: "
                + e.getMessage()
            );
        }

        return entradas;
    }

    public void excluir(
        Connection conn,
        int idEntrada
    ) {

        String sql =
            "DELETE FROM entrada_estoque " +
            "WHERE id_entrada = ?";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrada);

            int linhas =
                stmt.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                    "Entrada excluída com sucesso!"
                );

            } else {

                System.out.println(
                    "Nenhuma entrada encontrada."
                );
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao excluir entrada: "
                + e.getMessage()
            );
        }
    }
}