CREATE OR REPLACE FUNCTION fn_actualizar_historico_asignacion()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.asignacion IS DISTINCT FROM OLD.asignacion THEN
        INSERT INTO historico_asignacion (
            nro_documento,
            apellidos,
            nombres,
            dependencia,
            departamento_division,
            cargo,
            salario_anterior,
            diferencia,
            salario_actual,
            fecha_modificacion,
            usuario_modificacion,
            nro_resolucion
        )
        SELECT 
            p.nro_documento,
            p.apellidos,
            p.nombres,
            s.descripcion,
            c.descripcion,
            sl.descripcion,
            OLD.asignacion,
            NEW.asignacion - OLD.asignacion,
            NEW.asignacion,
            NOW(),
            CURRENT_USER,
            NEW.nro_resolucion
        FROM personas p
        JOIN cargos c ON NEW.cod_cargo = c.cod_cargo
        JOIN sedes s ON NEW.cod_sede = s.cod_sede
        JOIN situaciones_laborales sl ON NEW.cod_situacion_laboral = sl.cod_situacion_laboral
        WHERE p.cod_persona = NEW.cod_persona;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Eliminar el trigger si existe
DROP TRIGGER IF EXISTS tr_actualizar_historico_asignacion ON empleados;

-- Crear el trigger
CREATE TRIGGER tr_actualizar_historico_asignacion
AFTER UPDATE ON empleados
FOR EACH ROW
EXECUTE FUNCTION fn_actualizar_historico_asignacion();
