package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
    @JoinColumn(name = "cod_persona", referencedColumnName = "cod_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_documento", referencedColumnName = "cod_tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "nom_archivo")
    private String nomArchivo;

    @Column(name = "tip_archivo")
    private String tipArchivo;

    @Column(name = "tam_archivo")
    private Long tamArchivo;

    @Lob
    @Column(name = "archivo")
    private byte[] archivo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fec_documento")
    private Date fecDocumento;

    @Column(name = "fec_vencimiento")
    private Date fecVencimiento;

    @Column(name = "observacion")
    private String observacion;

}
