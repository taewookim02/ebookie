import{_ as g,r as u,u as v,o as f,c as b,a as t,b as o,t as k,w as m,d as r,e as h,f as w,g as V,h as x}from"./index-CbPe5fVT.js";/* empty css                                                                         */import{A as p}from"./AuthInputField-BQPp3VHf.js";const y={class:"auth"},A={class:"auth__err"},L={class:"auth__link"},T={__name:"LoginPage",setup(B){const a=u(""),l=u(""),i=u(""),_=v(),c=async()=>{V.post("/api/v1/auth/login",{email:a.value,password:l.value}).then(s=>{console.log(s),console.log("using customAxios"),_.setAccessToken(s.data.accessToken),x.push("/member/edit")}).catch(s=>{console.log(s),i.value=s.response.data.message})};return(s,e)=>{const d=w("RouterLink");return f(),b("section",y,[t("form",{onSubmit:h(c,["prevent"]),class:"auth__form"},[e[5]||(e[5]=t("h1",{class:"auth__heading"},"로그인",-1)),o(p,{type:"email",id:"email",name:"email",label:"이메일",modelValue:a.value,"onUpdate:modelValue":e[0]||(e[0]=n=>a.value=n),required:!0},null,8,["modelValue"]),o(p,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:l.value,"onUpdate:modelValue":e[1]||(e[1]=n=>l.value=n),required:!0},null,8,["modelValue"]),e[6]||(e[6]=t("button",{type:"submit",class:"auth__btn"},"로그인",-1)),t("span",A,k(i.value),1),t("div",L,[o(d,{to:"/reset-password",class:"auth__link--item"},{default:m(()=>e[2]||(e[2]=[r("비밀번호 찾기")])),_:1}),t("span",null,[e[4]||(e[4]=r("회원이 아니신가요? ")),o(d,{to:"/register",class:"auth__link--item"},{default:m(()=>e[3]||(e[3]=[r("회원가입")])),_:1})])])],32)])}}},C=g(T,[["__scopeId","data-v-022db7ab"]]);export{C as default};
