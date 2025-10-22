CREATE TABLE users (
	id serial NOT NULL,
	name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    deleted boolean NOT NULL,
    last_modification timestamp NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT email_un UNIQUE (email)
);

CREATE TABLE type_objects (
	id serial NOT NULL,
	type_name varchar(255) NOT NULL,
    description varchar(1000) NULL,
    deleted boolean NOT NULL,
    last_modification timestamp NOT NULL,
	CONSTRAINT type_objects_pk PRIMARY KEY (id)
);

CREATE TABLE objects (
	id serial NOT NULL,
	type_id int4 NOT NULL,
    object_name varchar(255) NOT NULL,
    status varchar(2) NOT NULL,
    deleted boolean NOT NULL,
    last_modification timestamp NOT NULL,
	CONSTRAINT objects_pk PRIMARY KEY (id),
	CONSTRAINT type_objects_fk FOREIGN KEY (type_id) REFERENCES type_objects(id)
);

CREATE TABLE maintenence (
	id serial NOT NULL,
    user_id int4 NOT NULL,
    object_id int4 NOT NULL,
    service_type varchar(255) NOT NULL,
    description varchar(1000) NOT NULL,
    performed_at timestamp NOT NULL,
    deleted boolean NOT NULL,
    last_modification timestamp NOT NULL,
	CONSTRAINT maintenence_pk PRIMARY KEY (id),
	CONSTRAINT objects_fk FOREIGN KEY (object_id) REFERENCES objects(id),
	CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users(id)
);
