CREATE TABLE "Sensores"("codigo" UUID NOT NULL);
ALTER TABLE
    "Sensores" ADD PRIMARY KEY("codigo");
CREATE TABLE "Fincas"(
                         "id_finca" UUID NOT NULL,
                         "nombre" VARCHAR(255) NOT NULL,
                         "numero_potreros" INTEGER NOT NULL,
                         "longitud" FLOAT(53) NOT NULL,
                         "latitud" FLOAT(53) NOT NULL,
                         "id_propietario" UUID NOT NULL
);
ALTER TABLE
    "Fincas" ADD PRIMARY KEY("id_finca");
CREATE TABLE "Bovinos"(
                          "codigo" VARCHAR(255) NOT NULL,
                          "fecha_nacimiento" DATE NOT NULL,
                          "fecha_ingreso" DATE NOT NULL,
                          "fecha_salida" DATE NOT NULL,
                          "sexo" CHAR(255) NOT NULL,
                          "peso_entrada" DECIMAL(8, 2) NOT NULL,
                          "imagen" VARCHAR(255) NULL,
                          "color" VARCHAR(255) NULL,
                          "id_potrero" UUID NOT NULL,
                          "id_sensor" UUID NOT NULL
);
ALTER TABLE
    "Bovinos" ADD PRIMARY KEY("codigo");
CREATE TABLE "Usuarios"(
                           "id_usuario" UUID NOT NULL,
                           "nombre" VARCHAR(255) NOT NULL,
                           "apellido" VARCHAR(255) NOT NULL,
                           "identificación" BIGINT NOT NULL,
                           "correo" VARCHAR(255) NOT NULL,
                           "contraseña" VARCHAR(255) NOT NULL,
                           "id_propietario" UUID NULL,
                           "id_rol" INTEGER NOT NULL
);
ALTER TABLE
    "Usuarios" ADD PRIMARY KEY("id_usuario");
CREATE TABLE "Roles"(
                        "id_rol" INTEGER NOT NULL,
                        "nombre" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Roles" ADD PRIMARY KEY("id_rol");
CREATE TABLE "Potreros"(
                           "id_potrero" UUID NOT NULL,
                           "longitud" FLOAT(53) NOT NULL,
                           "latitud" FLOAT(53) NOT NULL,
                           "area" INTEGER NOT NULL,
                           "id_finca" UUID NOT NULL
);
ALTER TABLE
    "Potreros" ADD PRIMARY KEY("id_potrero");
CREATE TABLE "Historial_ubicaciones"(
                                        "id_historial" UUID NOT NULL,
                                        "latitud" FLOAT(53) NOT NULL,
                                        "longitud" FLOAT(53) NOT NULL,
                                        "fecha" DATE NOT NULL,
                                        "id_bovino" VARCHAR(255) NOT NULL,
                                        "id_sensor" UUID NOT NULL
);
ALTER TABLE
    "Historial_ubicaciones" ADD PRIMARY KEY("id_historial");
ALTER TABLE
    "Fincas" ADD CONSTRAINT "fincas_id_propietario_foreign" FOREIGN KEY("id_propietario") REFERENCES "Usuarios"("id_usuario");
ALTER TABLE
    "Usuarios" ADD CONSTRAINT "usuarios_id_rol_foreign" FOREIGN KEY("id_rol") REFERENCES "Roles"("id_rol");
ALTER TABLE
    "Bovinos" ADD CONSTRAINT "bovinos_id_sensor_foreign" FOREIGN KEY("id_sensor") REFERENCES "Sensores"("codigo");
ALTER TABLE
    "Potreros" ADD CONSTRAINT "potreros_id_finca_foreign" FOREIGN KEY("id_finca") REFERENCES "Fincas"("id_finca");
ALTER TABLE
    "Usuarios" ADD CONSTRAINT "usuarios_id_propietario_foreign" FOREIGN KEY("id_propietario") REFERENCES "Usuarios"("id_usuario");
ALTER TABLE
    "Historial_ubicaciones" ADD CONSTRAINT "historial_ubicaciones_id_bovino_foreign" FOREIGN KEY("id_bovino") REFERENCES "Bovinos"("codigo");
ALTER TABLE
    "Historial_ubicaciones" ADD CONSTRAINT "historial_ubicaciones_id_sensor_foreign" FOREIGN KEY("id_sensor") REFERENCES "Sensores"("codigo");
ALTER TABLE
    "Bovinos" ADD CONSTRAINT "bovinos_id_potrero_foreign" FOREIGN KEY("id_potrero") REFERENCES "Potreros"("id_potrero");