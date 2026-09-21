import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.SwingUtilities;

public class App {

    private static final String URL =
        "jdbc:postgresql://localhost:5432/petcare";

    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static void main(String[] args) {

        try {

            Connection conn =
                DriverManager.getConnection(
                    URL,
                    USER,
                    PASS
                );

            SwingUtilities.invokeLater(() -> {

                JanelaMenu tela =
                    new JanelaMenu(conn);

                tela.setVisible(true);
            });

        } catch (Exception e) {

            System.out.println(
                "Erro ao conectar ao banco: "
                + e.getMessage()
            );
        }
    }
}