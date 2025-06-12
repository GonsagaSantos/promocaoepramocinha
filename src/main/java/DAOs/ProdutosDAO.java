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
            stmt.setString(1, obj.getCodBarras().getCodigoApenasNumeros());
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

    public Produto consultar(String codBarras) {
        this.conn.conectar();
        java.lang.String query = "SELECT * FROM produtos_cadastrados WHERE codigoDeBarras = ?";

        Produto obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, codBarras);
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

    public void alterar(Produto obj) {
        this.conn.conectar();
        String query = "UPDATE produtos_cadastrados SET " +
                "nome = ?, " +
                "categoria = ?, " +
                "marca = ? " +
                "WHERE codigoDeBarras = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCategoria());
            stmt.setString(3, obj.getMarca());
            stmt.setString(4, obj.getCodBarras().getCodigoApenasNumeros());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(String codigoDeBarras) {
        this.conn.conectar();
        String query = "DELETE FROM produtos_cadastrados WHERE codigoDeBarras = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, codigoDeBarras);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}