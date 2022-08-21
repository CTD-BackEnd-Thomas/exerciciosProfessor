package com.dh.clinica.service;

import dao.impl.EnderecoDaoH2;
import dao.impl.PacienteDaoH2;
import model.Endereco;
import model.Paciente;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import service.EnderecoService;
import service.PacienteService;

import java.util.Date;
import java.util.List;

public class PacienteServiceTest {
    private static PacienteService pacienteService = new PacienteService(new PacienteDaoH2(new EnderecoDaoH2()));
    private EnderecoService enderecoService = new EnderecoService(new EnderecoDaoH2());

    @BeforeEach
    void carregarDataSet() {
        Endereco endereco = new Endereco("Av. Avenida Eudorado", "444", "São Paulo", "Centro");
        Paciente p = pacienteService.salvar(new Paciente("Carlos", "Alberto", "88888888", new Date(), endereco));
        Endereco endereco1 = new Endereco("Av. Avenida do Forte", "333", "Porto Alegre", "Santana");
        Paciente p1 = pacienteService.salvar(new Paciente("Marcela", "Moura", "99999999", new Date(), endereco1));
    }

    @Test
    public void cadastrarEBuscarPacienteTest() {
        Endereco endereco = new Endereco("Av. São João", "123", "Rio de Janeiro", "Copacabana");
        Paciente p = pacienteService.salvar(new Paciente("Marcio", "Oliveira", "12345678", new Date(), endereco));
        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void excluirPacienteTest() {
        pacienteService.excluir(3);
        Assert.assertTrue(pacienteService.buscar(3).isEmpty());

    }

    @Test
    public void trazerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }


}
