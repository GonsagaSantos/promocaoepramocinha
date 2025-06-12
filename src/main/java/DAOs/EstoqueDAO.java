package DAOs;

import Model.Estoque;
import Enum.NivelEstoque; // Importação adicionada para NivelEstoque

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Importação para java.sql.Date, para conversão de LocalDate
import java.time.ZoneId; // Para auxiliar na conversão de LocalDate

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
                "status_estoque," + // Adicionei status_estoque
                "baixoEstoque)" +   // Adicionei baixoEstoque, se for parte da tabela
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 9 placeholders para 9 colunas

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, obj.getIdEstoque());
            stmt.setString(2, obj.getCodBarras());
            stmt.setString(3, obj.getCnpjFornecedor());
            stmt.setBigDecimal(4, obj.getPrecoVenda());
            stmt.setBigDecimal(5, obj.getPrecoCompra()); // Corrigido para getPrecoCompra
            stmt.setInt(6, obj.getQuantidade());

            // Conversão de LocalDate para java.sql.Date
            if (obj.getData_validade() != null) {
                stmt.setDate(7, Date.valueOf(obj.getData_validade()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE); // Define como NULL se a data for nula
            }

            stmt.setString(8, obj.getStatusEstoque().name()); // Definindo o nome do enum como String
            stmt.setBoolean(9, obj.isBaixoEstoque()); // Definindo o boolean

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    // Método consultar corrigido para retornar Estoque e consultar a tabela 'estoque'
    public Estoque consultar(Long idEstoque) { // Adicionei parâmetro para consulta específica
        this.conn.conectar();
        String query = "SELECT * FROM estoque WHERE idEstoque = ?"; // Query para a tabela estoque

        Estoque obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setLong(1, idEstoque); // Define o ID para a consulta
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                obj = new Estoque();
                obj.setIdEstoque(retorno.getLong("idEstoque"));
                obj.setCodBarras(retorno.getString("codigoDeBarras"));
                obj.setCnpjFornecedor(retorno.getString("cnpjFornecedor"));
                obj.setPrecoVenda(retorno.getBigDecimal("precoVenda"));
                obj.setPrecoCompra(retorno.getBigDecimal("precoCompra"));
                obj.setQuantidade(retorno.getInt("quantidade"));

                // Conversão de java.sql.Date para LocalDate
                Date sqlDate = retorno.getDate("dataDeValidade");
                if (sqlDate != null) {
                    obj.setData_validade(sqlDate.toLocalDate());
                }

                // Conversão de String para NivelEstoque enum
                String statusEstoqueStr = retorno.getString("status_estoque");
                if (statusEstoqueStr != null) {
                    obj.setStatusEstoque(NivelEstoque.valueOf(statusEstoqueStr));
                }
                obj.setBaixoEstoque(retorno.getBoolean("baixoEstoque"));
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    // Você pode adicionar outros métodos como 'atualizar' e 'deletar' conforme sua necessidade.
    // public void atualizar(Estoque obj) { ... }
    // public void deletar(Long idEstoque) { ... }
}