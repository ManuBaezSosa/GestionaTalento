package com.gestionatalento.gestiona_talento.Entity;


import java.time.LocalDateTime;

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
@Table(schema = "gestiona", name = "marcaciones_exportadas_tmp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionExportadaTmp {

    @EmbeddedId
    private MarcacionExportadaTmpPK id;

    @ManyToOne
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", insertable = false, updatable = false)
    private UsuarioExportadoTmp usuarioExportadoTmp;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}