import { type CompetencyType, type CountryType } from '../../utils/api'

interface Props {
  countries?: CountryType[]
  competencies?: CompetencyType[]
  setSelectedCountry: (countryId: string) => unknown
}

function FilterBox(props: Props) {
  return (
    <div className="flex flex-col w-full p-4 my-2 bg-sky-200">
      <div className="flex py-4">Region:</div>
      <div className="flex py-4">
        <p className="py-2 pr-4">Country:</p>
        <select
          className="select select-secondary w-full max-w-xs"
          onChange={(e) => props.setSelectedCountry(e.currentTarget.value)}
        >
          {(props.countries ?? []).map((country: CountryType) => {
            return (
              <option key={country.id} value={country.id}>
                {country.name}
              </option>
            )
          })}
        </select>
      </div>
      <div className="flex py-4">
        <p className="py-2 pr-4">Competency:</p>
        <select
          className="select select-secondary w-full max-w-xs"
          //onChange={(e) => props.setSelectedCountry(e.currentTarget.value)}
        >
          {(props.competencies ?? []).map((competency: CompetencyType) => {
            return (
              <option key={competency.id} value={competency.id}>
                {competency.name}
              </option>
            )
          })}
        </select>
      </div>
      <div className="flex py-4">
        <button className="btn-primary p-2">Apply</button>
      </div>
    </div>
  )
}

export default FilterBox
