import{_ as u,o as d,c as n,a,t as o,n as s}from"./index-DN6_OkXZ.js";const m={class:"input-field"},p=["for"],c=["required","type","id","name","value"],y={__name:"AuthInputField",props:{id:{type:String,required:!0},label:{type:String,required:!0},type:{type:String,required:!0},name:{type:String,required:!0},readonly:{type:Boolean,default:!1},modelValue:{type:String,required:!1,default:""},required:{type:Boolean}},emits:["update:modelValue"],setup(e,{emit:l}){const r=l;return(f,t)=>(d(),n("div",m,[a("label",{for:e.id,class:"input-field__label"},o(e.label),9,p),a("input",s({required:e.required,type:e.type,id:e.id,name:e.name,class:"input-field__input",value:e.modelValue,onInput:t[0]||(t[0]=i=>r("update:modelValue",i.target.value))},e.readonly?{readonly:!0}:{}),null,16,c)]))}},g=u(y,[["__scopeId","data-v-630384fd"]]);export{g as A};
