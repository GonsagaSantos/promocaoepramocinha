package org.example;

// Importe suas DAOs e Models
import DAOs.ConexaoSQLite; // Importe sua DAO de Conexão
import DAOs.PedidoDAO;     // Importe seu PedidoDAO
import Formularios.HistoricoPedidosGUI; // Importe sua GUI de Histórico

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

            System.out.println("--- Iniciando Testes da Aplicação ---");

            // Garante que a interface gráfica (Swing) seja executada na Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ConexaoSQLite conexao = new ConexaoSQLite(); //
                    PedidoDAO pedidoDAO = new PedidoDAO(); //
                    HistoricoPedidosGUI historicoGUI = new HistoricoPedidosGUI();
                    historicoGUI.setVisible(true);

                    System.out.println("\n--- GUI de Histórico de Pedidos Iniciada ---");
                }
            });
        }
    }
