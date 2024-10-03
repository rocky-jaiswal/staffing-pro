import { useState } from 'react'
import { type Competency, type Country, type Geography } from '../../utils/api'

interface Props {
  geographies: Geography[]
  countries?: Country[]
  competencies?: Competency[]
  setSelectedGeography: (geography: string) => unknown
  setFilter: (filter: (string | null)[]) => unknown
}

function FilterBox(props: Props) {
  const [geography, setGeography] = useState<string>('')
  const [country, setCountry] = useState<string>('')
  const [competency, setCompetency] = useState<string>('')

  return (
    <div className="flex flex-col w-full p-4 my-2 bg-sky-200">
      <div className="flex py-4">
        <p className="py-2 pr-4 w-1/6">Geography:</p>
        <select
          className="select select-secondary w-full max-w-xs"
          onChange={(e) => {
            setGeography(e.currentTarget.value)
            setCountry('')
            props.setSelectedGeography(e.currentTarget.value)
          }}
          value={geography}
        >
          <option key={0} value={''}>
            --
          </option>
          {(props.geographies ?? []).map((geography: Geography) => {
            return (
              <option key={geography.id} value={geography.id}>
                {geography.name}
              </option>
            )
          })}
        </select>
      </div>
      <div className="flex py-4">
        <p className="py-2 pr-4 w-1/6">Country:</p>
        <select
          className="select select-secondary w-full max-w-xs"
          onChange={(e) => setCountry(e.currentTarget.value)}
          value={country}
        >
          <option key={0} value={''}>
            --
          </option>
          {(props.countries ?? []).map((country: Country) => {
            return (
              <option key={country.id} value={country.id}>
                {country.name}
              </option>
            )
          })}
        </select>
      </div>
      <div className="flex py-4">
        <p className="py-2 pr-4 w-1/6">Competency:</p>
        <select
          className="select select-secondary w-full max-w-xs"
          onChange={(e) => setCompetency(e.currentTarget.value)}
          value={competency}
        >
          <option key={0} value={''}>
            --
          </option>
          {(props.competencies ?? []).map((competency: Competency) => {
            return (
              <option key={competency.id} value={competency.id}>
                {competency.name}
              </option>
            )
          })}
        </select>
      </div>
      <div className="flex py-4">
        <button
          className="btn-primary p-2 mx-2"
          disabled={[geography, country, competency].every((e) => e === '')}
          onClick={() => {
            props.setFilter([geography, country, competency])
          }}
        >
          Apply
        </button>
        <button
          className="btn-secondary p-2"
          onClick={() => {
            setGeography('')
            setCountry('')
            setCompetency('')
            props.setFilter([])
          }}
        >
          Clear All
        </button>
      </div>
    </div>
  )
}

export default FilterBox
