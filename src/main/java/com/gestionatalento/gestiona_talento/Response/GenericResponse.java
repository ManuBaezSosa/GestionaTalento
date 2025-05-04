package com.gestionatalento.gestiona_talento.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private String codigoMensaje;
    private String mensaje;
    private Object objeto;
    private String datoAdicional;
}
