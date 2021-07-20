<template>
  <v-container>
		<v-row style="border-radius:10">
            <v-text-field
                class="ma-5"
                v-on:keyup.enter="making()"
                v-model="search"
                label="검색할 노래를 입력하세요."
                required
                outlined
            ></v-text-field>
		</v-row>
        <v-row>
            <v-col v-for="(player,index) in players" :key="index" cols="3" sm="6" md="4" lg="3" >
                <h4 style="color:red">{{index+1}}번 리스트</h4>
                <h5 class="titleStyle">{{player.name}}</h5>
                <iframe width="100%" height="250" :src='player.iframe'></iframe>
            </v-col>
        </v-row>
	</v-container>
</template>

<script>
import axios from 'axios'

export default {
    created(){
        this.youtube()
    },
    data: function(){
        return{
            src:[],
            list:[], 
            players:[], // 상단 v-for에서 사용되는 배열
            search:'',  // 검색 창에서 입력되어지는 검색어
            source:'',  // youtube메소드 논리 연산을 위한 객체
            condition:'' // 날씨 정보
        }
    },
    methods:{
        //검색을 할 경우 현재 input에 있는 검색어로 localstorage의 search값을 바꾸고 페이지를 새로고침
        making(){
            localStorage.setItem('search', this.search);
            this.$router.go(0);
        },

        // 페이지 시작 시 바로 시작되며 저장되었던 search있다면 그것을 검색하고 아니라면 날씨에 따라서 검색함
        async youtube(){
            this.source = localStorage.getItem('search');
            // 검색어 인코딩후 url에 부착하여 axios get 요청 진행
            const encodingSearch = encodeURI(this.source);
            const fullUrl="https://www.googleapis.com/youtube/v3/search?part=snippet&q="+encodingSearch+"&key=AIzaSyCi9gnSKw2wgpuh0X04YiS-3GlC6lSCoo8&maxResults=1"
            const video = await axios.get(fullUrl);
            console.log(fullUrl);

            this.src = video.data.items;
            const leng = this.src.length;
            
            //for문으로 상단 v-for에서 사용할 player 배열 생성
            for(var i=0; i<leng; i++){
                const cName = this.src[i].snippet.title;
                const cIframe = 'https://www.youtube.com/embed/'+this.src[i].id.videoId+'?controls=0'
                this.players[i]= {
                    name: cName,  // 플레이어 이름
                    iframe: cIframe  // 플레이어 URL
                }
            }
        },
    }
}
</script>

<style>

</style>