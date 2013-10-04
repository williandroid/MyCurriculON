package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.Curriculo;
import br.auadeottoni.mycurriculon.modelo.Formacao;
import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FormacaoDAOImp implements GenericDAO<Formacao> {

    private Connection connection;

    public FormacaoDAOImp() throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Formacao formacao) {

        String dataConclusao = formacao.getConclusao() == null ? null
                : new SimpleDateFormat("").format(
                formacao.getConclusao());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO formacao (id_curriculo, curso, local, conclusao) "
                    + "VALUES (?, ?, ?, ?)");

            statement.setInt(1, formacao.getCurriculo().getIdCurriculo());
            statement.setString(2, formacao.getCurso());
            statement.setString(3, formacao.getLocal());
            statement.setString(4, dataConclusao);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar Formação.");
        }
    }

    @Override
    public void altera(Formacao formacao) {

        String dataConclusao = formacao.getConclusao() == null ? null
                : new SimpleDateFormat("").format(
                formacao.getConclusao());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE formacao SET id_curriculo = ?, "
                    + "curso = ?, local = ?, "
                    + "conclusao = ? "
                    + "WHERE id_formacao = ?");

            statement.setInt(1, formacao.getCurriculo().getIdCurriculo());
            statement.setString(2, formacao.getCurso());
            statement.setString(3, formacao.getLocal());
            statement.setString(4, dataConclusao);
            statement.setInt(5, formacao.getIdFormacao());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar Formação.");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM formacao "
                    + "WHERE id_formacao = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover Formação.");
        }
    }

    @Override
    public Formacao listaPorID(Object iD) {
        try {
            Formacao formacao = null;
            Curriculo curriculo = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, curso, local, conclusao "
                    + "FROM formacao "
                    + "WHERE id_formacao = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                formacao = new Formacao();
                curriculo = new Curriculo();
                formacao.setIdFormacao((Integer) iD);
                
                curriculo.setIdCurriculo(resultSet.getInt(1));
                
                formacao.setCurriculo(curriculo);
                formacao.setCurso(resultSet.getString(2));
                formacao.setLocal(resultSet.getString(3));
                formacao.setConclusao(resultSet.getDate(4));
            }

            resultSet.close();
            statement.close();

            return formacao;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar Formação.");
        }
    }

    @Override
    public List<Formacao> listaTudo() {
        try {
            List<Formacao> formacaos = new ArrayList<Formacao>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, curso, local, conclusao "
                    + "FROM formacao "
                    + "ORDER BY id_formacao");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                Formacao formacao = new Formacao();
                Curriculo curriculo = new Curriculo();
                
                curriculo.setIdCurriculo(resultSet.getInt(1));

                formacao.setCurriculo(curriculo);
                formacao.setCurso(resultSet.getString(2));
                formacao.setLocal(resultSet.getString(3));
                formacao.setConclusao(resultSet.getDate(4));

                formacaos.add(formacao);
            }

            resultSet.close();
            statement.close();

            return formacaos;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar Formações.");
        }
    }
}