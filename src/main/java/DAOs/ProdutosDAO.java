package DAOs;

import Model.Produto;
import Services.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutosDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Produto obj) {
        this.conn.conectar();
        String query = "INSERT INTO produtos_cadastrados(codigoDeBarras, nome, categoria, marca) VALUES(?, ?, ?, ?)";

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarras().getCodigoFormatado());
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

    public Produto consultar() {
        this.conn.conectar();
        java.lang.String query = "SELECT * FROM produtos_cadastrados";

        Produto obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            ResultSet retorno = stmt.executeQuery();

            if (retorno.next()) {
                obj = new Produto();
                CodigoDeBarras codigoDeBarrasObj = new CodigoDeBarras(retorno.getString("codigoDeBarras"));
                obj.setCodBarras(codigoDeBarrasObj);
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