package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "comisionados")
public class Comisionado {
    @Id
    @Column(name = "cod_persona")
    private Long codPersona;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    @Column(name = "entidad_destino")
    private String entidadDestino;

    @Column(name = "observacion")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "cod_persona", insertable = false, updatable = false)
    private Persona persona;
}