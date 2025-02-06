import{_ as m,o as a,a as l,b as e,d as h,t as o,r as _,e as n,c as u,f as c,w as p,g as i}from"./index-CWAn9xFJ.js";import{A as r}from"./AuthInputField-CTP0QCb3.js";const f=["type"],y={class:"gsi-material-button-content-wrapper"},w={class:"gsi-material-button-icon"},x={version:"1.1",xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 48 48","xmlns:xlink":"http://www.w3.org/1999/xlink",style:{display:"block"}},k={class:"gsi-material-button-contents"},b={__name:"GoogleAuthButton",props:{msg:{type:String,default:"Continue with Google"},type:{type:String,required:!0}},setup(t){return(g,s)=>(a(),l("button",{class:"gsi-material-button",type:t.type},[s[2]||(s[2]=e("div",{class:"gsi-material-button-state"},null,-1)),e("div",y,[e("div",w,[(a(),l("svg",x,s[0]||(s[0]=[h('<path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z" data-v-17de800c></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z" data-v-17de800c></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z" data-v-17de800c></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z" data-v-17de800c></path><path fill="none" d="M0 0h48v48H0z" data-v-17de800c></path>',5)])))]),e("span",k,o(t.msg),1),s[1]||(s[1]=e("span",{style:{display:"none"}},"Sign in with Google",-1))])],8,f))}},v=m(b,[["__scopeId","data-v-17de800c"]]),S={class:"auth"},B={action:"",class:"auth__form"},T={class:"auth__heading"},A={type:"submit",class:"auth__btn"},C={class:"auth__link"},q={__name:"AuthForm",props:{heading:{type:String,required:!0},googleButtonText:{type:String,required:!0},submitButtonText:{type:String,required:!0},linkText:{type:String},footerText:{type:String,required:!0},footerLinkText:{type:String,required:!0},footerLink:{type:String,required:!0},isSignUp:{type:Boolean,default:!1}},setup(t){return(g,s)=>{const d=_("RouterLink");return a(),l("section",S,[e("form",B,[e("h1",T,o(t.heading),1),n(v,{type:"button",msg:t.googleButtonText},null,8,["msg"]),s[0]||(s[0]=e("span",{class:"auth__or"},"or",-1)),n(r,{type:"email",id:"email",name:"email",label:"email"}),n(r,{type:"password",id:"password",name:"password",label:"password"}),t.isSignUp?(a(),u(r,{key:0,type:"password",id:"confirm-password",name:"confirm-password",label:"confirm password"})):c("",!0),e("button",A,o(t.submitButtonText),1),e("div",C,[t.isSignUp?c("",!0):(a(),u(d,{key:0,to:"/reset-password",class:"auth__link--item"},{default:p(()=>[i(o(t.linkText),1)]),_:1})),e("span",null,[i(o(t.footerText)+" ",1),n(d,{to:t.footerLink,class:"auth__link--item"},{default:p(()=>[i(o(t.footerLinkText),1)]),_:1},8,["to"])])])])])}}},z=m(q,[["__scopeId","data-v-d2b47251"]]);export{z as A};
