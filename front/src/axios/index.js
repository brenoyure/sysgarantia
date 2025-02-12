import axios from 'axios'

const apihost = process.env.NODE_ENV == 'development' ? 'http://localhost:8080/' : '/sysgarantiaapi'

axios.defaults.baseURL = apihost

export default axios