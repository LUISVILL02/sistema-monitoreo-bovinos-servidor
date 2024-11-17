CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE fincas
    ADD COLUMN capataz_id uuid;


ALTER TABLE fincas
    ADD CONSTRAINT fk_capataz
        FOREIGN KEY (capataz_id) REFERENCES usuarios(id_usuario)
            ON DELETE CASCADE;