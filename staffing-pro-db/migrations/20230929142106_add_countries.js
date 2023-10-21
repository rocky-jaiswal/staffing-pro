'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('countries', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('name').unique().notNull()

    table.uuid('geography_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('geography_id').references('geographies.id')
    table.index(['geography_id'], 'idx_geography_id_countries')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('countries')
}
