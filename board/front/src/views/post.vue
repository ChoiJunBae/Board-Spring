<template>
  <v-card class="pa-5" width="1000px" style="margin:auto">
    <h2 class="pa-5" style="text-align:center; background-color:#8E24AA33; border-radius:16px 16px 0 0">게시글 등록하기</h2>
    <form v-on:submit.prevent="submitForm">
      <v-text-field
          color="#8E24AA"
          v-model="title"
          class="font-weight-black mt-5"
          label="제목"
          required
          outlined
          placeholder="제목을 입력해주세요."
          
      ></v-text-field>
      <v-text-field
          color="#8E24AA"
          v-model="author"
          class="font-weight-black"
          label="작성자"
          required
          outlined    
          disabled      
      ></v-text-field>
      <v-text-field
          color="#8E24AA"
          v-model="content"
          height="300px"
          :counter="100"
          class="font-weight-black"
          outlined
          placeholder="내용을 입력해주세요. (최대 100자)"
          label="내용"
          required
          style="border-radius:0 0 16px 16px"
      ></v-text-field>
      <!-- <v-file-input class="mb-5 font-weight-black"
          v-model="file"
          outlined
          accept="image/*"
          label="첨부하실 파일을 선택해주세요!"
      ></v-file-input> -->
      <v-row class="ma-3">
        <v-spacer></v-spacer>
        <v-btn class="subButton mr-4" style="border-radius:13px"  @click="clear" color="#8E24AA44">
          <v-icon>mdi-reload</v-icon>
        </v-btn>
        <v-btn class="subButton" style="border-radius:13px" type="submit" color="#8E24AA44">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </v-row>

      
    </form>
  </v-card>
</template>

<script>
import axios from 'axios'

export default {
  created(){
    this.author=localStorage.getItem('name')
  },
  data:() =>{
    return{  

      title:'',
      author:'',
      content:'',
      // file:''
    }
  },
  methods:{
    clear(){
      this.title='',
      this.author='',
      this.content=''
      // this.file=''
    },
    
    async submitForm(){
      var url = "http://localhost:8080/post";
      var data={
        title:this.title,
        author:this.author,
        content:this.content,
        // origFilename:this.file
      }

      try {
         await axios.post(url,data);
         console.log(data);
         alert("등록되었습니다.");
         this.$router.push('/detail')
      } catch (error) {
        alert("등록에 실패하셨습니다.");
      //   })
      }
     

  

    },
  }
}
</script>

<style>
.subButton{
  width: 100px;
  height: 100px;
}
</style>