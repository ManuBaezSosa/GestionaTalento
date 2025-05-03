package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class HoraExtraPK implements Serializable {

    @Column(name = "cod_empleado")
    private Long codEmpleado;

    @Column(name = "cod_periodo")
    private String codPeriodo;

    public HoraExtraPK() {}

    public HoraExtraPK(Long codEmpleado, String codPeriodo) {
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
        if (!(o instanceof HoraExtraPK)) return false;
        HoraExtraPK that = (HoraExtraPK) o;
        return Objects.equals(codEmpleado, that.codEmpleado) &&
               Objects.equals(codPeriodo, that.codPeriodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEmpleado, codPeriodo);
    }
}
