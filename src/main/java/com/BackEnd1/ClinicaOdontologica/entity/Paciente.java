package com.BackEnd1.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cedula;
    @Column
    private LocalDate fechaIngreso;
    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;


    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email= email;
    }




    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                '}';
    }
}
