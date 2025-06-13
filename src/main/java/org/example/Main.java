package org.example;

import Formularios.*;
public class Main {
    public static void main(String[] args) {
        GestaoDeFornecedoresGUI gestaoFornecedoresGUI = new GestaoDeFornecedoresGUI();
        gestaoFornecedoresGUI.setVisible(false);

        HistoricoPedidosGUI historicoPedidosGUI = new HistoricoPedidosGUI();
        historicoPedidosGUI.setVisible(true);

        CadastrarFornecedorGUI cadastrarFornecedorGUI = new CadastrarFornecedorGUI();
        cadastrarFornecedorGUI.setVisible(false);
    }
}