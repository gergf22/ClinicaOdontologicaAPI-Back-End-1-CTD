package com.BackEnd1.ClinicaOdontologica.service;

import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {


    @Autowired
    private OdontologoRepository odontologoRepository;



    public Odontologo guardarOdontologo (Odontologo odontologo){
        return odontologoRepository.save(odontologo);

    }

    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarOdontologos(){
        return odontologoRepository.findAll();
    }

    public void eliminarOdontologo (Long id){
        odontologoRepository.deleteById(id);
    }

    public Optional<Odontologo> buscarPorMatricula (String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }

    public void actualizarOdontologo (Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }







}
