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
@Table(name = "Personas", schema = "prueba")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_persona")
    private Long codPersona;

    @OneToMany(mappedBy = "persona")
    private List<Empleado> empleados;
    
    @Column(name = "nro_documento")
    private String nroDocumento;
    
    @Column(name = "nro_ruc")
    private String nroRuc;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "cod_nivel_estudio")
    private String codNivelEstudio;

    @Column(name = "cod_pais_nacimiento")
    private Long codPaisNacimiento;

    @Column(name = "fec_nacimiento")
    private LocalDate fecNacimiento;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    
    @Column(name = "posee_discapacidad")
    private String poseeDiscapacidad;
    
    @Column(name = "descripcion_discapacidad")
    private String descripcionDiscapacidad;
    
    @Column(name = "ruta_foto")
    private String rutaFoto;
    
    @ManyToOne
    @JoinColumn(name = "cod_estado_civil", referencedColumnName = "cod_estado_civil")
    private EstadoCivil estadoCivil;
    
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
