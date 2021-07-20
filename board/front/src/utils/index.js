
import axios from 'axios';

function fetchData(){
    const response = axios.get('http://localhost:8080/');
    return response;
}

// function login(){
//     const response = axios.get('http://localhost:8080/login');
//     return response;
// }

export{
    fetchData
}