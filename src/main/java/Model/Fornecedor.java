package Model;

public class Fornecedor {
    private String cnpj;
    private String nome;
    private String endereco;
    private String contato;

    public Fornecedor(String cnpj, String nome, String endereco, String contato) {
        setCnpj(cnpj);
        setNome(nome);
        setEndereco(endereco);
        setContato(contato);
    }

    public Fornecedor() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
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
