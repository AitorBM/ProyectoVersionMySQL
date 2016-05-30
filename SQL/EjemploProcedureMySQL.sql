DELIMITER |
CREATE PROCEDURE INSERT_CATEGORIA (P_NOM varchar(100))
BEGIN
	DECLARE V_ID INT;
	SELECT MAX(ID_CAT)+1 INTO V_ID FROM CATEGORIAS;
	INSERT INTO CATEGORIAS(ID_CAT, NOMBRE) VALUES(V_ID, P_NOM);
END |