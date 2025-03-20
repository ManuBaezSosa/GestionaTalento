package com.gestionatalento.gestiona_talento.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private String username;
    private String password;
    private String nombreCompleto;
    private String documento;
    private String cargo;
    private String role; // "ADMIN" o "USER"

     // Helper method
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }

}
