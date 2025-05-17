package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documentos")
public class Documento {

    @Id
    @Column(name = "nro_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroDocumento;

    @ManyToOne
    @JoinColumn(name = "cod_persona", referencedColumnName = "cod_persona", insertable = false, updatable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_documento", referencedColumnName = "cod_tipo_documento", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nom_archivo")
    private String nomArchivo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fec_documento")
    private Date fecDocumento;

    @Column(name = "observacion")
    private String observacion;

}
