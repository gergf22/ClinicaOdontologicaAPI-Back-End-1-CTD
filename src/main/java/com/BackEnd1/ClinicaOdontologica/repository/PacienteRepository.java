package com.BackEnd1.ClinicaOdontologica.repository;

import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Optional<Paciente> findByEmail(String email);
}
