package org.example;

import DAOs.EstoqueDAO;
import DAOs.PedidoDAO;
import DAOs.ProdutosDAO;
import DAOs.ConexaoSQLite;
import Model.Estoque;
import Model.Pedido;
import Model.Produto;
import Services.CNPJ;
import Services.CodigoDeBarras;
import Services.FornecedorService;
import Services.GerarCodigoBarras;
import Enum.NivelEstoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Iniciando o Sistema de Supermercado ---");

        // Opcional: Criar tabelas se elas não existirem.
        // new ConexaoSQLite().criarTabelas();

        // --- TESTE DE CADASTRO E CONSULTA DE PEDIDOS REGISTRADOS ---
        System.out.println("\n--- Teste: Cadastro e Consulta de Pedidos Registrados ---");

        PedidoDAO pedidoDAO = new PedidoDAO();

        // Dados para o pedido de teste
        Long idPedidoTeste = System.currentTimeMillis(); // ID único baseado no tempo
        String codigoBarrasNumeros = GerarCodigoBarras.gerarNovo();
        String cnpjFornecedorNumeros = "11222333000181"; // CNPJ válido para teste
        int quantidadePedida = 50;
        BigDecimal precoTotalPedido = new BigDecimal("500.00"); // Preço total do pedido

        CodigoDeBarras codBarrasObj = null;
        CNPJ cnpjObj = null;
        try {
            codBarrasObj = new CodigoDeBarras(codigoBarrasNumeros);
            cnpjObj = new CNPJ(cnpjFornecedorNumeros);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erro na criação de objetos de valor (Código de Barras/CNPJ) para Pedido: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Pedido novoPedido = new Pedido();
        novoPedido.setIdPedido(idPedidoTeste);
        novoPedido.setCodBarras(codBarrasObj);
        novoPedido.setCpnjFornecedor(cnpjObj);
        novoPedido.setQuantidade(quantidadePedida);
        novoPedido.setDataPedido(LocalDate.now());
        novoPedido.setPreco(precoTotalPedido);


        System.out.println("\nTentando inserir o pedido ID: " + novoPedido.getIdPedido());

        try {
            // Limpa dados antigos para um teste limpo
            pedidoDAO.excluir(novoPedido.getIdPedido());
            Thread.sleep(100);

            // Insere o novo pedido
            pedidoDAO.inserir(novoPedido);
            Thread.sleep(100);
            System.out.println("✅ Sucesso na inserção do pedido.");
        } catch (Exception e) {
            System.err.println("❌ Falha na inserção do pedido: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // 4. Testar a consulta
        System.out.println("\nTentando consultar o pedido pelo ID: " + novoPedido.getIdPedido());
        Pedido pedidoConsultado = null;
        try {
            pedidoConsultado = pedidoDAO.consultarPorId(novoPedido.getIdPedido());
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println("❌ Falha na consulta do pedido: " + e.getMessage());
            e.printStackTrace();
        }

        // 5. Verificar o resultado da consulta
        if (pedidoConsultado != null) {
            System.out.println("✅ Sucesso na consulta! Pedido encontrado:");
            System.out.println("   ID Pedido: " + pedidoConsultado.getIdPedido());
            System.out.println("   Cód. Barras: " + pedidoConsultado.getCodBarras().getCodigoApenasNumeros());
            System.out.println("   CNPJ Fornecedor: " + pedidoConsultado.getCpnjFornecedor().getCnpjFormatado());
            System.out.println("   Quantidade: " + pedidoConsultado.getQuantidade());
            System.out.println("   Data Pedido: " + pedidoConsultado.getDataPedido());
            System.out.println("   Preço Total: " + pedidoConsultado.getPreco());

        } else {
            System.err.println("❌ Falha na consulta: Pedido não encontrado ou ocorreu um erro.");
        }
        System.out.println("\n--- Fim do Teste de Pedidos Registrados ---");
    }

    // Você pode descomentar e usar os outros métodos de teste conforme sua necessidade:
    // private static void testarCadastroDeProdutos() { ... }
    // private static void testarCadastroCompletoDeProdutoNoEstoque() { ... }
    // private static void testarFluxoFornecedorService() { ... }


    // --- Métodos auxiliares necessários para os testes (não considerados "novas funções de teste") ---
    private static Long getIdEstoquePorCodigoBarras(String codigoDeBarras) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = estoqueDAO.consultarPorCodigoDeBarras(codigoDeBarras);
        return (estoque != null) ? estoque.getIdEstoque() : null;
    }

    private static Long getUltimoIdPedidoPorCodigoBarras(String codigoDeBarras) {
        // Esta é uma implementação mock para o teste, pois não temos um método na PedidoDAO que busque por código de barras.
        // Para uma limpeza robusta em testes, seria ideal ter esse método na PedidoDAO para limpar dados.
        return null;
    }
}