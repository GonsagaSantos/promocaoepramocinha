package DAOs;

import Model.Vendas;
import Services.*;
import Enum.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class VendasDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Vendas obj) {
        this.conn.conectar();
        String query = "INSERT INTO vendas(" +
                "idVenda," +
                "codigoDeBarrasNotaFiscal," +
                "formaDePagamento," +
                "precoTotalVenda," +
                "precoPagoPeloCliente," +
                "troco," +
                "dataVenda" +
                "cpfCliente)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdVenda());
            stmt.setString(2, obj.getCodBarrasNF().getCodigoApenasNumeros());
            stmt.setString(3, obj.getForma_pagamento().toString());
            stmt.setBigDecimal(4, obj.getPrecoTotalVenda());
            stmt.setBigDecimal(5, obj.getValorRecebido());
            stmt.setBigDecimal(6, obj.getValorTroco());
            stmt.setDate(7, Date.valueOf(obj.getDataVenda()));
            stmt.setString(8, obj.getCpf_cliente().getCpfSomenteNumeros());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Vendas consultar(Long id) {
        this.conn.conectar();
        String query = "SELECT * FROM vendas WHERE idVenda = ?";
        Vendas obj = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Long idVenda = rs.getLong("idVenda");
                String codBarrasNF = rs.getString("codigoDeBarrasNotaFiscal");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(codBarrasNF);
                FormaPagamento formaDePagamento = FormaPagamento.valueOf(rs.getString("formaDePagamento"));
                BigDecimal precoTotalVenda = rs.getBigDecimal("precoTotalVenda");
                BigDecimal valorRecebido = rs.getBigDecimal("precoPagoPeloCliente");
                BigDecimal valorTroco = rs.getBigDecimal("troco");
                LocalDate dataVenda = rs.getDate("dataVenda").toLocalDate();
                String cpfCliente = rs.getString("cpfCliente");
                CPF cpfObj = new CPF(cpfCliente);

                obj = new Vendas(idVenda, codigoDeBarrasObj, formaDePagamento, precoTotalVenda, valorRecebido, valorTroco, dataVenda, cpfObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }
}
