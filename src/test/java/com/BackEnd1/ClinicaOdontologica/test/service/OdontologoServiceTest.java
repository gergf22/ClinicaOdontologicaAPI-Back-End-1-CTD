package com.BackEnd1.ClinicaOdontologica.test.service;

import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarodontologo() {

        Odontologo odontologo = new Odontologo("567", "enzo", "perez");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        assertEquals(1L, odontologoGuardado.getId());

    }

    @Test
    @Order(2)
    public void buscarPorId(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);

        assertTrue(odontologoBuscado.isPresent());

    }

    @Test
    @Order(3)
    public void buscarOdontologos (){
        List<Odontologo> odontologos = odontologoService.buscarOdontologos();

        assertEquals(1,odontologos.size());
    }


    @Test
    @Order(4)
    public void buscarPorMatricula (){
        String matricula = "567";
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        assertTrue(odontologoBuscado.isPresent());

    }


    @Test
    @Order(5)
    public void actualizarOdontologo (){
        Long id = 1L;
        Odontologo odontologo = new Odontologo(id,"912", "Quintero", "perez");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);

        assertEquals("Quintero",odontologoBuscado.get().getNombre());

    }


    @Test
    @Order(6)
    public void eliminarOdontologo(){
        Long id = 1L;
        odontologoService.eliminarOdontologo(id);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);

        assertFalse(odontologoBuscado.isPresent());

    }




}
