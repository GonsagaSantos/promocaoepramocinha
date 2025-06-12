package Formularios;

import DAOs.PedidoDAO;
import Model.Pedido;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

public class HistoricoPedidosGUI extends JFrame{
    private PedidoDAO pedidoDAO = new PedidoDAO();

    private JPanel panel1;
    private JFormattedTextField fTxtFieldCNPJ;
    private JFormattedTextField fTxtFieldData;
    private JTextField txtFieldID;
    private JButton buscarButtonID;
    private JButton buscarButtonData;
    private JButton buscarButtonCNPJ;
    private JPanel painel;
    private JTextArea resultadoTextArea;

    public HistoricoPedidosGUI() {
        this.pedidoDAO = pedidoDAO;
        setTitle("Histórico de Pedidos");
        setContentPane(panel1);
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buscarButtonID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(txtFieldID.getText());
                Pedido pedido = pedidoDAO.consultar(id);
                displayPedido(pedido);
            }
        });
        buscarButtonData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataString = fTxtFieldData.getText();
                LocalDate data = LocalDate.parse(dataString);
                Pedido pedido = pedidoDAO.consultarPedidoPorData(data);
                displayPedido(pedido);
            }
        });
        buscarButtonCNPJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cnpj = fTxtFieldCNPJ.getText();
                Pedido pedido = pedidoDAO.consultarPedidoPorCnpjFornecedor(cnpj);
                displayPedido(pedido);
            }
        });
    }

    private void displayPedido(Pedido pedido) {
        if (resultadoTextArea == null) {
            System.err.println("JTextArea 'resultadoTextArea' não encontrado ou não inicializado.");
            return;
        }

        if (pedido == null) {
            resultadoTextArea.setText("Nenhum pedido encontrado para a sua busca.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("ID Pedido: ").append(pedido.getIdPedido()).append("\n");
            sb.append("  Código de Barras: ").append(pedido.getCodBarras().getCodigoApenasNumeros()).append("\n");
            sb.append("  CNPJ Fornecedor: ").append(pedido.getCpnjFornecedor()).append("\n");
            sb.append("  Quantidade: ").append(pedido.getQuantidade()).append("\n");
            sb.append("  Data do Pedido: ").append(pedido.getDataPedido()).append("\n");
            sb.append("  Preço Total: ").append(pedido.getPreco()).append("\n");
            sb.append("------------------------------------------\n");
            resultadoTextArea.setText(sb.toString());
        }
    }

    private void createUIComponents() throws ParseException {
        MaskFormatter cnpjMask = new MaskFormatter("##.###.###/####-##");
        fTxtFieldCNPJ = new JFormattedTextField(cnpjMask);
        MaskFormatter dataMask = new MaskFormatter("####-##-##");
        fTxtFieldData = new JFormattedTextField(dataMask);
    }
}
