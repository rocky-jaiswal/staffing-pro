import { useState, type ReactElement } from 'react'
import type { GetServerSidePropsContext } from 'next'

import type { NextPageWithLayout } from './_app'
import { api } from '../utils/api'
import { validateCookie } from '../server/lib/token'

import LoggedInLayout from '../components/LoggedInLayout'
import ProjectList from '../components/ProjectList'

const PAGE_SIZE = 10

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
  const [pageNumber, setPageNumber] = useState(0)

  const projectCount = api.projects.projectCount.useQuery()
  const projects = api.projects.projectList.useQuery({ pageNumber })

  const isMaxPage = ((pageNumber + 1) * PAGE_SIZE) >= (projectCount.data?.count ?? PAGE_SIZE)
  
  return (
    <>
      <h1 className="text-3xl font-bold py-5 text-blue-500">Projects</h1>
      {projects.isSuccess && projects.data ? <ProjectList projects={projects.data} /> : <span className="loading loading-spinner loading-lg"/>}
      <div className="flex">
        <button className="btn mx-2" disabled={pageNumber === 0} onClick={() => setPageNumber(pageNumber-1)}>Prev</button>
        <button className="btn" disabled={isMaxPage} onClick={() => setPageNumber(pageNumber+1)}>Next</button>
      </div>
    </>
  )
}

HomePage.getLayout = function getLayout(page: ReactElement) {
  return <LoggedInLayout page={page} />
}

export default HomePage
