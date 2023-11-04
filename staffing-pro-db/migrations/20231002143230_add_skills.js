'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('skills', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('name').unique().notNull()
    table.text('description').nullable()

    table.uuid('competency_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('competency_id').references('competencies.id')
    table.index(['competency_id'], 'idx_competency_id_skills')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('skills')
}
