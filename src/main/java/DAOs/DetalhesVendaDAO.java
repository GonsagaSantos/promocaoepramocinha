package DAOs;

import Model.*;
import Services.CodigoDeBarras;
import java.math.BigDecimal;
import java.sql.*;

public class DetalhesVendaDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(DetalhesVenda obj) {
        this.conn.conectar();
        String query = "INSERT INTO detalhes_venda(" +
                "idDetalhesVenda," +
                "idVendas," +
                "codigoDeBarrasProduto," +
                "nomeItem," +
                "quantidade," +
                "preco," +
                "subtotal)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdDetalhes());
            stmt.setLong(2, obj.getIdVenda());
            stmt.setString(3, obj.getCodigoDeBarrasProduto().getCodigoApenasNumeros());
            stmt.setString(4, obj.getNomeItem());
            stmt.setInt(5, obj.getQuantidade());
            stmt.setBigDecimal(6, obj.getPreco());
            stmt.setBigDecimal(7, obj.getSubtotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public DetalhesVenda consultar(long idDetalhesVenda) {
        this.conn.conectar();
        String query = "SELECT * FROM detalhes_venda WHERE idDetalhesVenda = ?";

        DetalhesVenda obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idDetalhesVenda);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                long id = retorno.getLong("idDetalhesVenda");
                long idVendaFk = retorno.getLong("idVendas");
                String codigoDeBarrasProdutoStr = retorno.getString("codigoDeBarrasProduto");
                String nomeItem = retorno.getString("nomeItem");
                int quantidade = retorno.getInt("quantidade");
                BigDecimal preco = retorno.getBigDecimal("preco");
                BigDecimal subtotal = retorno.getBigDecimal("subtotal");

                Vendas vendaPlaceholder = new Vendas(idVendaFk, null, null,
                        null, null, null, null, null);
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(codigoDeBarrasProdutoStr);

                obj = new DetalhesVenda(id, vendaPlaceholder.getIdVenda(), codigoDeBarrasObj, nomeItem, quantidade, preco, subtotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(DetalhesVenda obj) {
        this.conn.conectar();
        String query = "UPDATE detalhesVenda SET" +
                "idVenda = ?," +
                "codigoDeBarrasProduto = ?," +
                "nomeDoItem = ?," +
                "quantidade = ?," +
                "precoDoITEM = ?," +
                "subtotal = ? " +
                "where idDetalhesVenda = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdVenda());
            stmt.setString(2, obj.getCodigoDeBarrasProduto().getCodigoApenasNumeros());
            stmt.setString(3, obj.getNomeItem());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setBigDecimal(5, obj.getPreco());
            stmt.setBigDecimal(6, obj.getSubtotal());
            stmt.setLong(7, obj.getIdDetalhes());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(long idDetalhesVenda) {
        this.conn.conectar();
        String query = "DELETE FROM detalhes_venda WHERE idDetalhesVenda = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idDetalhesVenda);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}