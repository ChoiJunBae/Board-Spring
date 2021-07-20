<template>
    <div id="app2" :class="typeof weather.main != 'undefined'
        && (this.weather.weather[0].main) == 'Clouds' ? 'cloud':''
        && (this.weather.weather[0].main) == 'Rain' ? 'rain':''
        ">
      <v-container>
        <v-row>
          <v-col>
            <main>
              <div class="weatherMain">
                <div class="location-box">
                  <span style="color:#8E24AA font-weight-black" class="location">{{this.query}}</span>
                  <span style="color:#8E24AA " class="text-h5">의 현재 날씨</span>
                  <div style="color:#8E24AA"  class="date">{{ dateBuilder() }}</div>
                </div>
                <div class="weather-box">
                  <div style="color:#8E24AAee"  class="temp">{{ Math.round(weather.main.temp) }}℃</div>  
                  <div style="color:#8E24AAaa"  class="weather">{{ this.condition }}</div>
                </div>
              </div>
            </main>
          </v-col>
          <v-col>
            <canvas id="myChart" width="400" height="200"></canvas>
          </v-col>
        </v-row>
      </v-container>
    </div>
</template>

<script> 
import Chart from 'chart.js/auto'
import {fetchBusan, fetchDeajeon, fetchGwang, fetchSoeul} from '../api/index';

export default {
  //시작과 동시에 fetchWeather를 실행하여한다.
  created(){
    this.query=localStorage.getItem('location');
    this.fetchWeather();
  },

  async mounted(){
    await this.getData();
    var ctx = document.getElementById('myChart');
    var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['서울', '부산', '광주', '대구'],
        datasets: [{
          label: '각 지역의 온도 정보',
          data: [this.w_seoul.main.temp, this.w_busan.main.temp, 
                  this.w_gwangju.main.temp, this.w_daejeon.main.temp],
          backgroundColor: [
              'rgba(255, 99, 132, 0.7)',
          ],
      }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
      }
    });
    console.log(myChart);
  },

  // data 정의부
  data: function () {
    return {
      api_key: "b2db8accc6ec05516f88f15ac5e84430",
      url_base: "https://api.openweathermap.org/data/2.5/",
      query: '',
      en_query:'',
      condition:'',
      weather: {},

      w_seoul:[],
      w_busan:[],
      w_gwangju:[],                                              
      w_daejeon:[],
    };
  }, 

  //메소드 정의부
  methods: {
    //회원의 지역 정보를 먼저 받아와서 query에서 한글을 영문으로 변경
    fetchWeather: function () {
      switch(this.query){
        case '서울':
          this.en_query = 'seoul';
          break;
        case '광주':
          this.en_query = 'gwangju';
          break;
        case '대전':
          this.en_query = 'daejeon';
          break;
        case '부산':
          this.en_query = 'busan';
          break;
        case '제주':
          this.en_query = 'jeju';
          break;
      }
      // 바꾼 영문 지역 명을 넣어 openWeatherMap에서 API값 받아오기
      let fetchUrl = `${this.url_base}weather?q=${this.en_query}&units=metric&APPID=${this.api_key}`;
      fetch(fetchUrl).then((res) => {
        console.log(res);
        return res.json();
        //성공했을 경우 setResult함수에 result 값 넘겨주기
      }).then((results) => {
        return this.setResult(results);
      });
    }, 

    //weather 배열에 저장
    setResult: function (results) {
      this.weather = results;
      //weather 내부의 json 배열 중 날씨 컨디션 정보를 한글화로 변경
      switch(this.weather.weather[0].main){
        case 'Thunderstorm':
          this.condition = '천둥'
          break;
        case 'Drizzle':
          this.condition = '이슬비'
          break;
        case 'Rain':
          this.condition = '비'
          break;
        case 'Snow':
          this.condition ='눈'
          break;
        case 'Clear':
          this.condition = '맑음'
          break;
        case 'Clouds':
          this.condition = '흐림'
          break;
        default:
          break;
      }
      localStorage.setItem('condition', this.condition);
    }, 

    //날짜 정보 핸들링 구간
    dateBuilder: function () {
      let d = new Date();
      let months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월",];
      let days = ["(일)", "(월)", "(화)", "(수)", "(목)", "(금)", "(토)",];
      let day = days[d.getDay()];
      let date = d.getDate();
      let month = months[d.getMonth()];
      let year = d.getFullYear();
      return `${year} ${month} ${date} ${day} `;
    },

    // 차트에 띄워줄 정보 가지고 오기
    async getData(){
      const seoul = await fetchSoeul();
      const busan = await fetchBusan();
      const gwangju = await fetchGwang();
      const daejeon = await fetchDeajeon();

      this.w_seoul = seoul.data;
      this.w_busan = busan.data;
      this.w_gwangju = gwangju.data;
      this.w_daejeon = daejeon.data;
   },
  },
}; 
</script>

<style>
#app2 {
  border-radius: 15px;
  background-image: url("../assets/sunny.jpg");
  background-size: cover;
  min-height: 100vh;
}

#app2.rain {
  background-image: url("../assets/rain.jpg");
}

#app2.cloud {
  background-image: url("../assets/cloud.jpg");
}

.weatherMain{
  padding: 30px;
}

.location-box .location {
  font-size: 40px;
  font-weight: 500;
  text-shadow: 1px 3px rgba(0, 0, 0, 0.25);
}

.location-box .date {
  color: #fff;
  font-size: 30px;
  font-weight: 500; 
}

.weather-box .temp {
  display: inline-block;
  padding: 10px 25px;
  font-size: 102px;
  font-weight: 700;
  text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.25);
  border-radius: 15px;
  margin: 30px 0px;
  box-shadow: 3px 6px rgba(0, 0, 0, 0.25);
}

.weather-box .weather {
  font-size: 50px;
  font-weight: 900; 
  text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
}
</style>
