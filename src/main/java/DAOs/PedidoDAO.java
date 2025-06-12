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
        String query = "INSERT INTO registroPedidos (" +
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
            stmt.setString(3, obj.getCpnjFornecedor());
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
        String query = "SELECT * FROM registroPedidos WHERE idRegistroPedido = ?";
        Pedido obj = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, id);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                Long idRegistroPedido = retorno.getLong("idRegistroPedido");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                String cnpjFornecedor = retorno.getString("cnpjFornecedor");
                int quantidade = retorno.getInt("quantidade");
                Date sqlDate = retorno.getDate("dataDoPedido");

                LocalDate dataPedido;
                if (sqlDate != null) {
                    dataPedido = sqlDate.toLocalDate();
                } else {
                    dataPedido = null;
                }

                BigDecimal preco = retorno.getBigDecimal("precoTotalPedido");

                obj = new Pedido(idRegistroPedido, codigoDeBarrasObj, cnpjFornecedor, quantidade, dataPedido, preco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(Pedido obj) {
        this.conn.conectar();
        String query = "UPDATE registroPedidos SET " +
                "codigoDeBarras = ?, " +
                "cnpjFornecedor = ?, " +
                "quantidade = ?, " +
                "dataDoPedido = ?, " +
                "precoTotalPedido = ? " +
                "WHERE idRegistroPedido = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarras().getCodigoApenasNumeros());
            stmt.setString(2, obj.getCpnjFornecedor());
            stmt.setInt(3, obj.getQuantidade());

            if (obj.getDataPedido() != null) {
                stmt.setDate(4, Date.valueOf(obj.getDataPedido()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setBigDecimal(5, obj.getPreco());
            stmt.setLong(6, obj.getIdPedido());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(long idRegistroPedido) {
        this.conn.conectar();
        String query = "DELETE FROM registroPedidos WHERE idRegistroPedido = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idRegistroPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Pedido consultarPedidoPorCnpjFornecedor(String cnpjNumeros) {
        this.conn.conectar();
        String query = "SELECT * FROM registroPedidos WHERE cnpjFornecedor = ? LIMIT 1"; // Adicionado LIMIT 1
        Pedido pedido = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, cnpjNumeros);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) { // Usa if em vez de while, já que esperamos apenas um
                Long idRegistroPedido = retorno.getLong("idRegistroPedido");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                String cnpjFornecedor = retorno.getString("cnpjFornecedor");
                int quantidade = retorno.getInt("quantidade");
                Date sqlDate = retorno.getDate("dataDoPedido");

                LocalDate dataPedido;
                if (sqlDate != null) {
                    dataPedido = sqlDate.toLocalDate();
                } else {
                    dataPedido = null;
                }

                BigDecimal preco = retorno.getBigDecimal("precoTotalPedido");
                pedido = new Pedido(idRegistroPedido, codigoDeBarrasObj, cnpjFornecedor, quantidade, dataPedido, preco);
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return pedido;
    }

    public Pedido consultarPedidoPorData(LocalDate data) {
        this.conn.conectar();
        String query = "SELECT * FROM registroPedidos WHERE dataDoPedido = ? LIMIT 1"; // Adicionado LIMIT 1
        Pedido pedido = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) { // Usa if em vez de while
                Long idRegistroPedido = retorno.getLong("idRegistroPedido");
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                String cnpjFornecedor = retorno.getString("cnpjFornecedor");
                int quantidade = retorno.getInt("quantidade");
                Date sqlDate = retorno.getDate("dataDoPedido");

                LocalDate dataPedido;
                if (sqlDate != null) {
                    dataPedido = sqlDate.toLocalDate();
                } else {
                    dataPedido = null;
                }

                BigDecimal preco = retorno.getBigDecimal("precoTotalPedido");
                pedido = new Pedido(idRegistroPedido, codigoDeBarrasObj, cnpjFornecedor, quantidade, dataPedido, preco);
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return pedido;
    }
}
