package org.example;

import Formularios.*;
public class Main {
    public static void main(String[] args) {
        GestaoDeFornecedoresGUI gestaoFornecedoresGUI = new GestaoDeFornecedoresGUI();
        gestaoFornecedoresGUI.setVisible(false);

        HistoricoPedidosGUI historicoPedidosGUI = new HistoricoPedidosGUI();
        historicoPedidosGUI.setVisible(false);

        CadastrarFornecedorGUI cadastrarFornecedorGUI = new CadastrarFornecedorGUI();
        cadastrarFornecedorGUI.setVisible(true);
    }
}