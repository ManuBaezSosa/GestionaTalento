package com.gestionatalento.gestiona_talento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
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
@Table(name = "parametros_salariales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametroSalarial {
    
    @EmbeddedId
    private ParametroSalarialPK id;

    @ManyToOne
    @JoinColumn(name = "cod_fuente_financiamiento", referencedColumnName = "cod_fuente_financiamiento", insertable = false, updatable = false)
    private FuenteFinanciamiento fuenteFinanciamiento;

    @ManyToOne
    @JoinColumn(name = "cod_presupuesto", referencedColumnName = "cod_presupuesto", insertable = false, updatable = false)
    private Presupuesto presupuesto;

    // Repite esto con los demás campos:
    @ManyToOne
    @JoinColumn(name = "cod_programa", referencedColumnName = "cod_programa", insertable = false, updatable = false)
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "cod_situacion_laboral", referencedColumnName = "cod_situacion_laboral", insertable = false, updatable = false)
    private SituacionLaboral situacionLaboral;

    @ManyToOne
    @JoinColumn(name = "cod_objeto_gasto", referencedColumnName = "cod_objeto_gasto", insertable = false, updatable = false)
    private ObjetoGasto objetoGasto;

    @ManyToOne
    @JoinColumn(name = "cod_subprograma", referencedColumnName = "cod_subprograma", insertable = false, updatable = false)
    private Subprograma subPrograma;
    
}