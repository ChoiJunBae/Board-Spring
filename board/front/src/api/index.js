import axios from 'axios';

async function fetchSoeul(){
    // 서울
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=seoul&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

async function fetchBusan(){
    // 부산
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=busan&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

async function fetchGwang(){
    // 광주
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=gwangju&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

async function fetchDeajeon(){
    // 대전
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=daejeon&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

async function fetchJeju(){
    // 제주
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=jeju&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

export {
    fetchSoeul, fetchBusan, fetchGwang, fetchDeajeon, fetchJeju
}