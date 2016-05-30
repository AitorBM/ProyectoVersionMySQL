CREATE OR REPLACE PACKAGE GEST_PROYECTO_GIFT AS

	PROCEDURE INSERT_CATEGORIA
	(
		P_NOM CATEGORIAS.NOMBRE%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE DELETE_CATEGORIA
	(
		P_NOM CATEGORIAS.NOMBRE%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE UPDATE_CATEGORIA
	(
		P_NOM CATEGORIAS.NOMBRE%TYPE,
		P_NEW_NOM CATEGORIAS.NOMBRE%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE INSERT_PREGUNTA
	(
		P_CATEGORIA CATEGORIAS.NOMBRE%TYPE,
		P_TEXTO PREGUNTAS.TEXTO%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE DELETE_PREGUNTA
	(
		P_ID PREGUNTAS.ID_PRE%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE UPDATE_PREGUNTA
	(
		P_ID PREGUNTAS.ID_PRE%TYPE,
		P_TEXTO PREGUNTAS.TEXTO%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE INSERT_RESPUESTA
	(
		P_PREGUNTA RESPUESTAS.ID_RES%TYPE,
		P_TEXTO RESPUESTAS.TEXTO%TYPE,
		P_CORRECTA RESPUESTAS.CORRECTA%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE DELETE_RESPUESTA
	(
		P_ID RESPUESTAS.ID_RES%TYPE
	);
	-----------------------------------------------------------------
	PROCEDURE UPDATE_RESPUESTA
	(
		P_ID RESPUESTAS.ID_RES%TYPE,
		P_NEW_TEXTO RESPUESTAS.TEXTO%TYPE,
		P_NEW_CORRECTA RESPUESTAS.CORRECTA%TYPE
	);
	-----------------------------------------------------------------
END GEST_PROYECTO_GIFT;