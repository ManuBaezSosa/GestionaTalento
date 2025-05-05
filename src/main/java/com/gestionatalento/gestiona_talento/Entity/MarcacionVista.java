package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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