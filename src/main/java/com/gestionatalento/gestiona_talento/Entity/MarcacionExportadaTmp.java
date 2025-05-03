package com.gestionatalento.gestiona_talento.Entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marcaciones_exportadas_tmp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionExportadaTmp {

    @EmbeddedId
    private MarcacionExportadaTmpPK id;

    @ManyToOne
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", insertable = false, updatable = false)
    private UsuarioExportadoTmp usuarioExportadoTmp;

}