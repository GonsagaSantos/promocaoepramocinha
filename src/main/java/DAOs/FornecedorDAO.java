package DAOs;

import Model.Fornecedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Fornecedor obj) {
        this.conn.conectar();
        String query = "INSERT INTO fornecedor(" +
                "cnpj," +
                "nome," +
                "endereco," +
                "informacoesDeContato)" +
                "VALUES (?,?,?,?)";
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCnpj());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getContato());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Fornecedor consultar(String cnpj) {
        this.conn.conectar();
        String query = "SELECT * FROM fornecedor WHERE cnpj = ?";
        Fornecedor obj = null;

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, cnpj);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                obj = new Fornecedor();

                obj.setCnpj(resultSet.getString("cnpj"));
                obj.setNome(resultSet.getString("nome"));
                obj.setEndereco(resultSet.getString("endereco"));
                obj.setContato(resultSet.getString("informacoesDeContato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar objeto CNPJ: " + e.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(Fornecedor obj) {
        this.conn.conectar();
        String query = "UPDATE fornecedor SET " +
                "nome = ?, " +
                "endereco = ?, " +
                "informacoesDeContato = ? " +
                "WHERE cnpj = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEndereco());
            stmt.setString(3, obj.getContato());
            stmt.setString(4, obj.getCnpj());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(String cnpjFornecedor) {
        this.conn.conectar();
        String query = "DELETE FROM Fornecedor WHERE cnpj = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, cnpjFornecedor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}
