package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

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
@Table(schema = "gestiona", name = "inventarios_vacaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioVacacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_inventario")
    private Long nroInventario;

    @ManyToOne
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado", unique = true)
    private Empleado empleado;
    
    @Column(name = "cantidad_generado")
    private int cantidadGenerada;

    @Column(name = "cantidad_utilizado")
    private int cantidadUtilizado;

    @Column(name = "cantidad_reservado")
    private int cantidadReservado;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fec_ultima_generacion")
    private Date fecUltimaGeneracion;

    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_actualizacion", nullable = false)
    private String usuarioActualizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}