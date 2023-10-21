import { z } from 'zod'
import { TRPCError } from '@trpc/server'

import { env } from '../../../env/server.mjs'
import { createTRPCRouter, protectedProcedure } from '../trpc'
import type { Project } from '../../types/project'


export const projectsRouter = createTRPCRouter({
  projectList: protectedProcedure.input(
    z.object({
      pageNumber: z.number(),
    })
  ).query(async ({ input, ctx }) => {
    try {
      const response = await fetch(`${env.MAIN_API_URL}/v1/projects/${input.pageNumber}`, {
        method: 'get',
        headers: {
          // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
          authorization: `Bearer ${ctx.session.token!}`,
        },
      })

      const responseBody = (await response.json()) as Project[]
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
