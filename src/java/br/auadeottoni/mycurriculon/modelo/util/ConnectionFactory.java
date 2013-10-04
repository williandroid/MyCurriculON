package br.auadeottoni.mycurriculon.modelo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static final String URL_BANCO = "jdbc:mysql://localhost:3306/db_curriculon";
    public static final String NOME_USUARIO = "root";
    public static final String SENHA = "";
    private static Connection conexao;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (conexao == null) {
            Class.forName("com.mysql.jdbc.driver");
            conexao = DriverManager.getConnection(URL_BANCO, NOME_USUARIO, SENHA);
        }

        return conexao;
    }
}
