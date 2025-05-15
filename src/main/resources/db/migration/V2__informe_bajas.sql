CREATE OR REPLACE VIEW v_informe_bajas AS
SELECT
    p.nro_documento AS "C.I.N°",
    p.apellidos AS "APELLIDOS",
    p.nombres AS "NOMBRES",
    s.descripcion AS "DEPENDENCIA",
    c.descripcion AS "DEPARTAMENTO O DIVISION",
    sl.descripcion AS "CARGO",
    e.asignacion AS "SALARIO",
    e.nro_resolucion AS "ref",
    e.fec_ingreso AS "FECHA_ALTA"
FROM empleados e
JOIN personas p ON e.cod_persona = p.cod_persona
JOIN cargos c ON e.cod_cargo = c.cod_cargo
JOIN sedes s ON e.cod_sede = s.cod_sede
JOIN situaciones_laborales sl ON e.cod_situacion_laboral = sl.cod_situacion_laboral
WHERE e.estado = 'I'  -- Empleados inactivos
ORDER BY e.fec_ingreso DESC;