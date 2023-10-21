# Staffing Pro

- Needs the `auth-service` project
- Start DB with `docker-compose up` first
- Run `auth-service` next
- Web Service + BFF (Next.js + TRPC)
  - Run with `yarn && yarn dev`
- DB Migrations / Seeding (Knex.js based)
  - Go to `staffing-pro-db`
  - Run `yarn migrate && yarn seed`
- Java API backend (Micronaut + Gradle based project)
  - `cd staffing-pro-api`
  - `./gradlew run`
