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
                            <span style="color:black; margin-left:35%">{{ list.title }}</span>
                            <span style="float:right">
                                <v-dialog
                                    v-model="dialog"
                                    persistent
                                    max-width="400px"
                                    height="200px"
                                    :retain-focus="false"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn-icon v-bind="attrs" v-on="on">
                                            <v-icon color="#8E24AA"
                                                default
                                                class="mr-2">
                                                mdi-pencil
                                            </v-icon>
                                        </v-btn-icon>
                                    </template>

                                    <form v-on:submit.prevent="editList(list.id, change_title)">
                                        <v-card>
                                            <v-card-text>
                                                <v-container>
                                                    <v-text-field
                                                        v-model="change_title"
                                                        label="제목 수정"
                                                        hint="바꾸실 제목을 입력해주세요!"
                                                        persistent-hint
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
                                <v-icon
                                    color="#8E24AA"
                                    default
                                    @click="deleteList(list.id)"
                                >
                                    mdi-delete
                                </v-icon>
                            </span>
                        </v-col>
                        <iframe width="100%" height="100%" :src='list.url'></iframe>
                    </v-card>
            </v-timeline-item>
        </v-timeline>
    </v-container>
</template>

<script>
import axios from 'axios';
import {getMyList} from '../utils/index'

export default {
    created(){
        this.getList()
    },
    data:()=>({
        change_title:'',
        dialog:false,
        lists:[],
        playList_user:'',
    }),
    methods:{
        async getList(){
            //플레이 리스트 가지고 오기
            const response = await getMyList();
            this.lists=response.data;
            console.log(this.lists);
        },

        async editList(item, title){
            console.log(item);
            const response = await axios.put('http://localhost:8080/mylist/'+item,
            {
                title: title
            });
            console.log(response);
            // this.$router.go(0);
        },

        async deleteList(item){
            const response = await axios.delete('http://localhost:8080/mylist/'+item);
            console.log(response);
            this.$router.go(0);
        },    
    }
}
</script>