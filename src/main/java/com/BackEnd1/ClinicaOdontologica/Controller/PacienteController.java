package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.exception.BadRequestException;
import com.BackEnd1.ClinicaOdontologica.exception.NotFoundException;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Controller de Paciente", description = "Permite cargar, listar, modificar y eliminar pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;



    @GetMapping(path = "/{id}")
    @Operation(summary = "Nos permite buscar un paciente especifico por su ID", description = "Indicar el ID del paciente en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el paciente con ese ID"),
            @ApiResponse(responseCode = "404",description = "Paciente no encontrado")
    })
    public ResponseEntity<Optional<Paciente>> buscarPorId (@PathVariable Long id) throws NotFoundException {

        if (pacienteService.buscarPorID(id).isPresent()){
            return ResponseEntity.ok(pacienteService.buscarPorID(id));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping
    @Operation(summary = "Lista a todos los pacientes")
    @ApiResponse(responseCode = "200",description = "Devuelve una lista de todos los pacientes")
    public ResponseEntity<List<Paciente>> buscarPacientes (){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }

    @PostMapping
    @Operation(summary = "Carga un paciente en BD ",description = "Indicar el paciente sin el ID")
    @ApiResponse(responseCode = "200",description = "Devuelve el paciente cargado con el ID")
    public ResponseEntity<Paciente> guardarPaciente (@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Elimina el paciente especifico con el Id indicado",description = "Indicar el ID del paciente en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente eliminado con éxito"),
            @ApiResponse(responseCode = "400",description = "Paciente a eliminar no existe")
    })
    public ResponseEntity<String> eliminarPaciente (@PathVariable Long id) throws BadRequestException {
        if (pacienteService.buscarPorID(id).isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se ha eliminado con éxito");
        }else {
            throw new BadRequestException("Paciente a eliminar no existe");
        }
    }

    @PutMapping
    @Operation(summary = "Actualiza los datos de un paciente especifico",description = "Debe indicarser el paciente completo con su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente actualizado con éxito"),
            @ApiResponse(responseCode = "404",description = "Paciente a actualizar no encontrado")
    })
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) throws NotFoundException{
        if (pacienteService.buscarPorID(paciente.getId()).isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con éxito");
        }else {
            throw new NotFoundException("Paciente a actualizar no encontrado");
        }
    }


}
