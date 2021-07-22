import Vue from "vue";
import VueRouter from "vue-router";

import MainPage from "../views/MainPage"
import Board from "../views/Board"
import Post from "../views/post"
import SignUp from "../views/SignUp"
import Recommend from "../views/Recommend"
import MyList from "../views/MyList"

Vue.use(VueRouter);

const routes =[
    {   //날씨 정보 확인하기
        path:'/',
        name:'MainPage',
        component:MainPage
    },
    {   //라리 유튜브 추천
        path:'/recommend',
        name:'Recommend',
        component:Recommend
    },
    {
        path:'/mylist',
        name:'MyList',
        component:MyList
    },
    {   //게시판 확인
        path:'/board',
        name:'Board',
        component:Board
    },
    {   //게시판 작성
        path:'/post',
        name:'Post',
        component:Post
    },
    
    {   //회원가입
        path:'/signUp',
        name:'SignUp',
        component:SignUp
    },
    
];

const router = new VueRouter({
    mode:'history',
    routes,
});

export default router;