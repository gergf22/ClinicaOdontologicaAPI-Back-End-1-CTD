package com.BackEnd1.ClinicaOdontologica.service;


import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import com.BackEnd1.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;



    public Turno guardarTurno (Turno turno){
        return turnoRepository.save(turno);
    }

    public List<Turno> mostrarTurnos(){
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPorId (Long id) {
        return turnoRepository.findById(id);
    }

    public  void eliminarTurno (Long id){
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno (Turno turno){
        turnoRepository.save(turno);
    }


    public Optional<List<Turno>> buscarPorPaciente (Long id){
        return turnoRepository.findByPaciente_id(id);
    }
}
