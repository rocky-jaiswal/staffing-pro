'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('cities', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('name').unique().notNull()

    table.uuid('country_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('country_id').references('countries.id')
    table.index(['country_id'], 'idx_country_id_cities')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('countries')
}
