CREATE OR REPLACE FUNCTION sp_informe_historico_asignacion(p_periodo VARCHAR(6))
RETURNS TABLE (
    "N" BIGINT,
    "C.I.N°" VARCHAR(20),
    "APELLIDOS" VARCHAR(100),
    "NOMBRES" VARCHAR(100),
    "DEPENDENCIA" VARCHAR(200),
    "DEPARTAMENTO O DIVISION" VARCHAR(200),
    "CARGO" VARCHAR(200),
    "SAL. ANTERIOR" NUMERIC(15,2),
    "DIFERENCIA" NUMERIC(15,2),
    "SAL. ACTUAL" NUMERIC(15,2),
    "REF" VARCHAR(50)
) AS $$
DECLARE
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
    v_mes INT;
    v_anio INT;
BEGIN
    v_mes := CAST(SUBSTRING(p_periodo, 1, 2) AS INT);
    v_anio := CAST(SUBSTRING(p_periodo, 3, 4) AS INT);

    v_fecha_inicio := TO_DATE(v_anio || LPAD(v_mes::TEXT, 2, '0') || '01', 'YYYYMMDD');
    v_fecha_fin := (v_fecha_inicio + INTERVAL '1 month') - INTERVAL '1 day';

    RETURN QUERY 
    SELECT 
        ROW_NUMBER() OVER() AS "N",
        nro_documento AS "C.I.N°",
        apellidos AS "APELLIDOS",
        nombres AS "NOMBRES",
        dependencia AS "DEPENDENCIA",
        departamento_division AS "DEPARTAMENTO O DIVISION",
        cargo AS "CARGO",
        salario_anterior AS "SAL. ANTERIOR",
        diferencia AS "DIFERENCIA",
        salario_actual AS "SAL. ACTUAL",
        nro_resolucion AS "REF"
    FROM historico_asignacion
    WHERE fecha_modificacion BETWEEN v_fecha_inicio AND v_fecha_fin
    ORDER BY fecha_modificacion DESC;
END;
$$ LANGUAGE plpgsql;