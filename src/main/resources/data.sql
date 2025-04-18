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

/*CREAMOS LAS VISTAS TANTO PARA LAS ALTAS, BAJAS Y MODIFICACIONES*/
USE proyecto2; --AQUI DEBE IR EL NOMBRE DEL ESQUEMA DE LA BASE DE DATOS
CREATE VIEW v_informe_altas AS 
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

USE proyecto2;
CREATE VIEW v_informe_bajas AS 
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




 
/*Si solo necesitas insertar datos al iniciar la aplicación, puedes usar un archivo data.sql en src/main/resources/:

📌 Crea el archivo data.sql y agrega tus INSERTs:

sql
Copiar
Editar
INSERT INTO empleados (id_persona, nombre, apellido, email, salario)
VALUES (1, 'Juan', 'Pérez', 'juan.perez@example.com', 50000);

INSERT INTO empleados (id_persona, nombre, apellido, email, salario)
VALUES (2, 'Ana', 'Gómez', 'ana.gomez@example.com', 55000);
📌 Configura Spring Boot para ejecutar data.sql en application.properties o application.yml:

properties
Copiar
Editar
spring.sql.init.mode=always


INSERT IGNORE INTO empleados (id_persona, nombre, apellido, email, salario)
VALUES (1, 'Juan', 'Pérez', 'juan.perez@example.com', 50000);

INSERT IGNORE INTO empleados (id_persona, nombre, apellido, email, salario)
VALUES (2, 'Ana', 'Gómez', 'ana.gomez@example.com', 55000);
*/

/*
🔹 Opción 3: Configurar Spring Boot para ejecutar data.sql solo en la primera ejecución
En application.properties, usa esta configuración para que data.sql solo se ejecute cuando la base de datos esté vacía:

properties
Copiar
Editar
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=update
Si ddl-auto=update, Spring Boot solo creará tablas si no existen y luego ejecutará data.sql solo la primera vez.

✅ Ventaja: Spring Boot se encarga de manejar la base de datos sin sobrescribir datos.
❌ Desventaja: No funciona si la base de datos ya está poblada y se cambia ddl-auto.
*/