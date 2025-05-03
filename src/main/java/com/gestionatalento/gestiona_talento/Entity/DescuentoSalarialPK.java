package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DescuentoSalarialPK implements Serializable {

    @Column(name = "cod_empleado")
    private Long codEmpleado;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    public DescuentoSalarialPK() {}

    public DescuentoSalarialPK(Long codEmpleado, String codPeriodo) {
        this.codEmpleado = codEmpleado;
        this.codPeriodo = codPeriodo;
    }

    public Long getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(Long codEmpleado) { this.codEmpleado = codEmpleado; }

    public String getCodPeriodo() { return codPeriodo; }
    public void setCodPeriodo(String codPeriodo) { this.codPeriodo = codPeriodo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescuentoSalarialPK)) return false;
        DescuentoSalarialPK that = (DescuentoSalarialPK) o;
        return Objects.equals(codEmpleado, that.codEmpleado) &&
               Objects.equals(codPeriodo, that.codPeriodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmpleado, codPeriodo);
    }
}
