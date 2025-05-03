DELETE FROM DESCUENTOS_SALARIALES;
DELETE FROM EMPLEADOS;
DELETE FROM PERSONAS;
DELETE FROM USUARIOS;
DELETE FROM SITUACIONES_LABORALES;
DELETE FROM SEDES;
DELETE FROM ESTADOS_CIVILES;
DELETE FROM CARGOS;
DELETE FROM DEPARTAMENTOS;
DELETE FROM DIRECCIONES;

INSERT INTO DIRECCIONES (COD_DIRECCION, DESCRIPCION, ESTADO)
VALUES (1, 'DIRECCION DE GESTION Y DESARROLLO DE PERSONAS', 'A');

INSERT INTO DIRECCIONES (COD_DIRECCION, DESCRIPCION, ESTADO)
VALUES (2, 'DIRECCION DE GESTION AMBIENTAL Y DESARROLLO HUMANO', 'A');

INSERT INTO DIRECCIONES (COD_DIRECCION, DESCRIPCION, ESTADO)
VALUES (3, 'DIRECCION DE TRANSITO', 'A');

INSERT INTO DIRECCIONES (COD_DIRECCION, DESCRIPCION, ESTADO)
VALUES (4, 'DIRECCION DE PMT', 'A');

INSERT INTO DIRECCIONES (COD_DIRECCION, DESCRIPCION, ESTADO)
VALUES (5, 'DIRECCION DE ADMINISTRACION Y FINANZAS', 'A');

INSERT INTO DEPARTAMENTOS (COD_DEPARTAMENTO, DESCRIPCION, ESTADO, COD_DIRECCION)
VALUES (1, 'DEPARTAMENTO DE ADMINISTRACION DE PERSONAS', 'A', 1);
INSERT INTO DEPARTAMENTOS (COD_DEPARTAMENTO, DESCRIPCION, ESTADO, COD_DIRECCION)
VALUES (2, 'DEPARTAMENTO DE GESTION AMBIENTAL Y DESARROLLO HUMANO', 'A', 2);
INSERT INTO DEPARTAMENTOS (COD_DEPARTAMENTO, DESCRIPCION, ESTADO, COD_DIRECCION)
VALUES (3, 'DEPARTAMENTO DE TRANSITO', 'A', 3);
INSERT INTO DEPARTAMENTOS (COD_DEPARTAMENTO, DESCRIPCION, ESTADO, COD_DIRECCION)
VALUES (4, 'DEPARTAMENTO PMT', 'A', 4);
INSERT INTO DEPARTAMENTOS (COD_DEPARTAMENTO, DESCRIPCION, ESTADO, COD_DIRECCION)
VALUES (5, 'DEPARTAMENTO DE ADMINISTRACION Y FINANZAS', 'A', 5);

INSERT INTO CARGOS (COD_CARGO, DESCRIPCION, ESTADO, COD_DEPARTAMENTO)
VALUES (1, 'AUXILIAR ADMINISTRATIVO', 'A', 1);
INSERT INTO CARGOS (COD_CARGO, DESCRIPCION, ESTADO, COD_DEPARTAMENTO)
VALUES (2, 'AUXILIAR ADMINISTRATIVO', 'A', 2);
INSERT INTO CARGOS (COD_CARGO, DESCRIPCION, ESTADO, COD_DEPARTAMENTO)
VALUES (3, 'AUXILIAR ADMINISTRATIVO', 'A', 3);
INSERT INTO CARGOS (COD_CARGO, DESCRIPCION, ESTADO, COD_DEPARTAMENTO)
VALUES (4, 'AUXILIAR ADMINISTRATIVO', 'A', 4);
INSERT INTO CARGOS (COD_CARGO, DESCRIPCION, ESTADO, COD_DEPARTAMENTO)
VALUES (5, 'AUXILIAR ADMINISTRATIVO', 'A', 5);

INSERT INTO ESTADOS_CIVILES(COD_ESTADO_CIVIL, DESCRIPCION, ESTADO)
VALUES (1, 'SOLTERO/A', 'A');
INSERT INTO ESTADOS_CIVILES(COD_ESTADO_CIVIL, DESCRIPCION, ESTADO)
VALUES (2, 'CASADO/A', 'A');
INSERT INTO ESTADOS_CIVILES(COD_ESTADO_CIVIL, DESCRIPCION, ESTADO)
VALUES (3, 'VIUDO/A', 'A');

INSERT INTO SEDES(COD_SEDE, DESCRIPCION, ESTADO)
VALUES (1, 'SEDE CENTRAL', 'A');
INSERT INTO SEDES(COD_SEDE, DESCRIPCION, ESTADO)
VALUES (2, 'SEDE ANTIGUA', 'A');

INSERT INTO SITUACIONES_LABORALES(COD_SITUACION_LABORAL, DESCRIPCION, ESTADO)
VALUES (1, 'CONTRATADO', 'A');

