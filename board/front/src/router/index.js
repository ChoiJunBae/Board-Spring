import Vue from "vue";
import VueRouter from "vue-router";

import Weather from "../views/Weather"
import Detail from "../views/detail"
import List from "../views/list"
import Post from "../views/post"
import Edit from "../views/edit"

Vue.use(VueRouter);

const routes =[
    {
        path:'/weather',
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
        path:'/',
        name:'List',
        component:List
    },
    {
        path:'/edit',
        name:'Edit',
        component:Edit
    },
];

const router = new VueRouter({
    mode:'history',
    routes,
});

export default router;