package com.BackEnd1.ClinicaOdontologica.repository;

import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {



    Optional<Odontologo> findByMatricula(String matricula);
}
