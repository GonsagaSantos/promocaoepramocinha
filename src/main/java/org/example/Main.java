package org.example; // Ajuste o pacote conforme a localização do seu Main

import DAOs.PedidoDAO;
import Model.Pedido;
import Services.CodigoDeBarras;
import Services.CNPJ; // Importe sua classe CNPJ
import java.math.BigDecimal;
import java.sql.Date; // Use java.sql.Date para o PreparedStatement
import java.time.LocalDate; // Para criar datas de forma mais fácil

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Iniciando Testes do Módulo de Pedidos ---");

        testarPedidoDAO();

        System.out.println("\n--- Testes do Módulo de Pedidos Concluídos ---");
    }

    private static void testarPedidoDAO() {
        System.out.println("\n--- Teste: Inserção e Consulta de Pedido ---");

        // 1. Instanciar a DAO
        PedidoDAO pedidoDAO = new PedidoDAO();

        // 2. Criar objetos de valor para o Pedido
        CodigoDeBarras codBarrasTeste = null;
        CNPJ cnpjFornecedorTeste = null;
        try {
            codBarrasTeste = new CodigoDeBarras("7 908278 34241 5"); // Código de barras válido
            cnpjFornecedorTeste = new CNPJ("00000000000100"); // CNPJ válido
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erro ao criar objetos de teste (Código de Barras/CNPJ): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // 3. Criar um objeto Pedido para teste
        // Gerar um ID de pedido único (para evitar conflitos em testes sucessivos)
        // Em um sistema real, o DB geraria isso ou você teria outra estratégia.
        long idPedidoTeste = System.currentTimeMillis(); // Um ID baseado no tempo atual

        // Criar uma data para o pedido
        Date dataPedidoTeste = Date.valueOf(LocalDate.now()); // Data de hoje

        Pedido pedidoNovo = new Pedido();
        pedidoNovo.setIdPedido(idPedidoTeste);
        pedidoNovo.setCodBarras(codBarrasTeste);
        pedidoNovo.setCpnjFornecedor(cnpjFornecedorTeste);
        pedidoNovo.setQuantidade(10);
        pedidoNovo.setDataPedido(dataPedidoTeste);
        pedidoNovo.setPreco(new BigDecimal("150.75")); // Preço total do pedido

        System.out.println("\nTentando inserir o pedido ID: " + pedidoNovo.getIdPedido());

        // 4. Testar a inserção
        try {
            pedidoDAO.inserir(pedidoNovo);
            System.out.println("✅ Sucesso na inserção do pedido.");
        } catch (Exception e) {
            System.err.println("❌ Falha na inserção do pedido: " + e.getMessage());
            e.printStackTrace();
            // Se a inserção falhar, não faz sentido tentar consultar
            return;
        }

        // 5. Testar a consulta
        System.out.println("\nTentando consultar o pedido pelo ID: " + pedidoNovo.getIdPedido());
        Pedido pedidoConsultado = null;
        try {
            pedidoConsultado = pedidoDAO.consultarPorCpf(pedidoNovo.getIdPedido()); // Usando o ID
        } catch (Exception e) {
            System.err.println("❌ Falha na consulta do pedido: " + e.getMessage());
            e.printStackTrace();
        }

        // 6. Verificar o resultado da consulta
        if (pedidoConsultado != null) {
            System.out.println("✅ Sucesso na consulta! Pedido encontrado:");
            System.out.println("   ID Pedido: " + pedidoConsultado.getIdPedido());
            System.out.println("   Cód. Barras: " + pedidoConsultado.getCodBarras().getCodigoFormatado());
            System.out.println("   CNPJ Fornecedor: " + pedidoConsultado.getCpnjFornecedor().getCnpjFormatado());
            System.out.println("   Quantidade: " + pedidoConsultado.getQuantidade());
            System.out.println("   Data Pedido: " + pedidoConsultado.getDataPedido());
            System.out.println("   Preço Total: " + pedidoConsultado.getPreco());

            // Opcional: Comparar se os dados inseridos são os mesmos dos consultados
            if (pedidoNovo.getIdPedido() == pedidoConsultado.getIdPedido() &&
                    pedidoNovo.getCodBarras().getCodigoFormatado().equals(pedidoConsultado.getCodBarras().getCodigoFormatado()) &&
                    pedidoNovo.getCpnjFornecedor().getCnpjFormatado().equals(pedidoConsultado.getCpnjFornecedor().getCnpjFormatado()) &&
                    pedidoNovo.getQuantidade() == pedidoConsultado.getQuantidade() &&
                    pedidoNovo.getDataPedido().equals(pedidoConsultado.getDataPedido()) && // Comparar datas
                    pedidoNovo.getPreco().compareTo(pedidoConsultado.getPreco()) == 0) { // Comparar BigDecimals
                System.out.println("✅ Dados inseridos e consultados são idênticos.");
            } else {
                System.err.println("⚠️ Dados inseridos e consultados NÃO são idênticos.");
            }

        } else {
            System.err.println("❌ Falha na consulta: Pedido não encontrado ou ocorreu um erro.");
        }
    }
}