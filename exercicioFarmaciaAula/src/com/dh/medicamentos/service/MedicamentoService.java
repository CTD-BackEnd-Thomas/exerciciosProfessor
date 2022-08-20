package com.dh.medicamentos.service;

import com.dh.medicamentos.dao.IDao;
import com.dh.medicamentos.dao.impl.MedicamentoDaoH2;
import com.dh.medicamentos.model.Medicamento;

import java.sql.SQLException;

public class MedicamentoService {

    private MedicamentoDaoH2 medicamentoDao;

    public MedicamentoService(MedicamentoDaoH2 medicamentoDao) {

        this.medicamentoDao = medicamentoDao;
    }

    public Medicamento salvar(Medicamento medicamento) throws SQLException {
        return medicamentoDao.salvar(medicamento);

    }
}
