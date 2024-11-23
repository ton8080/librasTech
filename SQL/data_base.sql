-- public.voluntario definition

-- Drop table

-- DROP TABLE public.voluntario;

CREATE TABLE public.voluntario (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	disponivel bool NOT NULL,
	CONSTRAINT voluntario_email_key UNIQUE (email),
	CONSTRAINT voluntario_pkey PRIMARY KEY (id)
);