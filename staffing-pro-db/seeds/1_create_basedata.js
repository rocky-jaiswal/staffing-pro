const { faker } = require('@faker-js/faker')

exports.seed = async function (knex) {
  await knex('geographies').insert([
    { name: 'North America' },
    { name: 'South America' },
    { name: 'Europe' },
    { name: 'Africa & Middle East' },
    { name: 'South Asia' },
    { name: 'Central Asia' },
    { name: 'Ocenia' },
  ])
  const northAmerica = await knex('geographies').select('id').where({ name: 'North America' }).first()
  const southAmerica = await knex('geographies').select('id').where({ name: 'South America' }).first()
  const europe = await knex('geographies').select('id').where({ name: 'Europe' }).first()
  const africa = await knex('geographies').select('id').where({ name: 'Africa & Middle East' }).first()
  const southAsia = await knex('geographies').select('id').where({ name: 'South Asia' }).first()
  const centralAsia = await knex('geographies').select('id').where({ name: 'Central Asia' }).first()
  const ocenia = await knex('geographies').select('id').where({ name: 'Ocenia' }).first()
  await knex('countries').insert([
    { name: 'Argentina', geography_id: southAmerica.id },
    { name: 'Australia', geography_id: ocenia.id },
    { name: 'Austria', geography_id: europe.id },
    { name: 'Bahrain', geography_id: africa.id },
    { name: 'Bangladesh', geography_id: southAsia.id },
    { name: 'Belgium', geography_id: europe.id },
    { name: 'Brazil', geography_id: southAmerica.id },
    { name: 'Cambodia', geography_id: southAsia.id },
    { name: 'Cameroon', geography_id: africa.id },
    { name: 'Canada', geography_id: northAmerica.id },
    { name: 'Chile', geography_id: southAmerica.id },
    { name: 'China', geography_id: centralAsia.id },
    { name: 'Colombia', geography_id: southAmerica.id },
    { name: 'Croatia', geography_id: europe.id },
    { name: 'Cyprus', geography_id: europe.id },
    { name: 'Denmark', geography_id: europe.id },
    { name: 'Ecuador', geography_id: southAmerica.id },
    { name: 'Egypt', geography_id: africa.id },
    { name: 'Estonia', geography_id: europe.id },
    { name: 'Ethiopia', geography_id: africa.id },
    { name: 'Finland', geography_id: europe.id },
    { name: 'France', geography_id: europe.id },
    { name: 'Georgia', geography_id: europe.id },
    { name: 'Germany', geography_id: europe.id },
    { name: 'Ghana', geography_id: africa.id },
    { name: 'Greece', geography_id: europe.id },
    { name: 'Honduras', geography_id: southAmerica.id },
    { name: 'Hungary', geography_id: europe.id },
    { name: 'Iceland', geography_id: europe.id },
    { name: 'India', geography_id: southAsia.id },
    { name: 'Indonesia', geography_id: southAsia.id },
    { name: 'Iran', geography_id: centralAsia.id },
    { name: 'Ireland', geography_id: europe.id },
    { name: 'Italy', geography_id: europe.id },
    { name: 'Japan', geography_id: centralAsia.id },
    { name: 'Kenya', geography_id: africa.id },
    { name: 'Malaysia', geography_id: southAsia.id },
    { name: 'Mexico', geography_id: southAmerica.id },
    { name: 'Monaco', geography_id: europe.id },
    { name: 'Morocco', geography_id: africa.id },
    { name: 'Namibia', geography_id: africa.id },
    { name: 'Nepal', geography_id: southAsia.id },
    { name: 'Netherlands', geography_id: europe.id },
    { name: 'New Zealand', geography_id: ocenia.id },
    { name: 'Niger', geography_id: africa.id },
    { name: 'Nigeria', geography_id: africa.id },
    { name: 'Norway', geography_id: europe.id },
    { name: 'Pakistan', geography_id: southAsia.id },
    { name: 'Philippines', geography_id: southAsia.id },
    { name: 'Poland', geography_id: europe.id },
    { name: 'Portugal', geography_id: europe.id },
    { name: 'Qatar', geography_id: africa.id },
    { name: 'Singapore', geography_id: southAsia.id },
    { name: 'Spain', geography_id: europe.id },
    { name: 'Sweden', geography_id: europe.id },
    { name: 'Switzerland', geography_id: europe.id },
    { name: 'Thailand', geography_id: southAsia.id },
    { name: 'Turkey', geography_id: europe.id },
    { name: 'Ukraine', geography_id: europe.id },
    { name: 'Uruguay', geography_id: southAmerica.id },
    { name: 'Vietnam', geography_id: southAsia.id },
    { name: 'United Arab Emirates', geography_id: africa.id },
    { name: 'United States of America', geography_id: northAmerica.id },
    { name: 'United Kingdom', geography_id: europe.id },
    { name: 'South Africa', geography_id: africa.id },
    { name: 'South Korea', geography_id: centralAsia.id },
    { name: 'Usbekistan', geography_id: centralAsia.id },
  ])
  const germany = await knex('countries').select('id').where({ name: 'Germany' }).first()
  const india = await knex('countries').select('id').where({ name: 'India' }).first()
  const usa = await knex('countries').select('id').where({ name: 'United States of America' }).first()
  const sweden = await knex('countries').select('id').where({ name: 'Sweden' }).first()
  const australia = await knex('countries').select('id').where({ name: 'Australia' }).first()
  const brazil = await knex('countries').select('id').where({ name: 'Brazil' }).first()
  const china = await knex('countries').select('id').where({ name: 'China' }).first()
  const nigeria = await knex('countries').select('id').where({ name: 'Nigeria' }).first()
  const uk = await knex('countries').select('id').where({ name: 'United Kingdom' }).first()
  const italy = await knex('countries').select('id').where({ name: 'Italy' }).first()
  const southAfrica = await knex('countries').select('id').where({ name: 'South Africa' }).first()
  await knex('cities').insert([
    { name: 'Berlin', country_id: germany.id },
    { name: 'Munich', country_id: germany.id },
    { name: 'Hamburg', country_id: germany.id },
    { name: 'Potsdam', country_id: germany.id },
    { name: 'Ingolstadt', country_id: germany.id },
    { name: 'New Delhi', country_id: india.id },
    { name: 'Mumbai', country_id: india.id },
    { name: 'Bangaluru', country_id: india.id },
    { name: 'Kolkata', country_id: india.id },
    { name: 'Chennai', country_id: india.id },
    { name: 'New York', country_id: usa.id },
    { name: 'Los Angeles', country_id: usa.id },
    { name: 'Chicago', country_id: usa.id },
    { name: 'Houston', country_id: usa.id },
    { name: 'Phoenix', country_id: usa.id },
    { name: 'Philadelphia', country_id: usa.id },
    { name: 'San Antonio', country_id: usa.id },
    { name: 'San Diego', country_id: usa.id },
    { name: 'San Francisco', country_id: usa.id },
    { name: 'Dallas', country_id: usa.id },
    { name: 'Austin', country_id: usa.id },
    { name: 'Denver', country_id: usa.id },
    { name: 'Seattle', country_id: usa.id },
    { name: 'Stockholm', country_id: sweden.id },
    { name: 'Goeteborg', country_id: sweden.id },
    { name: 'Malmo', country_id: sweden.id },
    { name: 'Uppsala', country_id: sweden.id },
    { name: 'Melbourne', country_id: australia.id },
    { name: 'Sydney', country_id: australia.id },
    { name: 'Adelaide', country_id: australia.id },
    { name: 'Brisbane', country_id: australia.id },
    { name: 'Perth', country_id: australia.id },
    { name: 'Canberra', country_id: australia.id },
    { name: 'Sao Paulo', country_id: brazil.id },
    { name: 'Rio de Janeiro', country_id: brazil.id },
    { name: 'Brasília', country_id: brazil.id },
    { name: 'Beijing', country_id: china.id },
    { name: 'Shanghai', country_id: china.id },
    { name: 'Guangzhou', country_id: china.id },
    { name: 'Shenzhen', country_id: china.id },
    { name: 'Lagos', country_id: nigeria.id },
    { name: 'Kano', country_id: nigeria.id },
    { name: 'Durban', country_id: southAfrica.id },
    { name: 'Cape Town', country_id: southAfrica.id },
    { name: 'London', country_id: uk.id },
    { name: 'Manchester', country_id: uk.id },
    { name: 'Liverpool', country_id: uk.id },
    { name: 'Newcastle', country_id: uk.id },
    { name: 'Birmingham', country_id: uk.id },
    { name: 'Rome', country_id: italy.id },
    { name: 'Milan', country_id: italy.id },
    { name: 'Napoli', country_id: italy.id },
  ])
  await knex('industries').insert([
    { name: 'Telecom' },
    { name: 'Energy' },
    { name: 'Oil & Gas' },
    { name: 'Healthcare' },
    { name: 'Banking & Finance' },
    { name: 'Real Estate' },
    { name: 'Automobile' },
    { name: 'Software & IT' },
    { name: 'Agricultural' },
    { name: 'Manufacturing' },
  ])
  await knex('verticals').insert([
    { name: 'HR' },
    { name: 'Accounting' },
    { name: 'Auditing' },
    { name: 'Software' },
    { name: 'ERP' },
    { name: 'Sustainibility' },
    { name: 'Sales & Marketing' },
  ])

  const companies = Array(500)
    .fill(null)
    .map((_) => `${faker.company.name()}`)
    .reduce((acc, name) => {
      if (!acc[name]) {
        acc[name] = true
      } else {
        acc[name + ' - ' + faker.location.country()] = true
      }
      return acc
    }, {})

  await knex('companies').insert(Object.keys(companies).map((name) => ({ name })))

  await knex('roles').insert([
    { name: 'Software Developer - Lead' },
    { name: 'Software Developer - Backend Developer' },
    { name: 'Software Developer - Frontend Developer' },
    { name: 'Design' },
    { name: 'Project Lead' },
    { name: 'Project Manager' },
    { name: 'Product Owner' },
    { name: 'Analyst' },
    { name: 'Auditor' },
    { name: 'Associate' },
    { name: 'Senior Associate' },
    { name: 'Legal Expert' },
    { name: 'Data Engineer' },
    { name: 'Data Analyst' },
    { name: 'Machine Learning - Engineer' },
  ])
  await knex('skills').insert([
    { name: 'Software Developer - Java' },
    { name: 'Software Developer - JavaScript' },
    { name: 'Software Developer - Python' },
    { name: 'Software Developer - Kafka' },
    { name: 'Software Developer - Frontend' },
    { name: 'Software Developer - Mobile' },
    { name: 'UI/UX Expert' },
    { name: 'Web Application Design' },
    { name: 'Mobile Application Design' },
    { name: 'Machine Learning - Python' },
  ])
  await knex('project_administration_roles').insert([
    { name: 'Admin' },
    { name: 'Editor' },
    { name: 'Member' },
    { name: 'Applicant' },
    { name: 'Observer' },
  ])
}
