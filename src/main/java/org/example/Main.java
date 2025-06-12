package org.example;

import DAOs.*;
import Model.String;
import Services.*;

public class Main {

    public static void main(java.lang.String[] args) {

        System.out.println("--- Iniciando Teste de Inserção de Produto ---");

        DAOs.ProdutosDAO produtosDAO = new ProdutosDAO();
        ProdutoService produtoService = new ProdutoService(produtosDAO);

        java.lang.String codBarrasTeste = "2";
        java.lang.String nomeTeste = "Biscoito Bauduco";
        java.lang.String categoriaTeste = "Bolachas";
        java.lang.String marcaTeste = "Bauduco";

        System.out.println("\nTentando cadastrar o produto: " + nomeTeste);

        try {
            String produtoCadastrado = produtoService.cadastrar(
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