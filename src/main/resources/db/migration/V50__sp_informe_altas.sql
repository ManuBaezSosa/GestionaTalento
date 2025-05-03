CREATE PROCEDURE sp_informe_altas(
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
    
    -- Ejecutar el informe de altas
    SELECT * FROM v_informe_altas 
    WHERE FECHA_ALTA BETWEEN v_fecha_inicio AND v_fecha_fin;
END;