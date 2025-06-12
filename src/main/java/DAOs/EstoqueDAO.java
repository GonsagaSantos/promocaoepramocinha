package DAOs;

import Model.Estoque;
import Services.*;
import Enum.NivelEstoque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public interface EstoqueDAO {
    final ConexaoSQLite conn = new ConexaoSQLite();

    public static void inserir(Estoque obj) {
        conn.conectar();
        String query = "INSERT INTO estoque(" +
                "idEstoque," +
                "codigoDeBarras," +
                "cnpjFornecedor," +
                "precoVenda," +
                "precoCompra," +
                "quantidade," +
                "dataDeValidade," +
                "status_estoque," +
                "baixoEstoque)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdEstoque());
            stmt.setString(2, obj.getCodBarras().getCodigoApenasNumeros());
            stmt.setString(3, obj.getCnpjFornecedor().getCnpjApenasNumeros());
            stmt.setBigDecimal(4, obj.getPrecoVenda());
            stmt.setBigDecimal(5, obj.getPrecoCompra());
            stmt.setInt(6, obj.getQuantidade());

            if (obj.getData_validade() != null) {
                stmt.setDate(7, Date.valueOf(obj.getData_validade()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }

            stmt.setString(8, obj.getStatusEstoque().name());
            stmt.setBoolean(9, obj.isBaixoEstoque());

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            conn.desconectar();
        }
    }

    static Estoque consultar(Long idEstoque) {
        conn.conectar();
        String query = "SELECT * FROM estoque WHERE idEstoque = ?";

        Estoque obj = null;
        try(PreparedStatement stmt = conn.preparedStatement(query)) {
            stmt.setLong(1, idEstoque);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                obj = new Estoque();
                obj.setIdEstoque(retorno.getLong("idEstoque"));

                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                obj.setCodBarras(codigoDeBarrasObj);

                CNPJ cnpjFornecedor = new CNPJ(retorno.getString("cnpjFornecedor"));
                obj.setCnpjFornecedor(cnpjFornecedor);

                obj.setPrecoVenda(retorno.getBigDecimal("precoVenda"));
                obj.setPrecoCompra(retorno.getBigDecimal("precoCompra"));
                obj.setQuantidade(retorno.getInt("quantidade"));
                Date sqlDate = retorno.getDate("dataDeValidade");

                if (sqlDate != null) {
                    obj.setData_validade(sqlDate.toLocalDate());
                }

                String statusEstoqueStr = retorno.getString("status_estoque");
                if (statusEstoqueStr != null) {
                    obj.setStatusEstoque(NivelEstoque.valueOf(statusEstoqueStr));
                }
                obj.setBaixoEstoque(retorno.getBoolean("baixoEstoque"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.desconectar();
        }
        return obj;
    }
}