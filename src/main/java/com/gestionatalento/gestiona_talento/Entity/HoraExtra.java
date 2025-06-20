package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "gestiona", name = "horas_extras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraExtra {
    
    @EmbeddedId
    private HoraExtraPK id;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado", insertable = false, updatable = false)
    private Empleado empleado;

    @Column(name = "hora_extra")
    private int horaExtra;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "exonera_entrada")
    private String exoneraEntrada;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}