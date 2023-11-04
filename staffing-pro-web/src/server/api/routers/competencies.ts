import { z } from 'zod'
import { TRPCError } from '@trpc/server'

import { env } from '../../../env/server.mjs'
import { createTRPCRouter, protectedProcedure } from '../trpc'
import type { Competency } from '../../types/project'

export const competenciesRouter = createTRPCRouter({
  competenciesList: protectedProcedure.query(async ({ ctx }) => {
    try {
      const response = await fetch(`${env.MAIN_API_URL}/v1/competencies`, {
        method: 'get',
        headers: {
          authorization: `Bearer ${ctx.session.token ?? ''}`,
        },
      })

      const responseBody = (await response.json()) as Competency[]
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
