// const SERVER_URL = "http://localhost:8080";
const SERVER_URL = window.location.origin;


export const getImageFromServer = (fileName) => {
    return `${SERVER_URL}/api/v1/image?name=${fileName}`;
};
