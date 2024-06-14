package com.BackEnd1.ClinicaOdontologica.repository;

import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Long> {


    Optional<List<Turno>> findByPaciente_id(Long paciente_id);
}
