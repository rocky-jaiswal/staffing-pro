import { type ProjectType } from '../../utils/api'

interface Props {
  projects: ProjectType[]
}

function ProjectList(props: Props) {
  return (
    <div className="flex justify-between">
      <div className="flex flex-col w-full">
        {props.projects.map((project: ProjectType) => {
          return (
            <div key={project.id}>
              <div className="flex justify-between border border-slate-200 rounded-sm p-1 shadow">
                <div className="text-xl font-bold py-5 px-5 text-gray-600">
                  {project.title}
                </div>
                <div className="flex flex-col justify-between">
                  <div className="flex flex-row-reverse">
                    <div className="badge bg-teal-100 mx-px">
                      {project.industry.name}
                    </div>
                    <div className="badge bg-pink-100">
                      {project.vertical.name}
                    </div>
                  </div>
                  <div className="flex flex-row-reverse">
                    {project.cities.map((city) => {
                      return (
                        <div className="badge bg-sky-100 mx-px" key={city.id}>
                          {city.name}, {city.country.name}
                        </div>
                      )
                    })}
                  </div>
                  <div className="flex flex-row-reverse">
                    <div className="badge bg-lime-100 mx-px">
                      {project.stage}
                    </div>
                    <div className="badge bg-fuchsia-100 mx-px">
                      {project.positions.length} Positions
                    </div>
                  </div>
                </div>
              </div>
              <div className="p-2" />
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default ProjectList
