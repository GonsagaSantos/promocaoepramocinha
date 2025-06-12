package DAOs;

import Model.*;
import Services.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PedidoDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Pedido obj) {
        this.conn.conectar();
        String query = "INSERT INTO pedido (" +
                "idRegistroPedido," +
                "codigoDeBarras," +
                "cnpjFornecedor," +
                "quantidade," +
                "dataDoPedido," +
                "precoTotalPedido)" +
                "VALUES (?,?,?,?,?,?)";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdPedido());
            stmt.setString(2, obj.getCodBarras().getCodigoApenasNumeros());
            stmt.setString(3, obj.getCpnjFornecedor().getCnpjApenasNumeros());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setDate(5, Date.valueOf(obj.getDataPedido()));
            stmt.setBigDecimal(6, obj.getPreco());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Pedido consultar(Long id) {
        this.conn.conectar();
        String query = "SELECT * FROM estoque WHERE idRegistroPedido = ?";
        Pedido obj = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, id);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Long idRegistroPedido = retorno.getLong("idRegistroPedido");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                CNPJ cnpjFornecedorObj = new CNPJ(retorno.getString("cnpjFornecedor"));
                int quantidade = retorno.getInt("quantidade");
                Date sqlDate = retorno.getDate("dataDoPedido");

                LocalDate dataPedido;
                if (sqlDate != null) {
                    dataPedido = sqlDate.toLocalDate();
                } else {
                    dataPedido = null;
                }

                BigDecimal preco = retorno.getBigDecimal("precoTotalPedido");

                obj = new Pedido(idRegistroPedido, codigoDeBarrasObj, cnpjFornecedorObj, quantidade, dataPedido, preco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }
}
