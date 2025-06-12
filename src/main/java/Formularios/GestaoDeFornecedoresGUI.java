package Formularios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestaoDeFornecedoresGUI extends JFrame {
    private JTextArea tituloFormulario;
    private JButton buttonCadastrarFornecedores;
    private JButton buttonRegistrarPedido;
    private JButton buttonHistoricoPedidos;

    public GestaoDeFornecedoresGUI() {
        buttonCadastrarFornecedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonRegistrarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonHistoricoPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoricoPedidosGUI().setVisible(true);
            }
        });
    }
}
