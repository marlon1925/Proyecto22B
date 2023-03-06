# Proyecto22B
CAPTURAS DE PANTALLA

![image](https://user-images.githubusercontent.com/117753844/223015979-bd890804-8319-465e-b360-43be4938cd4c.png)


Login administrador

![image](https://user-images.githubusercontent.com/117753844/223016007-0138eb31-6b52-4ca2-b50a-a0dbabc1462a.png)


Login cajero
![image](https://user-images.githubusercontent.com/117753844/223016080-251a1f72-3b66-4ec5-ae94-906292849c10.png)


Inicio Administrador
![image](https://user-images.githubusercontent.com/117753844/223016118-bfc74879-aeac-45c4-9e55-2997739c160e.png)


Inicio Cajero
![image](https://user-images.githubusercontent.com/117753844/223016143-fded1d15-5cdc-456f-bf27-617be97ed950.png)


CRUD de Productos
![image](https://user-images.githubusercontent.com/117753844/223016175-14b46008-21cf-431b-9ff8-985ba1634d48.png)



Pestaña de Ventas
![image](https://user-images.githubusercontent.com/117753844/223016209-a8a6a59a-a855-489b-a635-83d0bc913b4f.png)



Pantalla de agregar cajeros
![image](https://user-images.githubusercontent.com/117753844/223016266-7a84f88b-6b03-4503-a279-dc5cf0044173.png)






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
