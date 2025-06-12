package Model;

public class String {
    private java.lang.String codBarras;
    private java.lang.String nome;
    private java.lang.String categoria;
    private java.lang.String marca;

    public String(java.lang.String codBarras, java.lang.String nome, java.lang.String categoria, java.lang.String marca) {
        setCodBarras(codBarras);
        setNome(nome);
        setCategoria(categoria);
        setMarca(marca);
    }

    public String() {

    }

    public java.lang.String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(java.lang.String codBarras) {
        this.codBarras = codBarras;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }

    public java.lang.String getCategoria() {
        return categoria;
    }

    public void setCategoria(java.lang.String categoria) {
        this.categoria = categoria;
    }

    public java.lang.String getMarca() {
        return marca;
    }

    public void setMarca(java.lang.String marca) {
        this.marca = marca;
    }

}