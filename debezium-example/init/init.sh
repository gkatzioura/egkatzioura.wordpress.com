#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  create schema test_schema;
  create table test_schema.employee(
          id  SERIAL PRIMARY KEY,
          firstname   TEXT    NOT NULL,
          lastname    TEXT    NOT NULL,
          email       TEXT    not null,
          age         INT     NOT NULL,
          salary         real,
          unique(email)
      );
EOSQL