import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)


export default  new Vuex.Store({
    state:{
        // name:'',
        // email :'',
        // location : '',
        condition:''
    },
    mutations:{
        // setName(state, name){
        //     state.name = name;
        // },
        // setEmail(state,email){
        //     state.email = email;
        // },
        // setLocation(state, location){
        //     state.location = location;
        // },
        setCondition(state, condition){
            state.condition = condition;
        }
        
    }
});




