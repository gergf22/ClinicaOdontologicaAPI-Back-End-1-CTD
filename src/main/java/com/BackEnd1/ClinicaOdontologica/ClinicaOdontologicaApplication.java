package com.BackEnd1.ClinicaOdontologica;



import com.BackEnd1.ClinicaOdontologica.Controller.PacienteController;
import com.BackEnd1.ClinicaOdontologica.entity.Domicilio;
import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.repository.PacienteRepository;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);




	}


}
