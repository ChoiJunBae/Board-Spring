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
    // 대전
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=jeju&units=metric&APPID=b2db8accc6ec05516f88f15ac5e84430');
    return response;
}

async function youtube(){
    // const channelId = 'UCYklS0dalWLBU0aDKaLyuKw';
    // const apiKey = 'AIzaSyDMuWwweIbcucrUUDx2A0JS17QxwpY_erw';
    // const response = await axios.get('https://www.googleapis.com/youtube/v3/search?part=snippet&q=kpop+music&key=AIzaSyDMuWwweIbcucrUUDx2A0JS17QxwpY_erw');
    // const response = await axios.get( `https://www.googleapis.com/youtube/v3/playlists?key=${apiKey}&id=${channelId}&part=snippet,contentDetails,statistics`);
    const response = await axios.get( `https://www.googleapis.com/youtube/v3/search?part=snippet&q=%EB%94%A9%EA%B3%A0%20%EB%AE%A4%EC%A7%81&key=AIzaSyDMuWwweIbcucrUUDx2A0JS17QxwpY_erw`);
    return response;
}

export {
    fetchSoeul, fetchBusan, fetchGwang, fetchDeajeon, fetchJeju, youtube
}