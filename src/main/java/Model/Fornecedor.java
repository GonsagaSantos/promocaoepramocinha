package Model;

import Services.CNPJ;

public class Fornecedor {
    private CNPJ cnpj;
    private String nome;
    private String endereco;
    private String contato;

    public Fornecedor(CNPJ cnpj, String nome, String endereco, String contato) {
        setCnpj(cnpj);
        setNome(nome);
        setEndereco(endereco);
        setContato(contato);
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
