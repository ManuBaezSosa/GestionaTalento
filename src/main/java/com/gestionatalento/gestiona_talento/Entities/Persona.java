package com.gestionatalento.gestiona_talento.Entities;

import java.time.LocalDate;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Personas", schema = "prueba")
public class Persona {
    
    @Id
    @Column(name = "cod_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPersona;
    
    @Column(name = "nro_documento")
    private String nroDocumento;
    
    private String ruc;
    private String nombre;
    private String apellido;
    
    @Column(name = "fec_nacimiento")
    private LocalDate fecNacimiento;
    
    @Column(name = "cod_pais_nacimiento")
    private String codPaisNacimiento;
    
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    
    private Boolean discapacidad;
    
    @Column(name = "obs_discapacidad")
    private String obsDiscapacidad;
    
    @Column(name = "ruta_foto")
    private String rutaFoto;




}
