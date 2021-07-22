
import axios from 'axios';

function fetchData(){
    const response = axios.get('http://localhost:8080/');
    return response;
}

function getMyList(){
    const response = axios.get('http://localhost:8080/mylist');
    return response;
}

export{
    fetchData, getMyList
}