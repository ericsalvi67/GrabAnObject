-- V1__create_tables.sql
-- Migration: cria as tabelas iniciais para o projeto
-- Coloque este arquivo em db/migrations para execução manual ou em src/main/resources/db/migration para Flyway

-- 1) Tabela users
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- 2) Tabela type_objects
CREATE TABLE IF NOT EXISTS type_objects (
    id SERIAL PRIMARY KEY,
    type_name TEXT NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- 3) Tabela objects
CREATE TABLE IF NOT EXISTS objects (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    type_id INTEGER REFERENCES type_objects(id) ON DELETE SET NULL,
    created_by INTEGER REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- 4) Tabela maintenance
CREATE TABLE IF NOT EXISTS maintenance (
    id SERIAL PRIMARY KEY,
    object_id INTEGER NOT NULL REFERENCES objects(id) ON DELETE CASCADE,
    description TEXT NOT NULL,
    performed_by INTEGER REFERENCES users(id) ON DELETE SET NULL,
    performed_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Índices úteis
CREATE INDEX IF NOT EXISTS idx_objects_type_id ON objects(type_id);
CREATE INDEX IF NOT EXISTS idx_maintenance_object_id ON maintenance(object_id);

-- Exemplos de uso (comando psql):
-- psql "postgresql://<user>:<password>@<host>:<port>/<db>" -f db/migrations/V1__create_tables.sql

-- Flyway: coloque em src/main/resources/db/migration/V1__create_tables.sql e rode `flyway migrate`.
