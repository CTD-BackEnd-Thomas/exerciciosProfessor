package com.dh.animal;

import com.dh.animal.jdbc.ConnectionJDBC;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Animal; " +
            "CREATE TABLE Animal " +
            "(" +
            " id INT PRIMARY KEY," +
            " nome VARCHAR(100) NOT NULL, " +
            " tipo VARCHAR(100) NOT NULL" +
            ")";

    private static final String sqlInsertValue = "INSERT INTO Animal  (id, nome, tipo) VALUES(1, 'Buchecha', 'Cachorro');";
    private static final String sqlInsertValue1 = "INSERT INTO Animal  (id, nome, tipo) VALUES(2, 'Felix', 'Gato');";
    private static final String sqlInsertValue2 = "INSERT INTO Animal  (id, nome, tipo) VALUES(3, 'Dumbo', 'Elefante');";
    private static final String sqlInsertValue3 = "INSERT INTO Animal  (id, nome, tipo) VALUES(4, 'Bidu', 'Cachorro');";
    private static final String sqlInsertValue4 = "INSERT INTO Animal  (id, nome, tipo) VALUES(5, 'Pé de pano', 'Cavalo');";

    private  static final String sqlQuery = "SELECT * FROM Animal";

    private static final String sqlDelete = "DELETE FROM Animal where id = 4;";



    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try{
            connection = ConnectionJDBC.getConnection();
            logger.info("Conexão aberta");

            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);
            logger.info("Banco Animal criado");
            statement.execute(sqlInsertValue);
            logger.info("Animal inserido");
            statement.execute(sqlInsertValue1);
            logger.info("Animal inserido");
            statement.execute(sqlInsertValue2);
            logger.info("Animal inserido");
            statement.execute(sqlInsertValue3);
            logger.info("Animal inserido");
            statement.execute(sqlInsertValue4);
            logger.info("Animal inserido");

            logger.info("Imprimindo os 5 animais");


            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                System.out.println("id :"+resultSet.getInt(1)
                        +"; nome: "+resultSet.getNString(2)
                        +"; tipo: "+resultSet.getString(3));
            }

            statement.execute(sqlDelete);
            logger.info("Animal Deletado");


            logger.info("Imprimindo os 4 animais");

            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                System.out.println("id :"+resultSet.getInt(1)
                        +"; nome: "+resultSet.getNString(2)
                        +"; tipo: "+resultSet.getString(3));
            }


        }catch (Exception e){
            logger.error("Erro de conexão");
            e.printStackTrace();
        }finally {
            if (connection == null){
                return;
            }

            connection.close();
            logger.info("Conexão fechada");
        }
    }
}
