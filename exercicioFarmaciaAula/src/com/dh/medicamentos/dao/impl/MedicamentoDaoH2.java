package com.dh.medicamentos.dao.impl;

import com.dh.medicamentos.dao.ConfiguracaoJDBC;
import com.dh.medicamentos.dao.IDao;
import com.dh.medicamentos.model.Medicamento;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicamentoDaoH2 implements IDao<Medicamento> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger logger = Logger.getLogger(MedicamentoDaoH2.class);

    @Override
    public Medicamento salvar(Medicamento medicamento) throws SQLException {
        logger.info("Registrando medicamento: "+medicamento.getNome());
        configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/medicamento;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
        Connection connection = configuracaoJDBC.getConnection();

        String query = String.format("INSERT INTO Medicamento (nome, laboratorio, quantidade, preco)" +
                        " values('%s','%s','%s','%s')",
                medicamento.getNome(),medicamento.getLaboratorio(),medicamento.getQuantidade(),medicamento.getPreco());

        try{
            Statement statement = connection.createStatement();
            statement.execute(query,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                medicamento.setId(resultSet.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return medicamento;
    }
}
