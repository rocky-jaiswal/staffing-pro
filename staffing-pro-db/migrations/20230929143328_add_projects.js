exports.up = async (knex) => {
  await knex.raw('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"')

  return knex.schema.createTable('projects', (table) => {
    table.uuid('id').primary().defaultTo(knex.raw('uuid_generate_v4()'))
    table.text('title').unique().notNullable()
    table.text('description').notNullable()
    table.text('stage').notNull() // e.g. (not-started, beginning, in-progress, wrap-up, post-delivery, maintenance)

    table.uuid('company_id').notNullable()
    table.uuid('industry_id').notNullable()
    table.uuid('vertical_id').notNullable()

    table.date('start_date').notNullable()
    table.date('end_date').nullable()

    table.boolean('promoted').defaultTo(false)
    table.boolean('hidden').defaultTo(false)
    table.boolean('archived').defaultTo(false)

    table.timestamp('created_at').defaultTo(knex.fn.now())
    table.timestamp('updated_at').defaultTo(knex.fn.now())

    table.foreign('company_id').references('companies.id')
    table.foreign('industry_id').references('industries.id')
    table.foreign('vertical_id').references('verticals.id')
  })
}

exports.down = async (knex) => {
  return knex.schema.dropTable('projects')
}
