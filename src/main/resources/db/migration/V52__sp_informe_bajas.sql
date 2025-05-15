CREATE OR REPLACE FUNCTION sp_informe_bajas(p_periodo VARCHAR(6))
RETURNS TABLE (
    "C.I.N°" VARCHAR(20),
    "APELLIDOS" VARCHAR(100),
    "NOMBRES" VARCHAR(100),
    "DEPENDENCIA" VARCHAR(200),
    "DEPARTAMENTO O DIVISION" VARCHAR(200),
    "CARGO" VARCHAR(200),
    "SALARIO" NUMERIC(15,2),
    "ref" VARCHAR(50),
    "FECHA_ALTA" DATE
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
    SELECT * FROM v_informe_bajas
    WHERE "FECHA_ALTA" BETWEEN v_fecha_inicio AND v_fecha_fin;
END;
$$ LANGUAGE plpgsql;
