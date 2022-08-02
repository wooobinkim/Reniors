
import { apiInstance } from "./index.js"

const api = apiInstance()


async function login(credentials, success, fail){
  console.log(JSON.stringify(credentials))
  await api
  .post(`/users/login`, JSON.stringify(credentials))
  .then((response)=>{
    console.log(response)})
  .catch(fail)
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
  // await api.get(`/users`).then(success).catch(fail);
}

export {
  login, findById
}