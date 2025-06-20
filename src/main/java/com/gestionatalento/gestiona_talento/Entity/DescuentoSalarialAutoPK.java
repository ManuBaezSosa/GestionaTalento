package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DescuentoSalarialAutoPK implements Serializable {

    @Column(name = "cod_empleado")
    private Long codEmpleado;

    @Column(name = "nro_periodo")
    private Long nroPeriodo;

    public DescuentoSalarialAutoPK() {}

    public DescuentoSalarialAutoPK(Long codEmpleado, Long nroPeriodo) {
        this.codEmpleado = codEmpleado;
        this.nroPeriodo = nroPeriodo;
    }

    public Long getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(Long codEmpleado) { this.codEmpleado = codEmpleado; }

    public Long getNroPeriodo() { return nroPeriodo; }
    public void setNroPeriodo(Long nroPeriodo) { this.nroPeriodo = nroPeriodo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescuentoSalarialAutoPK)) return false;
        DescuentoSalarialAutoPK that = (DescuentoSalarialAutoPK) o;
        return Objects.equals(codEmpleado, that.codEmpleado) &&
               Objects.equals(nroPeriodo, that.nroPeriodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmpleado, nroPeriodo);
    }
}
