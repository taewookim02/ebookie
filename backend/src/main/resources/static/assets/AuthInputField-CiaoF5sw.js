import{_ as u,o as r,c as d,a,t as o,p as s}from"./index-DHCwKaF6.js";const p={class:"input-field"},c=["for"],m=["type","id","name","value"],y={__name:"AuthInputField",props:{id:{type:String,required:!0},label:{type:String,required:!0},type:{type:String,required:!0},name:{type:String,required:!0},readonly:{type:Boolean,default:!1},modelValue:{type:String,required:!1,default:""}},emits:["update:modelValue"],setup(e,{emit:l}){const i=l;return(f,t)=>(r(),d("div",p,[a("label",{for:e.id,class:"input-field__label"},o(e.label),9,c),a("input",s({type:e.type,id:e.id,name:e.name,class:"input-field__input",value:e.modelValue,onInput:t[0]||(t[0]=n=>i("update:modelValue",n.target.value))},e.readonly?{readonly:!0}:{}),null,16,m)]))}},_=u(y,[["__scopeId","data-v-cc18f680"]]);export{_ as A};
