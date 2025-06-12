package Model;
import Enum.*;
import Services.CPF;

public class Funcionario {
    private CPF Cpf;
    private String nome;
    private Cargos cargo;
    private Permissoes permissao;

    public Funcionario(CPF cpf, String nome, Cargos cargo, Permissoes permissao) {
        setCpf(cpf);
        setNome(nome);
        setCargo(cargo);
        setPermissao(permissao);
    }

    public Funcionario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CPF getCpf() {
        return Cpf;
    }

    public void setCpf(CPF cpf) {
        this.Cpf = cpf;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    public Permissoes getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissoes permissao) {
        this.permissao = permissao;
    }
}
