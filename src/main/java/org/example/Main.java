package org.example;

import DAOs.ConexaoSQLite; // Ainda precisamos dela para criar a tabela inicial
import DAOs.*;
import Model.Produtos;
import Services.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        System.out.println("--- Iniciando Teste de Inserção de Produto ---");

        // 2. Instanciar o DAO. Ele vai criar sua própria instância de ConexaoSQLite.
        DAOs.ProdutosDAO produtosDAO = new ProdutosDAO(); // <-- Agora sim, construtor sem argumentos!

        // 3. Instanciar o Service, passando a instância do DAO
        ProdutoService produtoService = new ProdutoService(produtosDAO);

        // 4. Preparar os dados do produto a ser cadastrado
        String codBarrasTeste = "1"; // Código de barras para teste
        String nomeTeste = "Cerveja Pilsen Lata 350ml";
        String categoriaTeste = "Bebidas Alcoólicas";
        String marcaTeste = "Cerveja Teste";

        System.out.println("\nTentando cadastrar o produto: " + nomeTeste);

        try {
            // 5. Chamar o método 'cadastrar' do ProdutoService
            Produtos produtoCadastrado = produtoService.cadastrar(
                    codBarrasTeste,
                    nomeTeste,
                    categoriaTeste,
                    marcaTeste
            );

            // 6. Verificar se o cadastro foi bem-sucedido
            if (produtoCadastrado != null && produtoCadastrado.getCodBarras() != null) {
                System.out.println("\nSucesso! Produto cadastrado:");
                System.out.println(produtoCadastrado.toString());
            } else {
                System.out.println("\nFalha! Produto não foi cadastrado (objeto retornado nulo ou sem código de barras).");
            }

            // --- Teste de Inserção com Código de Barras Duplicado (Esperado erro) ---
            System.out.println("\n--- Tentando cadastrar produto com código de barras duplicado (esperado erro) ---");
            String codBarrasDuplicado = "7891234567890"; // Mesmo código de barras
            String nomeDuplicado = "Cerveja Pilsen Lata 350ml (Duplicado)";

            try {
                produtoService.cadastrar(
                        codBarrasDuplicado,
                        nomeDuplicado,
                        "Bebidas",
                        "Cerveja Teste"
                );
                System.out.println("ERRO: Produto duplicado foi cadastrado, o que não deveria acontecer!");
            } catch (RuntimeException e) {
                System.err.println("Sucesso: Capturado erro esperado ao tentar inserir código de barras duplicado: " + e.getMessage());
            }

        } catch (RuntimeException e) {
            System.err.println("\nErro durante o processo de cadastro do produto: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Teste de Inserção Concluído ---");
    }
}