CREATE DATABASE MINIMARKET
USE MINIMARKET
drop database MINIMARKET
DROP TABLE ADMINISTRADOR
DROP TABLE CAJERO
DROP TABLE PRODUCTOS
DROP TABLE VENTAS

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
WHERE CEDULA_CAJEROFK = '1727203059' 

CREATE TABLE FACTURA(

	COD_FACT VARCHAR (5) PRIMARY KEY NOT NULL,
    NomPro_FACT VARCHAR (20) NOT NULL,
    CANTIDAD_FACT NUMERIC(5) NOT NULL,
    PRECIO_FACT NUMERIC(5) NOT NULL

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


