-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-05-12 22:00:59.671

-- tables
-- Table: banco
CREATE TABLE banco (
                       id_banco int  NOT NULL,
                       nombre_banco varchar(50)  NOT NULL,
                       CONSTRAINT banco_pk PRIMARY KEY (id_banco)
);

-- Table: comentario
CREATE TABLE comentario (
                            id_comentario int  NOT NULL,
                            id_proyecto int  NOT NULL,
                            id_donador int  NOT NULL,
                            comentario varchar(200)  NOT NULL,
                            hora time  NOT NULL,
                            CONSTRAINT id_Comentario PRIMARY KEY (id_comentario)
);

-- Table: contrato
CREATE TABLE contrato (
                          id_contrato int  NOT NULL,
                          contrato varchar(500)  NOT NULL,
                          tipo_contrato varchar(100)  NOT NULL,
                          CONSTRAINT id_Contrato PRIMARY KEY (id_contrato)
);

-- Table: cuenta
CREATE TABLE cuenta (
                        id_cuenta int  NOT NULL,
                        id_banco int  NOT NULL,
                        numero_cuenta varchar(100)  NOT NULL,
                        id_emprendedor int  NOT NULL,
                        tipo_cuenta varchar(50)  NOT NULL,
                        CONSTRAINT cuenta_pk PRIMARY KEY (id_cuenta)
);

-- Table: direccion
CREATE TABLE direccion (
                           id_direccion serial  NOT NULL,
                           zona varchar(50)  NOT NULL,
                           calle varchar(50)  NOT NULL,
                           ciudad varchar(50)  NOT NULL,
                           departamento varchar(50)  NOT NULL,
                           CONSTRAINT direccion_pk PRIMARY KEY (id_direccion)
);

-- Table: donacion
CREATE TABLE donacion (
                          id_donacion int  NOT NULL,
                          id_proyecto int  NOT NULL,
                          id_donador int  NOT NULL,
                          monto decimal(12,4)  NOT NULL,
                          hora time  NOT NULL,
                          fecha_donacion int  NOT NULL,
                          CONSTRAINT id_Donacion PRIMARY KEY (id_donacion)
);

-- Table: donador
CREATE TABLE donador (
                         id_donador int  NOT NULL,
                         id_contrato int  NOT NULL,
                         id_usuario int  NOT NULL,
                         CONSTRAINT id_Donador PRIMARY KEY (id_donador)
);

-- Table: emprendedor
CREATE TABLE emprendedor (
                             id_emprendedor int  NOT NULL,
                             id_imagen int  NOT NULL,
                             id_tipo_emprendimiento int  NOT NULL,
                             id_contrato int  NOT NULL,
                             id_usuario int  NOT NULL,
                             CONSTRAINT id_Emprendedor PRIMARY KEY (id_emprendedor)
);

-- Table: estado
CREATE TABLE estado (
                        id_estado int  NOT NULL,
                        estado varchar(50)  NULL,
                        CONSTRAINT estado_pk PRIMARY KEY (id_estado)
);

-- Table: imagen
CREATE TABLE imagen (
                        id_imagen int  NOT NULL,
                        src_imagen varchar(255)  NOT NULL,
                        imagen varchar(150)  NOT NULL,
                        CONSTRAINT id_Imagen PRIMARY KEY (id_imagen)
);

-- Table: imagen_proyecto
CREATE TABLE imagen_proyecto (
                                 id_imagen int  NOT NULL,
                                 id_proyecto int  NOT NULL,
                                 descripcion varchar(500)  NOT NULL,
                                 CONSTRAINT imagen_proyecto_pk PRIMARY KEY (id_imagen,id_proyecto)
);

-- Table: persona
CREATE TABLE persona (
                         id_persona serial  NOT NULL,
                         nombre_persona varchar(100)  NOT NULL,
                         apellidos varchar(100)  NOT NULL,
                         id_direccion_fk int  NOT NULL,
                         fecha_nacimiento varchar(100)  NOT NULL,
                         CONSTRAINT persona_pk PRIMARY KEY (id_persona)
);

