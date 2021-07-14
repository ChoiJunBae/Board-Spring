<template>
  <v-card>
      <v-card-title>
          노래 게시판
          <v-spacer></v-spacer>
          <v-text-field
            v-model = "search"
            append-icon="mdi-magnify"
            label="검색할 노래를 입력해주세요"
            single-line
            hide-details
            >
        </v-text-field>
        <v-spacer></v-spacer>
        <v-btn to="/post" class="ma-5" fab dark>
            <v-icon dark>
                mdi-plus
            </v-icon>
        </v-btn>
      </v-card-title>
      <v-data-table
          :headers="headers"
          :items="desserts"
          :search="search"
        >
      </v-data-table>
    </v-card>
</template>

<script>
import {fetchData} from '../api/index';

export default {
    created(){
        this.getData()
    },
    data(){
        return{
            list:[],
            search:'',
            headers:[
                { text: '게시글 번호', value: 'id' },
                { text: '제목', value: 'content' },
                { text: '작성자', value: 'author' },
                { text: '작성일', value: 'createDate' },
            ],
            desserts:[],
        }
    },
    methods:{
        async getData(){    
            const response = await fetchData();
            this.desserts = response.data;
        }
    }
}
</script>

<style>

</style>