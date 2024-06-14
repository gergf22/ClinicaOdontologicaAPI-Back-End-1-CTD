package com.BackEnd1.ClinicaOdontologica.test;



import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestOdontologosService {

    @Test
    public void mostrarOdontologosTestH2(){

        OdontologoService odolontologoService = new OdontologoService();


        Assertions.assertTrue(odolontologoService.buscarOdontologos().size()==2);
    }


    @Test
    public void guardarOdontologoOk (){
        OdontologoService odontologoService = new OdontologoService();


        Odontologo odontologo = new Odontologo("23232323","juan","perez");

        Odontologo resEsp = odontologoService.guardarOdontologo(odontologo);
        Assertions.assertEquals(resEsp,odontologo);
    }



}
