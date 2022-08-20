package com.dh.conta;

import com.dh.conta.jdbc.ConnectionJDBC;
import com.dh.conta.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String SQLCREATE = "DROP TABLE IF EXISTS Conta;" +
            " CREATE TABLE Conta " +
            "(" +
            " id INT PRIMARY KEY," +
            " nome VARCHAR(100) NOT NULL," +
            " numeroConta VARCHAR(100) NOT NULL," +
            " saldo numeric(15,2) " +
            ");";

    private static final String SQLINSERT = "INSERT INTO Conta (id,nome,numeroConta,saldo) VALUES (?,?,?,?);";
    private static final String SQLUPDATE = "UPDATE Conta SET saldo = ? WHERE id = ?;";

    public static void main(String[] args) throws SQLException {
        Conta conta = new Conta(1,"Daniel","xptozzz",20);
        Connection connection = null;

        try{
            connection = ConnectionJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLCREATE);

            PreparedStatement preparedStatementInsert = connection.prepareStatement(SQLINSERT);

            preparedStatementInsert.setInt(1,conta.getId());
            preparedStatementInsert.setString(2,conta.getNome());
            preparedStatementInsert.setString(3,conta.getNumConta());
            preparedStatementInsert.setDouble(4,conta.getSaldo());
            preparedStatementInsert.execute();

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(SQLUPDATE);
            preparedStatementUpdate.setDouble(1,conta.aumentarSaldo(10));
            preparedStatementUpdate.setInt(2,conta.getId());
            preparedStatementUpdate.execute();

            connection.setAutoCommit(false);


            preparedStatementUpdate.setDouble(1 , conta.aumentarSaldo(15));
            preparedStatementUpdate.setInt(2, conta.getId());
            preparedStatementUpdate.execute();

            preparedStatementUpdate.setDouble(1 , conta.aumentarSaldo(15));
            preparedStatementUpdate.setInt(2, conta.getId());
            preparedStatementUpdate.execute();

            connection.commit();

            connection.setAutoCommit(true);

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.close();
        }
    }
}
