package Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import Enum.*;

public class Estoque {
    private Long idEstoque;
    private Produtos codBarras;
    private String cnpjFornecedor;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private int quantidade;
    private LocalDate data_validade;
    private NivelEstoque statusEstoque;
    private boolean baixoEstoque;


    public Estoque(Long idEstoque, Produtos codBarras, String cnpjFornecedor, BigDecimal precoVenda, BigDecimal precoCompra, int quantidade, LocalDate data_validade, NivelEstoque statusEstoque, boolean baixoEstoque) {
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

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Produtos getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Produtos codBarras) {
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

    public LocalDate getData_validade() {
        return data_validade;
    }

    public void setData_validade(LocalDate data_validade) {
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

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }
}
