package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_persona", nullable = false)
    private Long codPersona;
    
    @Column(name = "nro_documento", nullable = false, unique = true)
    private String nroDocumento;
    
    @Column(name = "nro_ruc")
    private String nroRuc;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "cod_nivel_estudio", nullable = false)
    private String codNivelEstudio;

    @ManyToOne
    @JoinColumn(name = "cod_pais_nacimiento", referencedColumnName = "cod_pais", nullable = false)
    private Pais pais;

    @Column(name = "fec_nacimiento", nullable = false)
    private LocalDate fecNacimiento;

    @Column(name = "lugar_nacimiento", nullable = false)
    private String lugarNacimiento;
    
    @Column(name = "posee_discapacidad", nullable = false)
    private String poseeDiscapacidad;
    
    @Column(name = "descripcion_discapacidad")
    private String descripcionDiscapacidad;
    
    @Column(name = "ruta_foto")
    private String rutaFoto;
    
    @ManyToOne
    @JoinColumn(name = "cod_estado_civil", referencedColumnName = "cod_estado_civil")
    private EstadoCivil estadoCivil;

}
