import{_ as g,r as u,u as f,c as v,a as o,b as t,t as k,w as p,d as r,e as h,f as w,o as V,g as b,h as x}from"./index-KcXGOkF7.js";/* empty css                                                                         */import{A as m}from"./AuthInputField-BqyM5k7J.js";const y={class:"auth"},A={class:"auth__err"},L={class:"auth__link"},T={__name:"LoginPage",setup(B){const a=u(""),l=u(""),i=u(""),_=f(),c=async()=>{b.post("/api/auth/login",{email:a.value,password:l.value}).then(s=>{console.log(s),console.log("using customAxios"),_.setAccessToken(s.data.accessToken),x.push("/user/edit")}).catch(s=>{console.log(s),i.value=s.response.data.message})};return(s,e)=>{const d=w("RouterLink");return V(),v("section",y,[o("form",{onSubmit:h(c,["prevent"]),class:"auth__form"},[e[5]||(e[5]=o("h1",{class:"auth__heading"},"로그인",-1)),t(m,{type:"email",id:"email",name:"email",label:"이메일",modelValue:a.value,"onUpdate:modelValue":e[0]||(e[0]=n=>a.value=n),required:!0},null,8,["modelValue"]),t(m,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:l.value,"onUpdate:modelValue":e[1]||(e[1]=n=>l.value=n),required:!0},null,8,["modelValue"]),e[6]||(e[6]=o("button",{type:"submit",class:"auth__btn"},"로그인",-1)),o("span",A,k(i.value),1),o("div",L,[t(d,{to:"/reset-password",class:"auth__link--item"},{default:p(()=>e[2]||(e[2]=[r("비밀번호 찾기")])),_:1}),o("span",null,[e[4]||(e[4]=r("회원이 아니신가요? ")),t(d,{to:"/signup",class:"auth__link--item"},{default:p(()=>e[3]||(e[3]=[r("회원가입")])),_:1})])])],32)])}}},C=g(T,[["__scopeId","data-v-712ce73e"]]);export{C as default};
