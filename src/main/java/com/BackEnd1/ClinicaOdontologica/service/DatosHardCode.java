package com.BackEnd1.ClinicaOdontologica.service;

import com.BackEnd1.ClinicaOdontologica.entity.Domicilio;
import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import com.BackEnd1.ClinicaOdontologica.repository.OdontologoRepository;
import com.BackEnd1.ClinicaOdontologica.repository.PacienteRepository;
import com.BackEnd1.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosHardCode implements ApplicationRunner {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Domicilio domicilio = new Domicilio("falsa",123,"mvd","Uruguay");
        Domicilio domicilio2 = new Domicilio("siempre viva",123,"mvd","Uruguay");
        Paciente paciente = new Paciente("german","fraire","123", LocalDate.of(2024,6,20),domicilio,"ger@ger.com");
        Paciente paciente2 = new Paciente("Fabricio","Acosta","456", LocalDate.of(2024,6,20),domicilio2,"Geto@geto.com");

        Odontologo odontologo = new Odontologo("567", "enzo", "perez");
        Odontologo odontologo2 = new Odontologo("lalala", "pity", "Martinez");
        Odontologo odontologo3 = new Odontologo("25", "leo", "Ponzio");
        Odontologo odontologo4 = new Odontologo("09", "colibri", "borja");


        pacienteService.guardarPaciente(paciente);
        pacienteService.guardarPaciente(paciente2);
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.guardarOdontologo(odontologo3);
        odontologoService.guardarOdontologo(odontologo4);

        Turno turno = new Turno(odontologoService.buscarPorId(1L).get(), pacienteService.buscarPorID(1L).get(),LocalDate.of(2024,6,20));

        turnoRepository.save(turno);

    }
}
