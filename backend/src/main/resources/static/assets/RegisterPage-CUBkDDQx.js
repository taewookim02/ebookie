/* empty css                                                                         */import{_ as f,r,u as g,o as w,c as V,a as s,b as u,t as b,d as m,w as h,g as k,h as p,f as x}from"./index-BH-ml3UN.js";import{A as d}from"./AuthInputField-Bcy4mu4K.js";const P={class:"auth"},y={class:"auth__err"},A={class:"auth__link"},R={__name:"RegisterPage",setup(S){const n=r(""),a=r(""),t=r(""),l=r(""),c=g(),v=i=>{if(i.preventDefault(),a.value.length<8){l.value="비밀번호는 최소 8글자여야 합니다.";return}if(a.value!==t.value){l.value="비밀번호와 비밀번호 확인이 다릅니다.";return}k.post("/api/v1/auth/register",{email:n.value,password:a.value,confirmPassword:t.value}).then(e=>{c.setAccessToken(e.data.accessToken),p.push("/member/edit"),console.log("router.pushed"),console.log("router:",p)}).catch(e=>{console.log(e),l.value=e.response.data.message})};return(i,e)=>{const _=x("RouterLink");return w(),V("section",P,[s("form",{onSubmit:v,class:"auth__form"},[e[5]||(e[5]=s("h1",{class:"auth__heading"},"회원가입",-1)),u(d,{type:"email",id:"email",name:"email",label:"이메일",modelValue:n.value,"onUpdate:modelValue":e[0]||(e[0]=o=>n.value=o),required:!0},null,8,["modelValue"]),u(d,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:a.value,"onUpdate:modelValue":e[1]||(e[1]=o=>a.value=o),required:!0},null,8,["modelValue"]),u(d,{type:"password",id:"confirmPassword",name:"confirmPassword",label:"비밀번호 확인",modelValue:t.value,"onUpdate:modelValue":e[2]||(e[2]=o=>t.value=o),required:!0},null,8,["modelValue"]),e[6]||(e[6]=s("button",{type:"submit",class:"auth__btn"},"회원가입",-1)),s("span",y,b(l.value),1),s("div",A,[s("span",null,[e[4]||(e[4]=m("이미 가입하셨나요? ")),u(_,{to:"/login",class:"auth__link--item"},{default:h(()=>e[3]||(e[3]=[m("로그인")])),_:1})])])],32)])}}},N=f(R,[["__scopeId","data-v-520d1c89"]]);export{N as default};
