package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MarcacionExportadaTmpPK implements Serializable {

    @Column(name = "cod_usuario")
    private Long codUsuario;

    @Column(name = "fec_marcacion")
    private LocalDateTime fecMarcacion;

    public MarcacionExportadaTmpPK() {}

    public MarcacionExportadaTmpPK(Long codUsuario, LocalDateTime fecMarcacion) {
        this.codUsuario = codUsuario;
        this.fecMarcacion = fecMarcacion;
    }

    public Long getCodUsuario() { return codUsuario; }
    public void setCodUsuario(Long codUsuario) { this.codUsuario = codUsuario; }

    public LocalDateTime getFecMarcacion() { return fecMarcacion; }
    public void setFecMarcacion(LocalDateTime fecMarcacion) { this.fecMarcacion = fecMarcacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarcacionExportadaTmpPK)) return false;
        MarcacionExportadaTmpPK that = (MarcacionExportadaTmpPK) o;
        return Objects.equals(codUsuario, that.codUsuario) &&
               Objects.equals(fecMarcacion, that.fecMarcacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codUsuario, fecMarcacion);
    }
}
