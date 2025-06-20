package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;
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
@Table(schema = "gestiona", name = "contratos")
public class Contrato {
    
    @Id
    @Column(name = "nro_contrato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroContrato;

    @ManyToOne
    @JoinColumn(name = "nro_periodo", referencedColumnName = "nro_periodo")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "cod_persona", referencedColumnName = "cod_persona")
    private Persona persona;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "asignacion")
    private Double asignacion;

    @Column(name = "asignacion_letras")
    private String montoLetras;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fec_desde")
    private Date fecDesde;

    @Column(name = "fec_hasta")
    private Date fecHasta;

    @ManyToOne
    @JoinColumn(name = "cod_situacion_laboral", referencedColumnName = "cod_situacion_laboral")
    private SituacionLaboral situacionLaboral;

    @Column(name = "nom_firmante_1")
    private String nomFirmante1;

    @Column(name = "nom_firmante_2")
    private String nomFirmante2;

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