-- Table: progreso
CREATE TABLE progreso (
                          id_progreso int  NOT NULL,
                          descripcion varchar(200)  NOT NULL,
                          id_proyecto int  NOT NULL,
                          CONSTRAINT progreso_pk PRIMARY KEY (id_progreso)
);

-- Table: progreso_imagen
CREATE TABLE progreso_imagen (
                                 id_progreso_fk int  NOT NULL,
                                 id_imagen_fk int  NOT NULL,
                                 descripcion text  NOT NULL,
                                 CONSTRAINT progreso_imagen_pk PRIMARY KEY (id_progreso_fk,id_imagen_fk)
);

-- Table: proyecto
CREATE TABLE proyecto (
                          id_proyecto int  NOT NULL,
                          nombre_proyecto varchar(500)  NOT NULL,
                          descripcion varchar(500)  NOT NULL,
                          monto_recaudar decimal(12,4)  NOT NULL,
                          id_emprendedor int  NOT NULL,
                          hora_inicio time  NOT NULL,
                          hora_fin time  NOT NULL,
                          id_estado int  NOT NULL,
                          fecha_inicio varchar(50)  NOT NULL,
                          fecha_fin varchar(50)  NOT NULL,
                          CONSTRAINT id_Proyecto PRIMARY KEY (id_proyecto)
);

-- Table: recompensas
CREATE TABLE recompensas (
                             id_recompensa int  NOT NULL,
                             valor_min int  NOT NULL,
                             valor_max int  NOT NULL,
                             recompensa text  NOT NULL,
                             id_proyecto int  NOT NULL,
                             CONSTRAINT recompensas_pk PRIMARY KEY (id_recompensa)
);

-- Table: tipo_emprendimiento
CREATE TABLE tipo_emprendimiento (
                                     id_tipo_emprendimiento int  NOT NULL,
                                     tipo_emprendimiento varchar(100)  NOT NULL,
                                     area varchar(100)  NOT NULL,
                                     CONSTRAINT id_Tipo_Emprendimiento PRIMARY KEY (id_tipo_emprendimiento)
);

-- Table: usuario
CREATE TABLE usuario (
                         id_usuario serial  NOT NULL,
                         usuario varchar(100)  NOT NULL,
                         contrasena varchar(50)  NOT NULL,
                         correo_electronico varchar(50)  NOT NULL,
                         telefono int  NOT NULL,
                         id_persona_fk int  NOT NULL,
                         tipo_usuario varchar(50)  NOT NULL,
                         CONSTRAINT id_Usuario PRIMARY KEY (id_usuario)
);

-- foreign keys
-- Reference: Comentarios_Donador (table: comentario)
ALTER TABLE comentario ADD CONSTRAINT Comentarios_Donador
    FOREIGN KEY (id_donador)
        REFERENCES donador (id_donador)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Comentarios_Proyecto (table: comentario)
ALTER TABLE comentario ADD CONSTRAINT Comentarios_Proyecto
    FOREIGN KEY (id_proyecto)
        REFERENCES proyecto (id_proyecto)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Donacion_Donador (table: donacion)
ALTER TABLE donacion ADD CONSTRAINT Donacion_Donador
    FOREIGN KEY (id_donador)
        REFERENCES donador (id_donador)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Donacion_Proyecto (table: donacion)
ALTER TABLE donacion ADD CONSTRAINT Donacion_Proyecto
    FOREIGN KEY (id_proyecto)
        REFERENCES proyecto (id_proyecto)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Donador_Contrato (table: donador)
ALTER TABLE donador ADD CONSTRAINT Donador_Contrato
    FOREIGN KEY (id_contrato)
        REFERENCES contrato (id_contrato)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Emprendedor_Contrato (table: emprendedor)
