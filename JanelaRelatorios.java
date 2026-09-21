import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class JanelaRelatorios extends JFrame {

    private Connection conn;

    private JTable tabela;
    private DefaultTableModel modelo;

    public JanelaRelatorios(Connection conn) {

        this.conn = conn;

        setTitle("PetCare - Relatórios");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarTela();
    }

    private void criarTela() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        painel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel titulo = new JLabel(
            "RELATÓRIOS",
            SwingConstants.CENTER
        );

        titulo.setFont(
            new Font("Arial", Font.BOLD, 22)
        );

        painel.add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        tabela = new JTable(modelo);

        JScrollPane scroll =
            new JScrollPane(tabela);

        painel.add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();

        JButton btnEstoque =
            new JButton("Estoque");

        JButton btnAtendimentos =
            new JButton("Atendimentos do Dia");

        JButton btnFechar =
            new JButton("Fechar");

        painelBotoes.add(btnEstoque);
        painelBotoes.add(btnAtendimentos);
        painelBotoes.add(btnFechar);

        painel.add(
            painelBotoes,
            BorderLayout.SOUTH
        );

        btnEstoque.addActionListener(e -> {

            mostrarEstoque();

        });

 
        btnAtendimentos.addActionListener(e -> {

            mostrarAtendimentosDoDia();

        });

   
        btnFechar.addActionListener(e -> {

            dispose();

        });

        add(painel);
    }

    private void mostrarEstoque() {

        modelo.setRowCount(0);
        modelo.setColumnCount(0);

        modelo.addColumn("ID");
        modelo.addColumn("Produto");
        modelo.addColumn("Categoria");
        modelo.addColumn("Preço");
        modelo.addColumn("Estoque");

        String sql =
            "SELECT id_produto, nome, categoria, preco, quantidade_estoque " +
            "FROM produto " +
            "ORDER BY id_produto";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql);
             ResultSet rs =
                 stmt.executeQuery()) {

            while (rs.next()) {

                modelo.addRow(new Object[] {

                    rs.getInt("id_produto"),
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque")

                });
            }

            if (modelo.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Nenhum produto cadastrado."
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao consultar estoque: "
                + e.getMessage()
            );
        }
    }

    private void mostrarAtendimentosDoDia() {

        modelo.setRowCount(0);
        modelo.setColumnCount(0);

        modelo.addColumn("ID");
        modelo.addColumn("Pet");
        modelo.addColumn("Funcionário");
        modelo.addColumn("Atendimento");
        modelo.addColumn("Valor");
        modelo.addColumn("Data");

        String sql =
            "SELECT a.id_atendimento, " +
            "p.nome AS pet, " +
            "f.nome AS funcionario, " +
            "a.tipo_atendimento, " +
            "a.valor, " +
            "a.data_atendimento " +
            "FROM atendimento a " +
            "INNER JOIN pet p " +
            "ON a.id_pet = p.id_pet " +
            "INNER JOIN funcionario f " +
            "ON a.id_funcionario = f.id_funcionario " +
            "WHERE DATE(a.data_atendimento) = CURRENT_DATE " +
            "ORDER BY a.data_atendimento";

        try (PreparedStatement stmt =
                 conn.prepareStatement(sql);
             ResultSet rs =
                 stmt.executeQuery()) {

            while (rs.next()) {

                modelo.addRow(new Object[] {

                    rs.getInt("id_atendimento"),
                    rs.getString("pet"),
                    rs.getString("funcionario"),
                    rs.getString("tipo_atendimento"),
                    rs.getDouble("valor"),
                    rs.getTimestamp("data_atendimento")

                });
            }

            if (modelo.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Nenhum atendimento registrado hoje."
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao consultar atendimentos: "
                + e.getMessage()
            );
        }
    }
}