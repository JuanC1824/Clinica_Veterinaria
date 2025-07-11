CREATE DATABASE IF NOT EXISTS bd_hibernate;
USE bd_hibernate;

CREATE TABLE mascotas (
    id_mascota INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45),
    raza VARCHAR(45),
    color VARCHAR(45),
    sexo VARCHAR(45),
    PRIMARY KEY (id_mascota)
);
