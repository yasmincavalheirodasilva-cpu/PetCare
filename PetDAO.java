import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    
    public void inserir(Connection conn, Pet pet) {

        String sql = "INSERT INTO pet (nome, especie, raca, idade, id_cliente) "+ "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            stmt.setInt(5, pet.getIdCliente());

            stmt.executeUpdate();

            System.out.println("Pet cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pet: " + e.getMessage());
        }
    }

    public List<Pet> listar(Connection conn) {

        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT * FROM pet";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Pet pet = new Pet(
                    rs.getString("nome"),
                    rs.getString("especie"),
                    rs.getString("raca"),
                    rs.getInt("idade"),
                    rs.getInt("id_cliente")
                );

                pet.setIdPet(rs.getInt("id_pet"));

                pets.add(pet);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar pets: " + e.getMessage());
        }

        return pets;
    }

    public void atualizar(Connection conn, Pet pet) {

        String sql = "UPDATE pet SET nome = ?, especie = ?, raca = ?, "+ "idade = ?, id_cliente = ? WHERE id_pet = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            stmt.setInt(5, pet.getIdCliente());
            stmt.setInt(6, pet.getIdPet());

            stmt.executeUpdate();

            System.out.println("Pet atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pet: " + e.getMessage());
        }
    }

    public void excluir(Connection conn, int idPet) {

        String sql = "DELETE FROM pet WHERE id_pet = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPet);

            stmt.executeUpdate();

            System.out.println("Pet excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pet: " + e.getMessage());
        }
    }
}