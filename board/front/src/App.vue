<template>
  <v-app>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer" style="color:#8E24AA"/>
      <v-spacer></v-spacer>
      <v-toolbar-title class="text-h5 font-weight-black">
        <router-link style="text-decoration:none" id="title" to="/">Lali PlayList</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-icon class="mr-6" color="#8E24AA" @click="clearStorage">mdi-logout</v-icon>
      <!--로그인 dialog-->
      <v-dialog
          v-model="dialog"
          persistent
          max-width="450px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn-icon v-bind="attrs" v-on="on">
            <v-icon style="color:#8E24AA">mdi-account</v-icon>
          </v-btn-icon>
        </template>

        <!--로그인 내부-->
        <form v-on:submit.prevent="submitForm">
          <v-card v-on:keyup.enter="submitForm">
            <v-card-title class="ma-2">
              <v-spacer></v-spacer>
              <span id="lali" class="text-h4 font-weight-black">Lali</span>
              <span class="text-h4 ">.Login</span>
              <v-spacer></v-spacer>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                        v-model="email"
                        class="text-h7"
                        label="Email"
                        hint="write only your email"
                        persistent-hint
                        required
                        color="#8E24AA"
                    >
                    </v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                        v-model="password"
                        class="text-h7"
                        label="Password"
                        type="password"
                        required
                        color="#8E24AA"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
              
            </v-card-text>
            <v-card-actions>
              <v-btn
                class="ma-4"
                outlined
                color="#8E24AA"
                text
                @click="dialog = false"
                to="/signUp"
              >
                회원가입
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                  outlined
                  color="#8E24AA"
                  text
                  @click="dialog = false"
              >
                닫기
              </v-btn>
              <v-btn
                  class="mr-5"
                  outlined
                  color="#8E24AA"
                  text
                  @click="dialog = false"
                  type="submit"
              >
                로그인
              </v-btn>
            </v-card-actions>
          </v-card>
        </form>
      </v-dialog>
    </v-app-bar>

    <!-- 네비게이션 바-->
    <v-navigation-drawer
        v-model="drawer"
        app
        white
        :src="require('@/assets/nav.jpg')"
    >
      <template v-slot:img="props">
        <v-img :gradient="gradient" v-bind="props"/>
      </template>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title v-model="navName" class="text-h6 mt-5 mb-2 font-weight-black ">{{navName}}</v-list-item-title>
          <v-list-item-subtitle class="font-weight-black">{{navEmail}}</v-list-item-subtitle><br>
          <v-list-item-subtitle class="font-weight-black">{{navLocation}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item v-for="item in items" :key="item.title" link :to="item.to">
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main style="white">
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios';

export default {
  created(){
    this.navName=localStorage.getItem('name');
    this.navEmail=localStorage.getItem('email');
    this.navLocation=localStorage.getItem('location'); 
  },
  name: 'App',

  components: {},
  data: () => ({
    //로그인 페이지
    dialog:false,
    //좌측 네비게이션
    drawer: false,
    gradient: 'rgba(255,255,255, .7), rgba(0,0,0, .7)',
    items: [
      {title: '현재 날씨 확인하기', icon: 'mdi-weather-hazy', to: '/'},
      {title: '전국 날씨 보기', icon: 'mdi-weather-hazy', to: '/Chart'},
      {title: '음악 게시판 가기', icon: 'mdi-animation-play', to: '/list'},
      {title: '디테일 정보', icon: 'mdi-animation-play', to: '/detail'}
    ],
    right: null,
    
    email:'',
    password:'',
    navName:'로그인하세요',
    navEmail:'',
    navLocation:''
  }),

  methods:{
    clear(){
      this.email='',
      this.password=''
    },

    clearStorage(){
      localStorage.clear('name','password','location');
      this.$router.go(0);
      alert('로그아웃 되었습니다...')
    },

    async submitForm(){
      var url = "http://localhost:8080/login"
      var data={
        email:this.email,
        password:this.password
      }
      const response = await axios.post(url, data);
      console.log(response.data.findUsers);
      
      if(response.data.findUsers.length==0){
        alert("아이디 비밀번호를 확인해주세요");
        this.clear()
      }else{
        localStorage.setItem('name', response.data.findUsers[0].name);
        localStorage.setItem('email', response.data.findUsers[0].email);
        localStorage.setItem('location', response.data.findUsers[0].location);
        this.$router.go(0);
      }
    }
  }
};
</script>

<style>

</style>
