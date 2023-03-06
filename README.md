# Proyecto final de Programación Orientada a Objetos
------------------

Integrantes: 
- Guayanay Wilson
- Lalangui Marlon
- Molina Melani
- Josué Salazar


-------------------------

Enlace del video:

https://www.facebook.com/100054393104975/videos/3160328874258870/



-------------------------
CAPTURAS DE PANTALLA

![image](https://user-images.githubusercontent.com/117753844/223015979-bd890804-8319-465e-b360-43be4938cd4c.png)

--------------------


Login administrador



![image](https://user-images.githubusercontent.com/117753844/223016007-0138eb31-6b52-4ca2-b50a-a0dbabc1462a.png)


--------------------------


Login cajero


![image](https://user-images.githubusercontent.com/117753844/223016080-251a1f72-3b66-4ec5-ae94-906292849c10.png)

------------------------


Inicio Administrador



![image](https://user-images.githubusercontent.com/117753844/223016118-bfc74879-aeac-45c4-9e55-2997739c160e.png)

---------------------------



Inicio Cajero



![image](https://user-images.githubusercontent.com/117753844/223016143-fded1d15-5cdc-456f-bf27-617be97ed950.png)


----------------------


CRUD de Productos



![image](https://user-images.githubusercontent.com/117753844/223016175-14b46008-21cf-431b-9ff8-985ba1634d48.png)


----------------------



Pestaña de Ventas


![image](https://user-images.githubusercontent.com/117753844/223016209-a8a6a59a-a855-489b-a635-83d0bc913b4f.png)


-------------------


Pantalla de agregar cajeros


![image](https://user-images.githubusercontent.com/117753844/223016266-7a84f88b-6b03-4503-a279-dc5cf0044173.png)



----------------------


--- 
SCRIPT DE LA BASE DE DATOS
---

CREATE DATABASE MINIMARKET
USE MINIMARKET
drop database MINIMARKET
DROP TABLE ADMINISTRADOR
DROP TABLE CAJERO
DROP TABLE PRODUCTOS
DROP TABLE VENTAS
DROP TABLE FACTURA


CREATE TABLE ADMINISTRADOR(
                              CEDULA_ADMIN NUMERIC(10) PRIMARY KEY NOT NULL,
                              NOMBRE_ADMIN VARCHAR(25) NOT NULL,
                              CONTRASEÑA_ADMIN VARCHAR(20) NOT NULL
);

CREATE TABLE CAJERO(
                       CEDULA_CAJERO NUMERIC(10) PRIMARY KEY NOT NULL,
                       NOMBRE_CAJERO VARCHAR(15) NOT NULL,
                       APELLIDO_CAJERO VARCHAR(15) NOT NULL,
                       CONTRASEÑA_CAJERO VARCHAR(20) NOT NULL
);

INSERT INTO CAJERO VALUES (1727203059,"Adrian", "Lalangui","cajero1");

CREATE TABLE VENTAS(
                       COD_VENTA VARCHAR(5) PRIMARY KEY NOT NULL,
                       Producto_Venta VARCHAR(20) NOT NULL,
                       PRECIOPROD_VENTA FLOAT NOT NULL,
                       CANTIDAD_VENTA NUMERIC(5) NOT NULL,
                       TOTAL FLOAT NOT NULL
);
ALTER TABLE VENTAS
    ADD CEDULA_CAJEROFK NUMERIC(10) NOT NULL REFERENCES CAJERO (CEDULA_CAJERO);

ALTER TABLE VENTAS ADD COD_PRODFK VARCHAR(5) NOT NULL REFERENCES PRODUCTOS (COD_PROD);

INSERT INTO VENTAS VALUES('V001', 'Atun','1.30', '2', '2.60', '1727203059', 'P001');

SELECT * FROM VENTAS;

SELECT COD_VENTA, COD_PRODFK, PRODUCTO_VENTA, CANTIDAD_VENTA, PRECIOPROD_VENTA FROM ventas 
WHERE CEDULA_CAJEROFK = '1727203059'; 

CREATE TABLE FACTURA(
    NomPro_FACT VARCHAR (20) NOT NULL,
    CANTIDAD_FACT NUMERIC(5) NOT NULL,
    PRECIO_FACT FLOAT NOT NULL,
	TOTAL_FACT FLOAT NOT NULL
    );


CREATE TABLE PRODUCTOS(
                          COD_PROD VARCHAR(10) PRIMARY KEY NOT NULL,
                          NOM_PROD VARCHAR(20) NOT NULL,
                          PRECIO FLOAT NOT NULL,
                          STOCK NUMERIC NOT NULL
);

    INSERT INTO PRODUCTOS VALUES("P001","Atun",1.30,25);
    
select * from PRODUCTOS;


    INSERT INTO ADMINISTRADOR VALUES(1718688284,"Wilson","patito1"),(1726025040,"Marlon","patito2"),(0504871195,"Melani","patito3"),(1723359327,"Josue","patito");

select * from CAJERO;
select * from PRODUCTOS;
SELECT * FROM ADMINISTRADOR;
SELECT * FROM VENTAS;
SELECT * FROM FACTURA;



