
import axios from 'axios';

async function fetchData(){
    const response = await axios.get('http://localhost:8080/home');
    return response;
}

export{
    fetchData,
}