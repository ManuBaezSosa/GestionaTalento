
-- 3. CREAMOS UN PROCEDIMINENTO DONDE PUEDA REFLEJAR EL INFORME CORRESPONDIENTE
CREATE PROCEDURE sp_informe_historico_asignacion(
    IN p_periodo VARCHAR(6) -- Formato "MMAAAA" (ej. "032025" para marzo 2025)
)
BEGIN
    DECLARE v_fecha_inicio DATE;
    DECLARE v_fecha_fin DATE;
    
    -- Extraer el mes y año del período
    SET @mes = SUBSTRING(p_periodo, 1, 2);
    SET @anio = SUBSTRING(p_periodo, 3, 4);
    
    -- Calcular el primer y último día del mes
    SET v_fecha_inicio = STR_TO_DATE(CONCAT(@anio, '-', @mes, '-01'), '%Y-%m-%d');
    SET v_fecha_fin = LAST_DAY(v_fecha_inicio);
    
    -- Ejecutar el informe de modificaciones de asignación
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
    FROM 
        historico_asignacion
    WHERE 
        fecha_modificacion BETWEEN v_fecha_inicio AND v_fecha_fin
    ORDER BY 
        fecha_modificacion DESC;
END;