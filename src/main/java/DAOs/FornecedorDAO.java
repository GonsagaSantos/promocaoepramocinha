package DAOs;

import Model.Fornecedor;
import Services.CNPJ;
import Services.CodigoDeBarras;

import javax.xml.transform.Result;
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
            stmt.setString(1, obj.getCnpj().getCnpjApenasNumeros());
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

    public Fornecedor consultar() {
        this.conn.conectar();
        String query = "SELECT * FROM fornecedor";
        Fornecedor obj = null;
        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                obj = new Fornecedor();
                String cnpjString = resultSet.getString("cnpj");
                CNPJ cnpjObj = new CNPJ(cnpjString);
                obj.setCnpj(cnpjObj);
                obj.setNome(resultSet.getString("nome"));
                obj.setEndereco(resultSet.getString("endereco"));
                obj.setContato(resultSet.getString("informacoesDeContato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }
}
