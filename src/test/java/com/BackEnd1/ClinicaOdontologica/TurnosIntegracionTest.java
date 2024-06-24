package com.BackEnd1.ClinicaOdontologica;

import com.BackEnd1.ClinicaOdontologica.entity.Domicilio;
import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnosIntegracionTest {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = Logger.getLogger(TurnosIntegracionTest.class);



    private void cargarDatos(){
        Domicilio domicilio = new Domicilio("falsa",123,"mvd","Uruguay");
        Paciente paciente = new Paciente("german","fraire","123", LocalDate.of(2024,6,20),domicilio,"ger@ger.com");

        Odontologo odontologo = new Odontologo("567", "enzo", "perez");

        pacienteService.guardarPaciente(paciente);
        odontologoService.guardarOdontologo(odontologo);

        logger.info("Datos Cargados");

    }




    @Test
    @Order(1)
    public void cargarTurno () throws JsonProcessingException {

        cargarDatos();
        Turno turno = new Turno(odontologoService.buscarPorId(1L).get(),pacienteService.buscarPorID(1L).get(),LocalDate.of(2024,6,20));

        try {
            ObjectWriter writer = new ObjectMapper()
                    .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                    .writer();


            String payload = writer.writeValueAsString(turno);


            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(payload))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

            assertFalse(result.getResponse().getContentAsString().isEmpty());

        } catch (Exception e){

            logger.error(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void ListarTodosLosTurnosTest() throws Exception{
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }







}
