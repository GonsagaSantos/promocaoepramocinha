package org.example;

import DAOs.*;
import Model.*;
import Services.*;

public class Main {

    public static void main(java.lang.String[] args) {

        System.out.println("--- Iniciando Teste de Inserção de Produto ---");

        DAOs.ProdutosDAO produtosDAO = new ProdutosDAO();
        ProdutoService produtoService = new ProdutoService(produtosDAO);

        Services.CodigoDeBarras codBarrasTeste = new Services.CodigoDeBarras("7908278342415");
        String nomeTeste = "Fanta Uva 2L";
        String categoriaTeste = "Refrigerantes";
        String marcaTeste = "Fanta";

        System.out.println("\nTentando cadastrar o produto: " + nomeTeste);

        try {
            Produto produtoCadastrado = produtoService.cadastrar(
                    codBarrasTeste,
                    nomeTeste,
                    categoriaTeste,
                    marcaTeste
            );

            if (produtoCadastrado != null && produtoCadastrado.getCodBarras() != null) {
                System.out.println("\nSucesso! Produto cadastrado:");
                System.out.println(produtoCadastrado.toString());
            } else {
                System.out.println("\nFalha! Produto não foi cadastrado (objeto retornado nulo ou sem código de barras).");
            }

        } catch (RuntimeException e) {
            System.err.println("\nErro durante o processo de cadastro do produto: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Teste de Inserção Concluído ---");
    }
}