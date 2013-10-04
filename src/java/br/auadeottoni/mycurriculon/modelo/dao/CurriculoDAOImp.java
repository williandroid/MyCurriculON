package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.Curriculo;
import br.auadeottoni.mycurriculon.modelo.Usuario;
import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CurriculoDAOImp implements GenericDAO<Curriculo> {

    private Connection connection;

    public CurriculoDAOImp() throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Curriculo curriculo) {

        String dataDataCriacao = curriculo.getDataCriacao() == null ? null
                : new SimpleDateFormat("").format(
                curriculo.getDataCriacao());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO curriculo (id_usuario, data_criacao) "
                    + "VALUES (?, ?)");

            statement.setInt(1, curriculo.getUsuario().getIdUsuario());
            statement.setString(2, dataDataCriacao);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar Currículo.");
        }
    }

    @Override
    public void altera(Curriculo curriculo) {

        String dataDataCriacao = curriculo.getDataCriacao() == null ? null
                : new SimpleDateFormat("").format(
                curriculo.getDataCriacao());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE curriculo SET id_usuario = ?, "
                    + "data_criacao = ? "
                    + "WHERE id_curriculo = ?");

            statement.setInt(1, curriculo.getUsuario().getIdUsuario());
            statement.setString(2, dataDataCriacao);
            statement.setInt(3, curriculo.getIdCurriculo());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar Currículo.");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM curriculo "
                    + "WHERE id_curriculo = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover Currículo.");
        }
    } 

    @Override
    public Curriculo listaPorID(Object iD) {
        try {
            Curriculo curriculo = null;
            Usuario usuario = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_usuario, data_criacao "
                    + "FROM curriculo "
                    + "WHERE id_curriculo = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                curriculo = new Curriculo();
                usuario = new Usuario();
                curriculo.setIdCurriculo((Integer) iD);
                usuario.setIdUsuario(resultSet.getInt(1));
                curriculo.setUsuario(usuario);
                curriculo.setDataCriacao(resultSet.getDate(2));
            }

            resultSet.close();
            statement.close();

            return curriculo;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar Currículo.");
        }
    }

    @Override
    public List<Curriculo> listaTudo() {
        try {
            List<Curriculo> curriculos = new ArrayList<Curriculo>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_usuario, data_criacao "
                    + "FROM curriculo "
                    + "ORDER BY id_curriculo");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                Curriculo curriculo = new Curriculo();
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultSet.getInt(1));
                curriculo.setUsuario(usuario);
                curriculo.setDataCriacao(resultSet.getDate(2));

                curriculos.add(curriculo);
            }

            resultSet.close();
            statement.close();

            return curriculos;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar Currículos.");
        }
    }
}