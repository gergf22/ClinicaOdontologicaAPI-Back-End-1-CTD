package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import com.BackEnd1.ClinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odolontologoService;
    @Autowired
    private TurnoService turnoService;



    public static final Logger logger = Logger.getLogger(TurnoController.class);

    @PostMapping
    public ResponseEntity<Turno> guardarTurno (@RequestBody Turno turno){
        if (pacienteService.buscarPorID(turno.getPaciente().getId()).isPresent()
                && odolontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()){

            turnoService.guardarTurno(turno);
            return ResponseEntity.ok(turnoService.buscarPorId(turno.getId()).get());

        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Turno>> mostrarTurnos (){
        return ResponseEntity.ok(turnoService.mostrarTurnos());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id){
        if (turnoService.buscarPorId(id).isPresent()){
            return ResponseEntity.ok(turnoService.buscarPorId(id));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable Long id){
        logger.info("buscando turno con id: " + id);
        if (turnoService.buscarPorId(id).isPresent()){
            logger.info("turno encontrado");
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminaod con exito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno){
        if(turnoService.buscarPorId(turno.getId()).isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado");
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(path = "/paciente/{id}")
    public ResponseEntity<Optional<List<Turno>>> buscarTurnoPorPaciente (@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarPorPaciente(id));

    }



}


/*         Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odolontologoService.buscarPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent()
                && odontologoBuscado.isPresent()){

            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));

        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/