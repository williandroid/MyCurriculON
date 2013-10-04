
package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import br.auadeottoni.mycurriculon.modelo.Qualificacoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QualificacoesDAOImp implements GenericDAO<Qualificacoes> {

    private Connection connection;

    public QualificacoesDAOImp() throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Qualificacoes qualificacoes) {

        String dataData = qualificacoes.getData() == null ? null
                : new SimpleDateFormat("").format(
                qualificacoes.getData());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO qualificacoes (id_curriculo, data, descricao) "
                    + "VALUES (?, ?, ?)");

            statement.setInt(1, qualificacoes.getIdCurriculo());
            statement.setString(2, dataData);
            statement.setString(3, qualificacoes.getDescricao());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar qualificação.");
        }
    }

    @Override
    public void altera(Qualificacoes qualificacoes) {

        String dataData = qualificacoes.getData() == null ? null
                : new SimpleDateFormat("").format(
                qualificacoes.getData());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE qualificacoes SET id_curriculo = ?, "
                    + "data = ?, descricao = ? "
                    + "WHERE id_qualificacoes = ?");

            statement.setInt(1, qualificacoes.getIdCurriculo());
            statement.setString(2, dataData);
            statement.setString(3, qualificacoes.getDescricao());
            statement.setInt(4, qualificacoes.getIdQualificacoes());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar a alterar qualificação.");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM qualificacoes "
                    + "WHERE id_qualificacoes = ?");
            statement.setObject(1, iD);
            
            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover qualificação.");
        }
    }

    @Override
    public Qualificacoes listaPorID(Object iD) {
        try {
            Qualificacoes qualificacoes = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, data, descricao "
                    + "FROM qualificacoes "
                    + "WHERE id_qualificacoes = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                qualificacoes = new Qualificacoes();
                qualificacoes.setIdQualificacoes((Integer) iD);
                qualificacoes.setIdCurriculo(resultSet.getInt(1));
                qualificacoes.setData(resultSet.getDate(2));
                qualificacoes.setDescricao(resultSet.getString(3));
            }

            resultSet.close();
            statement.close();

            return qualificacoes;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar qualificações por id.");
        }
    }

    
    @Override
    public List<Qualificacoes> listaTudo() {
        try {
            List<Qualificacoes> qualificacoess = new ArrayList<Qualificacoes>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, data, descricao "
                    + "FROM qualificacoes "
                    + "ORDER BY id_qualificacoes");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Qualificacoes qualificacoes = new Qualificacoes();

                qualificacoes.setIdCurriculo(resultSet.getInt(1));
                qualificacoes.setData(resultSet.getDate(2));
                qualificacoes.setDescricao(resultSet.getString(3));

                qualificacoess.add(qualificacoes);
            } 

            resultSet.close();
            statement.close();

            return qualificacoess;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar qualificações.");
        }
    }

}
