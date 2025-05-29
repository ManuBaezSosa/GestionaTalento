package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDateTime;

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
@Table(name = "empleados_novedades")
public class EmpleadoNovedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "nro_novedad")
    private Long nroNovedad;
    
    @ManyToOne
    @JoinColumn(name = "cod_empleado", nullable = false)
    private Empleado empleado;

    @Column(name =  "estado")
    private String estado;

    @Column(name =  "fecha")
    private LocalDateTime fecha;

    @Column(name =  "comentario")
    private String comentario;

}
