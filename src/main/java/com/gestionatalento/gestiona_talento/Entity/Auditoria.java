package com.gestionatalento.gestiona_talento.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(schema = "gestiona", name = "auditorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auditoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_registro")
    private Long nroRegistro;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "esquema")
    private String esquema;

    @Column(name = "tabla")
    private String tabla;

    @Column(name = "pk_referencia")
    private String pkReferencia;

    @Column(name = "operacion")
    private String operacion;
    
    @Column(name = "dato_anterior", columnDefinition = "TEXT")
    private String datoAnterior;

    @Column(name = "dato_nuevo", columnDefinition = "TEXT")
    private String datoNuevo;

    @Column(name = "usuario", nullable = false)
    private String usuario;

}