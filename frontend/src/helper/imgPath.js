const SERVER_URL = "http://localhost:8080";


export const getImageFromServer = (fileName) => {
    return `${SERVER_URL}/api/v1/image?name=${fileName}`;
};
