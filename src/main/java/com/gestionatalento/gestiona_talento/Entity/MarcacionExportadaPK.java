package com.gestionatalento.gestiona_talento.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MarcacionExportadaPK implements Serializable {

    @Column(name = "cod_usuario")
    private Long codUsuario;

    @Column(name = "fec_marcacion")
    private LocalDateTime fecMarcacion;

    public MarcacionExportadaPK() {}

    public MarcacionExportadaPK(Long codUsuario, LocalDateTime fecMarcacion) {
        this.codUsuario = codUsuario;
        this.fecMarcacion = fecMarcacion;
    }

    public Long getCodUsuario() { return codUsuario; }
    public void setCodUsuario(Long codUsuario) { this.codUsuario = codUsuario; }

    public LocalDateTime getFecMarcacion() { return fecMarcacion; }
    public void setFecMarcacion(LocalDateTime fecMarcacion) { this.fecMarcacion = fecMarcacion; }
}
