<template>
  <v-card>
    <h3 class="pa-7">
      {{this.mainText}}
    </h3>
  </v-card>
</template>

<script>
export default {
  created(){
    this.name=localStorage.getItem('name');
    this.fetchData();
    
  },
  data:function(){
    return{
      name:'',
      mainText:''
    };
  },
  methods:{
    fetchData:function(){
      if(this.name==null){
        this.mainText="로그인 부탁드립니다."
      }else{  
        this.mainText=this.name + "님 환영합니다."
      }
    }
  },
}
</script>

<style>

</style>