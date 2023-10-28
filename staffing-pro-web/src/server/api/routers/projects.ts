import { z } from 'zod'
import { TRPCError } from '@trpc/server'

import { env } from '../../../env/server.mjs'
import { createTRPCRouter, protectedProcedure } from '../trpc'
import type { Project } from '../../types/project'

interface ProjectResponse {
  count: number
  projects: Project[]
}

export const projectsRouter = createTRPCRouter({
  projectList: protectedProcedure
    .input(
      z.object({
        pageNumber: z.number(),
        selectedCountry: z.string().nullable(),
      })
    )
    .query(async ({ input, ctx }) => {
      let queryUrl = `${env.MAIN_API_URL}/v1/projects/${input.pageNumber}`

      if (input.selectedCountry) {
        queryUrl = `${queryUrl}?country=${input.selectedCountry}`
      }

      try {
        const response = await fetch(queryUrl, {
          method: 'get',
          headers: {
            authorization: `Bearer ${ctx.session.token ?? ''}`,
          },
        })

        const responseBody = (await response.json()) as ProjectResponse
        return responseBody
      } catch (err) {
        ctx.logger.error(err)
        throw new TRPCError({
          code: 'INTERNAL_SERVER_ERROR', // TODO: check / change error type based on err
          message: 'An unexpected error occurred, please try again later.',
          cause: err,
        })
      }
    }),
})
