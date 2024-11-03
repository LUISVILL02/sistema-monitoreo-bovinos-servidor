CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE Coordenadas(
                            id UUID NOT NULL DEFAULT uuid_generate_v4(),
                            latitud FLOAT(53) NOT NULL,
                            longitud FLOAT(53) NOT NULL,
                            id_potrero UUID NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (id_potrero) REFERENCES potreros(id_potrero)
);