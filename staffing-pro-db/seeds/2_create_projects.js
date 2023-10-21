const { faker } = require('@faker-js/faker')

function shuffle(a) {
  for (let i = a.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1))
    ;[a[i], a[j]] = [a[j], a[i]]
  }
  return a
}

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1)
}

exports.seed = async function (knex) {
  const cities = await knex('cities').select('id')
  const industries = await knex('industries').select('id')
  const verticals = await knex('verticals').select()
  const clients = await knex('companies').select()
  const roles = await knex('roles').select()
  const skills = await knex('skills').select()

  const stages = ['NOT_STARTED', 'BEGINNING', 'IN_PROGRESS', 'WRAP_UP', 'POST_DELIVERY', 'MAINTENANCE']

  const projects = Array(50)
    .fill(null)
    .map((_) => {
      const vertical = shuffle(verticals)[0]
      const client = shuffle(clients)[0]
      return {
        title: `${vertical.name} project (${capitalizeFirstLetter(faker.company.buzzVerb())}) for ${client.name}`,
        description: faker.lorem.paragraph(),
        stage: shuffle(stages)[0],
        company_id: client.id,
        industry_id: shuffle(industries)[0].id,
        vertical_id: vertical.id,
        start_date: faker.date.future(),
        end_date: faker.date.future({ years: 1 }),
      }
    })

  await knex('projects').insert(projects)

  const projectsFromDB = await knex('projects').select('id')

  const projectCities = projectsFromDB.map((project) => {
    return {
      project_id: project.id,
      city_id: shuffle(cities)[0].id,
    }
  })
  await knex('project_cities').insert(projectCities)

  const projectPositions1 = projectsFromDB.map((project) => {
    const role = shuffle(roles)[0]
    return {
      project_id: project.id,
      title: role.name,
      role_id: role.id,
    }
  })
  await knex('project_positions').insert(projectPositions1)

  // Add 1 more position
  const projectPositions2 = projectsFromDB.map((project) => {
    const role = shuffle(roles)[0]
    return {
      project_id: project.id,
      title: role.name,
      role_id: role.id,
    }
  })
  await knex('project_positions').insert(projectPositions2)

  const projectPosFromDB = await knex('project_positions').select('id')

  const projectPositionSkills = projectPosFromDB.map((projectPos) => {
    const skill = shuffle(skills)[0]
    return {
      name: skill.name,
      project_position_id: projectPos.id,
      skill_id: skill.id,
    }
  })
  await knex('project_position_skills').insert(projectPositionSkills)
}
