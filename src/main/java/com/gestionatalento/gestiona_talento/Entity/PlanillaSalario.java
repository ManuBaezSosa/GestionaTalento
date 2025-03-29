package com.gestionatalento.gestiona_talento.Entity;

import java.math.BigDecimal;

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
@Table(name = "planilla_salario")
public class PlanillaSalario {
    @Id
    @Column(name = "cod_persona")
    private Long codPersona;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    @Column(name = "grado_salarial")
    private String gradoSalarial;

    @Column(name = "asignacion")
    private BigDecimal asignacion;

    @Column(name = "cod_presupuesto")
    private String codPresupuesto;

    @Column(name = "cod_dependencia")
    private String codDependencia;

    @Column(name = "cod_subprograma")
    private String codSubprograma;

    @Column(name = "cod_programa")
    private String codPrograma;

    @Column(name = "cod_periodo_planilla")
    private String codPeriodoPlanilla;

    @ManyToOne
    @JoinColumn(name = "cod_persona", insertable = false, updatable = false)
    private Persona persona;
}