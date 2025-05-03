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
@Table(name = "horas_extras_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraExtraHistorica {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado")
    private Empleado empleado;

    @Id
    @Column(name = "cod_periodo")
    private String codPeriodo;

    @Column(name = "hora_extra")
    private int horaExtra;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "exonera_entrada")
    private String exoneraEntrada;
}