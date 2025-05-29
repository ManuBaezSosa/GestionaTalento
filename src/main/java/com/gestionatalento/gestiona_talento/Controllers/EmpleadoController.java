package com.gestionatalento.gestiona_talento.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionatalento.gestiona_talento.Dto.EmpleadoDto;
import com.gestionatalento.gestiona_talento.Dto.NovedadDto;
import com.gestionatalento.gestiona_talento.Entity.Empleado;
import com.gestionatalento.gestiona_talento.Entity.EmpleadoNovedad;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoNovedadRepository;
import com.gestionatalento.gestiona_talento.Repository.EmpleadoRepository;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;
import com.gestionatalento.gestiona_talento.ServiceImpl.EmpleadoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    // ----------- EMPLEADOS -------- //
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoServiceImpl empleadoServiceImpl;

    @Autowired
    EmpleadoNovedadRepository empleadoNovedadRepository;

    /* Este metodo crea los empleados, se debe de ver el metodo de crearEmpleado en la clase de EmpleadoServiceImpl */
    @PostMapping("/crear")
    public GenericResponse crearEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.crearEmpleado(empleadoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/actualizar")
    public GenericResponse actualizarEmpleado(@Valid @RequestBody EmpleadoDto empleadoDto) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.actualizarEmpleado(empleadoDto);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtenerLista")
    public GenericResponse listarPersonas() {
        GenericResponse genericResponse = new GenericResponse();
        try {
            List<Empleado> empleados = empleadoRepository.findAll();
    
            // Verificamos si la lista está vacía
            if (empleados.isEmpty()) {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existen empleados registrados");
                return genericResponse;
            }
    
            // Creamos una lista de respuesta para agregar los objetos empleados directamente
            List<Empleado> responseList = new ArrayList<>();
    
            // Añadimos cada empleado directamente a la lista de respuesta
            responseList.addAll(empleados);
    
            genericResponse.setCodigoMensaje("200");
            genericResponse.setMensaje("Han sido obtenidos los empleados correctamente");
            genericResponse.setObjeto(responseList);  // Ahora la respuesta es una lista de empleados directamente
    
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/id/{codEmpleado}")
    public GenericResponse buscarEmpleado(@Valid @PathVariable Long codEmpleado) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<Empleado> empleado = empleadoRepository.findById(codEmpleado);
            if (empleado.isPresent()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado obtenido exitosamente");
                genericResponse.setObjeto(empleado.get());
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado registrado con el valor proporcionado. ID: " + codEmpleado);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/documento/{nroDocumento}")
    public GenericResponse buscarEmpleado(@PathVariable String nroDocumento) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            List<Empleado> empleados = empleadoRepository.findByNroDocumento(nroDocumento);

            if (!empleados.isEmpty()) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado obtenido exitosamente");
                genericResponse.setObjeto(empleados);
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado registrado con el valor proporcionado.");
                genericResponse.setDatoAdicional("Nro de Documento: " + nroDocumento);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/obtener/documento/activo/{nroDocumento}")
    public GenericResponse buscarEmpleadoActivo(@PathVariable String nroDocumento) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            Empleado empleados = empleadoRepository.findByNroDocumentoEmpleadoActivo(nroDocumento);

            if (empleados != null) {
                genericResponse.setCodigoMensaje("200");
                genericResponse.setMensaje("Empleado obtenido exitosamente");
                genericResponse.setObjeto(empleados);
                return genericResponse; // Si existe, devolver el objeto
            } else {
                genericResponse.setCodigoMensaje("404");
                genericResponse.setMensaje("No existe empleado activo registrado con el valor proporcionado.");
                genericResponse.setDatoAdicional("Nro de Documento: " + nroDocumento);
                return genericResponse;
            }
        } catch (Exception e){
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @PutMapping("/bajar")
    public GenericResponse bajarEmpleado(@RequestBody EmpleadoDto request){
        GenericResponse genericResponse = new GenericResponse();
        try {
            genericResponse = empleadoServiceImpl.bajarEmpleado(request);
            return genericResponse;
        } catch (Exception e) {
            genericResponse.setCodigoMensaje("500");
            genericResponse.setMensaje("Ha ocurrido un error interno en el servidor: " + e.getMessage());
            return genericResponse;
        }
    }

    @GetMapping("/altas")
    public ResponseEntity<GenericResponse> obtenerInformeAltas(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeAltas(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    @GetMapping("/bajas")
    public ResponseEntity<GenericResponse> obtenerInformeBajas(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeBajas(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    @GetMapping("/modificacionSalario")
    public ResponseEntity<GenericResponse> obtenerInformeHistoricoAsignacion(@RequestParam("periodo") String periodo) {
        GenericResponse response = empleadoServiceImpl.obtenerInformeHistoricoAsignacion(periodo);
        return new ResponseEntity<>(response, getStatusFromCodigo(response.getCodigoMensaje()));
    }

    @GetMapping("/dashboard/obtenerNovedades")
    public List<NovedadDto> obtenerUltimos6Meses() {
        List<String> meses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", new Locale("es", "ES"));
        LocalDate fechaActual = LocalDate.now();
        NovedadDto novedadDto;

        List<NovedadDto> novedades = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            LocalDate mes = fechaActual.minusMonths(i);
            System.out.println(mes);

            String nomMes = mes.format(formatter).toString();
            nomMes = nomMes.toUpperCase();
            int cantidadAltas = empleadoNovedadRepository.findByMesAlta(mes);
            int cantidadBajas = empleadoNovedadRepository.findByMesBaja(mes);

            novedadDto = new NovedadDto();
            novedadDto.setMes(nomMes);
            novedadDto.setCantidadAltas(cantidadAltas);           
            novedadDto.setCantidadBajas(cantidadBajas);          

            novedades.add(novedadDto);
        }

        return novedades;
    }

    private HttpStatus getStatusFromCodigo(String codigo) {
        switch (codigo) {
            case "200": return HttpStatus.OK;
            case "404": return HttpStatus.NOT_FOUND;
            case "409": return HttpStatus.CONFLICT;
            default: return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
