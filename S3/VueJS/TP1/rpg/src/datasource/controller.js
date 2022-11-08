import { towns } from './data'

// controllers: mime the API behavior

function getAllTowns() {
  return {error: 0, data: towns}
}

export default{
  getAllTowns
}