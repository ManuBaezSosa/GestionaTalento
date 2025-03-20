package com.gestionatalento.gestiona_talento.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRequest {
    private String tipoBusqueda;
    private String valor;
}
