package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventarios_documentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDocumento {
    
    @EmbeddedId
    private InventarioDocumentoPK id;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "nro_periodo", referencedColumnName = "nro_periodo", insertable = false, updatable = false)
    private Periodo periodo;
    
    @Column(name = "estado")
    private String estado;

    @Column(name = "comentario")
    private String comentario;

}