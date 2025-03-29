package com.gestionatalento.gestiona_talento.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "titulo_universitario")
    private String tituloUniversitario;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "pais_nacimiento")
    private String paisNacimiento;

    @Column(name = "fec_nacimiento")
    private LocalDate fecNacimiento;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    
    private Boolean discapacidad;
    
    @Column(name = "obs_discapacidad")
    private String obsDiscapacidad;
    
    @Column(name = "ruta_foto")
    private String rutaFoto;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Correo> correo;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> telefono;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Justificativo> justificativos;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Comisionado> comisionados;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<PlanillaSalario> planillaSalario;

    




}
