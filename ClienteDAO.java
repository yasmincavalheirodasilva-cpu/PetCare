import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Insere um novo cliente no banco
    public void inserir(Connection conn, Cliente cliente) {

        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, endereco) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());

            stmt.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // Consulta todos os clientes cadastrados
    public List<Cliente> listar(Connection conn) {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
                );

                cliente.setIdCliente(rs.getInt("id_cliente"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }

        return clientes;
    }

    // Atualiza os dados de um cliente
    public void atualizar(Connection conn, Cliente cliente) {

        String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, "
                   + "email = ?, endereco = ? WHERE id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getIdCliente());

            stmt.executeUpdate();

            System.out.println("Cliente atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Exclui um cliente pelo ID
    public void excluir(Connection conn, int idCliente) {

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            stmt.executeUpdate();

            System.out.println("Cliente excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}