INSERT INTO SITUACIONES_LABORALES(COD_SITUACION_LABORAL, DESCRIPCION, ESTADO)
VALUES (2, 'PERMANENTE', 'A');

INSERT INTO SITUACIONES_LABORALES(COD_SITUACION_LABORAL, DESCRIPCION, ESTADO)
VALUES (3, 'COMISIONADO', 'A');

INSERT INTO SITUACIONES_LABORALES(COD_SITUACION_LABORAL, DESCRIPCION, ESTADO)
VALUES (4, 'PASANTIA EDUCATIVA', 'A');

INSERT INTO SITUACIONES_LABORALES(COD_SITUACION_LABORAL, DESCRIPCION, ESTADO)
VALUES (5, 'PASANTIA UNIVERSITARIA', 'A');

INSERT INTO USUARIOS(COD_USUARIO, ADMIN, PASSWORD, USERNAME)
VALUES (1, 1, "$2a$10$foCsZkbjViW4kioy84PPAupIk8G.kb80X29q6wKtV3mDpYTqkk/J6", "juan"); /*Contraseña: 12345*/

--- =============================================================== ---
--- SECCION PROCEDIMINENTOS, TRIGGERS Y VISTAS PARA LOS INFORMES
--- SE DEBE TENER CUIDADO QUE SE DEBE CAMBIAR LA BASE DE DATOS, ES DECIR EN MI CASO ES proyecto2
--- =============================================================== ---

/* CREAMOS LAS VISTAS TANTO PARA LAS ALTAS */
--USE proyecto2; --AQUI DEBE IR EL NOMBRE DEL ESQUEMA DE LA BASE DE DATOS
CREATE OR REPLACE VIEW v_informe_altas AS 
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
FROM 
    empleados e
JOIN 
    personas p ON e.cod_persona = p.cod_persona
JOIN 
    cargos c ON e.cod_cargo = c.cod_cargo
JOIN 
    sedes s ON e.cod_sede = s.cod_sede
JOIN 
    situaciones_laborales sl ON e.cod_situacion_laboral = sl.cod_situacion_laboral
WHERE 
    e.estado = 'A'  -- Ajustado a tu valor para empleados activos
ORDER BY 
    e.fec_ingreso DESC;

/* CREAMOS LAS VISTAS TANTO PARA LAS BAJAS */
--USE proyecto2;-- Se le debe cambiar el nombre por el esquema correcto
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
FROM 
    empleados e
JOIN 
    personas p ON e.cod_persona = p.cod_persona
JOIN 
    cargos c ON e.cod_cargo = c.cod_cargo
JOIN 
    sedes s ON e.cod_sede = s.cod_sede
JOIN 
    situaciones_laborales sl ON e.cod_situacion_laboral = sl.cod_situacion_laboral
WHERE 
    e.estado = 'I'  -- Ajustado a tu valor para empleados activos
ORDER BY 
    e.fec_ingreso DESC;

/* CREAMOS EL PROCEDIMINENTO PARA LAS ALTAS */
DELIMITER //
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
END //
DELIMITER ;
	
--- =============================================================== ---
--- PARA CREAR LOS REPORTES DE CAMBIO DE SALIARIO SE DEDE CREAR
--- =============================================================== ---

-- 1. UNA TABLA DONDE SE ALMACENEN LOS HISTORICOS
CREATE TABLE historico_asignacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nro_documento VARCHAR(20),
    apellidos VARCHAR(100),
    nombres VARCHAR(100),
    dependencia VARCHAR(100),
    departamento_division VARCHAR(100),
    cargo VARCHAR(100),
    salario_anterior DECIMAL(12,2),
    diferencia DECIMAL(12,2),
    salario_actual DECIMAL(12,2),
    fecha_modificacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    usuario_modificacion VARCHAR(50),
    nro_resolucion VARCHAR(50)
);

-- 2. UN TRIGGER DONDE SE PUEDA POBLAR ESTA TABLA
-- Primero elimina el trigger existente
DROP TRIGGER IF EXISTS tr_actualizar_historico_asignacion;
-- Luego crea el nuevo trigger
DELIMITER //
CREATE TRIGGER tr_actualizar_historico_asignacion 
AFTER UPDATE ON empleados
FOR EACH ROW
BEGIN
    IF NEW.asignacion <> OLD.asignacion THEN
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
            fecha_modificacion,  -- Añadida explícitamente
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
            (NEW.asignacion - OLD.asignacion),
            NEW.asignacion,
            NOW(),  -- Función MySQL que devuelve la fecha y hora actual
            CURRENT_USER(),
            NEW.nro_resolucion
        FROM 
            personas p
        JOIN 
            cargos c ON NEW.cod_cargo = c.cod_cargo
        JOIN 
            sedes s ON NEW.cod_sede = s.cod_sede
        JOIN 
            situaciones_laborales sl ON NEW.cod_situacion_laboral = sl.cod_situacion_laboral
        WHERE 
            p.cod_persona = NEW.cod_persona;
    END IF;
END //
DELIMITER ;
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
END //
DELIMITER ;