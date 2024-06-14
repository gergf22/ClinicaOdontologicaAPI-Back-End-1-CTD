package com.BackEnd1.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "odontologos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Odontologo {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;


    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
