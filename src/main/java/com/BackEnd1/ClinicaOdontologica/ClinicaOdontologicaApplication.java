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


		/*LocalDate date = LocalDate.now();

		Domicilio domicilio = new Domicilio("ombú",123,"mvd","Uruguay");
		Paciente paciente1 = new Paciente("german","Fraire","123123",date,domicilio,"ger@ger.com");
		Paciente paciente2 = new Paciente("pupi","bunnyta","1323123",date,domicilio,"pupi@guonejo.com");
		Odontologo odontologo1 = new Odontologo("123","pitty","martinez");
		Odontologo odontologo2 = new Odontologo("234","Julian","alvarez");
		Odontologo odontologo3 = new Odontologo("567","enzo","perez");*/

	}


}
