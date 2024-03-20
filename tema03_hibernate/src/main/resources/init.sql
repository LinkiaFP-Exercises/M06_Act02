DROP DATABASE IF EXISTS m06Act02Tema03;
CREATE DATABASE m06Act02Tema03;
USE m06Act02Tema03;

CREATE TABLE empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    telefono_contacto VARCHAR(20)
);

CREATE TABLE incidencias (
    id_incidencia INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_empleado_origen INT,
    id_empleado_destino INT,
    detalle VARCHAR(255) NOT NULL,
    tipo CHAR(1) CHECK (tipo IN ('U', 'N')) NOT NULL,
    FOREIGN KEY (id_empleado_origen) REFERENCES empleados(id_empleado) ON DELETE CASCADE,
    FOREIGN KEY (id_empleado_destino) REFERENCES empleados(id_empleado) ON DELETE CASCADE
);

INSERT INTO empleados (nombre_usuario, contrasena, nombre_completo, telefono_contacto) VALUES
    ('agonzalez', 'password', 'Ana Gonzalez', '123456789'),
    ('jramirez', 'password', 'Juan Ramirez', '123456780'),
    ('afernandez', 'password', 'Antonio Fernandez', '123456781'),
    ('smartinez', 'password', 'Sonia Martinez', '123456782'),
    ('lsuarez', 'password', 'Luis Suarez', '123456783');

INSERT INTO incidencias (fecha_hora, id_empleado_origen, id_empleado_destino, detalle, tipo) VALUES
    ('2019-09-21 15:27:14', (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'agonzalez'), (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'jramirez'), 'La impresora no tiene tóner.', 'U'),
    ('2019-09-22 10:28:37', (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'jramirez'), (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'afernandez'), 'No se ha entregado la documentación del expediente EXP324.', 'N'),
    ('2019-09-22 16:28:45', (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'smartinez'), (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'jramirez'), 'No quedan folios.', 'N'),
    ('2019-09-23 11:03:05', (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'smartinez'), (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'lsuarez'), 'El ordenador de recepción no funciona.', 'U'),
    ('2019-09-28 13:11:29', (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'jramirez'), (SELECT id_empleado FROM empleados WHERE nombre_usuario = 'lsuarez'), 'Mi portátil no puede acceder a la wifi.', 'N');

SELECT * FROM empleados;
SELECT * FROM incidencias;
