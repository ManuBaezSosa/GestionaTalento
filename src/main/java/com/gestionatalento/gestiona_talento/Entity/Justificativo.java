package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;

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
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cod_tip_justificativo", referencedColumnName = "cod_tip_justificativo")
    private TipoJustificativo tipoJustificativo;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cod_persona")
    private Persona persona;
}