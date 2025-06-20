DELETE FROM PARAMETROS_SALARIALES;
DELETE FROM GRADOS_SALARIALES;
DELETE FROM SUBPROGRAMAS;
DELETE FROM PROGRAMAS;
DELETE FROM OBJETOS_GASTOS;
DELETE FROM FUENTES_FINANCIAMIENTOS;
DELETE FROM PRESUPUESTOS;
DELETE FROM DESCUENTOS_SALARIALES;
DELETE FROM HORAS_EXTRAS;
DELETE FROM EVENTOS;
DELETE FROM JUSTIFICATIVOS;
DELETE FROM MARCACIONES_MANUALES;
DELETE FROM EMPLEADOS;
DELETE FROM PERSONAS;
DELETE FROM USUARIOS;
DELETE FROM SITUACIONES_LABORALES;
DELETE FROM TIPOS_JUSTIFICATIVOS;
DELETE FROM TIPOS_EVENTOS;
DELETE FROM SEDES;
DELETE FROM ESTADOS_CIVILES;
DELETE FROM CARGOS;
DELETE FROM DEPARTAMENTOS;
DELETE FROM DIRECCIONES;
DELETE FROM PARAMETROS;
DELETE FROM PERIODOS;
DELETE FROM PAISES;
DELETE FROM TIPOS_DOCUMENTOS;

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
VALUES (1, true, '$2a$10$foCsZkbjViW4kioy84PPAupIk8G.kb80X29q6wKtV3mDpYTqkk/J6', 'juan'); /*Contraseña: 12345*/

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (1, 'S', 'Reposo Medico');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (2, 'S', 'Permiso por Maternidad');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (3, 'S', 'Permiso por Lactancia');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (4, 'S', 'Permiso por Paternidad');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (5, 'S', 'Duelo');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (6, 'S', 'Permiso Particular');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (7, 'S', 'Orden de Trabajo');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (8, 'S', 'Otros');

insert into TIPOS_JUSTIFICATIVOS (cod_tip_justificativo, ACTIVO, descripcion)
values (9, 'S', 'Vacaciones');


insert into TIPOS_EVENTOS (cod_tip_evento, ACTIVO, descripcion)
values (1, 'S', 'Feriado');

insert into TIPOS_EVENTOS (cod_tip_evento, ACTIVO, descripcion)
values (2, 'S', 'Asueto');

insert into TIPOS_EVENTOS (cod_tip_evento, ACTIVO, descripcion)
values (3, 'S', 'Otros');

insert into TIPOS_EVENTOS (cod_tip_evento, ACTIVO, descripcion)
values (4, 'S', 'Con Exoneracion de Entrada');

insert into TIPOS_EVENTOS (cod_tip_evento, ACTIVO, descripcion)
values (5, 'S', 'Con Exoneracion de Salida');

INSERT INTO programas
(cod_programa, descripcion, cod_situacion_laboral)
VALUES(1, 'Legislativo Municipal', 2);
INSERT INTO programas
(cod_programa, descripcion, cod_situacion_laboral)
VALUES(2, 'Ejecutivo Municipal', 2);
INSERT INTO programas
(cod_programa, descripcion, cod_situacion_laboral)
VALUES(3, 'Ejecutivo Municipal', 1);

INSERT INTO subprogramas
(cod_subprograma, descripcion)
VALUES(1, 'Junta Municipal');
INSERT INTO subprogramas
(cod_subprograma, descripcion)
VALUES(2, 'Intendencia Municipal');
INSERT INTO subprogramas
(cod_subprograma, descripcion)
VALUES(3, 'Administracion y Finanzas');
INSERT INTO subprogramas
(cod_subprograma, descripcion)
VALUES(5, 'Obras y Servicios Municipales');

INSERT INTO presupuestos
(cod_presupuesto, descripcion)
VALUES(1, 'Actividades Centrales');
INSERT INTO presupuestos
(cod_presupuesto, descripcion)
VALUES(2, 'Servicios Personales');

INSERT INTO fuentes_financiamientos
(cod_fuente_financiamiento, descripcion)
VALUES(1, 'Recursos Propios');
INSERT INTO fuentes_financiamientos
(cod_fuente_financiamiento, descripcion)
VALUES(2, 'Recursos Propios, Royalties y Compensaciones Según Disponibilidad');
INSERT INTO fuentes_financiamientos
(cod_fuente_financiamiento, descripcion)
VALUES(30, 'Recursos Institucionales');

INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(1, 'Honorarios Profesionales', 1);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(2, 'Contratación del Personal Técnico', 1);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(3, 'Jornal - Royalties y Compensaciones', 1);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(4, 'Jornales', 2);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(5, 'Jornal - Fondos Propios 60%', 1);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(111, 'Sueldos', 30);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(112, 'Dietas', 30);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(113, 'Gastos de Representación', 30);
INSERT INTO objetos_gastos
(cod_objeto_gasto, descripcion, cod_fuente_financiamiento)
VALUES(114, 'Aguinaldo Personal Permanente', 30);

INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 1, 2, 30, 112, 1);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 1, 2, 30, 113, 1);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 2, 2, 30, 111, 1);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 2, 2, 30, 111, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 2, 2, 30, 111, 3);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 2, 2, 30, 111, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(1, 2, 2, 30, 113, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 1, 1);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 1, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 1, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 2, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 3, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 3, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 5, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 1, 5, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 2, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 3, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 4, 1);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 4, 2);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 4, 3);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 2, 4, 5);
INSERT INTO parametros_salariales
(cod_presupuesto, cod_programa, cod_situacion_laboral, cod_fuente_financiamiento, cod_objeto_gasto, cod_subprograma)
VALUES(2, 3, 1, 30, 2, 2);

INSERT INTO parametros
(cod_parametro, descripcion, valor)
VALUES('PERIODO_HORAS_EXTRAS', 'Periodo en curso de gestión de horas extras', '2025/05');
INSERT INTO parametros
(cod_parametro, descripcion, valor)
VALUES('PERIODO_DESCUENTOS_SALARIALES', 'Periodo en curso de gestión de descuentos salariales', '2025/05');
INSERT INTO parametros
(cod_parametro, descripcion, valor)
VALUES('PERIODO_PLANILLA_SALARIOS', 'Periodo en curso de gestión de planilla de salarios', '2025/05');

INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(1, 'ENERO 2025', '2025/01', 'Periodo Correspondiente a Enero del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(2, 'FEBRERO 2025', '2025/02', 'Periodo Correspondiente a Febrero del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(3, 'MARZO 2025', '2025/03', 'Periodo Correspondiente a Marzo del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(4, 'ABRIL 2025', '2025/04', 'Periodo Correspondiente a Abril del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(5, 'MAYO 2025', '2025/05', 'Periodo Correspondiente a Mayo del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(6, 'JUNIO 2025', '2025/06', 'Periodo Correspondiente a Junio del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(7, 'JULIO 2025', '2025/07', 'Periodo Correspondiente a Julio del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(8, 'AGOSTO 2025', '2025/08', 'Periodo Correspondiente a Agosto del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(9, 'SEPTIEMBRE 2025', '2025/09', 'Periodo Correspondiente a Septiembre del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(10, 'OCTUBRE 2025', '2025/10', 'Periodo Correspondiente a Octubre del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(11, 'NOVIEMBRE 2025', '2025/11', 'Periodo Correspondiente a Noviembre del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(12, 'DICIEMBRE 2025', '2025/12', 'Periodo Correspondiente a Diciembre del 2025');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(13, 'ENERO 2026', '2026/01', 'Periodo Correspondiente a Enero del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(14, 'FEBRERO 2026', '2026/02', 'Periodo Correspondiente a Febrero del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(15, 'MARZO 2026', '2026/03', 'Periodo Correspondiente a Marzo del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(16, 'ABRIL 2026', '2026/04', 'Periodo Correspondiente a Abril del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(17, 'MAYO 2026', '2026/05', 'Periodo Correspondiente a Mayo del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(18, 'JUNIO 2026', '2026/06', 'Periodo Correspondiente a Junio del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(19, 'JULIO 2026', '2026/07', 'Periodo Correspondiente a Julio del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(20, 'AGOSTO 2026', '2026/08', 'Periodo Correspondiente a Agosto del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(21, 'SEPTIEMBRE 2026', '2026/09', 'Periodo Correspondiente a Septiembre del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(22, 'OCTUBRE 2026', '2026/10', 'Periodo Correspondiente a Octubre del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(23, 'NOVIEMBRE 2026', '2026/11', 'Periodo Correspondiente a Noviembre del 2026');
INSERT INTO periodos
(nro_periodo, abreviatura, cod_periodo, descripcion)
VALUES(24, 'DICIEMBRE 2026', '2026/12', 'Periodo Correspondiente a Diciembre del 2026');

INSERT INTO PAISES (COD_PAIS, DESCRIPCION)
VALUES(1, 'PARAGUAY');

INSERT INTO PAISES (COD_PAIS, DESCRIPCION)
VALUES(2, 'ARGENTINA');

INSERT INTO PAISES (COD_PAIS, DESCRIPCION)
VALUES(3, 'BRASIL');

INSERT INTO PAISES (COD_PAIS, DESCRIPCION)
VALUES(4, 'PARAGUAY');

INSERT INTO GRADOS_SALARIALES(NRO_GRADO, descripcion, VALOR_MINIMO, VALOR_MAXIMO)
VALUES(0, 'Grado Salarial Contratado', 0, 999999999);

INSERT INTO TIPOS_DOCUMENTOS(COD_TIPO_DOCUMENTO, ACTIVO, descripcion, tip_categoria)
VALUES(1, 'S', 'CONTRATO', 'E');

INSERT INTO TIPOS_DOCUMENTOS(COD_TIPO_DOCUMENTO, ACTIVO, descripcion, tip_categoria)
VALUES(2, 'S', 'TITULO UNIVERSITARIO', 'P');

INSERT INTO TIPOS_DOCUMENTOS(COD_TIPO_DOCUMENTO, ACTIVO, descripcion, tip_categoria)
VALUES(3, 'S', 'FOTOCOPIA DE CEDULA DE IDENTIDAD', 'P');

insert into tipos_documentos(cod_tipo_documento, activo, descripcion, tip_categoria)
values(4, 'S', 'MEMORANDUM', 'G');