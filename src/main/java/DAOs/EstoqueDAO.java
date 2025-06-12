package DAOs;

import Model.Estoque;
import Services.*;
import Enum.NivelEstoque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class EstoqueDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Estoque obj) {
        this.conn.conectar();
        String query = "INSERT INTO estoque(" +
                "idEstoque," +
                "codigoDeBarras," +
                "cnpjFornecedor," +
                "precoVenda," +
                "precoCompra," +
                "quantidade," +
                "dataDeValidade," +
                "status_estoque," + // Nome da coluna
                "alertaEstoqueBaixo)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
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

            stmt.setString(8, obj.getStatusEstoque().getDbValue()); // CORREÇÃO AQUI: Usa o valor do DB
            stmt.setBoolean(9, obj.isBaixoEstoque());

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Estoque consultar(Long idEstoque) {
        this.conn.conectar();
        String query = "SELECT * FROM estoque WHERE idEstoque = ?";

        Estoque obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
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
                    obj.setStatusEstoque(NivelEstoque.fromDbValue(statusEstoqueStr)); // CORREÇÃO AQUI: Converte do valor do DB
                }
                obj.setBaixoEstoque(retorno.getBoolean("alertaEstoqueBaixo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public Estoque consultarPorCodigoDeBarras(String codigoDeBarrasApenasNumeros) {
        this.conn.conectar();
        String query = "SELECT * FROM estoque WHERE codigoDeBarras = ?";

        Estoque obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, codigoDeBarrasApenasNumeros);
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                obj = new Estoque();
                obj.setIdEstoque(retorno.getLong("idEstoque"));
                obj.setCodBarras(new CodigoDeBarras(retorno.getString("codigoDeBarras")));
                obj.setCnpjFornecedor(new CNPJ(retorno.getString("cnpjFornecedor")));
                obj.setPrecoVenda(retorno.getBigDecimal("precoVenda"));
                obj.setPrecoCompra(retorno.getBigDecimal("precoCompra"));
                obj.setQuantidade(retorno.getInt("quantidade"));
                Date sqlDate = retorno.getDate("dataDeValidade");
                if (sqlDate != null) {
                    obj.setData_validade(sqlDate.toLocalDate());
                }
                String statusEstoqueStr = retorno.getString("status_estoque");
                if (statusEstoqueStr != null) {
                    obj.setStatusEstoque(NivelEstoque.fromDbValue(statusEstoqueStr)); // CORREÇÃO AQUI: Converte do valor do DB
                }
                obj.setBaixoEstoque(retorno.getBoolean("alertaEstoqueBaixo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar objeto de valor (CodigoDeBarras/CNPJ): " + e.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(Estoque obj) {
        this.conn.conectar();
        String query = "UPDATE estoque SET " +
                "codigoDeBarras = ?, " +
                "cnpjFornecedor = ?, " +
                "precoVenda = ?, " +
                "precoCompra = ?, " +
                "quantidade = ?, " +
                "dataDeValidade = ?, " +
                "status_estoque = ?, " +
                "alertaEstoqueBaixo = ? " +
                "WHERE idEstoque = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarras().getCodigoApenasNumeros());
            stmt.setString(2, obj.getCnpjFornecedor().getCnpjApenasNumeros());
            stmt.setBigDecimal(3, obj.getPrecoVenda());
            stmt.setBigDecimal(4, obj.getPrecoCompra());
            stmt.setInt(5, obj.getQuantidade());

            if (obj.getData_validade() != null) {
                stmt.setDate(6, Date.valueOf(obj.getData_validade()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setString(7, obj.getStatusEstoque().getDbValue()); // CORREÇÃO AQUI: Usa o valor do DB
            stmt.setBoolean(8, obj.isBaixoEstoque());
            stmt.setLong(9, obj.getIdEstoque());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(Long idEstoque) {
        this.conn.conectar();
        String query = "DELETE FROM estoque WHERE idEstoque = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idEstoque);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}