'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('project_cities', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))

    table.uuid('project_id').notNullable()
    table.uuid('city_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('project_id').references('projects.id')
    table.index(['project_id'], 'idx_project_id_project_cities')

    table.foreign('city_id').references('cities.id')
    table.index(['city_id'], 'idx_city_id_project_cities')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('project_cities')
}
