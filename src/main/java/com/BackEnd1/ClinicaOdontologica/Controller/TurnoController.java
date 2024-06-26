package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Turno;
import com.BackEnd1.ClinicaOdontologica.exception.BadRequestException;
import com.BackEnd1.ClinicaOdontologica.exception.NotFoundException;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import com.BackEnd1.ClinicaOdontologica.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@Tag(name = "Controller de turnos", description = "Permite cargar, listar, modificar y eliminar turnos ")
public class TurnoController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odolontologoService;
    @Autowired
    private TurnoService turnoService;



    public static final Logger logger = Logger.getLogger(TurnoController.class);

    @PostMapping
    @Operation(summary = "Carga un turno en BD ",description = "Indicar los Id de odontologo y paciente junto con la fecha del turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el turno cargado"),
            @ApiResponse(responseCode = "400",description = "Paciente u odontologos no existen")
    })
    public ResponseEntity<Turno> guardarTurno (@RequestBody Turno turno) throws BadRequestException {
        if (pacienteService.buscarPorID(turno.getPaciente().getId()).isPresent()
                && odolontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()){

            turnoService.guardarTurno(turno);
            return ResponseEntity.ok(turnoService.buscarPorId(turno.getId()).get());

        } else {
            throw new BadRequestException("Paciente u odontologos no existen");
        }
    }


    @GetMapping
    @Operation(summary = "Lista a todos los turnos")
    @ApiResponse(responseCode = "200",description = "Devuelve una lista de todos los turnos")
    public ResponseEntity<List<Turno>> mostrarTurnos (){
        return ResponseEntity.ok(turnoService.mostrarTurnos());

    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Nos permite buscar un turno especifico por su ID", description = "Indicar el ID del turno en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el turno con ese ID"),
            @ApiResponse(responseCode = "404",description = "Turno no encontrado")
    })
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id) throws NotFoundException {
        if (turnoService.buscarPorId(id).isPresent()){
            return ResponseEntity.ok(turnoService.buscarPorId(id));
        }else {
            throw new NotFoundException("Turno no encontrado");
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Elimina el turno especifico con el Id indicado",description = "Indicar el ID del turno en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno eliminado con éxito"),
            @ApiResponse(responseCode = "400",description = "Turno a eliminar no existe")
    })
    public ResponseEntity<String> eliminarTurno (@PathVariable Long id) throws BadRequestException{
        logger.info("buscando turno con id: " + id);
        if (turnoService.buscarPorId(id).isPresent()){
            logger.info("turno encontrado");
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con exito");
        } else {
            throw new BadRequestException("Turno a eliminar no existe");
        }
    }

    @PutMapping
    @Operation(summary = "Actualiza los datos de un turno especifico",description = "Debe indicarser el turno completo con su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno actualizado con éxito"),
            @ApiResponse(responseCode = "404",description = "Turno a actualizar no encontrado")
    })
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno) throws NotFoundException{
        if(turnoService.buscarPorId(turno.getId()).isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado");
        }else {
            throw new NotFoundException("Turno no encontrado");
        }

    }

    @GetMapping(path = "/paciente/{id}")
    @Operation( summary = "Permite encontrar los turno a partir de un paciente en especifico", description = "Se debe indicar el id del pacinte en el path")
    @ApiResponse(responseCode = "200",description = "Devuelve una lista de todos los turnos de ese paciente")
    public ResponseEntity<Optional<List<Turno>>> buscarTurnoPorPaciente (@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarPorPaciente(id));

    }



}

