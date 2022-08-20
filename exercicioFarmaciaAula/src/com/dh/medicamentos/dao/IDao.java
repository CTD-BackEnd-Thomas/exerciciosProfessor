package com.dh.medicamentos.dao;

import java.sql.SQLException;

public interface IDao<T>{
    public T salvar(T t) throws SQLException;
}
