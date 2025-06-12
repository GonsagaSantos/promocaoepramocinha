package Model;

import Enum.FormaPagamento;
import Services.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vendas {
    private Long idVenda;
    private CodigoDeBarras codBarrasNF;
    private FormaPagamento forma_pagamento;
    private BigDecimal precoTotalVenda;
    private BigDecimal valorRecebido;
    private BigDecimal valorTroco;
    private LocalDate dataVenda;
    private CPF cpf_cliente;

    public Vendas(Long idVenda, CodigoDeBarras codBarrasNF, FormaPagamento forma_pagamento, BigDecimal precoTotalVenda, BigDecimal valorRecebido, BigDecimal valorTroco, LocalDate dataVenda, CPF cpf_cliente) {
        setIdVenda(idVenda);
        setCodBarrasNF(codBarrasNF);
        setForma_pagamento(forma_pagamento);
        setPrecoTotalVenda(precoTotalVenda);
        setValorRecebido(valorRecebido);
        setValorTroco(valorTroco);
        setDataVenda(dataVenda);
        setCpf_cliente(cpf_cliente);
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public CodigoDeBarras getCodBarrasNF() {
        return codBarrasNF;
    }

    public void setCodBarrasNF(CodigoDeBarras codBarrasNF) {
        this.codBarrasNF = codBarrasNF;
    }

    public FormaPagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(FormaPagamento forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public BigDecimal getPrecoTotalVenda() {
        return precoTotalVenda;
    }

    public void setPrecoTotalVenda(BigDecimal precoTotalVenda) {
        this.precoTotalVenda = precoTotalVenda;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public BigDecimal getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(BigDecimal valorTroco) {
        this.valorTroco = valorTroco;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public CPF getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(CPF cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }
}
