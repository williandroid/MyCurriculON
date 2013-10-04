package br.auadeottoni.mycurriculon.modelo.dao;


import br.auadeottoni.mycurriculon.modelo.Curriculo;
import br.auadeottoni.mycurriculon.modelo.InfoExtra;
import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InfoExtraDAOImp implements GenericDAO<InfoExtra> {


    private Connection connection;

    public InfoExtraDAOImp() throws ClassNotFoundException, SQLException {

        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(InfoExtra infoExtra) {

        String dataData = infoExtra.getData() == null ? null
                : new SimpleDateFormat("").format(
                infoExtra.getData());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO info_extra (id_curriculo, descricao, data) "
                    + "VALUES (?, ?, ?)");

            statement.setInt(1, infoExtra.getCurriculo().getIdCurriculo());
            statement.setString(2, infoExtra.getDescricao());
            statement.setString(3, dataData);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao inserir InfoExtra");
        }
    }

    @Override
    public void altera(InfoExtra infoExtra) {

        String dataData = infoExtra.getData() == null ? null
                : new SimpleDateFormat("").format(
                infoExtra.getData());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE info_extra SET id_curriculo = ?, "
                    + "descricao = ?, data = ? "
                    + "WHERE id_info_extra = ?");

            statement.setInt(1, infoExtra.getCurriculo().getIdCurriculo());
            statement.setString(2, infoExtra.getDescricao());
            statement.setString(3, dataData);
            statement.setInt(4, infoExtra.getIdInfoExtra());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            System.out.println("Erro ao executar comando no banco de dados!\n"
                    + sQLException.getMessage());
            throw new RuntimeException("Falha ao alterar InfoExtra");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM info_extra "
                    + "WHERE id_info_extra = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover InfoExtra");
        }
    }

    @Override
    public InfoExtra listaPorID(Object iD) {
        try {
            InfoExtra infoExtra = null;
            Curriculo curriculo = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, descricao, data "
                    + "FROM info_extra "
                    + "WHERE id_info_extra = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                infoExtra = new InfoExtra();
                curriculo = new Curriculo();
                infoExtra.setIdInfoExtra((Integer) iD);
                
                curriculo.setIdCurriculo(resultSet.getInt(1));
                
                infoExtra.setCurriculo(curriculo);
                infoExtra.setDescricao(resultSet.getString(2));
                infoExtra.setData(resultSet.getDate(3));
            }

            resultSet.close();
            statement.close();

            return infoExtra;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar InfoExtra");
        }
    }

    @Override
    public List<InfoExtra> listaTudo() {
        try {
            List<InfoExtra> infoExtras = new ArrayList<InfoExtra>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, descricao, data "
                    + "FROM info_extra "
                    + "ORDER BY id_info_extra");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                InfoExtra infoExtra = new InfoExtra();
                Curriculo curriculo = new Curriculo();
                curriculo.setIdCurriculo(resultSet.getInt(1));

                infoExtra.setCurriculo(curriculo);
                infoExtra.setDescricao(resultSet.getString(2));
                infoExtra.setData(resultSet.getDate(3));

                infoExtras.add(infoExtra);
            }

            resultSet.close();
            statement.close();

            return infoExtras;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar InfoExtras");
        }
    }
}
