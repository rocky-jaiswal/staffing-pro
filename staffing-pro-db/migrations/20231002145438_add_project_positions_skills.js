'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('project_position_skills', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('name').notNull()

    table.uuid('project_position_id').notNullable()
    table.uuid('skill_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('project_position_id').references('project_positions.id')
    table.index(['project_position_id'], 'idx_project_id_pps')

    table.foreign('skill_id').references('skills.id')
    table.index(['skill_id'], 'idx_skill_id_pps')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('project_positions_skill')
}
