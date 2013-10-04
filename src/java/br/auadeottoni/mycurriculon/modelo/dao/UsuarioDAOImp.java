package br.auadeottoni.mycurriculon.modelo.dao;

import br.auadeottoni.mycurriculon.modelo.util.ConnectionFactory;
import br.auadeottoni.mycurriculon.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements GenericDAO<Usuario> {

    private Connection connection;

    public UsuarioDAOImp() throws ClassNotFoundException, SQLException {
       connection = ConnectionFactory.getConnection();
    }

    @Override
    public void adiciona(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO usuario (id_usuario, empresa, nome_usuario, nacionalidade, est_civil, idade, logradouro, numero, bairro, estado, telefone, celular, email, user, senha) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, usuario.getIdUsuario());
            statement.setInt(2, usuario.getEmpresa());
            statement.setString(3, usuario.getNomeUsuario());
            statement.setString(4, usuario.getNacionalidade());
            statement.setString(5, usuario.getEstCivil());
            statement.setInt(6, usuario.getIdade());
            statement.setString(7, usuario.getLogradouro());
            statement.setInt(8, usuario.getNumero());
            statement.setString(9, usuario.getBairro());
            statement.setString(10, usuario.getEstado());
            statement.setString(11, usuario.getTelefone());
            statement.setString(12, usuario.getCelular());
            statement.setString(13, usuario.getEmail());
            statement.setString(14, usuario.getUser());
            statement.setString(15, usuario.getSenha());

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao adicionar usuário.");
        }
    }

    @Override
    public void altera(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE usuario SET empresa = ?, "
                    + "nome_usuario = ?, nacionalidade = ?, "
                    + "est_civil = ?, idade = ?, "
                    + "logradouro = ?, numero = ?, "
                    + "bairro = ?, estado = ?, "
                    + "telefone = ?, celular = ?, "
                    + "email = ?, user = ?, "
                    + "senha = ? "
                    + "WHERE id_usuario = ?");

            statement.setInt(1, usuario.getEmpresa());
            statement.setString(2, usuario.getNomeUsuario());
            statement.setString(3, usuario.getNacionalidade());
            statement.setString(4, usuario.getEstCivil());
            statement.setInt(5, usuario.getIdade());
            statement.setString(6, usuario.getLogradouro());
            statement.setInt(7, usuario.getNumero());
            statement.setString(8, usuario.getBairro());
            statement.setString(9, usuario.getEstado());
            statement.setString(10, usuario.getTelefone());
            statement.setString(11, usuario.getCelular());
            statement.setString(12, usuario.getEmail());
            statement.setString(13, usuario.getUser());
            statement.setString(14, usuario.getSenha());
            statement.setInt(15, usuario.getIdUsuario());

 
            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao alterar usuário.");
        }
    } 

    @Override
    public void remove(Object iD) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM usuario "
                    + "WHERE id_usuario = ?");

            statement.setObject(1, iD);

            statement.execute();
            statement.close();
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao remover o usuário.");
        }
    }

    @Override
    public Usuario listaPorID(Object iD) {
        try {
            Usuario usuario = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT empresa, nome_usuario, nacionalidade, est_civil, idade, logradouro, numero, bairro, estado, telefone, celular, email, user, senha "
                    + "FROM usuario "
                    + "WHERE id_usuario = ?");

            statement.setObject(1, iD);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                usuario = new Usuario();
                usuario.setIdUsuario((Integer) iD);
                usuario.setEmpresa(resultSet.getInt(1));
                usuario.setNomeUsuario(resultSet.getString(2));
                usuario.setNacionalidade(resultSet.getString(3));
                usuario.setEstCivil(resultSet.getString(4));
                usuario.setIdade(resultSet.getInt(5));
                usuario.setLogradouro(resultSet.getString(6));
                usuario.setNumero(resultSet.getInt(7));
                usuario.setBairro(resultSet.getString(8));
                usuario.setEstado(resultSet.getString(9));
                usuario.setTelefone(resultSet.getString(10));
                usuario.setCelular(resultSet.getString(11));
                usuario.setEmail(resultSet.getString(12));
                usuario.setUser(resultSet.getString(13));
                usuario.setSenha(resultSet.getString(14));
            }

            resultSet.close();
            statement.close();

            return usuario;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar usuários.");
        }
    }

    @Override
    public List<Usuario> listaTudo() {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT empresa, nome_usuario, nacionalidade, est_civil, idade, logradouro, numero, bairro, estado, telefone, celular, email, user, senha "
                    + "FROM usuario "
                    + "ORDER BY id_usuario");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();

                usuario.setEmpresa(resultSet.getInt(1));
                usuario.setNomeUsuario(resultSet.getString(2));
                usuario.setNacionalidade(resultSet.getString(3));
                usuario.setEstCivil(resultSet.getString(4));
                usuario.setIdade(resultSet.getInt(5));
                usuario.setLogradouro(resultSet.getString(6));
                usuario.setNumero(resultSet.getInt(7));
                usuario.setBairro(resultSet.getString(8));
                usuario.setEstado(resultSet.getString(9));
                usuario.setTelefone(resultSet.getString(10));
                usuario.setCelular(resultSet.getString(11));
                usuario.setEmail(resultSet.getString(12));
                usuario.setUser(resultSet.getString(13));
                usuario.setSenha(resultSet.getString(14));

                usuarios.add(usuario);
            }

            resultSet.close();
            statement.close();

            return usuarios;
        } catch (SQLException sQLException) {
            throw new RuntimeException("Falha ao listar usuários.");
        }
    }

}
