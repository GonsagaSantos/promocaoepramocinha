package Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {
    private Long idPedido;
    private Produtos codBarras;
    private Fornecedor cpnjFornecedor;
    private int quantidade;
    private LocalDate dataPedido;
    private BigDecimal preco;

    public Pedido(Long idPedido, Produtos codBarras, Fornecedor cpnjFornecedor, int quantidade, LocalDate dataPedido, BigDecimal preco) {
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

    public Produtos getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Produtos codBarras) {
        this.codBarras = codBarras;
    }

    public Fornecedor getCpnjFornecedor() {
        return cpnjFornecedor;
    }

    public void setCpnjFornecedor(Fornecedor cpnjFornecedor) {
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
