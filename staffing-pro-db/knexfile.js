"use strict";

require("dotenv").config({ path: process.env.DOTENV_CONFIG_PATH });

const dbConfiguration = {
  client: "postgresql",
  useNullAsDefault: true,
  connection: process.env.DB_CONN,
  pool: {
    min: 5,
    max: 5,
  },
  migrations: {
    directory: "./migrations",
    tableName: "knex_migrations",
  },
  seeds: {
    directory: "./seeds",
  },
};

module.exports = {
  development: dbConfiguration,
  test: dbConfiguration,
  canary: dbConfiguration,
  production: dbConfiguration,
};
