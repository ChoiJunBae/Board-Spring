<template>
  <v-container>
		<v-row class="pa-6" >
            <v-text-field
                class="ma-9"
                v-on:keyup.enter="making()"
                v-model="search"
                label="검색할 노래를 입력하세요."
                required
                height="60px"
                background-color="#8E24AA22"
                solo
            ></v-text-field>
            <h3 class="pa-6">[ 검색어 : {{this.source}} ]</h3>
		</v-row>
        
        <v-row >
            <v-col v-for="(player,index) in players" :key="index" cols="12" sm="6" md="4" lg="3" class="OneRecommend" width="350" height="250">
                <span class="indexText">No. {{index+1}}</span>
                <span style="float:right">
                    <v-dialog
                        v-model="dialog"
                        persistent
                        max-width="400px"
                        height="200px"
                        :retain-focus="false"
                    >
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn-icon v-bind="attrs" v-on="on" @click="setIndex(index)">
                                <v-icon style="color:#8E24AA">mdi-playlist-plus</v-icon>
                            </v-btn-icon>
                        </template>

                        <form v-on:submit.prevent="goMyList(user_title,itemIndex)">
                            <v-card>
                                <v-card-text>
                                    <v-container>
                                        <v-text-field
                                            v-model="user_title"
                                            label="제목 입력"
                                            hint="어떤 제목으로 저장할지 입력해주세요!"
                                            persistent-hints
                                            required
                                            color="#8E24AA"
                                        >
                                        </v-text-field>
                                    </v-container>
                                </v-card-text>
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                        outlined
                                        color="#8E24AA"
                                        @click="dialog = false"
                                    >
                                        <v-icon>mdi-close-thick</v-icon>
                                    </v-btn>
                                    <v-btn
                                        outlined
                                        color="#8E24AA"
                                        @click="dialog=false"
                                        type="submit"
                                    >
                                        <v-icon>mdi-check-bold</v-icon>
                                    </v-btn>
                                    <v-spacer></v-spacer>
                                </v-card-actions>
                            </v-card>
                        </form>
                    </v-dialog>
                </span>
                <hr>
                <h4 class="titleStyle ">{{ player.title}}</h4>
                <iframe id="youtube" width="100%" height="100%" :src='player.url'></iframe>
            </v-col>
        </v-row>
	</v-container>
</template>

<script>
import axios from 'axios'

export default {
    created(){
        this.user_id=parseInt( localStorage.getItem('id'));
        this.youtube()
        console.log(this.players);
    }, 
    data: function(){
        return{
            dialog:false,

            user_id:0,
            user_name:'',
            user_condition:'',
            
            src:[],
            list:[], 
            players:[], // 상단 v-for에서 사용되는 배열
            search:'',  
            source:'',  

            user_title:'',
            itemIndex :0
        }
    },
    methods:{
        setIndex(forIndex){
            this.itemIndex = forIndex
        },
        async goMyList(user_title,index){     //DB에 저장할 노래 고르기
            var url = `http://localhost:8080/mylist`;
            var data = {
                user:this.user_id,
                title:user_title,
                url:this.players[index].url
            }
            try{
                await axios.post(url, data);
                console.log(this.user_id);
                console.log(index);
                alert("등록 성공!");
            }catch(error){
                alert("등록 실패!")
            }
        },

        making(){
            localStorage.setItem('search', this.search);
            this.$router.go(0);
        },
        
        async youtube(){
            this.user_name=localStorage.getItem('name');
            this.user_condition=localStorage.getItem('condition');
            this.source=localStorage.getItem('search');

            if(this.source==null){
                this.source=this.user_condition+" 노래";
            }

            if(this.user_name!=null){
                const encodingSearch = encodeURI(this.source);
                const fullUrl="https://www.googleapis.com/youtube/v3/search?part=snippet&q="+encodingSearch+"&key=AIzaSyBtT841MQP7oR0GgErtATSj6emtSyUWtFc&maxResults=3"
                const video = await axios.get(fullUrl);

                this.src = video.data.items;
                const leng = this.src.length;

                for(var i=0; i<leng; i++){
                    this.list[i]={
                        title: this.src[i].snippet.title,
                        url:'https://www.youtube.com/embed/'+this.src[i].id.videoId
                    }
                }
                this.players=this.list
                console.log(this.players);
            }else{
                alert('로그인 후 사용 부탁드립니다.')
                this.$router.push('/')
            } 
        },
    }
}
</script>

<style>
.titleStyle{
    display: inline-block;
    width:100%;
    height: 50px;
    white-space: nowrap;
    overflow:hidden;
    text-overflow: ellipsis;
    white-space: normal;
}

#youtube{
    border-radius: 16px;
    border:0;
    box-shadow: 8px 8px 8px gray;
}

.OneRecommend{
    padding: 30px;
    margin: 30px 60px 100px 60px;
    background-color: #8E24AA22;
    border-radius: 16px;
}

.indexText{
    font-size: 30px;
    font-weight: bold;
    color: #8E24AA;
}
</style>