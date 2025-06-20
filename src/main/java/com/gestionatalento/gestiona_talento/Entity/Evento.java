package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(schema = "gestiona", name = "eventos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_evento")
    private Long nroEvento;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "cod_tip_evento", referencedColumnName = "cod_tip_evento")
    private TipoEvento tipoEvento;

    @Column(name = "hora_inicial")
    private LocalTime horaInicial;
    
    @Column(name = "hora_final")
    private LocalTime horaFinal;

    @Column(name = "vigente")
    private String vigente;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}