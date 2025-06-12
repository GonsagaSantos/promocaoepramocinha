package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import Enum.NivelEstoque;
import Services.*;

public class Estoque {
    private Long idEstoque;
    private CodigoDeBarras codBarras;
    private CNPJ cnpjFornecedor;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private int quantidade;
    private LocalDate data_validade;
    private NivelEstoque statusEstoque;
    private boolean baixoEstoque;


    public Estoque(Long idEstoque, CodigoDeBarras codBarras, CNPJ cnpjFornecedor, BigDecimal precoVenda, BigDecimal precoCompra, int quantidade, LocalDate data_validade, NivelEstoque statusEstoque, boolean baixoEstoque) {
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

    public Estoque() {
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public CodigoDeBarras getCodBarras() {
        return codBarras;
    }

    public CNPJ getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData_validade() {
        return data_validade;
    }

    public NivelEstoque getStatusEstoque() {
        return statusEstoque;
    }

    public boolean isBaixoEstoque() {
        return baixoEstoque;
    }


    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public void setCodBarras(CodigoDeBarras codBarras) {
        this.codBarras = codBarras;
    }

    public void setCnpjFornecedor(CNPJ cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setData_validade(LocalDate data_validade) {
        this.data_validade = data_validade;
    }

    public void setStatusEstoque(NivelEstoque statusEstoque) {
        this.statusEstoque = statusEstoque;
    }

    public void setBaixoEstoque(boolean baixoEstoque) {
        this.baixoEstoque = baixoEstoque;
    }
}