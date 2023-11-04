export interface Competency {
  id: string
  name: string
}

export interface Geography {
  id: string
  name: string
}

export interface Country {
  id: string
  name: string
  geography: Geography
}

export interface City {
  id: string
  name: string
  country: Country
}

interface Company {
  id: string
  name: string
}

export interface Industry {
  id: string
  name: string
}

export interface Vertical {
  id: string
  name: string
}

interface Skill {
  id: string
  name: string
}

interface ProjectPosition {
  id: string
  isOpen: boolean
  title: string
  skills: Skill[]
}

export interface Project {
  archived: boolean
  cities: City[]
  company: Company
  description: string
  endDate: string
  hidden: boolean
  id: string
  industry: Industry
  vertical: Vertical
  promoted: boolean
  stage: string
  startDate: string
  title: string
  positions: ProjectPosition[]
}
