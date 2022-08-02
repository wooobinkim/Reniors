import axios from "axios";

const API_BASE_URL = "http://localhost:7777";

function apiInstance(){
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json"
    }
  })
  return instance
}

const api = apiInstance();

async function login(user, success, fail){
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

export {
  login, findById
}