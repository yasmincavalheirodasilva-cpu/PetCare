import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class JanelaMenu extends JFrame {

    private Connection conn;

    public JanelaMenu(Connection conn) {

        this.conn = conn;

        setTitle("PetCare - Menu Principal");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarTela();
    }

    private void criarTela() {

        JPanel painel = new JPanel();

        painel.setLayout(
            new GridLayout(5, 1, 10, 15)
        );

        painel.setBorder(
            BorderFactory.createEmptyBorder(
                30, 60, 30, 60
            )
        );

        JLabel titulo = new JLabel(
            "PETCARE",
            SwingConstants.CENTER
        );

        titulo.setFont(
            new Font("Arial", Font.BOLD, 26)
        );

        painel.add(titulo);

        // Botão de cadastros
        JButton btnCadastros =
            new JButton("Cadastros");

        // Botão de movimentações
        JButton btnMovimentacoes =
            new JButton("Movimentações");

        // Botão de relatórios
        JButton btnRelatorios =
            new JButton("Relatórios");

        // Botão sair
        JButton btnSair =
            new JButton("Sair");

        painel.add(btnCadastros);
        painel.add(btnMovimentacoes);
        painel.add(btnRelatorios);
        painel.add(btnSair);

        // Abre a tela de cadastros
        btnCadastros.addActionListener(e -> {

            JanelaCadastros tela =
                new JanelaCadastros(conn);

            tela.setVisible(true);
        });

        // Abre a tela de movimentações
        btnMovimentacoes.addActionListener(e -> {

            JanelaMovimentacoes tela =
                new JanelaMovimentacoes(conn);

            tela.setVisible(true);
        });

        // Abre a tela de relatórios
        btnRelatorios.addActionListener(e -> {

            JanelaRelatorios tela =
                new JanelaRelatorios(conn);

            tela.setVisible(true);
        });

        // Sair
        btnSair.addActionListener(e -> {

            int resposta =
                JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente sair?",
                    "Sair",
                    JOptionPane.YES_NO_OPTION
                );

            if (resposta == JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        });

        add(painel);
    }
}