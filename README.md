# Proyecto22B

--- 
BD
---
CREATE DATABASE REGISTROS 
USE REGISTROS	

CREATE TABLE ADMINISTRADOR (
    ID_Admin NUMERIC(10) PRIMARY KEY NOT NULL,
	Contra_Admin VARCHAR(35) NOT NULL,
    Nombre_Admin VARCHAR(15) NOT  NULL,
    Apellido_Admin VARCHAR(15) NOT  NULL,
    Edad_Admin NUMERIC(2) NOT NULL,
    Email_Admin VARCHAR(35) NOT NULL 
)

CREATE TABLE CAJERO (
    ID_Cajero NUMERIC(10) PRIMARY KEY NOT NULL,
    Contra_Cajero VARCHAR(35) NOT NULL,
    Genero_Cajero VARCHAR(15) ,
    Nombre_Cajero VARCHAR(15) NOT  NULL,
    Apellido_Cajero VARCHAR(15) NOT  NULL,
    Edad_Cajero NUMERIC(2) NOT NULL,
    Email_Cajero  VARCHAR(35) NOT NULL 
)
INSERT INTO ADMINISTRADOR VALUES('0504871195','admin123','Josue','Salazar','20'
                                  ,'josue.salazar@epn.edu.ec' )
                                  
INSERT INTO ADMINISTRADOR VALUES('1704871286','admin123','Melani','Molina','20'
                                  ,'melani.molina@epn.edu.ec' )  
                                  
INSERT INTO ADMINISTRADOR VALUES('1715982397','admin123','Wilson','Guayanay','20'
                                  ,'wilson.guayanay@epn.edu.ec' )  
                                  
INSERT INTO ADMINISTRADOR VALUES('1726093489','admin123','Marlon','Lalangui','20'
                                  ,'marlon.lalanguig@epn.edu.ec' )  
SELECT*FROM ADMINISTRADOR
 
 INSERT INTO CAJERO VALUES('1765437689','Caja1234','Mujer','Adriana','Morales','25'
                                  ,'adriana.morales@gmail.com' )
SELECT*FROM CAJERO
