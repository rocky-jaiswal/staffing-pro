import { createTRPCRouter } from './trpc'

import { healthCheckRouter } from './routers/healthCheckRouter'
import { sessionsRouter } from './routers/session'
import { usersRouter } from './routers/users'
import { projectsRouter } from './routers/projects'

export const appRouter = createTRPCRouter({
  health: healthCheckRouter,
  sessions: sessionsRouter,
  users: usersRouter,
  projects: projectsRouter
})

// export type definition of API
export type AppRouter = typeof appRouter

