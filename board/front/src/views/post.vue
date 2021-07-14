<template>
  <v-card class="pa-5">
    <form>
      <v-text-field
          v-model="name"
          :error-messages="nameErrors"
          class="font-weight-black"
          label="제목"
          required
          outlined
          placeholder="제목을 입력해주세요."
          @input="$v.name.$touch()"
          @blur="$v.name.$touch()"
      ></v-text-field>
      <v-text-field
          v-model="author"
          :error-messages="authorErrors"
          class="font-weight-black"
          label="작성자"
          required
          outlined
          placeholder="이름을 입력해주세요."
          @input="$v.author.$touch()"
          @blur="$v.author.$touch()"
      ></v-text-field>
      <v-text-field
          v-model="content"
          :error-messages="contentErrors"
          :counter="100"
          class="font-weight-black"
          outlined
          placeholder="내용을 입력해주세요. (최대 100자)"
          label="내용"
          required
          @change="$v.content.$touch()"
          @blur="$v.content.$touch()"
      ></v-text-field>
      <v-file-input class="mb-5 font-weight-black"
          outlined
          accept="image/*"
          label="첨부하실 파일을 선택해주세요!"
      ></v-file-input>
      <v-row class="ma-3">
        <v-spacer></v-spacer>
        <v-btn style="border-radius:25px" class="mr-4" @click="clear">
          <v-icon>mdi-reload</v-icon>
        </v-btn>
        <v-btn style="border-radius:25px" @click="submit">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </v-row>

      
    </form>
  </v-card>
</template>

<script>
export default {
  data:() =>({
    name:'',
    author:'',
    content:'',
  }),
  methods:{
    clear(){
      this.name='',
      this.author='',
      this.content=''
    }
  }
}
</script>

<style>

</style>