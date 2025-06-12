package DAOs;

import Model.Funcionario;
import Services.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Enum.*;

public class FuncionarioDAO {
    private final ConexaoSQLite conn = new ConexaoSQLite();

    public void inserir(Funcionario obj) {
        this.conn.conectar();
        String query = "INSERT INTO funcionarios(cpf, nome, cargo, nivelDePermissao) VALUES(?, ?, ?, ?)";

        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getCpf().getCpfSomenteNumeros());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getCargo().name());
            stmt.setString(4, obj.getPermissao().name());

            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public Funcionario consultarPorCpf(String cpfNumeros) {
        this.conn.conectar();
        String query = "SELECT * FROM funcionarios WHERE cpf = ?";

        Funcionario obj = null;
        try(PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, cpfNumeros);

            try(ResultSet retorno = stmt.executeQuery()) {
                if (retorno.next()) {
                    obj = new Funcionario();

                    CPF cpf = new CPF(retorno.getString("cpf"));
                    obj.setCpf(cpf);

                    obj.setNome(retorno.getString("nome"));

                    String cargoString = retorno.getString("cargo");
                    obj.setCargo(Cargos.valueOf(cargoString));

                    String permissaoString = retorno.getString("nivelDePermissao");
                    obj.setPermissao(Permissoes.valueOf(permissaoString));
                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        } finally {
            this.conn.desconectar();
        }
        return obj;
    }

    public void alterar(Funcionario obj) {
        this.conn.conectar();
        String query = "UPDATE funcionarios SET " +
                "nome = ?, " +
                "cargo = ?, " +
                "nivelDePermissao = ? " +
                "WHERE cpf = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCargo().name());
            stmt.setString(3, obj.getPermissao().name());
            stmt.setString(4, obj.getCpf().getCpfSomenteNumeros());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }

    public void excluir(String cpfFuncionario) {
        this.conn.conectar();
        String query = "DELETE FROM funcionarios WHERE cpf = ?";

        try (PreparedStatement stmt = this.conn.preparedStatement(query)) {
            stmt.setString(1, cpfFuncionario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn.desconectar();
        }
    }
}