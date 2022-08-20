package com.dh.medicamentos.service;

import com.dh.medicamentos.dao.ConfiguracaoJDBC;
import com.dh.medicamentos.dao.impl.MedicamentoDaoH2;
import com.dh.medicamentos.model.Medicamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MedicamentoServiceTest {

    MedicamentoService medicamentoService;
    @BeforeEach
    void doBefore(){
        medicamentoService = new MedicamentoService(new MedicamentoDaoH2());

    }
    @Test
    public void guardarMedicamento() throws SQLException {
        Medicamento medicamento = new Medicamento(
                "Ibuprofeno", "Aché", 395, 23.0);
        medicamentoService.salvar(medicamento);
        Assertions.assertTrue(medicamento.getId() >= 0);

        Medicamento medicamento2 = new Medicamento(
                "Cetoconazol", "Medley", 550, 26.5);
        medicamentoService.salvar(medicamento2);
        Assertions.assertTrue(medicamento2.getId() >= 0);

    }
}