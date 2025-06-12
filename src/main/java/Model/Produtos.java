package Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produtos {
    private String codBarras;
    private String nome;
    private String categoria;
    private String marca;

    public Produtos(String codBarras, String nome, String categoria, String marca) {
        setCodBarras(codBarras);
        setNome(nome);
        setCategoria(categoria);
        setMarca(marca);
    }

    public Produtos() {

    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}