package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.exception.BadRequestException;
import com.BackEnd1.ClinicaOdontologica.exception.NotFoundException;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo (@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId (@PathVariable Long id) throws NotFoundException {

        if (odontologoService.buscarPorId(id).isPresent()){
            return ResponseEntity.ok(odontologoService.buscarPorId(id));
        }else {
            throw new NotFoundException("Odongotolo no encontrado");
        }

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarOdontogolo (@PathVariable Long id) throws BadRequestException {
        if (odontologoService.buscarPorId(id).isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se ha eliminado con exito el Odontologo");
        }else {
            throw new BadRequestException("Odongotolo a eliminar no existe");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos (){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @GetMapping(path = "/matricula")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@RequestBody String matricula)  throws NotFoundException{
        if (odontologoService.buscarPorMatricula(matricula).isPresent()){
            return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
        } else {
            throw new NotFoundException("Odongotolo no encontrado");
        }
    }


    @PutMapping
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo)  throws NotFoundException{
        if (odontologoService.buscarPorId(odontologo.getId()).isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado con éxito");
        }else {
            throw new NotFoundException("Odongotolo a actualizar no encontrado");
        }

    }


}
