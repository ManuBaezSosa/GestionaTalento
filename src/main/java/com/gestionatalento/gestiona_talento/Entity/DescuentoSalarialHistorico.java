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
@Table(name = "descuentos_salariales_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoSalarialHistorico {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_persona", referencedColumnName = "cod_persona")
    private Persona persona;

    @Id
    @Column(name = "cod_periodo")
    private String codPeriodo;

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
}