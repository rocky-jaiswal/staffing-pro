'use strict'

exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('project_users', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))

    table.uuid('project_id').notNullable()
    table.uuid('user_id').notNullable()
    table.uuid('project_administration_role_id').notNullable()

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('project_id').references('projects.id')
    table.index(['project_id'], 'idx_project_id_project_users')

    table.foreign('user_id').references('users.id')
    table.index(['user_id'], 'idx_user_id_project_users')

    table.foreign('project_administration_role_id').references('project_administration_roles.id')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('project_users')
}
