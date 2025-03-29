package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "justificativos")
public class Justificativo {
    @Id
    @Column(name = "nro_justificativo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroJustificativo;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cod_generacion")
    private String codGeneracion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cod_persona")
    private Persona persona;
}