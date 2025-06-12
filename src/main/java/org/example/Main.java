package org.example;

import Formularios.GestaoDeFornecedoresGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { //
            @Override
            public void run() { //
                GestaoDeFornecedoresGUI gestaoFornecedoresGUI = new GestaoDeFornecedoresGUI();
                gestaoFornecedoresGUI.setVisible(true);
            }
        });
    }
}
