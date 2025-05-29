package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "periodos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periodo {
    
    @Id
    @Column(name = "nro_periodo")
    private Long nroPeriodo;

    @Column(name = "cod_periodo", unique = true)
    private String codPeriodo;

    @Column(name = "abreviatura")
    private String abreviatura;

    @Column(name = "descripcion")
    private String descripcion;
    
}