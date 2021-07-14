<template>
  <v-app>
    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer" style="color:#8E24AA"/>
      <v-spacer></v-spacer>
      <v-toolbar-title class="text-h5 font-weight-black">
        <router-link id="title" :to="{name:'Weather'}">음악 등록 페이지</router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <!--상단 로그인 버튼-->
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

        <!--        로그인 내부-->
        <v-card>
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
                      label="Email"
                      hint="write only your google email"
                      persistent-hint
                      required
                      color="#8E24AA"
                  >
                  </v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                      label="Password"
                      type="password"
                      required
                      color="#8E24AA"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="#8E24AA"
                text
                @click="dialog = false"
            >
              Close
            </v-btn>
            <v-btn
                color="#8E24AA"
                text
                @click="dialog = false"
            >
              Login
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-app-bar>

    <!--    좌측 네비게이션-->
    <v-navigation-drawer
        v-model="drawer"
        app
        white
        :src="require('@/assets/cold-bg.jpg')"
    >
      <template v-slot:img="props">
        <v-img :gradient="gradient" v-bind="props"/>
      </template>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h mt-5 mb-2 font-weight-black"> 최준배</v-list-item-title>
          <v-list-item-subtitle>cjb9610@naver.com</v-list-item-subtitle>
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
        <router-link to="/">리스트 보기</router-link>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {},
  data: () => ({
    //로그인 페이지
    dialog:false,
    //좌측 네비게이션
    drawer: false,
    gradient: 'rgba(255,255,255, .7), rgba(0,0,0, .7)',
    items: [
      {title: 'Weather', icon: 'mdi-weather-hazy', to: '/weather'}
    ],
    right: null,
    
  }),
};
</script>

<style>

#lali{
  color:#8E24AA;
}

</style>
