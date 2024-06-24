package com.BackEnd1.ClinicaOdontologica;


import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoIntegrationTest {

    @Autowired
    private OdontologoService service;

    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = Logger.getLogger(OdontologoIntegrationTest.class);


    @Test
    public void cargarOdontologos () throws JsonProcessingException {

        Odontologo odontologo = new Odontologo("567", "enzo", "perez");

        try {
            ObjectWriter writer = new ObjectMapper()
                    .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                    .writer();


            String payload = writer.writeValueAsString(odontologo);


            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andReturn();

            assertFalse(result.getResponse().getContentAsString().isEmpty());

        }catch (Exception e){
            logger.error(e.getMessage());
        }


    }

}
