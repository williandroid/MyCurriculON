package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.Curriculo;
import br.auadeottoni.mycurriculon.modelo.Objetivo;
import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjetivoDAOImp implements GenericDAO<Objetivo> {

    private Connection connection;

    public ObjetivoDAOImp() throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Objetivo objetivo) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO objetivo (id_curriculo, descricao) "
                    + "VALUES (?, ?)");

            statement.setInt(1, objetivo.getCurriculo().getIdCurriculo());
            statement.setString(2, objetivo.getDescricao());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao inserir objetivo");
        }
    }

    @Override
    public void altera(Objetivo objetivo) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE objetivo SET id_curriculo = ?, "
                    + "descricao = ? "
                    + "WHERE id_objetivo = ?");

            statement.setInt(1, objetivo.getCurriculo().getIdCurriculo());
            statement.setString(2, objetivo.getDescricao());
            statement.setInt(3, objetivo.getIdObjetivo());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            System.out.println("Erro ao executar comando no banco de dados!\n"
                    + sQLException.getMessage());
            throw new RuntimeException("Falha ao alterar objetivo");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM objetivo "
                    + "WHERE id_objetivo = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover objetivo");
        }
    }

    @Override
    public Objetivo listaPorID(Object iD) {
        try {
            Objetivo objetivo = null;
            Curriculo curriculo = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, descricao "
                    + "FROM objetivo "
                    + "WHERE id_objetivo = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                objetivo = new Objetivo();
                curriculo = new Curriculo();
                objetivo.setIdObjetivo((Integer) iD);
                
                curriculo.setIdCurriculo(resultSet.getInt(1));
                
                objetivo.setCurriculo(curriculo);
                objetivo.setDescricao(resultSet.getString(2));
            }

            resultSet.close();
            statement.close();

            return objetivo;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar objetivo");
        }
    }

    @Override
    public List<Objetivo> listaTudo() {
        try {
            List<Objetivo> objetivos = new ArrayList<Objetivo>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, descricao "
                    + "FROM objetivo "
                    + "ORDER BY id_objetivo");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                Objetivo objetivo = new Objetivo();
                Curriculo curriculo = new Curriculo();

                curriculo.setIdCurriculo(resultSet.getInt(1));
                
                objetivo.setCurriculo(curriculo);
                objetivo.setDescricao(resultSet.getString(2));

                
                objetivos.add(objetivo);
            }

            resultSet.close();
            statement.close();

            return objetivos;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar objetivos");
        } 
    }

}
