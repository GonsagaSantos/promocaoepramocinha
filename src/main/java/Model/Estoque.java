package Model;

import java.math.BigDecimal;
import java.time.LocalDate; // Usar LocalDate para datas no modelo

import Enum.NivelEstoque;

public class Estoque {
    private Long idEstoque;
    private String codBarras; // Usar java.lang.String
    private String cnpjFornecedor; // Usar java.lang.String
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private int quantidade;
    private LocalDate data_validade; // Campo como LocalDate
    private NivelEstoque statusEstoque;
    private boolean baixoEstoque;


    public Estoque(Long idEstoque, String codBarras, String cnpjFornecedor, BigDecimal precoVenda, BigDecimal precoCompra, int quantidade, LocalDate data_validade, NivelEstoque statusEstoque, boolean baixoEstoque) {
        this.idEstoque = idEstoque;
        this.codBarras = codBarras;
        this.cnpjFornecedor = cnpjFornecedor;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.data_validade = data_validade;
        this.statusEstoque = statusEstoque;
        this.baixoEstoque = baixoEstoque;
    }

    // Construtor padrão adicionado, se necessário
    public Estoque() {
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getCodBarras() { // Retorna java.lang.String
        return codBarras;
    }

    public void setCodBarras(String codBarras) { // Recebe java.lang.String
        this.codBarras = codBarras;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData_validade() { // Retorna LocalDate
        return data_validade;
    }

    public void setData_validade(LocalDate data_validade) { // Recebe LocalDate
        this.data_validade = data_validade;
    }

    public NivelEstoque getStatusEstoque() {
        return statusEstoque;
    }

    public void setStatusEstoque(NivelEstoque statusEstoque) {
        this.statusEstoque = statusEstoque;
    }

    public boolean isBaixoEstoque() {
        return baixoEstoque;
    }

    public void setBaixoEstoque(boolean baixoEstoque) {
        this.baixoEstoque = baixoEstoque;
    }

    public String getCnpjFornecedor() { // Retorna java.lang.String
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) { // Recebe java.lang.String
        this.cnpjFornecedor = cnpjFornecedor;
    }
}