'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('project_positions', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('title').notNull()
    table.text('description').nullable()
    table.boolean('is_open').notNullable().defaultTo(true)

    table.date('start_date').nullable()
    table.date('end_date').nullable()

    table.uuid('project_id').notNullable()
    table.uuid('role_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('project_id').references('projects.id')
    table.foreign('role_id').references('roles.id')

    table.index(['project_id'], 'idx_project_id_project_positions')
    table.index(['role_id'], 'idx_role_id_project_positions')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('project_positions')
}
