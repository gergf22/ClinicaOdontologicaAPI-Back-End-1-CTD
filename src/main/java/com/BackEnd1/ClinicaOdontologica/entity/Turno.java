package com.BackEnd1.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "turnos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turno {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;
    @Column
    private LocalDate fechaYHora;


    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
