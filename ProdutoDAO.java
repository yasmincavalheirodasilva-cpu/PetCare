import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // insere um novo produto no banco
    public void inserir(Connection conn, Produto produto) {
        String sql = "INSERT INTO produto (nome, categoria, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade()); 

            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

     // lista todos os produtos cadastrados
     public List<Produto> listar(Connection conn) {

        List<Produto> lista = new ArrayList<>(); 
        String sql = "SELECT * FROM produto";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Produto p = new Produto( 
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque")
                );
    
                p.setIdProduto(rs.getInt("id_produto"));
                lista.add(p); 
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
        }
    
        return lista;
    }

    // atualiza os dados de um produto
    public void atualizar(Connection conn, Produto produto) {

        String sql = "UPDATE produto SET nome = ?, categoria = ?, preco = ?, quantidade_estoque = ? WHERE id_produto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getPreco());
            stmt.setDouble(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdProduto()); 

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // exclui um produto pelo ID
    public void excluir(Connection conn, int idProduto) { 

        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            stmt.executeUpdate();
            System.out.println("Produto excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }
    }
}