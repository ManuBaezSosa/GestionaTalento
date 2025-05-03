package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historico_asignacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoAsignacion {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "dependencia")
    private String dependencia;

    @Column(name = "departamento_division")
    private String departamentoDivision;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario_anterior")
    private Double salarioAnterior;

    @Column(name = "diferencia")
    private Double diferencia;

    @Column(name = "salario_actual")
    private Double salarioActual;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    
    @Column(name = "nro_resolucion")
    private String nro_resolucion;
}