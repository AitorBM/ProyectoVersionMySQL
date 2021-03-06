/* **************************************************************
	PROCEDURES DE CATEGORIA
************************************************************** */
CREATE OR REPLACE PROCEDURE INSERT_CATEGORIA
(
  P_NOM CATEGORIAS.NOMBRE%TYPE
)
AS
	V_ID CATEGORIAS.ID_CAT%TYPE;
BEGIN
	SELECT MAX(ID_CAT)+1 INTO V_ID FROM CATEGORIAS;
	INSERT INTO CATEGORIAS(ID_CAT, NOMBRE) VALUES(V_ID, P_NOM);
END INSERT_CATEGORIA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE DELETE_CATEGORIA
(
	P_NOM CATEGORIAS.NOMBRE%TYPE
)
AS
	V_ID CATEGORIAS.ID_CAT%TYPE;
BEGIN
	SELECT ID_CAT INTO V_ID FROM CATEGORIAS WHERE NOMBRE = P_NOM;
	DELETE FROM CATEGORIAS WHERE ID_CAT = V_ID;
END DELETE_CATEGORIA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UPDATE_CATEGORIA
(
	P_NOM CATEGORIAS.NOMBRE%TYPE,
	P_NEW_NOM CATEGORIAS.NOMBRE%TYPE
)
AS
	V_ID CATEGORIAS.ID_CAT%TYPE;
BEGIN
	SELECT ID_CAT INTO V_ID FROM CATEGORIAS WHERE NOMBRE = P_NOM;
	UPDATE CATEGORIAS SET NOMBRE = P_NEW_NOM WHERE ID_CAT = V_ID;
END UPDATE_CATEGORIA;
-----------------------------------------------------------------

/* **************************************************************
	PROCEDURES DE PREGUNTA
************************************************************** */
CREATE OR REPLACE PROCEDURE INSERT_PREGUNTA
(
	P_CATEGORIA CATEGORIAS.NOMBRE%TYPE,
	P_TEXTO PREGUNTAS.TEXTO%TYPE
)
AS
	V_ID PREGUNTAS.ID_PRE%TYPE;
	V_ID_CATEGORIA CATEGORIAS.ID_CAT%TYPE;
BEGIN
	SELECT MAX(ID_PRE)+1 INTO V_ID FROM PREGUNTAS;
	SELECT ID_CAT INTO V_ID_CATEGORIA FROM CATEGORIAS WHERE NOMBRE = P_CATEGORIA;
	INSERT INTO PREGUNTAS(ID_PRE, TEXTO, CATEGORIAS_ID_CAT) VALUES(V_ID, P_TEXTO, V_ID_CATEGORIA);
END INSERT_PREGUNTA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE DELETE_PREGUNTA
(
	P_ID PREGUNTAS.ID_PRE%TYPE
)
AS
BEGIN
	DELETE FROM PREGUNTAS WHERE ID_PRE = P_ID;
END DELETE_PREGUNTA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UPDATE_PREGUNTA
(
	P_ID PREGUNTAS.ID_PRE%TYPE,
	P_TEXTO PREGUNTAS.TEXTO%TYPE
)
AS
BEGIN
	UPDATE PREGUNTAS SET TEXTO = P_TEXTO WHERE ID_PRE = P_ID;
END UPDATE_PREGUNTA;
-----------------------------------------------------------------

/* **************************************************************
	PROCEDURES DE RESPUESTA
************************************************************** */
CREATE OR REPLACE PROCEDURE INSERT_RESPUESTA
(
  P_PREGUNTA RESPUESTAS.ID_RES%TYPE,
  P_TEXTO RESPUESTAS.TEXTO%TYPE,
  P_CORRECTA RESPUESTAS.CORRECTA%TYPE
)
AS
	V_ID RESPUESTAS.ID_RES%TYPE;
BEGIN
	SELECT MAX(ID_RES)+1 INTO V_ID FROM RESPUESTAS;
	INSERT INTO RESPUESTAS(ID_RES, TEXTO, CORRECTA, PREGUNTAS_ID_PRE) VALUES(V_ID, P_TEXTO, P_CORRECTA, P_PREGUNTA);
END INSERT_RESPUESTA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE DELETE_RESPUESTA
(
	P_ID RESPUESTAS.ID_RES%TYPE
)
AS
BEGIN
	DELETE FROM RESPUESTAS WHERE ID_RES = P_ID;
END DELETE_RESPUESTA;
-----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UPDATE_RESPUESTA
(
	P_ID RESPUESTAS.ID_RES%TYPE,
	P_NEW_TEXTO RESPUESTAS.TEXTO%TYPE,
	P_NEW_CORRECTA RESPUESTAS.CORRECTA%TYPE
)
AS
BEGIN
	IF P_NEW_TEXTO IS NOT NULL THEN
		UPDATE RESPUESTAS SET TEXTO = P_NEW_TEXTO WHERE ID_RES = P_ID;
	END IF;
	IF P_NEW_CORRECTA IS NOT NULL THEN
		UPDATE RESPUESTAS SET CORRECTA = P_NEW_CORRECTA WHERE ID_RES = P_ID;
	END IF;
END UPDATE_RESPUESTA;