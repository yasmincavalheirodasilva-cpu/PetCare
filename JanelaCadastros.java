import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class JanelaCadastros extends JFrame {

    private Connection conn;

    private JComboBox<String> comboCadastro;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtEndereco;

    private JTextField txtCategoria;
    private JTextField txtPreco;
    private JTextField txtQuantidade;

    private JTextField txtEspecie;
    private JTextField txtRaca;
    private JTextField txtIdade;
    private JTextField txtIdCliente;

    private JTextField txtCargo;

    // Labels
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblCpf;
    private JLabel lblTelefone;
    private JLabel lblEmail;
    private JLabel lblEndereco;

    private JLabel lblCategoria;
    private JLabel lblPreco;
    private JLabel lblQuantidade;

    private JLabel lblEspecie;
    private JLabel lblRaca;
    private JLabel lblIdade;
    private JLabel lblIdCliente;

    private JLabel lblCargo;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public JanelaCadastros(Connection conn) {

        super("PetCare - Cadastros");

        this.conn = conn;

        setSize(950, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarTela();
        mudarCadastro();
    }

    private void criarTela() {

        JPanel painelPrincipal =
            new JPanel(new BorderLayout(10, 10));

        painelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(
                10, 10, 10, 10
            )
        );

        // =================================
        // PARTE SUPERIOR
        // =================================

        JPanel painelSuperior =
            new JPanel(
                new FlowLayout(FlowLayout.LEFT)
            );

        painelSuperior.add(
            new JLabel("Cadastro:")
        );

        comboCadastro =
            new JComboBox<>(
                new String[]{
                    "Cliente",
                    "Pet",
                    "Produto",
                    "Funcionário",
                    "Fornecedor"
                }
            );

        painelSuperior.add(comboCadastro);

        painelPrincipal.add(
            painelSuperior,
            BorderLayout.NORTH
        );

        // =================================
        // FORMULÁRIO
        // =================================

        JPanel painelFormulario =
            new JPanel(new GridBagLayout());

        painelFormulario.setBorder(
            BorderFactory.createTitledBorder(
                "Dados"
            )
        );

        GridBagConstraints gbc =
            new GridBagConstraints();

        gbc.insets =
            new Insets(5, 5, 5, 5);

        gbc.fill =
            GridBagConstraints.HORIZONTAL;

        // ID
        lblId = new JLabel("ID:");

        adicionarCampo(
            painelFormulario,
            lblId,
            txtId = new JTextField(20),
            gbc,
            0
        );

        txtId.setEditable(false);

        // Nome
        lblNome = new JLabel("Nome:");

        adicionarCampo(
            painelFormulario,
            lblNome,
            txtNome = new JTextField(20),
            gbc,
            1
        );

        // CPF
        lblCpf = new JLabel("CPF:");

        adicionarCampo(
            painelFormulario,
            lblCpf,
            txtCpf = new JTextField(20),
            gbc,
            2
        );

        // Telefone
        lblTelefone = new JLabel("Telefone:");

        adicionarCampo(
            painelFormulario,
            lblTelefone,
            txtTelefone = new JTextField(20),
            gbc,
            3
        );

        // E-mail
        lblEmail = new JLabel("E-mail:");

        adicionarCampo(
            painelFormulario,
            lblEmail,
            txtEmail = new JTextField(20),
            gbc,
            4
        );

        // Endereço
        lblEndereco = new JLabel("Endereço:");

        adicionarCampo(
            painelFormulario,
            lblEndereco,
            txtEndereco = new JTextField(20),
            gbc,
            5
        );

        // Categoria
        lblCategoria = new JLabel("Categoria:");

        adicionarCampo(
            painelFormulario,
            lblCategoria,
            txtCategoria = new JTextField(20),
            gbc,
            6
        );

        // Preço
        lblPreco = new JLabel("Preço:");

        adicionarCampo(
            painelFormulario,
            lblPreco,
            txtPreco = new JTextField(20),
            gbc,
            7
        );

        // Quantidade
        lblQuantidade = new JLabel("Quantidade:");

        adicionarCampo(
            painelFormulario,
            lblQuantidade,
            txtQuantidade = new JTextField(20),
            gbc,
            8
        );

        // Espécie
        lblEspecie = new JLabel("Espécie:");

        adicionarCampo(
            painelFormulario,
            lblEspecie,
            txtEspecie = new JTextField(20),
            gbc,
            9
        );

        // Raça
        lblRaca = new JLabel("Raça:");

        adicionarCampo(
            painelFormulario,
            lblRaca,
            txtRaca = new JTextField(20),
            gbc,
            10
        );

        // Idade
        lblIdade = new JLabel("Idade:");

        adicionarCampo(
            painelFormulario,
            lblIdade,
            txtIdade = new JTextField(20),
            gbc,
            11
        );

        // ID Cliente
        lblIdCliente = new JLabel("ID Cliente:");

        adicionarCampo(
            painelFormulario,
            lblIdCliente,
            txtIdCliente = new JTextField(20),
            gbc,
            12
        );

        // Cargo
        lblCargo = new JLabel("Cargo:");

        adicionarCampo(
            painelFormulario,
            lblCargo,
            txtCargo = new JTextField(20),
            gbc,
            13
        );

        // =================================
        // BOTÕES
        // =================================

        JPanel painelBotoes =
            new JPanel();

        JButton btnSalvar =
            new JButton("Salvar");

        JButton btnExcluir =
            new JButton("Excluir");

        JButton btnLimpar =
            new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;

        painelFormulario.add(
            painelBotoes,
            gbc
        );

        // =================================
        // TABELA
        // =================================

        modeloTabela =
            new DefaultTableModel();

        tabela =
            new JTable(modeloTabela);

        JScrollPane scroll =
            new JScrollPane(tabela);

        JPanel painelCentro =
            new JPanel(new BorderLayout());

        painelCentro.add(
            painelFormulario,
            BorderLayout.WEST
        );

        painelCentro.add(
            scroll,
            BorderLayout.CENTER
        );

        painelPrincipal.add(
            painelCentro,
            BorderLayout.CENTER
        );

        // =================================
        // EVENTOS
        // =================================

        comboCadastro.addActionListener(
            e -> mudarCadastro()
        );

        btnSalvar.addActionListener(
            e -> salvar()
        );

        btnExcluir.addActionListener(
            e -> excluir()
        );

        btnLimpar.addActionListener(
            e -> limpar()
        );

        tabela.getSelectionModel()
            .addListSelectionListener(e -> {

                if (!e.getValueIsAdjusting()
                        && tabela.getSelectedRow() != -1) {

                    preencherCampos();
                }
            });

        add(painelPrincipal);
    }

    // =================================
    // ADICIONA LABEL + CAMPO
    // =================================

    private void adicionarCampo(
        JPanel painel,
        JLabel label,
        JTextField campo,
        GridBagConstraints gbc,
        int linha
    ) {

        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;

        painel.add(label, gbc);

        gbc.gridx = 1;

        painel.add(campo, gbc);
    }

    // =================================
    // MUDA O CADASTRO
    // =================================

    private void mudarCadastro() {

        limpar();

        String cadastro =
            comboCadastro
                .getSelectedItem()
                .toString();

        esconderCampos();

        if (cadastro.equals("Cliente")) {

            mostrar(
                lblId,
                txtId,
                lblNome,
                txtNome,
                lblCpf,
                txtCpf,
                lblTelefone,
                txtTelefone,
                lblEmail,
                txtEmail,
                lblEndereco,
                txtEndereco
            );

            carregarClientes();

        } else if (cadastro.equals("Pet")) {

            mostrar(
                lblId,
                txtId,
                lblNome,
                txtNome,
                lblEspecie,
                txtEspecie,
                lblRaca,
                txtRaca,
                lblIdade,
                txtIdade,
                lblIdCliente,
                txtIdCliente
            );

            carregarPets();

        } else if (cadastro.equals("Produto")) {

            mostrar(
                lblId,
                txtId,
                lblNome,
                txtNome,
                lblCategoria,
                txtCategoria,
                lblPreco,
                txtPreco,
                lblQuantidade,
                txtQuantidade
            );

            carregarProdutos();

        } else if (cadastro.equals("Funcionário")) {

            mostrar(
                lblId,
                txtId,
                lblNome,
                txtNome,
                lblCpf,
                txtCpf,
                lblTelefone,
                txtTelefone,
                lblCargo,
                txtCargo
            );

            carregarFuncionarios();

        } else if (cadastro.equals("Fornecedor")) {

            mostrar(
                lblId,
                txtId,
                lblNome,
                txtNome,
                lblCpf,
                txtCpf,
                lblTelefone,
                txtTelefone,
                lblEmail,
                txtEmail
            );

            carregarFornecedores();
        }

        revalidate();
        repaint();
    }

    // =================================
    // ESCONDE TODOS OS CAMPOS
    // =================================

    private void esconderCampos() {

        lblId.setVisible(false);
        txtId.setVisible(false);

        lblNome.setVisible(false);
        txtNome.setVisible(false);

        lblCpf.setVisible(false);
        txtCpf.setVisible(false);

        lblTelefone.setVisible(false);
        txtTelefone.setVisible(false);

        lblEmail.setVisible(false);
        txtEmail.setVisible(false);

        lblEndereco.setVisible(false);
        txtEndereco.setVisible(false);

        lblCategoria.setVisible(false);
        txtCategoria.setVisible(false);

        lblPreco.setVisible(false);
        txtPreco.setVisible(false);

        lblQuantidade.setVisible(false);
        txtQuantidade.setVisible(false);

        lblEspecie.setVisible(false);
        txtEspecie.setVisible(false);

        lblRaca.setVisible(false);
        txtRaca.setVisible(false);

        lblIdade.setVisible(false);
        txtIdade.setVisible(false);

        lblIdCliente.setVisible(false);
        txtIdCliente.setVisible(false);

        lblCargo.setVisible(false);
        txtCargo.setVisible(false);
    }

    // =================================
    // MOSTRA OS CAMPOS NECESSÁRIOS
    // =================================

    private void mostrar(Object... componentes) {

        for (Object componente : componentes) {

            if (componente instanceof JComponent) {

                ((JComponent) componente)
                    .setVisible(true);
            }
        }
    }

    // =================================
    // CLIENTES
    // =================================

    private void carregarClientes() {

        modeloTabela.setColumnIdentifiers(
            new String[]{
                "ID",
                "Nome",
                "CPF",
                "Telefone",
                "E-mail",
                "Endereço"
            }
        );

        modeloTabela.setRowCount(0);

        ClienteDAO dao =
            new ClienteDAO();

        List<Cliente> lista =
            dao.listar(conn);

        for (Cliente c : lista) {

            modeloTabela.addRow(
                new Object[]{
                    c.getIdCliente(),
                    c.getNome(),
                    c.getCpf(),
                    c.getTelefone(),
                    c.getEmail(),
                    c.getEndereco()
                }
            );
        }
    }

    // =================================
    // PETS
    // =================================

    private void carregarPets() {

        modeloTabela.setColumnIdentifiers(
            new String[]{
                "ID",
                "Nome",
                "Espécie",
                "Raça",
                "Idade",
                "ID Cliente"
            }
        );

        modeloTabela.setRowCount(0);

        PetDAO dao =
            new PetDAO();

        List<Pet> lista =
            dao.listar(conn);

        for (Pet p : lista) {

            modeloTabela.addRow(
                new Object[]{
                    p.getIdPet(),
                    p.getNome(),
                    p.getEspecie(),
                    p.getRaca(),
                    p.getIdade(),
                    p.getIdCliente()
                }
            );
        }
    }

    // =================================
    // PRODUTOS
    // =================================

    private void carregarProdutos() {

        modeloTabela.setColumnIdentifiers(
            new String[]{
                "ID",
                "Nome",
                "Categoria",
                "Preço",
                "Estoque"
            }
        );

        modeloTabela.setRowCount(0);

        ProdutoDAO dao =
            new ProdutoDAO();

        List<Produto> lista =
            dao.listar(conn);

        for (Produto p : lista) {

            modeloTabela.addRow(
                new Object[]{
                    p.getIdProduto(),
                    p.getNome(),
                    p.getCategoria(),
                    p.getPreco(),
                    p.getQuantidade()
                }
            );
        }
    }

    private void carregarFuncionarios() {

        modeloTabela.setColumnIdentifiers(
            new String[]{
                "ID",
                "Nome",
                "CPF",
                "Telefone",
                "Cargo"
            }
        );

        modeloTabela.setRowCount(0);

        FuncionarioDAO dao =
            new FuncionarioDAO();

        List<Funcionario> lista =
            dao.listar(conn);

        for (Funcionario f : lista) {

            modeloTabela.addRow(
                new Object[]{
                    f.getIdFuncionario(),
                    f.getNome(),
                    f.getCpf(),
                    f.getTelefone(),
                    f.getCargo()
                }
            );
        }
    }


    private void carregarFornecedores() {

        modeloTabela.setColumnIdentifiers(
            new String[]{
                "ID",
                "Nome",
                "CNPJ",
                "Telefone",
                "E-mail"
            }
        );

        modeloTabela.setRowCount(0);

        FornecedorDAO dao =
            new FornecedorDAO();

        List<Fornecedor> lista =
            dao.listar(conn);

        for (Fornecedor f : lista) {

            modeloTabela.addRow(
                new Object[]{
                    f.getIdFornecedor(),
                    f.getNome(),
                    f.getCnpj(),
                    f.getTelefone(),
                    f.getEmail()
                }
            );
        }
    }


    private void salvar() {

        String cadastro =
            comboCadastro
                .getSelectedItem()
                .toString();

        try {

            if (cadastro.equals("Cliente")) {

                Cliente cliente =
                    new Cliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtEmail.getText(),
                        txtEndereco.getText()
                    );

                ClienteDAO dao =
                    new ClienteDAO();

                if (txtId.getText().isEmpty()) {

                    dao.inserir(
                        conn,
                        cliente
                    );

                } else {

                    cliente.setIdCliente(
                        Integer.parseInt(
                            txtId.getText()
                        )
                    );

                    dao.atualizar(
                        conn,
                        cliente
                    );
                }

                carregarClientes();
            }

            else if (cadastro.equals("Pet")) {

                Pet pet =
                    new Pet(
                        txtNome.getText(),
                        txtEspecie.getText(),
                        txtRaca.getText(),
                        Integer.parseInt(
                            txtIdade.getText()
                        ),
                        Integer.parseInt(
                            txtIdCliente.getText()
                        )
                    );

                PetDAO dao =
                    new PetDAO();

                if (txtId.getText().isEmpty()) {

                    dao.inserir(
                        conn,
                        pet
                    );

                } else {

                    pet.setIdPet(
                        Integer.parseInt(
                            txtId.getText()
                        )
                    );

                    dao.atualizar(
                        conn,
                        pet
                    );
                }

                carregarPets();
            }

            else if (cadastro.equals("Produto")) {

                Produto produto =
                    new Produto(
                        txtNome.getText(),
                        txtCategoria.getText(),
                        Double.parseDouble(
                            txtPreco.getText()
                        ),
                        Integer.parseInt(
                            txtQuantidade.getText()
                        )
                    );

                ProdutoDAO dao =
                    new ProdutoDAO();

                if (txtId.getText().isEmpty()) {

                    dao.inserir(
                        conn,
                        produto
                    );

                } else {

                    produto.setIdProduto(
                        Integer.parseInt(
                            txtId.getText()
                        )
                    );

                    dao.atualizar(
                        conn,
                        produto
                    );
                }

                carregarProdutos();
            }

            else if (
                cadastro.equals("Funcionário")
            ) {

                Funcionario funcionario =
                    new Funcionario(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtCargo.getText()
                    );

                FuncionarioDAO dao =
                    new FuncionarioDAO();

                if (txtId.getText().isEmpty()) {

                    dao.inserir(
                        conn,
                        funcionario
                    );

                } else {

                    funcionario.setIdFuncionario(
                        Integer.parseInt(
                            txtId.getText()
                        )
                    );

                    dao.atualizar(
                        conn,
                        funcionario
                    );
                }

                carregarFuncionarios();
            }

            else if (
                cadastro.equals("Fornecedor")
            ) {

                Fornecedor fornecedor =
                    new Fornecedor(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        txtEmail.getText()
                    );

                FornecedorDAO dao =
                    new FornecedorDAO();

                if (txtId.getText().isEmpty()) {

                    dao.cadastrar(
                        conn,
                        fornecedor
                    );

                } else {

                    fornecedor.setIdFornecedor(
                        Integer.parseInt(
                            txtId.getText()
                        )
                    );

                    dao.atualizar(
                        conn,
                        fornecedor
                    );
                }

                carregarFornecedores();
            }

            JOptionPane.showMessageDialog(
                this,
                "Operação realizada com sucesso!"
            );

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


    private void excluir() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um registro."
            );

            return;
        }

        int resposta =
            JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
            );

        if (
            resposta !=
            JOptionPane.YES_OPTION
        ) {
            return;
        }

        try {

            int id =
                Integer.parseInt(
                    txtId.getText()
                );

            String cadastro =
                comboCadastro
                    .getSelectedItem()
                    .toString();

            if (cadastro.equals("Cliente")) {

                new ClienteDAO()
                    .excluir(conn, id);

                carregarClientes();

            } else if (cadastro.equals("Pet")) {

                new PetDAO()
                    .excluir(conn, id);

                carregarPets();

            } else if (cadastro.equals("Produto")) {

                new ProdutoDAO()
                    .excluir(conn, id);

                carregarProdutos();

            } else if (
                cadastro.equals("Funcionário")
            ) {

                new FuncionarioDAO()
                    .excluir(conn, id);

                carregarFuncionarios();

            } else if (
                cadastro.equals("Fornecedor")
            ) {

                new FornecedorDAO()
                    .excluir(conn, id);

                carregarFornecedores();
            }

            limpar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao excluir: "
                + e.getMessage()
            );
        }
    }

    private void preencherCampos() {

        int linha =
            tabela.getSelectedRow();

        if (linha == -1) {
            return;
        }

        String cadastro =
            comboCadastro
                .getSelectedItem()
                .toString();

        txtId.setText(
            modeloTabela
                .getValueAt(linha, 0)
                .toString()
        );

        if (cadastro.equals("Cliente")) {

            txtNome.setText(
                modeloTabela
                    .getValueAt(linha, 1)
                    .toString()
            );

            txtCpf.setText(
                modeloTabela
                    .getValueAt(linha, 2)
                    .toString()
            );

            txtTelefone.setText(
                modeloTabela
                    .getValueAt(linha, 3)
                    .toString()
            );

            txtEmail.setText(
                modeloTabela
                    .getValueAt(linha, 4)
                    .toString()
            );

            txtEndereco.setText(
                modeloTabela
                    .getValueAt(linha, 5)
                    .toString()
            );

        } else if (cadastro.equals("Pet")) {

            txtNome.setText(
                modeloTabela
                    .getValueAt(linha, 1)
                    .toString()
            );

            txtEspecie.setText(
                modeloTabela
                    .getValueAt(linha, 2)
                    .toString()
            );

            txtRaca.setText(
                modeloTabela
                    .getValueAt(linha, 3)
                    .toString()
            );

            txtIdade.setText(
                modeloTabela
                    .getValueAt(linha, 4)
                    .toString()
            );

            txtIdCliente.setText(
                modeloTabela
                    .getValueAt(linha, 5)
                    .toString()
            );

        } else if (cadastro.equals("Produto")) {

            txtNome.setText(
                modeloTabela
                    .getValueAt(linha, 1)
                    .toString()
            );

            txtCategoria.setText(
                modeloTabela
                    .getValueAt(linha, 2)
                    .toString()
            );

            txtPreco.setText(
                modeloTabela
                    .getValueAt(linha, 3)
                    .toString()
            );

            txtQuantidade.setText(
                modeloTabela
                    .getValueAt(linha, 4)
                    .toString()
            );

        } else if (
            cadastro.equals("Funcionário")
        ) {

            txtNome.setText(
                modeloTabela
                    .getValueAt(linha, 1)
                    .toString()
            );

            txtCpf.setText(
                modeloTabela
                    .getValueAt(linha, 2)
                    .toString()
            );

            txtTelefone.setText(
                modeloTabela
                    .getValueAt(linha, 3)
                    .toString()
            );

            txtCargo.setText(
                modeloTabela
                    .getValueAt(linha, 4)
                    .toString()
            );

        } else if (
            cadastro.equals("Fornecedor")
        ) {

            txtNome.setText(
                modeloTabela
                    .getValueAt(linha, 1)
                    .toString()
            );

            txtCpf.setText(
                modeloTabela
                    .getValueAt(linha, 2)
                    .toString()
            );

            txtTelefone.setText(
                modeloTabela
                    .getValueAt(linha, 3)
                    .toString()
            );

            txtEmail.setText(
                modeloTabela
                    .getValueAt(linha, 4)
                    .toString()
            );
        }
    }


    private void limpar() {

        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");

        txtCategoria.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");

        txtEspecie.setText("");
        txtRaca.setText("");
        txtIdade.setText("");
        txtIdCliente.setText("");

        txtCargo.setText("");

        if (tabela != null) {
            tabela.clearSelection();
        }
    }
}