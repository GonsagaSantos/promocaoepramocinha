package Model;

import java.math.BigDecimal;
import Services.*;

public class DetalhesVenda {
    private long idDetalhesVenda;
    private long idVendas;
    private CodigoDeBarras produtoProduto;
    private String nomeItem;
    private int quantidade;
    private BigDecimal preco;
    private BigDecimal subtotal;

    public DetalhesVenda(long idDetalhes, long idVendas, CodigoDeBarras produtoProduto, String nomeItem, int quantidade, BigDecimal preco, BigDecimal subtotal) {
        setIdDetalhes(idDetalhes);
        setIdVenda(idVendas);
        setCodigoDeBarrasProduto(produtoProduto);
        setNomeItem(nomeItem);
        setQuantidade(quantidade);
        setPreco(preco);
        setSubtotal(subtotal);
    }

    public long getIdDetalhes() {
        return idDetalhesVenda;
    }

    public void setIdDetalhes(long idDetalhes) {
        this.idDetalhesVenda = idDetalhes;
    }

    public long getIdVenda() {
        return idVendas;
    }

    public void setIdVenda(long idVendas) {
        this.idVendas = idVendas;
    }

    public CodigoDeBarras getCodigoDeBarrasProduto() {
        return produtoProduto;
    }

    public void setCodigoDeBarrasProduto(CodigoDeBarras produtoProduto) {
        this.produtoProduto = produtoProduto;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
