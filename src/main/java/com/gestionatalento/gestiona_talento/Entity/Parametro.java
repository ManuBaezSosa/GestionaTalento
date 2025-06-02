package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parametros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parametro {
    
    @Id
    @Column(name = "cod_parametro")
    private String codParametro;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valor")
    private String valor;
    
}