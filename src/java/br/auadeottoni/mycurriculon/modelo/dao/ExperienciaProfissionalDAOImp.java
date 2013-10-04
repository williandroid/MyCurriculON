package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.Curriculo;
import br.auadeottoni.mycurriculon.modelo.ExperienciaProfissional;
import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExperienciaProfissionalDAOImp implements GenericDAO<ExperienciaProfissional> {

    private Connection connection;

    public ExperienciaProfissionalDAOImp() throws ClassNotFoundException, SQLException {
        connection = ConnectionFactory.getConnection();
    } 

    @Override
    public void adiciona(ExperienciaProfissional experienciaProfissional) {

        String dataAnoInicio = experienciaProfissional.getAnoInicio() == null ? null
                : new SimpleDateFormat("").format(
                experienciaProfissional.getAnoInicio());

        String dataAnoFinal = experienciaProfissional.getAnoFinal() == null ? null
                : new SimpleDateFormat("").format(
                experienciaProfissional.getAnoFinal());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO experiencia_profissional (id_curriculo, ano_inicio, ano_final, cargo, princ_atividades, empresa) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            
            statement.setInt(1, experienciaProfissional.getCurriculo().getIdCurriculo());
            statement.setString(2, dataAnoInicio);
            statement.setString(3, dataAnoFinal);
            statement.setString(4, experienciaProfissional.getCargo());
            statement.setString(5, experienciaProfissional.getPrincAtividades());
            statement.setString(6, experienciaProfissional.getEmpresa());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar Experiência Profissional.");
        } 
    } 

    @Override
    public void altera(ExperienciaProfissional experienciaProfissional) {

        String dataAnoInicio = experienciaProfissional.getAnoInicio() == null ? null
                : new SimpleDateFormat("").format(
                experienciaProfissional.getAnoInicio());

        String dataAnoFinal = experienciaProfissional.getAnoFinal() == null ? null
                : new SimpleDateFormat("").format(
                experienciaProfissional.getAnoFinal());

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE experiencia_profissional SET id_curriculo = ?, "
                    + "ano_inicio = ?, ano_final = ?, "
                    + "cargo = ?, princ_atividades = ?, "
                    + "empresa = ? "
                    + "WHERE id_exp_profissonal = ?");

            statement.setInt(1, experienciaProfissional.getCurriculo().getIdCurriculo());
            statement.setString(2, dataAnoInicio);
            statement.setString(3, dataAnoFinal);
            statement.setString(4, experienciaProfissional.getCargo());
            statement.setString(5, experienciaProfissional.getPrincAtividades());
            statement.setString(6, experienciaProfissional.getEmpresa());
            statement.setInt(7, experienciaProfissional.getIdExpProfissonal());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar Experiência Profissional");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM experiencia_profissional "
                    + "WHERE id_exp_profissonal = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover Experiência Profissional.");
        }
    }

    @Override
    public ExperienciaProfissional listaPorID(Object iD) {
        try {
            ExperienciaProfissional experienciaProfissional = null;
            Curriculo curriculo = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, ano_inicio, ano_final, cargo, princ_atividades, empresa "
                    + "FROM experiencia_profissional "
                    + "WHERE id_exp_profissonal = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                experienciaProfissional = new ExperienciaProfissional();
                curriculo = new Curriculo();
                
                experienciaProfissional.setIdExpProfissonal((Integer) iD);
                
                curriculo.setIdCurriculo(resultSet.getInt(1));
                
                experienciaProfissional.setCurriculo(curriculo);
                experienciaProfissional.setAnoInicio(resultSet.getDate(2));
                experienciaProfissional.setAnoFinal(resultSet.getDate(3));
                experienciaProfissional.setCargo(resultSet.getString(4));
                experienciaProfissional.setPrincAtividades(resultSet.getString(5));
                experienciaProfissional.setEmpresa(resultSet.getString(6));
            }

            resultSet.close();
            statement.close();

            return experienciaProfissional;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar Experiência Profissional.");
        }
    }
    @Override
    public List<ExperienciaProfissional> listaTudo() {
        try {
            List<ExperienciaProfissional> experienciaProfissionals = new ArrayList<ExperienciaProfissional>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_curriculo, ano_inicio, ano_final, cargo, princ_atividades, empresa "
                    + "FROM experiencia_profissional "
                    + "ORDER BY id_exp_profissonal");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                ExperienciaProfissional experienciaProfissional = new ExperienciaProfissional();
                Curriculo curriculo = new Curriculo();
                
                curriculo.setIdCurriculo(resultSet.getInt(1));

                experienciaProfissional.setCurriculo(curriculo);
                experienciaProfissional.setAnoInicio(resultSet.getDate(2));
                experienciaProfissional.setAnoFinal(resultSet.getDate(3));
                experienciaProfissional.setCargo(resultSet.getString(4));
                experienciaProfissional.setPrincAtividades(resultSet.getString(5));
                experienciaProfissional.setEmpresa(resultSet.getString(6));

                experienciaProfissionals.add(experienciaProfissional);
            }

            resultSet.close();
            statement.close();

            return experienciaProfissionals;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar Experiências Profissionais.");
        }
    }
}