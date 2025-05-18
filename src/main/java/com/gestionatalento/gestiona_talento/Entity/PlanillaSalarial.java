package com.gestionatalento.gestiona_talento.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planillas_salariales",
       uniqueConstraints = @UniqueConstraint(columnNames = {
           "cod_empleado", "nro_periodo", "cod_presupuesto", "cod_programa",
           "cod_situacion_laboral", "cod_fuente_financiamiento", "cod_objeto_gasto", "cod_subprograma"
       }))
public class PlanillaSalarial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_planilla", nullable = false)
    private Long nroPlanilla;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "nro_periodo", referencedColumnName = "nro_periodo")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "cod_presupuesto", referencedColumnName = "cod_presupuesto")
    private Presupuesto presupuesto;

    @ManyToOne
    @JoinColumn(name = "cod_programa", referencedColumnName = "cod_programa")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "cod_situacion_laboral", referencedColumnName = "cod_situacion_laboral")
    private SituacionLaboral situacionLaboral;

    @ManyToOne
    @JoinColumn(name = "cod_fuente_financiamiento", referencedColumnName = "cod_fuente_financiamiento")
    private FuenteFinanciamiento fuenteFinanciamiento;

    @ManyToOne
    @JoinColumn(name = "cod_objeto_gasto", referencedColumnName = "cod_objeto_gasto")
    private ObjetoGasto objetoGasto;

    @ManyToOne
    @JoinColumn(name = "cod_subprograma", referencedColumnName = "cod_subprograma")
    private Subprograma subprograma;

    @ManyToOne
    @JoinColumn(name = "nro_grado", referencedColumnName = "nro_grado")
    private GradoSalarial gradoSalarial;

    @Column(name = "asignacion")
    private Double asignacion;
    
}