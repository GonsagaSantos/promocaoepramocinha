package DAOs;

import Model.*;
import Services.*;
import Enum.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class VendasDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserirV(Vendas obj) {
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

    public Vendas consultar(long id) {
        this.conn.conectar();
        String query = "SELECT * FROM vendas WHERE idVenda = ?";
        Vendas obj = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                long idVenda = resultSet.getLong("idVenda");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(resultSet.getString("codigoDeBarrasNotaFiscal"));
                FormaPagamento formaDePagamento = FormaPagamento.valueOf(resultSet.getString("formaDePagamento"));
                BigDecimal precoTotalVenda = resultSet.getBigDecimal("precoTotalVenda");
                BigDecimal valorRecebido = resultSet.getBigDecimal("precoPagoPeloCliente");
                BigDecimal valorTroco = resultSet.getBigDecimal("troco");
                LocalDate dataVenda = resultSet.getDate("dataVenda").toLocalDate();
                CPF cpfObj = new CPF(resultSet.getString("cpfCliente"));

                obj = new Vendas(idVenda, codigoDeBarrasObj, formaDePagamento, precoTotalVenda, valorRecebido, valorTroco, dataVenda, cpfObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(Vendas obj) {
        this.conn.conectar();
        String query = "UPDATE vendas SET " +
                "codigoDeBarrasNotaFiscal = ?, " +
                "formaDePagamento = ?, " +
                "precoTotalVenda = ?, " +
                "precoPagoPeloCliente = ?, " +
                "troco = ?, " +
                "dataVenda = ?, " +
                "cpfCliente = ? " +
                "WHERE idVenda = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarrasNF().getCodigoApenasNumeros());
            stmt.setString(2, obj.getForma_pagamento().name());
            stmt.setBigDecimal(3, obj.getPrecoTotalVenda());
            stmt.setBigDecimal(4, obj.getValorRecebido());
            stmt.setBigDecimal(5, obj.getValorTroco());
            stmt.setDate(6, Date.valueOf(obj.getDataVenda()));
            stmt.setString(7, obj.getCpf_cliente().getCpfSomenteNumeros());
            stmt.setLong(8, obj.getIdVenda());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(long idVenda) {
        this.conn.conectar();
        String query = "DELETE FROM vendas WHERE idVenda = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idVenda);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}
