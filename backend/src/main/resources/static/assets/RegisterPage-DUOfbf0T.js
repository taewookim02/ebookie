import{_ as c,r as a,u as g,c as v,a as s,b as t,t as w,d as m,w as V,g as b,h as k,f as h,o as x}from"./index-DHCwKaF6.js";import{G as y}from"./GoogleAuthButton-BH1GhRnA.js";import{A as r}from"./AuthInputField-CiaoF5sw.js";const P={class:"auth"},S={class:"auth__err"},A={class:"auth__link"},B={__name:"RegisterPage",setup(R){const l=a(""),n=a(""),u=a(""),d=a(""),p=g(),_=i=>{i.preventDefault(),b.post("http://localhost:8080/api/auth/register",{email:l.value,password:n.value,confirmPassword:u.value}).then(e=>{console.log(e),p.setAccessToken(e.data.accessToken),k.push("/user/edit")}).catch(e=>{console.log(e),d.value=e.response.data.message})};return(i,e)=>{const f=h("RouterLink");return x(),v("section",P,[s("form",{onSubmit:_,class:"auth__form"},[e[5]||(e[5]=s("h1",{class:"auth__heading"},"회원가입",-1)),t(y,{type:"button",msg:"Google 계정으로 회원가입"}),e[6]||(e[6]=s("span",{class:"auth__or"},"or",-1)),t(r,{type:"email",id:"email",name:"email",label:"이메일",modelValue:l.value,"onUpdate:modelValue":e[0]||(e[0]=o=>l.value=o)},null,8,["modelValue"]),t(r,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:n.value,"onUpdate:modelValue":e[1]||(e[1]=o=>n.value=o)},null,8,["modelValue"]),t(r,{type:"password",id:"confirmPassword",name:"confirmPassword",label:"비밀번호 확인",modelValue:u.value,"onUpdate:modelValue":e[2]||(e[2]=o=>u.value=o)},null,8,["modelValue"]),e[7]||(e[7]=s("button",{type:"submit",class:"auth__btn"},"로그인",-1)),s("span",S,w(d.value),1),s("div",A,[s("span",null,[e[4]||(e[4]=m("이미 가입하셨나요? ")),t(f,{to:"/login",class:"auth__link--item"},{default:V(()=>e[3]||(e[3]=[m("로그인")])),_:1})])])],32)])}}},U=c(B,[["__scopeId","data-v-ee8f8d38"]]);export{U as default};
