package DAOs;

import Model.String;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutosDAO {

    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(String obj) {
        this.conn.conectar();
        java.lang.String query = "INSERT INTO produtos_cadastrados(codigoDeBarras, nome, categoria, marca) VALUES(?, ?, ?, ?)";

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarras());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getCategoria());
            stmt.setString(4, obj.getMarca());

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public String consultar() {
        this.conn.conectar();
        java.lang.String query = "SELECT * FROM produtos_cadastrados";

        String obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                obj = new String();
                obj.setCodBarras(retorno.getString("codigoDeBarras"));
                obj.setNome(retorno.getString("nome"));
                obj.setCategoria(retorno.getString("categoria"));
                obj.setMarca(retorno.getString("marca"));
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }
}