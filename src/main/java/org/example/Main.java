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

        testarFluxoFornecedorService();

        System.out.println("\n--- Todos os Testes Concluídos. Sistema Pronto ---");
    }

    private static void testarFluxoFornecedorService() {
        System.out.println("\n--- Teste Completo: Cadastro de Pedido de Compra e Atualização de Estoque ---");

        ProdutosDAO produtosDAO = new ProdutosDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        FornecedorService fornecedorService = new FornecedorService(pedidoDAO, produtosDAO, estoqueDAO);

        String codigoBarrasNumeros = GerarCodigoBarras.gerarNovo();
        CodigoDeBarras codBarrasProdutoTeste = null;
        CNPJ cnpjFornecedorTeste = null;
        Long idPedidoTeste = 100001L; // ID fixo para o pedido de teste
        try {
            codBarrasProdutoTeste = new CodigoDeBarras(codigoBarrasNumeros);
            // Passe o CNPJ sem formatação para o construtor
            cnpjFornecedorTeste = new CNPJ("11222333000181"); // Este CNPJ foi testado e é válido com a sua lógica
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erro na criação de objetos de valor (Código de Barras/CNPJ): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        int quantidadeDoPedido = 75;
        BigDecimal precoCompraInicial = new BigDecimal("12.50");
        int quantidadeEstoqueInicial = 25;

        try {
            produtosDAO.excluir(codBarrasProdutoTeste.getCodigoApenasNumeros());
            Long idEstoqueParaExcluir = getIdEstoquePorCodigoBarras(codBarrasProdutoTeste.getCodigoApenasNumeros());
            if (idEstoqueParaExcluir != null) {
                estoqueDAO.excluir(idEstoqueParaExcluir);
            }
            pedidoDAO.excluir(idPedidoTeste);


            System.out.println("\n--> Preparando Produto base para teste...");
            Produto novoProdutoBase = new Produto();
            novoProdutoBase.setCodBarras(codBarrasProdutoTeste);
            novoProdutoBase.setNome("Refrigerante Cola " + System.currentTimeMillis());
            novoProdutoBase.setCategoria("Refrigerantes");
            novoProdutoBase.setMarca("Marca Teste");
            produtosDAO.inserir(novoProdutoBase);
            System.out.println("Produto base inserido: " + novoProdutoBase.getNome() + " | Cód: " + novoProdutoBase.getCodBarras().getCodigoApenasNumeros());


            System.out.println("--> Preparando Estoque inicial para o produto teste...");
            Estoque novoEstoqueInicial = new Estoque();
            // Para o idEstoque, se for auto-incremento no DB, você pode deixar o DB gerá-lo e não setar aqui,
            // ou usar um ID fixo para teste como 1L, mas certifique-se que é único.
            novoEstoqueInicial.setIdEstoque(12345L); // ID fixo para o estoque de teste
            novoEstoqueInicial.setCodBarras(codBarrasProdutoTeste);
            novoEstoqueInicial.setCnpjFornecedor(cnpjFornecedorTeste);
            novoEstoqueInicial.setPrecoVenda(new BigDecimal("18.00"));
            novoEstoqueInicial.setPrecoCompra(precoCompraInicial);
            novoEstoqueInicial.setQuantidade(quantidadeEstoqueInicial);
            novoEstoqueInicial.setData_validade(LocalDate.of(2025, 12, 31));
            novoEstoqueInicial.setStatusEstoque(NivelEstoque.DISPONIVEL);
            novoEstoqueInicial.setBaixoEstoque(false);
            estoqueDAO.inserir(novoEstoqueInicial);
            System.out.println("Estoque inicial inserido para o produto: " + novoEstoqueInicial.getCodBarras().getCodigoApenasNumeros() + " | Quantidade: " + novoEstoqueInicial.getQuantidade());

        } catch (Exception e) {
            System.err.println("❌ ERRO GRAVE NA PREPARAÇÃO DO BANCO DE DADOS PARA O TESTE: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // --- EXECUTANDO A FORNECEDORSERVICE ---
        System.out.println("\n--- Executando FornecedorService.cadastrarNovoPedido ---");
        System.out.println("  ID do Pedido: " + idPedidoTeste);
        System.out.println("  Produto (Cód): " + codBarrasProdutoTeste.getCodigoApenasNumeros());
        System.out.println("  Fornecedor (CNPJ): " + cnpjFornecedorTeste.getCnpjFormatado());
        System.out.println("  Quantidade do Pedido: " + quantidadeDoPedido);

        Pedido pedidoResultante = null;
        try {
            pedidoResultante = fornecedorService.cadastrarNovoPedido(
                    idPedidoTeste, // Passa o ID fixo
                    codBarrasProdutoTeste,
                    cnpjFornecedorTeste,
                    quantidadeDoPedido
            );

            if (pedidoResultante != null) {
                System.out.println("\n✅ SUCESSO! Pedido de compra processado.");
                System.out.println("   ID do Pedido Gerado: " + pedidoResultante.getIdPedido());
                System.out.println("   Preço Total do Pedido: " + pedidoResultante.getPreco());

                // 4. Verificar se o estoque foi atualizado (consultando diretamente a DAO de Estoque)
                System.out.println("\n--- Verificando Estoque Após Pedido ---");
                Estoque estoqueAtualizado = estoqueDAO.consultarPorCodigoDeBarras(codBarrasProdutoTeste.getCodigoApenasNumeros());
                if (estoqueAtualizado != null) {
                    System.out.println("✅ Estoque atualizado para o produto " + estoqueAtualizado.getCodBarras().getCodigoApenasNumeros() + ": " + estoqueAtualizado.getQuantidade() + " unidades.");
                    int quantidadeEsperada = quantidadeEstoqueInicial + quantidadeDoPedido;
                    if (estoqueAtualizado.getQuantidade() == quantidadeEsperada) {
                        System.out.println("✅ Quantidade em estoque CORRETA (" + quantidadeEsperada + ").");
                    } else {
                        System.err.println("⚠️ Quantidade em estoque INCORRETA. Esperado: " + quantidadeEsperada + " | Atual: " + estoqueAtualizado.getQuantidade());
                    }
                } else {
                    System.err.println("❌ Falha ao consultar estoque após atualização.");
                }

                // 5. Verificar se o pedido foi inserido (consultando diretamente a DAO de Pedido)
                System.out.println("\n--- Verificando Pedido no Registro ---");
                Pedido pedidoNoRegistro = pedidoDAO.consultarPorId(pedidoResultante.getIdPedido());
                if (pedidoNoRegistro != null) {
                    System.out.println("✅ Pedido encontrado no registro com ID: " + pedidoNoRegistro.getIdPedido());
                    if (pedidoNoRegistro.getQuantidade() == quantidadeDoPedido &&
                            pedidoNoRegistro.getPreco().compareTo(pedidoResultante.getPreco()) == 0) {
                        System.out.println("✅ Dados do pedido registrados CORRETAMENTE.");
                    } else {
                        System.err.println("⚠️ Dados do pedido registrados INCORRETAMENTE.");
                    }
                } else {
                    System.err.println("❌ Falha ao consultar pedido no registro.");
                }

            } else {
                System.err.println("\n❌ Falha! O método cadastrarNovoPedido retornou nulo.");
            }

        } catch (RuntimeException e) {
            System.err.println("\n❌ ERRO DURANTE O FLUXO DA FORNECEDORSERVICE: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Fim do Teste de FornecedorService ---");
    }

    // Métodos auxiliares para facilitar a limpeza (opcional, para testes repetidos)
    private static Long getIdEstoquePorCodigoBarras(String codigoDeBarras) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = estoqueDAO.consultarPorCodigoDeBarras(codigoDeBarras);
        return (estoque != null) ? estoque.getIdEstoque() : null;
    }

    private static Long getUltimoIdPedidoPorCodigoBarras(String codigoDeBarras) {
        // Implementação mock para o teste, pois não temos um método na PedidoDAO que busque por código de barras.
        // Para um teste robusto, seria ideal ter esse método na PedidoDAO para limpar dados.
        return null;
    }
}