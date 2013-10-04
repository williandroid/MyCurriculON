package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import br.auadeottoni.mycurriculon.modelo.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAOImp implements GenericDAO<Empresa> {

    private Connection connection;

    public EmpresaDAOImp() throws ClassNotFoundException, SQLException {

        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Empresa empresa) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO empresa (nome_empresa, logradouro, numero, bairro, estado, telefone, email, user, senha) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, empresa.getNomeEmpresa());
            statement.setString(2, empresa.getLogradouro());
            statement.setInt(3, empresa.getNumero());
            statement.setString(4, empresa.getBairro());
            statement.setString(5, empresa.getEstado());
            statement.setString(6, empresa.getTelefone());
            statement.setString(7, empresa.getEmail());
            statement.setString(8, empresa.getUser());
            statement.setString(9, empresa.getSenha());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar Empresa.");
        }
    }

    @Override
    public void altera(Empresa empresa) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE empresa SET nome_empresa = ?, "
                    + "logradouro = ?, numero = ?, "
                    + "bairro = ?, estado = ?, "
                    + "telefone = ?, email = ?, "
                    + "user = ?, senha = ? "
                    + "WHERE id_empresa = ?");

            statement.setString(1, empresa.getNomeEmpresa());
            statement.setString(2, empresa.getLogradouro());
            statement.setInt(3, empresa.getNumero());
            statement.setString(4, empresa.getBairro());
            statement.setString(5, empresa.getEstado());
            statement.setString(6, empresa.getTelefone());
            statement.setString(7, empresa.getEmail());
            statement.setString(8, empresa.getUser());
            statement.setString(9, empresa.getSenha());
            statement.setInt(10, empresa.getIdEmpresa());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar Empresa.");
        }
    }

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM empresa "
                    + "WHERE id_empresa = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover Empresa.");
        }
    }

    @Override
    public Empresa listaPorID(Object iD) {
        try {
            Empresa empresa = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT nome_empresa, logradouro, numero, bairro, estado, telefone, email, user, senha "
                    + "FROM empresa "
                    + "WHERE id_empresa = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                empresa = new Empresa();
                empresa.setIdEmpresa((Integer) iD);
                empresa.setNomeEmpresa(resultSet.getString(1));
                empresa.setLogradouro(resultSet.getString(2));
                empresa.setNumero(resultSet.getInt(3));
                empresa.setBairro(resultSet.getString(4));
                empresa.setEstado(resultSet.getString(5));
                empresa.setTelefone(resultSet.getString(6));
                empresa.setEmail(resultSet.getString(7));
                empresa.setUser(resultSet.getString(8));
                empresa.setSenha(resultSet.getString(9));
            } 

            resultSet.close();
            statement.close();

            return empresa;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao buscar Empresa.");
        }
    }

    @Override
    public List<Empresa> listaTudo() {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT nome_empresa, logradouro, numero, bairro, estado, telefone, email, user, senha "
                    + "FROM empresa "
                    + "ORDER BY id_empresa");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                Empresa empresa = new Empresa();

                empresa.setNomeEmpresa(resultSet.getString(1));
                empresa.setLogradouro(resultSet.getString(2));
                empresa.setNumero(resultSet.getInt(3));
                empresa.setBairro(resultSet.getString(4));
                empresa.setEstado(resultSet.getString(5));
                empresa.setTelefone(resultSet.getString(6));
                empresa.setEmail(resultSet.getString(7));
                empresa.setUser(resultSet.getString(8));
                empresa.setSenha(resultSet.getString(9));

                empresas.add(empresa);
            }

            resultSet.close();
            statement.close();

            return empresas;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar Empresas.");
        }
    }
}