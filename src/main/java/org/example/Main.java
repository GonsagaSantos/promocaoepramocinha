package org.example;

import DAOs.*;
import Model.*;
import Services.*;
import Enum.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        DetalhesVendaDAO detalhesVendaDAO = new DetalhesVendaDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProdutosDAO produtosDAO = new ProdutosDAO();
        VendasDAO vendasDAO = new VendasDAO();

        Random random = new Random();

        String cnpjFornecedor = "11.222.333/0001-" + (10 + random.nextInt(90));
        CPF cpfObj = new CPF(String.format("%03d.%03d.%03d-%02d", random.nextInt(1000), random.nextInt(1000), random.nextInt(1000), random.nextInt(100)));
        String codigoBarrasProdutoStr = GerarCodigoBarras.gerarNovo();
        CodigoDeBarras codBarrasProd = new CodigoDeBarras(codigoBarrasProdutoStr);
        Long idEstoque = random.nextLong();
        Long idPedido = random.nextLong();
        long idVenda = random.nextLong();
        String novoCodigoBarrasNFStr = GerarCodigoBarras.gerarNovo();
        CodigoDeBarras codBarrasNF = new CodigoDeBarras(novoCodigoBarrasNFStr);
        long idDetalhes = random.nextLong();

        System.out.println("--- Preparando dados iniciais para testes ---");
        Fornecedor fornecedorInicial = new Fornecedor(cnpjFornecedor, "Fornecedor Inicial", "Rua C, 456", "contato.inicial@teste.com");
        fornecedorDAO.inserir(fornecedorInicial);
        System.out.println("Fornecedor inicial inserido: " + fornecedorInicial.getNome());

        Funcionario funcionarioInicial = new Funcionario(cpfObj, "Funcionario Inicial", Cargos.OPERADOR_DE_CAIXA, Permissoes.BASICA);
        funcionarioDAO.inserir(funcionarioInicial);
        System.out.println("Funcionário inicial inserido: " + funcionarioInicial.getNome());

        Produto produtoInicial = new Produto(codBarrasProd, "Produto Inicial", "Categoria Teste", "Marca Inicial");
        produtosDAO.inserir(produtoInicial);
        System.out.println("Produto inicial inserido: " + produtoInicial.getNome());

        System.out.println("\n--- Testando EstoqueDAO ---");
        Estoque estoque = new Estoque(idEstoque, codBarrasProd, cnpjFornecedor, BigDecimal.valueOf(50.00), BigDecimal.valueOf(25.00), 100, LocalDate.now().plusMonths(6), NivelEstoque.DISPONIVEL, false);
        estoqueDAO.inserir(estoque);
        System.out.println("Estoque inserido para o produto: " + estoque.getCodBarras().getCodigoApenasNumeros());

        Estoque retrievedEstoque = estoqueDAO.consultar(idEstoque);
        System.out.println("Estoque consultado para o produto: " + retrievedEstoque.getCodBarras().getCodigoApenasNumeros());

        retrievedEstoque.setQuantidade(120);
        estoqueDAO.alterar(retrievedEstoque);
        System.out.println("Quantidade do estoque atualizada: " + estoqueDAO.consultar(idEstoque).getQuantidade());

        System.out.println("\n--- Testando VendasDAO ---");
        Vendas venda = new Vendas(idVenda, codBarrasNF, FormaPagamento.CARTAO_DE_CREDITO, BigDecimal.valueOf(100.00), BigDecimal.valueOf(100.00), BigDecimal.valueOf(0.00), LocalDate.now(), cpfObj);
        vendasDAO.inserir(venda);
        System.out.println("Venda inserida com ID: " + venda.getIdVenda());

        Vendas retrievedVenda = vendasDAO.consultar(idVenda);
        System.out.println("Venda consultada: " + retrievedVenda.getIdVenda());

        retrievedVenda.setValorRecebido(BigDecimal.valueOf(110.00));
        retrievedVenda.setValorTroco(BigDecimal.valueOf(10.00));
        vendasDAO.alterar(retrievedVenda);
        System.out.println("Troco da venda atualizado: " + vendasDAO.consultar(idVenda).getValorTroco());

        System.out.println("\n--- Testando DetalhesVendaDAO ---");
        DetalhesVenda detalhesVenda = new DetalhesVenda(idDetalhes, idVenda, codBarrasProd, "Produto Inicial", 1, BigDecimal.valueOf(50.00), BigDecimal.valueOf(50.00));
        detalhesVendaDAO.inserir(detalhesVenda);
        System.out.println("DetalhesVenda inserido com ID: " + detalhesVenda.getIdDetalhes());

        DetalhesVenda retrievedDetalhesVenda = detalhesVendaDAO.consultar(idDetalhes);
        System.out.println("DetalhesVenda consultado: " + retrievedDetalhesVenda.getNomeItem());

        retrievedDetalhesVenda.setQuantidade(2);
        retrievedDetalhesVenda.setSubtotal(BigDecimal.valueOf(100.00));
        detalhesVendaDAO.alterar(retrievedDetalhesVenda);
        System.out.println("Quantidade de DetalhesVenda atualizada: " + detalhesVendaDAO.consultar(idDetalhes).getQuantidade());

        System.out.println("\n--- Realizando Operações de Exclusão ---");

        detalhesVendaDAO.excluir(idDetalhes);
        System.out.println("DetalhesVenda com ID " + idDetalhes + " excluído: " + (detalhesVendaDAO.consultar(idDetalhes) == null));

        vendasDAO.excluir(idVenda);
        System.out.println("Venda com ID " + idVenda + " excluída: " + (vendasDAO.consultar(idVenda) == null));

        pedidoDAO.excluir(idPedido);
        System.out.println("Pedido com ID " + idPedido + " excluído: " + (pedidoDAO.consultar(idPedido) == null));

        estoqueDAO.excluir(idEstoque);
        System.out.println("Estoque com ID " + idEstoque + " excluído: " + (estoqueDAO.consultar(idEstoque) == null));

        produtosDAO.excluir(codBarrasProd.getCodigoApenasNumeros());
        System.out.println("Produto com código de barras " + codBarrasProd.getCodigoApenasNumeros() + " excluído: " + (produtosDAO.consultar(codBarrasProd.getCodigoApenasNumeros()) == null));

        funcionarioDAO.excluir(cpfObj.getCpfSomenteNumeros());
        System.out.println("Funcionário com CPF " + cpfObj.getCpfSomenteNumeros() + " excluído: " + (funcionarioDAO.consultarPorCpf(cpfObj.getCpfSomenteNumeros()) == null));

        fornecedorDAO.excluir(cnpjFornecedor);
        System.out.println("Fornecedor com CNPJ " + cnpjFornecedor + " excluído: " + (fornecedorDAO.consultar(cnpjFornecedor) == null));
    }
}