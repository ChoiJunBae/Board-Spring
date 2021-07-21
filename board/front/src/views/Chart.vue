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
            <v-col v-for="player in players" :key="player" cols="3" sm="6" md="4" lg="3" >
                <iframe width="100%" height="250" :src='player'></iframe>
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
            search:'',  
            source:'',  
        }
    },
    methods:{
        making(){
            localStorage.setItem('search', this.search);
            this.$router.go(0);
        },
        async youtube(){
            this.source=localStorage.getItem('search');
            const encodingSearch = encodeURI(this.source);
            const fullUrl="https://www.googleapis.com/youtube/v3/search?part=snippet&q="+encodingSearch+"&key=AIzaSyC-vIZ3-hbvv-gEsUEhgMvWTqtvyGPK0NE&maxResults=2"
            const video = await axios.get(fullUrl);

            this.src = video.data.items;
            const leng = this.src.length;

            for(var i=0; i<leng; i++){
                this.list[i]='https://www.youtube.com/embed/'+this.src[i].id.videoId
            }
            this.players=this.list
        },
    }
}
</script>

<style>

</style>