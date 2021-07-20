import Vue from "vue";
import VueRouter from "vue-router";

// import Main from '../views/Main'
import Weather from "../views/Weather"
import Detail from "../views/detail"
import List from "../views/list"
import Post from "../views/post"
import Edit from "../views/edit"
import SignUp from "../views/SignUp"
import Chart from "../views/Chart"

Vue.use(VueRouter);

const routes =[
    {
        path:'/',
        name:'Weather',
        component:Weather
    },
    {
        path:'/post',
        name:'Post',
        component:Post
    },
    {
        path:'/detail',
        name:'Detail',
        component:Detail
    },
    {
        path:'/list',
        name:'List',
        component:List
    },
    {
        path:'/edit',
        name:'Edit',
        component:Edit
    },
    {
        path:'/signUp',
        name:'SignUp',
        component:SignUp
    },
    {
        path:'/chart',
        name:'Chart',
        component:Chart
    }  
];

const router = new VueRouter({
    mode:'history',
    routes,
});

export default router;