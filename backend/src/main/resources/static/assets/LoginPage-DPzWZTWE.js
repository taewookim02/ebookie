import{_ as k,r as a,u as y,o as m,c as _,a as s,b as o,t as w,w as v,d as u,e as h,f as V,g as x,h as L}from"./index-DJuSyOPU.js";/* empty css                                                                         */import{A as c}from"./AuthInputField-hrsmw_4Y.js";/* empty css                                                                     */const A={key:0,class:"loading"},T={key:1,class:"auth"},B={class:"auth__err"},N={class:"auth__link"},S={__name:"LoginPage",setup(q){const l=a(""),n=a(""),d=a(""),g=y(),r=a(!1),f=a(!1),b=async()=>{try{r.value=!0;const t=await x.post("/api/v1/auth/login",{email:l.value,password:n.value});g.setAccessToken(t.data.accessToken),L.push("/member/edit")}catch(t){console.log(t),f.value=!0,d.value=t.response.data.message}finally{r.value=!1}};return(t,e)=>{const p=V("RouterLink");return r.value?(m(),_("div",A,e[2]||(e[2]=[s("div",{class:"spinner-border text-primary",role:"status"},[s("span",{class:"visually-hidden"},"Loading...")],-1)]))):(m(),_("section",T,[s("form",{onSubmit:h(b,["prevent"]),class:"auth__form"},[e[6]||(e[6]=s("h1",{class:"auth__heading"},"로그인",-1)),o(c,{type:"email",id:"email",name:"email",label:"이메일",modelValue:l.value,"onUpdate:modelValue":e[0]||(e[0]=i=>l.value=i),required:!0},null,8,["modelValue"]),o(c,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:n.value,"onUpdate:modelValue":e[1]||(e[1]=i=>n.value=i),required:!0},null,8,["modelValue"]),e[7]||(e[7]=s("button",{type:"submit",class:"auth__btn btn btn-primary btn-lg"},"로그인",-1)),s("span",B,w(d.value),1),s("div",N,[o(p,{to:"/reset-password",class:"auth__link--item"},{default:v(()=>e[3]||(e[3]=[u("비밀번호 찾기")])),_:1}),s("span",null,[e[5]||(e[5]=u("회원이 아니신가요? ")),o(p,{to:"/register",class:"auth__link--item"},{default:v(()=>e[4]||(e[4]=[u("회원가입")])),_:1})])])],32)]))}}},P=k(S,[["__scopeId","data-v-9b23149b"]]);export{P as default};
