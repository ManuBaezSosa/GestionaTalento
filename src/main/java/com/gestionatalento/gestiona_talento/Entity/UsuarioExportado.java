package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios_exportados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioExportado {
    
    @Id
    @Column(name = "cod_usuario")
    private Long codUsuario;

    @Column(name = "nro_documento")
    private String nroDocumento;

}