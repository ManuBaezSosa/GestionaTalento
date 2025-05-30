package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos_documentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_tipo_documento")
    private Long codTipoDocumento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private String activo;

    @Column(name = "tip_categoria")
    private String tipCategoria;
    
}