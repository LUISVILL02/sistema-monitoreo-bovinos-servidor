CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE bovinos
    ADD COLUMN propietario_id uuid;


ALTER TABLE bovinos
    ADD CONSTRAINT fk_propietario
        FOREIGN KEY (propietario_id) REFERENCES usuarios(id_usuario)
            ON DELETE CASCADE;
