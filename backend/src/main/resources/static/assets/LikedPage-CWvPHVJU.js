import{_ as I,r as k,a as D,j as L,o as i,c as v,b as t,l as $,v as B,d as c,w as d,A as m,F as f,n as N,t as _,h,g as P,e as p,p as S,q as V}from"./index-Ct8V6wF8.js";const z={class:"liked"},F={class:"table"},M={colspan:"2"},R=["checked","onChange"],T=["src"],j={class:"text-muted"},q={class:"td-action"},E={key:1},U={__name:"LikedPage",setup(G){const o=k([]),n=D(),a=k(new Set),r=k(!1),C=async l=>{try{const e=await h.post("/api/v1/cart",{productId:l});n.success("장바구니 추가 완료!")}catch(e){console.log("handleCartClick() err:",e),n.error("장바구니 추가 중 에러 발생!")}},w=async l=>{try{const e=await h.delete(`/api/v1/liked/${l}`);o.value=o.value.filter(u=>u.productId!==l),a.value.delete(l),n.info("좋아요 목록에서 삭제 완료!")}catch(e){console.log("handleDeleteClick() err:",e),n.error("삭제 중 에러 발생!")}},y=()=>{r.value?a.value=new Set(o.value.map(l=>l.productId)):a.value.clear()},b=l=>{a.value.has(l)?(a.value.delete(l),r.value=!1):(a.value.add(l),r.value=a.value.size===o.value.length)},x=async()=>{if(a.value.size===0){n.warning("삭제할 상품을 선택해주세요");return}try{const l=Array.from(a.value);await h.delete("/api/v1/liked/bulk",{data:{productIds:l}}),o.value=o.value.filter(e=>!a.value.has(e.productId)),a.value.clear(),r.value=!1,n.info(`${l.length}개 상품이 좋아요 목록에서 삭제되었습니다`)}catch(l){console.log("handleCheckedDelete() err:",l),n.error("선택한 상품 삭제 중 에러가 발생했습니다!")}},A=async()=>{try{const l=await h.get("/api/v1/liked");o.value=l.data}catch(l){console.log("fetchLikedProducts() err:",l)}};return L(()=>{A()}),(l,e)=>{const u=P("RouterLink");return i(),v(f,null,[e[8]||(e[8]=t("h1",null,"좋아요한 상품",-1)),t("section",z,[t("table",F,[e[7]||(e[7]=t("colgroup",null,[t("col",{width:"1%"}),t("col",{width:"20%"}),t("col",{width:"*"}),t("col",{width:"20%"})],-1)),t("thead",null,[t("tr",null,[t("th",null,[$(t("input",{type:"checkbox",name:"all",id:"all","onUpdate:modelValue":e[0]||(e[0]=s=>r.value=s),onChange:y},null,544),[[B,r.value]])]),t("th",M,[e[2]||(e[2]=t("label",{for:"all"},"전체선택",-1)),c(m,{onAction:x,disabled:a.value.size===0,class:"btn-select-delete"},{default:d(()=>e[1]||(e[1]=[p("선택삭제")])),_:1},8,["disabled"])]),e[3]||(e[3]=t("th",null,null,-1))])]),t("tbody",null,[o.value&&o.value.length>0?(i(!0),v(f,{key:0},N(o.value,s=>(i(),v("tr",null,[t("td",null,[t("input",{type:"checkbox",checked:a.value.has(s.productId),onChange:g=>b(s.productId)},null,40,R)]),t("td",null,[c(u,{to:`/products/${s.productId}`},{default:d(()=>[t("img",{class:"img-product",src:S(V)(s.thumbnail),alt:"상품 이미지"},null,8,T)]),_:2},1032,["to"])]),t("td",null,[c(u,{to:`/products/${s.productId}`,class:"link-dark"},{default:d(()=>[p(_(s.name),1)]),_:2},1032,["to"]),t("div",j,[t("small",null,_(s.authorNames),1)])]),t("td",q,[c(m,{class:"w-100 mb-2",onAction:g=>C(s.productId)},{default:d(()=>e[4]||(e[4]=[p("장바구니 담기 ")])),_:2},1032,["onAction"]),c(m,{class:"w-100",onAction:g=>w(s.productId)},{default:d(()=>e[5]||(e[5]=[p("삭제")])),_:2},1032,["onAction"])])]))),256)):(i(),v("tr",E,e[6]||(e[6]=[t("td",{colspan:"4",class:"td-empty text-muted"},[t("small",null,"좋아요 한 상품이 없습니다.")],-1)])))])])])],64)}}},J=I(U,[["__scopeId","data-v-536e2d64"]]);export{J as default};
