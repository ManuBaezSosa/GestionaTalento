package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;

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
}
