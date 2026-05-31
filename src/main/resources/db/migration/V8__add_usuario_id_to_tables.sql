-- Adicionar coluna usuario_id nas tabelas existentes

ALTER TABLE ingreso_fijo
    ADD COLUMN usuario_id BIGINT NULL;

ALTER TABLE ingreso_variable
    ADD COLUMN usuario_id BIGINT NULL;

ALTER TABLE egreso_categoria
    ADD COLUMN usuario_id BIGINT NULL;

ALTER TABLE egreso_detalle
    ADD COLUMN usuario_id BIGINT NULL;

-- Adicionar Foreign Keys

ALTER TABLE ingreso_fijo
    ADD CONSTRAINT fk_ingreso_fijo_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE ingreso_variable
    ADD CONSTRAINT fk_ingreso_variable_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE egreso_categoria
    ADD CONSTRAINT fk_egreso_categoria_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE egreso_detalle
    ADD CONSTRAINT fk_egreso_detalle_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id);