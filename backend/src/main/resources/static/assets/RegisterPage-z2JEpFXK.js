/* empty css                                                                         */import{_ as f,r as u,u as g,c as w,a as s,b as r,t as V,d as m,w as b,g as h,h as p,f as k,o as x}from"./index-U-ejpoW_.js";import{A as d}from"./AuthInputField-xd6R_ht8.js";const P={class:"auth"},y={class:"auth__err"},A={class:"auth__link"},R={__name:"RegisterPage",setup(S){const n=u(""),o=u(""),t=u(""),l=u(""),c=g(),v=i=>{if(i.preventDefault(),o.value.length<8){l.value="비밀번호는 최소 8글자여야 합니다.";return}if(o.value!==t.value){l.value="비밀번호와 비밀번호 확인이 다릅니다.";return}h.post("/api/auth/register",{email:n.value,password:o.value,confirmPassword:t.value}).then(e=>{console.log(e),c.setAccessToken(e.data.accessToken),p.push("/user/edit"),console.log("router.pushed"),console.log("router:",p)}).catch(e=>{console.log(e),l.value=e.response.data.message})};return(i,e)=>{const _=k("RouterLink");return x(),w("section",P,[s("form",{onSubmit:v,class:"auth__form"},[e[5]||(e[5]=s("h1",{class:"auth__heading"},"회원가입",-1)),r(d,{type:"email",id:"email",name:"email",label:"이메일",modelValue:n.value,"onUpdate:modelValue":e[0]||(e[0]=a=>n.value=a),required:!0},null,8,["modelValue"]),r(d,{type:"password",id:"password",name:"password",label:"비밀번호",modelValue:o.value,"onUpdate:modelValue":e[1]||(e[1]=a=>o.value=a),required:!0},null,8,["modelValue"]),r(d,{type:"password",id:"confirmPassword",name:"confirmPassword",label:"비밀번호 확인",modelValue:t.value,"onUpdate:modelValue":e[2]||(e[2]=a=>t.value=a),required:!0},null,8,["modelValue"]),e[6]||(e[6]=s("button",{type:"submit",class:"auth__btn"},"회원가입",-1)),s("span",y,V(l.value),1),s("div",A,[s("span",null,[e[4]||(e[4]=m("이미 가입하셨나요? ")),r(_,{to:"/login",class:"auth__link--item"},{default:b(()=>e[3]||(e[3]=[m("로그인")])),_:1})])])],32)])}}},N=f(R,[["__scopeId","data-v-5f6d18f0"]]);export{N as default};