ALTER TABLE emprendedor ADD CONSTRAINT Emprendedor_Contrato
    FOREIGN KEY (id_contrato)
        REFERENCES contrato (id_contrato)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Emprendedor_Imagen (table: emprendedor)
ALTER TABLE emprendedor ADD CONSTRAINT Emprendedor_Imagen
    FOREIGN KEY (id_imagen)
        REFERENCES imagen (id_imagen)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Emprendedor_Tipo_Emprendimiento (table: emprendedor)
ALTER TABLE emprendedor ADD CONSTRAINT Emprendedor_Tipo_Emprendimiento
    FOREIGN KEY (id_tipo_emprendimiento)
        REFERENCES tipo_emprendimiento (id_tipo_emprendimiento)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Imagen_Proyecto_Imagen (table: imagen_proyecto)
ALTER TABLE imagen_proyecto ADD CONSTRAINT Imagen_Proyecto_Imagen
    FOREIGN KEY (id_imagen)
        REFERENCES imagen (id_imagen)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Imagen_Proyecto_Proyecto (table: imagen_proyecto)
ALTER TABLE imagen_proyecto ADD CONSTRAINT Imagen_Proyecto_Proyecto
    FOREIGN KEY (id_proyecto)
        REFERENCES proyecto (id_proyecto)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Persona_direccion (table: persona)
ALTER TABLE persona ADD CONSTRAINT Persona_direccion
    FOREIGN KEY (id_direccion_fk)
        REFERENCES direccion (id_direccion)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Progreso_Proyecto (table: progreso)
ALTER TABLE progreso ADD CONSTRAINT Progreso_Proyecto
    FOREIGN KEY (id_proyecto)
        REFERENCES proyecto (id_proyecto)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Proyecto_Emprendedor (table: proyecto)
ALTER TABLE proyecto ADD CONSTRAINT Proyecto_Emprendedor
    FOREIGN KEY (id_emprendedor)
        REFERENCES emprendedor (id_emprendedor)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Proyecto_Estado (table: proyecto)
ALTER TABLE proyecto ADD CONSTRAINT Proyecto_Estado
    FOREIGN KEY (id_estado)
        REFERENCES estado (id_estado)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Recompensas_Proyecto (table: recompensas)
ALTER TABLE recompensas ADD CONSTRAINT Recompensas_Proyecto
    FOREIGN KEY (id_proyecto)
        REFERENCES proyecto (id_proyecto)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: cuenta_banco (table: cuenta)
ALTER TABLE cuenta ADD CONSTRAINT cuenta_banco
    FOREIGN KEY (id_banco)
        REFERENCES banco (id_banco)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: cuenta_emprendedor (table: cuenta)
ALTER TABLE cuenta ADD CONSTRAINT cuenta_emprendedor
    FOREIGN KEY (id_emprendedor)
        REFERENCES emprendedor (id_emprendedor)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: donador_usuario (table: donador)
ALTER TABLE donador ADD CONSTRAINT donador_usuario
    FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: emprendedor_usuario (table: emprendedor)
ALTER TABLE emprendedor ADD CONSTRAINT emprendedor_usuario
    FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: progreso_imagen_imagen (table: progreso_imagen)
ALTER TABLE progreso_imagen ADD CONSTRAINT progreso_imagen_imagen
    FOREIGN KEY (id_imagen_fk)
        REFERENCES imagen (id_imagen)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: progreso_imagen_progreso (table: progreso_imagen)
ALTER TABLE progreso_imagen ADD CONSTRAINT progreso_imagen_progreso
    FOREIGN KEY (id_progreso_fk)
        REFERENCES progreso (id_progreso)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: usuario_persona (table: usuario)
ALTER TABLE usuario ADD CONSTRAINT usuario_persona
    FOREIGN KEY (id_persona_fk)
        REFERENCES persona (id_persona)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.

