package DAOs;

import java.sql.*;

public class ConexaoSQLite {
    private Connection conexao;

    public Boolean conectar() {
        try {
            this.conexao = DriverManager.getConnection(
                    "jdbc:sqlite:.\\database\\database"
            );
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean desconectar() {
        try {
            if(!this.conexao.isClosed()) {
                this.conexao.close();
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Statement criarStatement() {
        try {
            return this.conexao.createStatement();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Connection getConexao() { return this.conexao; }

    public PreparedStatement preparedStatement(String sql) {
        try {
            return this.conexao.prepareStatement(sql);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}