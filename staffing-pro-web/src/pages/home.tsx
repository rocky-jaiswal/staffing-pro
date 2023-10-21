import type { ReactElement } from 'react'
import type { GetServerSidePropsContext } from 'next'

import type { NextPageWithLayout } from './_app'
import { api } from '../utils/api'
import { validateCookie } from '../server/lib/token'

import LoggedInLayout from '../components/LoggedInLayout'
import ProjectList from '../components/ProjectList'

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const userId = await validateCookie(context.req.cookies['token'])

  if (userId?.id) {
    return { props: {} }
  }

  return {
    redirect: {
      destination: '/',
      permanent: false,
    },
  }
}

const HomePage: NextPageWithLayout = () => {
  const projects = api.projects.projectList.useQuery({ pageNumber: 0 })

  return (
    <>
      <h1 className="text-3xl font-bold py-5 text-blue-500">Projects</h1>
      {projects.isSuccess && projects.data ? <ProjectList projects={projects.data} /> : <span className="loading loading-spinner loading-lg"/>}
    </>
  )
}

HomePage.getLayout = function getLayout(page: ReactElement) {
  return <LoggedInLayout page={page} />
}

export default HomePage
