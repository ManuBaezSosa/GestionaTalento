package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HISTORICO_EMPLEADOS")
public class HistoricoEmpleado {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_persona")
    private Long codPersona;

    //Persona que realiza la modificacion
    private String usuarioModificacion;

    private Double asignacionAnterior;

    private Double asignacionActual;
    
    private LocalDate fechaModificacion;

    //mes y año
    private String fechaPeriodo;

}
