package com.gestionatalento.gestiona_talento.Entity;

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
@Table(name = "departamentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_departamento")
    private Long codDepartamento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cod_direccion", referencedColumnName = "cod_direccion")
    private Direccion direccion;
}