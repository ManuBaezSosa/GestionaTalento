package com.gestionatalento.gestiona_talento.Entity;

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
@Table(name = "descuentos_salariales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoSalarial {

    @EmbeddedId
    private DescuentoSalarialPK id;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cod_periodo", referencedColumnName = "cod_periodo", insertable = false, updatable = false)
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
}
