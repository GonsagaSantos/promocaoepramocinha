package DAOs;

import Model.Produtos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutosDAO {

    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Produtos obj) {
        this.conn.conectar();
        String query = "INSERT INTO produtos_cadastrados(codigoDeBarras, nome, categoria, marca) VALUES(?, ?, ?, ?)";
        //PreparedStatement stmt = this.conn.preparedStatement(query);

        byte var5;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCodBarras());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getCategoria());
            stmt.setString(4, obj.getMarca());

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            var5 = 0;
        } finally {
            this.conn.desconectar();
        }

    }

    public Produtos consultar() {
        this.conn.conectar();
        String query = "SELECT * FROM produtos_cadastrados";

        Produtos var5;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            ResultSet retorno = stmt.executeQuery();

            Produtos obj = new Produtos();
            obj.setCodBarras(retorno.getString("codigoDeBarras"));
            obj.setNome(retorno.getString("nome"));
            obj.setCategoria(retorno.getString("categoria"));
            obj.setMarca(retorno.getString("marca"));

            var5 = obj;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            Produtos obj = null;
            return (Produtos)obj;
        } finally {
            this.conn.desconectar();
        }

        return var5;
    }



}