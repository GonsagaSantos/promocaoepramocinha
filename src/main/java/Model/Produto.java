package Model;

import Services.*;

public class Produto {
    private CodigoDeBarras codBarras;
    private String nome;
    private String categoria;
    private String marca;

    public Produto(CodigoDeBarras codBarras, String nome, String categoria, String marca) {
        this.codBarras = codBarras;
        this.nome = nome;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Produto () {
    }

    public CodigoDeBarras getCodBarras() {
        return codBarras;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setCodBarras(CodigoDeBarras codBarras) {
        this.codBarras = codBarras;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}