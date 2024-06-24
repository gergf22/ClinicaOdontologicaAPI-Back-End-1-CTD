package com.BackEnd1.ClinicaOdontologica.Controller;

import com.BackEnd1.ClinicaOdontologica.entity.Paciente;
import com.BackEnd1.ClinicaOdontologica.exception.BadRequestException;
import com.BackEnd1.ClinicaOdontologica.exception.NotFoundException;
import com.BackEnd1.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;



    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId (@PathVariable Long id) throws NotFoundException {

        if (pacienteService.buscarPorID(id).isPresent()){
            return ResponseEntity.ok(pacienteService.buscarPorID(id));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes (){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente (@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarPaciente (@PathVariable Long id) throws BadRequestException {
        if (pacienteService.buscarPorID(id).isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se ha eliminado con éxito");
        }else {
            throw new BadRequestException("Paciente a eliminar no existe");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) throws NotFoundException{
        if (pacienteService.buscarPorID(paciente.getId()).isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con éxito");
        }else {
            throw new NotFoundException("Paciente a actualizar no encontrado");
        }
    }







    //ahora vienen todos los metodos que nos permitan actuar como intermediarios CON VISTAS.
    /*@GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){

        Paciente paciente= pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";

        //return pacienteService.buscarPorEmail(email);
    }

    @GetMapping
    public String mostrarPacientes (Model model){


        List<Paciente> pacientes = pacienteService.mostrarPacientes();

        model.addAttribute("pacientes",pacientes);

        return "index";
    }*/
}
