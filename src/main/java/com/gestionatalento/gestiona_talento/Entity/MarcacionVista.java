package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_marcaciones")
public class MarcacionVista {

    @Id
    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "fecha_marcacion")
    private Date fecha;

    @Column(name = "fec_entrada")
    private String entrada;

    @Column(name = "fec_salida")
    private String salida;

    // Getters y setters
}