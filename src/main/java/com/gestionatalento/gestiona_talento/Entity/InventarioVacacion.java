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
@Table(name = "inventarios_vacaciones")
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

}