package Formularios;

import DAOs.FornecedorDAO;
import Model.Fornecedor;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CadastrarFornecedorGUI extends JFrame {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    private JPanel CadastrarFuncionario;
    private JButton buttonCadastrar;
    private JLabel lblNome;
    private JLabel lblCNPJ;
    private JLabel lblEndereco;
    private JLabel lblInformacoesDeContato;
    private JFormattedTextField formattedTextFieldCNPJ;
    private JTextField textFieldNome;
    private JTextField textFieldEndereco;
    private JTextField textFieldinformacoesDeContato;
    private JTextArea resultadoTextArea;

    public CadastrarFornecedorGUI() {
        setTitle("Cadastro de Fornecedor");
        setContentPane(CadastrarFuncionario);
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFornecedor();
            }
        });
    }

    private void cadastrarFornecedor() {
        String cnpj = formattedTextFieldCNPJ.getText();
        String nome = textFieldNome.getText();
        String endereco = textFieldEndereco.getText();
        String contato = textFieldinformacoesDeContato.getText();

        if (cnpj.isEmpty() || nome.isEmpty() || endereco.isEmpty() || contato.isEmpty()) {
            resultadoTextArea.setText("Erro: Todos os campos são obrigatórios!");
            return;
        }

        try {
            Fornecedor novoFornecedor = new Fornecedor(cnpj, nome, endereco, contato);

            fornecedorDAO.inserir(novoFornecedor);
            resultadoTextArea.setText("Fornecedor " + nome + " cadastrado com sucesso!");

            formattedTextFieldCNPJ.setText("");
            textFieldNome.setText("");
            textFieldEndereco.setText("");
            textFieldinformacoesDeContato.setText("");

        } catch (Exception ex) {
            resultadoTextArea.setText("Erro ao cadastrar fornecedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void createUIComponents() {
        try {
            MaskFormatter cnpjMask = new MaskFormatter("##.###.###/####-##");
            formattedTextFieldCNPJ = new JFormattedTextField(cnpjMask);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
