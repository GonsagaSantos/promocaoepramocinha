package Model;

import Services.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {
    private Long idPedido;
    private CodigoDeBarras codBarras;
    private String cpnjFornecedor;
    private int quantidade;
    private LocalDate dataPedido;
    private BigDecimal preco;

    public Pedido(Long idPedido, CodigoDeBarras codBarras, String cpnjFornecedor, int quantidade, LocalDate dataPedido, BigDecimal preco) {
        setIdPedido(idPedido);
        setCodBarras(codBarras);
        setCpnjFornecedor(cpnjFornecedor);
        setQuantidade(quantidade);
        setDataPedido(dataPedido);
        setPreco(preco);
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public CodigoDeBarras getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(CodigoDeBarras codBarras) {
        this.codBarras = codBarras;
    }

    public String getCpnjFornecedor() {
        return cpnjFornecedor;
    }

    public void setCpnjFornecedor(String cpnjFornecedor) {
        this.cpnjFornecedor = cpnjFornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
