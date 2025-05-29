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
@Table(name = "tipos_justificativos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoJustificativo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_tip_justificativo")
    private Long codTipJustificativo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private String activo;
    
}