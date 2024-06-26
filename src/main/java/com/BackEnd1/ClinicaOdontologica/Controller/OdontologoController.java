package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Odontologo;
import com.BackEnd1.ClinicaOdontologica.exception.BadRequestException;
import com.BackEnd1.ClinicaOdontologica.exception.NotFoundException;
import com.BackEnd1.ClinicaOdontologica.service.OdontologoService;
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
@RequestMapping("/odontologos")
@Tag(name = "Controller de odontologos", description = "Permite cargar, listar, modificar y eliminar odontologos ")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    @Operation(summary = "Carga un paciente en BD ",description = "Indicar el odontologo sin el ID")
    @ApiResponse(responseCode = "200",description = "Devuelve el odontologo cargado con el ID")
    public ResponseEntity<Odontologo> guardarOdontologo (@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping(path = "/{id}") 
    @Operation(summary = "Nos permite buscar un odontologo especifico por su ID", description = "Indicar el ID del odontologo en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el odontologo con ese ID"),
            @ApiResponse(responseCode = "404",description = "Odontologo no encontrado")
    })
    public ResponseEntity<Optional<Odontologo>> buscarPorId (@PathVariable Long id) throws NotFoundException {

        if (odontologoService.buscarPorId(id).isPresent()){
            return ResponseEntity.ok(odontologoService.buscarPorId(id));
        }else {
            throw new NotFoundException("Odongotolo no encontrado");
        }

    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Elimina el odontologo especifico con el Id indicado",description = "Indicar el ID del odontologo en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Odongotolo eliminado con éxito"),
            @ApiResponse(responseCode = "400",description = "Odongotolo a eliminar no existe")
    })
    public ResponseEntity<String> eliminarOdontogolo (@PathVariable Long id) throws BadRequestException {
        if (odontologoService.buscarPorId(id).isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se ha eliminado con exito el Odontologo");
        }else {
            throw new BadRequestException("Odongotolo a eliminar no existe");
        }
    }

    @GetMapping
    @Operation(summary = "Lista a todos los odontologos")
    @ApiResponse(responseCode = "200",description = "Devuelve una lista de todos los odontologos")
    public ResponseEntity<List<Odontologo>> buscarOdontologos (){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @GetMapping(path = "/matricula")
    @Operation(summary = "Nos permite buscar un odontologo especifico por su matricula", description = "Indicar la matricula del odontologo en el path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Devuelve el odontologo con esa magtricula"),
            @ApiResponse(responseCode = "404",description = "Odontologo no encontrado")
    })
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@RequestBody String matricula)  throws NotFoundException{
        if (odontologoService.buscarPorMatricula(matricula).isPresent()){
            return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
        } else {
            throw new NotFoundException("Odongotolo no encontrado");
        }
    }


    @PutMapping
    @Operation(summary = "Actualiza los datos de un odontologo especifico",description = "Debe odontologo el paciente completo con su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Odongotolo actualizado con éxito"),
            @ApiResponse(responseCode = "404",description = "Odongotolo a actualizar no encontrado")
    })
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo)  throws NotFoundException{
        if (odontologoService.buscarPorId(odontologo.getId()).isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado con éxito");
        }else {
            throw new NotFoundException("Odongotolo a actualizar no encontrado");
        }

    }


}
