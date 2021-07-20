<template>
<v-card>
    <v-form class="pa-5" style="text-align:center" v-on:submit.prevent="submitForm">
        <div class="ma-6">
            <h2>회원가입</h2>
        </div>
        <!-- 이름 입력부 -->
        <v-row>
            <v-spacer></v-spacer>
            <v-text-field
            style="width:50%"
            class="pr-6 pl-6 mb-6"
            :rules="nameRules"
            required
            v-model="name"
            label="이름 입력"
            type="text"
            prepend-icon="mdi-account"
            ></v-text-field>
            <v-spacer></v-spacer>
        </v-row>

        <!-- 이메일 입력부 -->
        <v-row>
            <v-spacer></v-spacer>
            <v-text-field
            style="width:50%"
            class="pr-6 pl-6 mb-6"
            :rules="emailRules"
            required
            v-model="email"
            label="이메일 입력"
            type="email"
            prepend-icon="mdi-email"
            ></v-text-field>
            <v-spacer></v-spacer>
        </v-row>

        <!-- 비밀번호 입력부 -->
        <v-row>
            <v-spacer></v-spacer>
            <v-text-field
            style="width:50%"
            class="pr-6 pl-6"
            :rules="passwordRules"
            required
            v-model="password"
            label="비밀번호 입력"
            type="password"
            prepend-icon="mdi-lock"
            ></v-text-field>
            <v-spacer></v-spacer>
        </v-row>

        <!-- 재비밀번호 입력부 -->
        <v-row>
            <v-spacer></v-spacer>
            <v-text-field
            style="width:50%"
            class="pr-6 pl-6 mb-6"
            :rules="[rePasswordRules, matchPassword]"
            required
            v-model="repassword"
            label="비밀번호 재확인"
            type="password"
            prepend-icon="mdi-lock"
            ></v-text-field>
            <v-spacer></v-spacer>
        </v-row>

        
        <!-- 지역 선택 select부 -->
        <v-row>
            <v-spacer></v-spacer>
            <v-select
            style="width:50%"
            class="pr-6 pl-6"
            v-model="location"
            :items="items"
            :rules="[v => !!v || 'Item is required']"
            label="사는 지역을 입력해주세요"
            required
            prepend-icon="mdi-map"
            ></v-select>
            <v-spacer></v-spacer>
        </v-row>
        
        <v-row class="mt-4 mb-4">
            <v-spacer></v-spacer>
            <!-- 가입하기 버튼 -->
            <v-btn style="border-radius:25px" class="mr-4" @click="clear">
                <v-icon>mdi-reload</v-icon>
            </v-btn>
            <v-btn style="border-radius:25px" class="mr-5" type="submit">
                <v-icon>mdi-send</v-icon>
            </v-btn>
        </v-row>
    </v-form>
  </v-card>
</template>

<script>
import axios from 'axios'
export default {
    data: () => ({
        name:'',
        email:'',
        password:'',
        repassword:'',
        location: '',

        nameRules: [
            v => !!v || '이름을 작성해주세요',
        ],
        emailRules: [
            v => !!v || '이메일을 작성해주세요',
            v => /.+@.+\..+/.test(v) || '이메일 형식으로 작성해주세요',
        ],
        passwordRules:[
            v => !!v || '비밀번호를 작성해주세요',
        ],
        rePasswordRules:[
            v => !!v || '비밀번호를 재작성해주세요', 
        ],
        
        items: [
            '서울',
            '광주',
            '대전',
            '부산',
            '제주'
        ],
    }),

    methods: {
        clear(){
            this.name='',
            this.email='',
            this.password='',
            this.repassword='',
            this.location=''
        },

        async submitForm(){
            var url = "http://localhost:8080/signup"
            var data={
                name:this.name,
                email:this.email,
                password:this.password,
                location:this.location
            }
            try{
                await axios.post(url, data);
                console.log(data);
                alert("가입되었습니다.")
                this.$router.push('/')
            }catch(error){
                alert("가입 실패하셨습니다.")
            }
        }
    },

    computed:{
        matchPassword(){
            return ()=>(this.password===this.repassword) || '비밀번호가 일치하지 않습니다.'
        }
    }
  }

</script>

<style>

</style>
