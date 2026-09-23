import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class JanelaMovimentacoes extends JFrame {

    private Connection conn;

    private JComboBox<String> comboMovimento;

    private JTextField txtIdCliente;
    private JTextField txtIdProduto;
    private JTextField txtQuantidade;
    private JTextField txtValor;
    private JTextField txtData;
    private JTextField txtIdFuncionario;

    private JLabel lblIdCliente;
    private JLabel lblIdProduto;
    private JLabel lblQuantidade;
    private JLabel lblValor;
    private JLabel lblData;
    private JLabel lblIdFuncionario;

    public JanelaMovimentacoes(Connection conn) {

        super("PetCare - Movimentações");

        this.conn = conn;

        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarTela();
    }

    private void criarTela() {

        JPanel painel = new JPanel(new GridBagLayout());

        painel.setBorder(
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel(
            "MOVIMENTAÇÕES",
            SwingConstants.CENTER
        );

        titulo.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        painel.add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        painel.add(
            new JLabel("Movimentação:"),
            gbc
        );

        comboMovimento = new JComboBox<>(
            new String[]{
                "Venda",
                "Entrada de Estoque"
            }
        );

        gbc.gridx = 1;

        painel.add(
            comboMovimento,
            gbc
        );

        lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblIdCliente,
            txtIdCliente,
            2
        );

        lblIdProduto = new JLabel("ID Produto:");
        txtIdProduto = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblIdProduto,
            txtIdProduto,
            3
        );

        lblQuantidade = new JLabel("Quantidade:");
        txtQuantidade = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblQuantidade,
            txtQuantidade,
            4
        );

        lblValor = new JLabel("Valor:");
        txtValor = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblValor,
            txtValor,
            5
        );

        lblData = new JLabel("Data:");
        txtData = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblData,
            txtData,
            6
        );

        lblIdFuncionario = new JLabel("ID Funcionário:");
        txtIdFuncionario = new JTextField(15);

        adicionarCampo(
            painel,
            gbc,
            lblIdFuncionario,
            txtIdFuncionario,
            7
        );

        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");

        JPanel botoes = new JPanel();

        botoes.add(btnSalvar);
        botoes.add(btnLimpar);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;

        painel.add(botoes, gbc);

        comboMovimento.addActionListener(
            e -> atualizarCampos()
        );

        btnSalvar.addActionListener(
            e -> salvar()
        );

        btnLimpar.addActionListener(
            e -> limpar()
        );

        add(painel);

        atualizarCampos();
    }

    private void adicionarCampo(
        JPanel painel,
        GridBagConstraints gbc,
        JLabel label,
        JTextField campo,
        int linha
    ) {

        gbc.gridx = 0;
        gbc.gridy = linha;

        painel.add(label, gbc);

        gbc.gridx = 1;

        painel.add(campo, gbc);
    }

    private void atualizarCampos() {

        String movimento =
            comboMovimento
                .getSelectedItem()
                .toString();

        boolean venda =
            movimento.equals("Venda");

        boolean entrada =
            movimento.equals("Entrada de Estoque");

        lblIdCliente.setVisible(venda);
        txtIdCliente.setVisible(venda);

        lblIdProduto.setVisible(venda || entrada);
        txtIdProduto.setVisible(venda || entrada);

        lblQuantidade.setVisible(venda || entrada);
        txtQuantidade.setVisible(venda || entrada);

        lblValor.setVisible(venda);
        txtValor.setVisible(venda);

        lblData.setVisible(venda || entrada);
        txtData.setVisible(venda || entrada);

        lblIdFuncionario.setVisible(entrada);
        txtIdFuncionario.setVisible(entrada);

        revalidate();
        repaint();
    }

    private void salvar() {

        try {

            String movimento =
                comboMovimento
                    .getSelectedItem()
                    .toString();

            if (movimento.equals("Venda")) {

                Venda venda = new Venda(
                    Integer.parseInt(
                        txtIdCliente.getText()
                    ),
                    Integer.parseInt(
                        txtIdProduto.getText()
                    ),
                    Integer.parseInt(
                        txtQuantidade.getText()
                    ),
                    Double.parseDouble(
                        txtValor.getText()
                    ),
                    txtData.getText()
                );

                VendaDAO dao = new VendaDAO();

                dao.cadastrar(
                    conn,
                    venda
                );

                JOptionPane.showMessageDialog(
                    this,
                    "Venda cadastrada com sucesso!"
                );
            }

            else if (
                movimento.equals("Entrada de Estoque")
            ) {

                EntradaEstoque entrada =
                    new EntradaEstoque(
                        Integer.parseInt(
                            txtIdProduto.getText()
                        ),
                        Integer.parseInt(
                            txtIdFuncionario.getText()
                        ),
                        Integer.parseInt(
                            txtQuantidade.getText()
                        ),
                        txtData.getText()
                    );

                EntradaEstoqueDAO dao =
                    new EntradaEstoqueDAO();

                dao.cadastrar(
                    conn,
                    entrada
                );

                JOptionPane.showMessageDialog(
                    this,
                    "Entrada de estoque cadastrada!"
                );
            }

            limpar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpar() {

        txtIdCliente.setText("");
        txtIdProduto.setText("");
        txtQuantidade.setText("");
        txtValor.setText("");
        txtData.setText("");
        txtIdFuncionario.setText("");
    }
}