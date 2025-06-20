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
@Table(schema = "gestiona", name = "descuentos_salariales_auto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoSalarialAuto {

    @EmbeddedId
    private DescuentoSalarialAutoPK id;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "nro_periodo", referencedColumnName = "nro_periodo", insertable = false, updatable = false)
    private Periodo periodo;

    @Column(name = "entrada_tardia")
    private int entradaTardia;

    @Column(name = "salida_anticipada")
    private int salidaAnticipada;

    @Column(name = "ausencia")
    private int ausencia;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}
