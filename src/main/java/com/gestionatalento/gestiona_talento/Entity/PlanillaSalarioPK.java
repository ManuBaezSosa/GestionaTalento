package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PlanillaSalarioPK implements Serializable {

    @Column(name = "cod_empleado")
    private Long codEmpleado;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    @Column(name = "cod_presupuesto")
    private Long codPresupuesto;

    @Column(name = "cod_programa")
    private Long codPrograma;

    @Column(name = "cod_situacion_laboral")
    private Long codSituacionLaboral;

    @Column(name = "cod_fuente_financiamiento")
    private Long codFuenteFinanciamiento;

    @Column(name = "cod_objeto_gasto")
    private Long codObjetoGasto;

    @Column(name = "cod_subprograma")
    private Long codSubprograma;

    public PlanillaSalarioPK() {}

    public PlanillaSalarioPK(Long codEmpleado, String codPeriodo, Long codPresupuesto, Long codPrograma, Long codSituacionLaboral, Long codFuenteFinanciamiento, Long codObjetoGasto, Long codSubprograma) {
        this.codEmpleado = codEmpleado;
        this.codPeriodo = codPeriodo;
        this.codPresupuesto = codPresupuesto;
        this.codPrograma = codPrograma;
        this.codSituacionLaboral = codSituacionLaboral;
        this.codFuenteFinanciamiento = codFuenteFinanciamiento;
        this.codObjetoGasto = codObjetoGasto;
        this.codSubprograma = codSubprograma;
    }

    public Long getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(Long codEmpleado) { this.codEmpleado = codEmpleado; }

    public String getCodPeriodo() { return codPeriodo; }
    public void setCodPeriodo(String codPeriodo) { this.codPeriodo = codPeriodo; }

    public Long getCodPresupuesto() {
        return codPresupuesto;
    }

    public Long getCodPrograma() {
        return codPrograma;
    }

    public Long getCodSituacionLaboral() {
        return codSituacionLaboral;
    }

    public Long getCodFuenteFinanciamiento() {
        return codFuenteFinanciamiento;
    }

    public Long getCodObjetoGasto() {
        return codObjetoGasto;
    }

    public Long getCodSubprograma() {
        return codSubprograma;
    }

    public void setCodPresupuesto(Long codPresupuesto) {
        this.codPresupuesto = codPresupuesto;
    }

    public void setCodPrograma(Long codPrograma) {
        this.codPrograma = codPrograma;
    }

    public void setCodSituacionLaboral(Long codSituacionLaboral) {
        this.codSituacionLaboral = codSituacionLaboral;
    }

    public void setCodFuenteFinanciamiento(Long codFuenteFinanciamiento) {
        this.codFuenteFinanciamiento = codFuenteFinanciamiento;
    }

    public void setCodObjetoGasto(Long codObjetoGasto) {
        this.codObjetoGasto = codObjetoGasto;
    }

    public void setCodSubprograma(Long codSubprograma) {
        this.codSubprograma = codSubprograma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanillaSalarioPK)) return false;
        PlanillaSalarioPK that = (PlanillaSalarioPK) o;
        return Objects.equals(codEmpleado, that.codEmpleado) &&
               Objects.equals(codPeriodo, that.codPeriodo) &&
               Objects.equals(codPresupuesto, that.codPresupuesto) &&
               Objects.equals(codPrograma, that.codPrograma) &&
               Objects.equals(codSituacionLaboral, that.codSituacionLaboral) &&
               Objects.equals(codFuenteFinanciamiento, that.codFuenteFinanciamiento) &&
               Objects.equals(codObjetoGasto, that.codObjetoGasto) &&
               Objects.equals(codSubprograma, that.codSubprograma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmpleado, codPeriodo, codPresupuesto, codPrograma, codSituacionLaboral, codFuenteFinanciamiento, codObjetoGasto, codSubprograma);
    }
}
