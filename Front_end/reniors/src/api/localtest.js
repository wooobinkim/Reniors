import axios from "axios";

let token = localStorage.getItem("token") || "";

export default axios.create({
  headers: {
    "Content-Type": "application/json",
    Authorization: "Bearer " + token,
  },
});
