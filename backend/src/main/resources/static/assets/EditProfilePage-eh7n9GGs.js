import{A as i}from"./AuthInputField-D_gYwr7s.js";import{j as w,k as d,l as u,o as l,c as s,m as _,n as k,p as y,a as t,_ as A,r as b,u as H,q as M,g as x,b as r,w as V,f as L,s as Z}from"./index-hikKrf4A.js";const B=["width","height","fill","transform"],P={key:0},z=t("path",{d:"M228,128a12,12,0,0,1-12,12H69l51.52,51.51a12,12,0,0,1-17,17l-72-72a12,12,0,0,1,0-17l72-72a12,12,0,0,1,17,17L69,116H216A12,12,0,0,1,228,128Z"},null,-1),C=[z],S={key:1},N=t("path",{d:"M112,56V200L40,128Z",opacity:"0.2"},null,-1),E=t("path",{d:"M216,120H120V56a8,8,0,0,0-13.66-5.66l-72,72a8,8,0,0,0,0,11.32l72,72A8,8,0,0,0,120,200V136h96a8,8,0,0,0,0-16ZM104,180.69,51.31,128,104,75.31Z"},null,-1),$=[N,E],j={key:2},T=t("path",{d:"M224,128a8,8,0,0,1-8,8H120v64a8,8,0,0,1-13.66,5.66l-72-72a8,8,0,0,1,0-11.32l72-72A8,8,0,0,1,120,56v64h96A8,8,0,0,1,224,128Z"},null,-1),q=[T],I={key:3},R=t("path",{d:"M222,128a6,6,0,0,1-6,6H54.49l61.75,61.76a6,6,0,1,1-8.48,8.48l-72-72a6,6,0,0,1,0-8.48l72-72a6,6,0,0,1,8.48,8.48L54.49,122H216A6,6,0,0,1,222,128Z"},null,-1),U=[R],D={key:4},F=t("path",{d:"M224,128a8,8,0,0,1-8,8H59.31l58.35,58.34a8,8,0,0,1-11.32,11.32l-72-72a8,8,0,0,1,0-11.32l72-72a8,8,0,0,1,11.32,11.32L59.31,120H216A8,8,0,0,1,224,128Z"},null,-1),G=[F],W={key:5},J=t("path",{d:"M220,128a4,4,0,0,1-4,4H49.66l65.17,65.17a4,4,0,0,1-5.66,5.66l-72-72a4,4,0,0,1,0-5.66l72-72a4,4,0,0,1,5.66,5.66L49.66,124H216A4,4,0,0,1,220,128Z"},null,-1),K=[J],O={name:"PhArrowLeft"},Q=w({...O,props:{weight:{type:String},size:{type:[String,Number]},color:{type:String},mirrored:{type:Boolean}},setup(c){const a=c,p=d("weight","regular"),n=d("size","1em"),e=d("color","currentColor"),m=d("mirrored",!1),o=u(()=>a.weight??p),g=u(()=>a.size??n),v=u(()=>a.color??e),f=u(()=>a.mirrored!==void 0?a.mirrored?"scale(-1, 1)":void 0:m?"scale(-1, 1)":void 0);return(h,ae)=>(l(),s("svg",y({xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 256 256",width:g.value,height:g.value,fill:v.value,transform:f.value},h.$attrs),[_(h.$slots,"default"),o.value==="bold"?(l(),s("g",P,C)):o.value==="duotone"?(l(),s("g",S,$)):o.value==="fill"?(l(),s("g",j,q)):o.value==="light"?(l(),s("g",I,U)):o.value==="regular"?(l(),s("g",D,G)):o.value==="thin"?(l(),s("g",W,K)):k("",!0)],16,B))}}),X={class:"settings"},Y={class:"settings__controls"},ee={action:"",class:"settings__form"},te={__name:"EditProfilePage",setup(c){const a=b(""),p=H();return M(()=>{const n=p.accessToken;x.get("http://localhost:8080/api/member/detail",{headers:{Authorization:`Bearer ${n}`}}).then(e=>{console.log("onMounted token"),a.value=e.data.email}).catch(e=>{console.log(e)})}),(n,e)=>{const m=L("RouterLink");return l(),s("section",X,[t("div",Y,[r(m,{class:"settings__controls--link link-dark",to:"/"},{default:V(()=>[r(Z(Q),{size:32}),e[1]||(e[1]=t("span",{class:"settings__controls--text"},"뒤로가기",-1))]),_:1})]),t("form",ee,[e[2]||(e[2]=t("h1",null,"회원 정보 수정",-1)),r(i,{type:"email",id:"email",name:"email",label:"이메일",valueText:"hello",readonly:!0,modelValue:a.value,"onUpdate:modelValue":e[0]||(e[0]=o=>a.value=o)},null,8,["modelValue"]),r(i,{type:"password",id:"password",name:"password",label:"새로운 비밀번호"}),r(i,{type:"password",id:"confirm-password",name:"confirm-password",label:"새로운 비밀번호 확인"}),r(i,{type:"password",id:"current-password",name:"current-password",label:"현재 비밀번호"}),e[3]||(e[3]=t("button",{type:"submit"},"Update",-1))])])}}},se=A(te,[["__scopeId","data-v-1ee0015c"]]);export{se as default};
