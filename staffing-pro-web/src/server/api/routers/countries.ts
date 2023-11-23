import { z } from 'zod'
import { TRPCError } from '@trpc/server'

import { env } from '../../../env/server.mjs'
import { createTRPCRouter, protectedProcedure } from '../trpc'
import type { Country } from '../../types/project'

export const countriesRouter = createTRPCRouter({
  countryList: protectedProcedure
    .input(
      z.object({
        selectedGeography: z.string().nullable(),
      })
    )
    .query(async ({ input, ctx }) => {
      try {
        let queryUrl = `${env.MAIN_API_URL}/v1/countries`

        if (input.selectedGeography) {
          queryUrl = `${queryUrl}?geography=${input.selectedGeography}`
        }

        const response = await fetch(queryUrl, {
          method: 'get',
          headers: {
            authorization: `Bearer ${ctx.session.token ?? ''}`,
          },
        })

        const responseBody = (await response.json()) as Country[]
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
