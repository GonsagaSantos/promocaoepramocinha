package Model;

import Services.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Pedido {
    private Long idPedido;
    private CodigoDeBarras codBarras;
    private CNPJ cpnjFornecedor;
    private int quantidade;
    private Date dataPedido;
    private BigDecimal preco;

    public Pedido(Long idPedido, CodigoDeBarras codBarras, CNPJ cpnjFornecedor, int quantidade, Date dataPedido, BigDecimal preco) {
        setIdPedido(idPedido);
        setCodBarras(codBarras);
        setCpnjFornecedor(cpnjFornecedor);
        setQuantidade(quantidade);
        setDataPedido(dataPedido);
        setPreco(preco);
    }

    public Pedido() {

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

    public CNPJ getCpnjFornecedor() {
        return cpnjFornecedor;
    }

    public void setCpnjFornecedor(CNPJ cpnjFornecedor) {
        this.cpnjFornecedor = cpnjFornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
