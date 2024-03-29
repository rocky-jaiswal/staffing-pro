import { createTRPCRouter } from './trpc'

import { healthCheckRouter } from './routers/healthCheckRouter'
import { sessionsRouter } from './routers/session'
import { usersRouter } from './routers/users'
import { projectsRouter } from './routers/projects'
import { geographiesRouter } from './routers/geographies'
import { countriesRouter } from './routers/countries'
import { competenciesRouter } from './routers/competencies'

export const appRouter = createTRPCRouter({
  health: healthCheckRouter,
  sessions: sessionsRouter,
  users: usersRouter,
  projects: projectsRouter,
  geographies: geographiesRouter,
  countries: countriesRouter,
  competencies: competenciesRouter,
})

// export type definition of API
export type AppRouter = typeof appRouter
