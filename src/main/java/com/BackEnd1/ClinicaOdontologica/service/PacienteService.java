package com.BackEnd1.ClinicaOdontologica.service;



import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;




    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPorID(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    public void eliminarPaciente (Long id){  pacienteRepository.deleteById(id);}

    public void actualizarPaciente(Paciente paciente){ pacienteRepository.save(paciente);}


}
