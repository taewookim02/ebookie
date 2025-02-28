import{_ as w,j as B,f as r,c as D,z as R,L as q,o as c,a as u,e as t,t as n,g as v,u as y,F as z,C as E,l as S,q as F,b as V,K as A,E as M,x as m,n as K,D as T,k as Y}from"./index-ibK3QWKb.js";import{b as j}from"./format-B6-WTpBz.js";import{L as G,S as H,C as J}from"./CartButton-DV_QtIDy.js";import{P as O}from"./Pagination-CIUZq7gx.js";import{G as Q}from"./PhBookOpen.vue-BrHL-UmV.js";const U={class:"product-list-page"},W={key:0,class:"text-center py-5"},X={key:1,class:"text-center py-5"},Z={class:"empty-state"},ee={key:2},te={class:"product-grid"},se={class:"product-image"},ae=["src"],oe={class:"product-info"},ne={class:"product-name"},ie={class:"text-decoration-line-through"},le={class:"product-price",style:{color:"var(--primary-color)"}},re={class:"product-actions"},ce={__name:"ProductCategoryListPage",setup(de){const g=A(),p=M(),d=B(),i=r([]),h=r(0),L=r(0),l=r(0),_=r(!1),k=r(""),N=r(20),P=D(()=>g.params.id),x=s=>{l.value=s-1,p.push({query:{...g.query,page:l.value}}),f()},I=async s=>{try{await m.post(`/api/v1/saved/${s}`);const e=i.value.find(o=>o.id===s);e&&(e.isSaved=!e.isSaved,e.isSaved?d.success("찜 목록에 저장 완료!"):d.info("찜 목록에서 삭제 완료!"))}catch(e){console.log("handleSave() err:",e),e.status===401&&p.push("/login")}},$=async s=>{try{await m.post(`/api/v1/liked/${s}`);const e=i.value.find(o=>o.id===s);e&&(e.isLiked=!e.isLiked,e.isLiked?d.success("좋아요 목록에 저장 완료!"):d.info("좋아요 목록에서 삭제 완료!"))}catch(e){console.log("handleLike() err:",e),e.status===401&&p.push("/login")}},b=async s=>{try{await m.post(`/api/v1/cart/${s}`);const e=i.value.find(o=>o.id===s);e&&(e.isInCart=!e.isInCart,e.isInCart?d.success("장바구니 목록에 저장 완료!"):d.info("장바구니 목록에서 삭제 완료!"))}catch(e){console.log("handleCartAdd() err:",e),e.status===401&&p.push("/login")}},f=async()=>{_.value=!0;try{const s=await m.get(`/api/v1/products/categories/${P.value}?page=${l.value}&size=${N.value}`);i.value=s.data.products,h.value=s.data.totalPages,L.value=s.data.totalElements,l.value=s.data.currentPage,k.value=s.data.categoryName}catch(s){console.error("Failed to fetch products:",s),i.value=[]}finally{_.value=!1}};return R(()=>{const s=Number(g.query.page)||0;l.value=s,f()}),q(()=>g.params.id,(s,e)=>{l.value=0,f()}),(s,e)=>{const o=K("RouterLink");return c(),u("div",U,[t("h3",null,n(k.value)+" 관련도서",1),t("h4",null,"상품 ("+n(L.value.toLocaleString())+")",1),_.value?(c(),u("div",W,e[0]||(e[0]=[t("div",{class:"spinner-border text-primary",role:"status"},[t("span",{class:"visually-hidden"},"Loading...")],-1)]))):i.value.length?(c(),u("div",ee,[t("div",te,[(c(!0),u(z,null,E(i.value,a=>(c(),u("div",{class:"product-item",key:a.id},[t("div",se,[v(o,{to:`/products/${a.id}`},{default:S(()=>[t("img",{src:y(T)(a.thumbnail.fileName),alt:"상품 이미지"},null,8,ae)]),_:2},1032,["to"])]),t("div",oe,[t("span",ne,[v(o,{to:`/products/${a.id}`,class:"link-dark"},{default:S(()=>[Y(n(a.name),1)]),_:2},1032,["to"])]),t("div",null,[t("small",null,n(a.authorNames)+" 저",1)]),t("div",null,[t("small",null,n(a.publisherName),1)]),t("div",null,[t("small",null,n(y(j)(a.publishedDate)),1)]),t("div",null,[t("small",ie,n(a.price.toLocaleString())+"원",1),t("span",le,n((a.price-a.price*a.discountRate/100).toLocaleString())+"원",1)]),t("div",null,[t("small",null,"판매량 "+n(a.sold.toLocaleString())+"부",1)])]),t("div",re,[v(G,{onLike:C=>$(a.id),"is-active":a.isLiked},null,8,["onLike","is-active"]),v(H,{onSave:C=>I(a.id),"is-active":a.isSaved},null,8,["onSave","is-active"]),v(J,{onCart:C=>b(a.id),"is-active":a.isInCart},null,8,["onCart","is-active"])])]))),128))]),h.value>0?(c(),F(O,{key:0,"current-page":l.value+1,"total-pages":h.value,onPageChange:x},null,8,["current-page","total-pages"])):V("",!0)])):(c(),u("div",X,[t("div",Z,[v(y(Q),{size:40,color:"var(--muted-color)"}),e[1]||(e[1]=t("h3",{class:"mt-4"},"상품이 없습니다",-1)),e[2]||(e[2]=t("p",{class:"text-muted"},"해당하는 상품을 찾을 수 없습니다.",-1))])]))])}}},he=w(ce,[["__scopeId","data-v-a91121b3"]]);export{he as default};
