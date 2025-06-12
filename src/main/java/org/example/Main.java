package org.example;

import DAOs.FuncionarioDAO; // Importe sua DAO
import Model.Funcionario;   // Importe sua classe Model Funcionario
import Services.CPF;        // Importe sua classe Services CPF
import Enum.Cargos;         // Importe seu Enum Cargos
import Enum.Permissoes;     // Importe seu Enum Permissoes

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Iniciando Testes do Módulo de Funcionários ---");

        testarFuncionarioDAO();

        System.out.println("\n--- Testes do Módulo de Funcionários Concluídos ---");
    }

    private static void testarFuncionarioDAO() {
        System.out.println("\n--- Teste: Inserção e Consulta de Funcionário ---");

        // 1. Instanciar a DAO
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        // 2. Criar um objeto Funcionario para teste
        CPF cpfTeste = null;
        try {
            cpfTeste = new CPF("123.456.789-01"); // Use um CPF válido para teste
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar CPF de teste: " + e.getMessage());
            return; // Aborta o teste se o CPF for inválido
        }

        Funcionario funcionarioNovo = new Funcionario(
                cpfTeste,
                "João da Silva",
                Cargos.GERENTE,      // Use uma constante do seu Enum Cargos
                Permissoes.ADMIN     // Use uma constante do seu Enum Permissoes
        );

        System.out.println("\nTentando inserir o funcionário: " + funcionarioNovo.getNome() + " (CPF: " + funcionarioNovo.getCpf().getCpfSomenteNumeros() + ")");

        // 3. Testar a inserção
        try {
            funcionarioDAO.inserir(funcionarioNovo);
            System.out.println("✅ Sucesso na inserção do funcionário.");
        } catch (Exception e) {
            System.err.println("❌ Falha na inserção do funcionário: " + e.getMessage());
            e.printStackTrace();
            // Se a inserção falhar, não faz sentido tentar consultar
            return;
        }

        // 4. Testar a consulta
        System.out.println("\nTentando consultar o funcionário pelo CPF: " + funcionarioNovo.getCpf().getCpfSomenteNumeros());
        Funcionario funcionarioConsultado = null;
        try {
            funcionarioConsultado = funcionarioDAO.consultarPorCpf(funcionarioNovo.getCpf().getCpfSomenteNumeros());
        } catch (Exception e) {
            System.err.println("❌ Falha na consulta do funcionário: " + e.getMessage());
            e.printStackTrace();
        }

        // 5. Verificar o resultado da consulta
        if (funcionarioConsultado != null) {
            System.out.println("✅ Sucesso na consulta! Funcionário encontrado:");
            System.out.println("   Nome: " + funcionarioConsultado.getNome());
            System.out.println("   CPF: " + funcionarioConsultado.getCpf().getCpfConcatenado());
            System.out.println("   Cargo: " + funcionarioConsultado.getCargo());
            System.out.println("   Permissão: " + funcionarioConsultado.getPermissao());

            // Opcional: Comparar se os dados inseridos são os mesmos dos consultados
            if (funcionarioNovo.getCpf().getCpfSomenteNumeros().equals(funcionarioConsultado.getCpf().getCpfSomenteNumeros()) &&
                    funcionarioNovo.getNome().equals(funcionarioConsultado.getNome()) &&
                    funcionarioNovo.getCargo().equals(funcionarioConsultado.getCargo()) &&
                    funcionarioNovo.getPermissao().equals(funcionarioConsultado.getPermissao())) {
                System.out.println("✅ Dados inseridos e consultados são idênticos.");
            } else {
                System.err.println("⚠️ Dados inseridos e consultados NÃO são idênticos.");
            }

        } else {
            System.err.println("❌ Falha na consulta: Funcionário não encontrado ou ocorreu um erro.");
        }
    }
}