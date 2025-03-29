package com.gestionatalento.gestiona_talento.Entity;

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
@Table(name = "contratos")
public class Contrato {
    @Id
    @Column(name = "nro_contrato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroContrato;

    @Column(name = "cod_persona")
    private Long codPersona;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    @Column(name = "nom_archivo")
    private String nomArchivo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observacion")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "cod_persona", insertable = false, updatable = false)
    private Persona persona;
}
