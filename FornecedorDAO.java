import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void cadastrar(Connection conn, Fornecedor fornecedor) {

        String sql = "INSERT INTO fornecedor (nome, cnpj, telefone, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());

            stmt.executeUpdate();

            System.out.println("Fornecedor cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> listar(Connection conn) {

        List<Fornecedor> fornecedores = new ArrayList<>();

        String sql = "SELECT * FROM fornecedor";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor(
                    rs.getString("nome"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );

                fornecedor.setIdFornecedor(
                    rs.getInt("id_fornecedor")
                );

                fornecedores.add(fornecedor);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }

    public void atualizar(Connection conn, Fornecedor fornecedor) {

        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, telefone = ?, email = ? WHERE id_fornecedor = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setInt(5, fornecedor.getIdFornecedor());

            stmt.executeUpdate();

            System.out.println("Fornecedor atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    public void excluir(Connection conn, int idFornecedor) {

        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);

            stmt.executeUpdate();

            System.out.println("Fornecedor excluído com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao excluir fornecedor: " + e.getMessage());
        }
    }
}