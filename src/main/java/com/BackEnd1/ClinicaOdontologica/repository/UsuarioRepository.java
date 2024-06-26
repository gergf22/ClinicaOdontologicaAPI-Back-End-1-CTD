package com.BackEnd1.ClinicaOdontologica.repository;

import com.BackEnd1.ClinicaOdontologica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);
}
