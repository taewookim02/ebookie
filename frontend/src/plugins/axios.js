import axios from "axios";


const customAxios = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 1000
});


export { customAxios };