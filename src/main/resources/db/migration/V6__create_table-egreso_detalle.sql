CREATE TABLE egreso_detalle (
id BIGINT NOT NULL auto_increment,
nombre_lugar VARCHAR(100) not null,
monto INT NOT NULL,
fecha DATE NOT NULL,
categoria_id bigint NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (categoria_id) REFERENCES egreso_categoria(id)

);