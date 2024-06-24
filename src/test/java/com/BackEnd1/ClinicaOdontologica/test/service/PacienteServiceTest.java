package com.BackEnd1.ClinicaOdontologica.test.service;

import com.BackEnd1.ClinicaOdontologica.entity.Domicilio;
import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Domicilio domicilio = new Domicilio("falsa",123,"mvd","Uruguay");
        Paciente paciente = new Paciente("german","fraire","123", LocalDate.of(2024,6,20),domicilio,"ger@ger.com");

        Paciente pacienteBuscado = pacienteService.guardarPaciente(paciente);

        assertEquals(1L,pacienteBuscado.getId());

    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id= 1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        assertNotNull(pacienteBuscado.get());

    }



    @Test
    @Order(3)
    public void buscarPorMail(){
        String mail = "ger@ger.com";
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(mail);
        assertNotNull(pacienteBuscado.get());

    }


    @Test
    @Order(4)
    public void buscarPacientes(){

        List<Paciente> pacientes = pacienteService.buscarPacientes();

        assertEquals(1,pacientes.size());
    }



    @Test
    @Order(5)
    public void actualizarPaciente(){
        Long id= 1L;
        Domicilio domicilio = new Domicilio("falsa",123,"mvd","Uruguay");
        Paciente paciente = new Paciente(id,"fabi","geto","123", LocalDate.of(2024,6,20),"ger@ger.com",domicilio);

        pacienteService.actualizarPaciente(paciente);

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);


        assertEquals("geto",pacienteBuscado.get().getApellido());

    }



    @Test
    @Order(6)
    public void eliminarPaciente(){
        Long id= 1L;
        pacienteService.eliminarPaciente(id);
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        assertFalse(pacienteBuscado.isPresent());
    }



}
