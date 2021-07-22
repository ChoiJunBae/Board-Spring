<template>
    <v-container style="max-width: 1000px;">
        <v-timeline dense clipped v-for="(list,index) in lists" :key="index">
            <v-timeline-item
                fill-dot
                class="mb-16"
                style="font-weight: bold"
                color="#8E24AA22"
                large
            >
                <template v-slot:icon>
                    <span>{{index+1}}</span>
                </template>
                    <v-card width="500px" height="300px" outlined style="border-radius:20px; background-color:#8E24AA22">
                        <v-col pa-5>
                            <h4 style="color:black; text-align:center; font-weight: bold">{{ list.title }}</h4>
                        </v-col>
                        <iframe width="100%" height="100%" :src='list.url'></iframe>
                    </v-card>
            </v-timeline-item>
        </v-timeline>
    </v-container>
</template>

<script>
import {getMyList} from '../utils/index'

export default {
    created(){
        this.getList()
    },
    data:()=>({
        lists:[],
        playList_user:'',
    }),
    methods:{
        async getList(){
            //플레이 리스트 가지고 오기
            const response = await getMyList();
            this.lists=response.data;
            console.log(this.list);

            //플레이 리스트 사용자 가지고 오기
            
        }
    }
}
</script>