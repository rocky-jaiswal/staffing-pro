import { useState, type ReactElement } from 'react'
import type { GetServerSidePropsContext } from 'next'

import type { NextPageWithLayout } from './_app'
import { api } from '../utils/api'
import { validateCookie } from '../server/lib/token'

import LoggedInLayout from '../components/LoggedInLayout'
import ProjectList from '../components/ProjectList'
import FilterBox from '../components/FilterBox'

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
  const [showFilters, setShowFilters] = useState(false)
  const [selectedCountry, setSelectedCountry] = useState<string | null>(null)

  const competencies = api.competencies.competenciesList.useQuery()
  const countries = api.countries.countryList.useQuery()
  const projects = api.projects.projectList.useQuery({
    pageNumber,
    selectedCountry,
  })

  const isMaxPage =
    (pageNumber + 1) * PAGE_SIZE >= (projects.data?.count ?? PAGE_SIZE)

  return (
    <>
      <h1 className="text-3xl font-bold py-5 text-blue-500">Projects</h1>
      <button
        className="btn-primary w-20"
        onClick={() => setShowFilters(!showFilters)}
      >
        Filter
      </button>
      <div>
        {showFilters ? (
          <FilterBox
            countries={countries.isSuccess ? countries.data : []}
            competencies={competencies.isSuccess ? competencies.data : []}
            setSelectedCountry={(country: string) =>
              setSelectedCountry(country)
            }
          />
        ) : (
          <div />
        )}
      </div>
      <div className="h-5" />
      {projects.isSuccess && projects.data ? (
        <ProjectList projects={projects.data.projects} />
      ) : (
        <span className="loading loading-spinner loading-lg" />
      )}
      <div className="flex">
        <button
          className="btn mx-2"
          disabled={pageNumber === 0}
          onClick={() => setPageNumber(pageNumber - 1)}
        >
          ‹ Prev
        </button>
        <button
          className="btn"
          disabled={isMaxPage}
          onClick={() => setPageNumber(pageNumber + 1)}
        >
          Next ›
        </button>
      </div>
    </>
  )
}

HomePage.getLayout = function getLayout(page: ReactElement) {
  return <LoggedInLayout page={page} />
}

export default HomePage
