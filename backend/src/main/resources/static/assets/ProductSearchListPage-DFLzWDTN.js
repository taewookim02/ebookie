import{_ as b,j as w,f as r,c as B,z as D,L as R,o as c,a as d,e as s,t as n,g as v,u as y,F as z,C as E,l as C,q as F,b as V,K as A,E as M,x as m,n as K,D as T,k as Y}from"./index-ibK3QWKb.js";import{b as j}from"./format-B6-WTpBz.js";import{L as G,S as H,C as J}from"./CartButton-DV_QtIDy.js";import{P as O}from"./Pagination-CIUZq7gx.js";import{G as Q}from"./PhBookOpen.vue-BrHL-UmV.js";const U={class:"product-list-page"},W={key:0,class:"text-center py-5"},X={key:1,class:"text-center py-5"},Z={class:"empty-state"},ee={key:2},se={class:"product-grid"},te={class:"product-image"},ae=["src"],oe={class:"product-info"},ne={class:"product-name"},ie={class:"text-decoration-line-through"},le={class:"product-price",style:{color:"var(--primary-color)"}},re={class:"product-actions"},ce={__name:"ProductSearchListPage",setup(ue){const g=A(),p=M(),u=w(),i=r([]),h=r(0),L=r(0),l=r(0),_=r(!1),k=r(""),N=r(20),P=B(()=>g.query.query),x=async t=>{try{await m.post(`/api/v1/saved/${t}`);const e=i.value.find(o=>o.id===t);e&&(e.isSaved=!e.isSaved,e.isSaved?u.success("찜 목록에 저장 완료!"):u.info("찜 목록에서 삭제 완료!"))}catch(e){console.log("handleSave() err:",e),e.status===401&&p.push("/login")}},I=async t=>{try{await m.post(`/api/v1/liked/${t}`);const e=i.value.find(o=>o.id===t);e&&(e.isLiked=!e.isLiked,e.isLiked?u.success("좋아요 목록에 저장 완료!"):u.info("좋아요 목록에서 삭제 완료!"))}catch(e){console.log("handleLike() err:",e),e.status===401&&p.push("/login")}},$=async t=>{try{await m.post(`/api/v1/cart/${t}`);const e=i.value.find(o=>o.id===t);e&&(e.isInCart=!e.isInCart,e.isInCart?u.success("장바구니 목록에 저장 완료!"):u.info("장바구니 목록에서 삭제 완료!"))}catch(e){console.log("handleCartAdd() err:",e),e.status===401&&p.push("/login")}},f=async()=>{_.value=!0;try{const t=await m.get(`/api/v1/products/search/result?query=${P.value}&page=${l.value}&size=${N.value}`);i.value=t.data.products,h.value=t.data.totalPages,L.value=t.data.totalElements,l.value=t.data.currentPage,k.value=t.data.categoryName}catch(t){console.error("Failed to fetch products:",t),i.value=[]}finally{_.value=!1}},q=t=>{l.value=t-1,p.push({query:{...g.query,page:l.value}}),f()};return D(()=>{const t=Number(g.query.page)||0;l.value=t,f()}),R(()=>g.query.query,(t,e)=>{l.value=0,f()}),(t,e)=>{const o=K("RouterLink");return c(),d("div",U,[s("h3",null,n(k.value)+" 관련도서",1),s("h4",null,"상품 ("+n(L.value.toLocaleString())+")",1),_.value?(c(),d("div",W,e[0]||(e[0]=[s("div",{class:"spinner-border text-primary",role:"status"},[s("span",{class:"visually-hidden"},"Loading...")],-1)]))):i.value.length?(c(),d("div",ee,[s("div",se,[(c(!0),d(z,null,E(i.value,a=>(c(),d("div",{class:"product-item",key:a.id},[s("div",te,[v(o,{to:`/products/${a.id}`},{default:C(()=>[s("img",{src:y(T)(a.thumbnail.fileName),alt:"상품 이미지"},null,8,ae)]),_:2},1032,["to"])]),s("div",oe,[s("span",ne,[v(o,{to:`/products/${a.id}`,class:"link-dark"},{default:C(()=>[Y(n(a.name),1)]),_:2},1032,["to"])]),s("div",null,[s("small",null,n(a.authorNames)+" 저",1)]),s("div",null,[s("small",null,n(a.publisherName),1)]),s("div",null,[s("small",null,n(y(j)(a.publishedDate)),1)]),s("div",null,[s("small",ie,n(a.price.toLocaleString())+"원",1),s("span",le,n((a.price-a.price*a.discountRate/100).toLocaleString())+"원",1)]),s("div",null,[s("small",null,"판매량 "+n(a.sold.toLocaleString())+"부",1)])]),s("div",re,[v(G,{onLike:S=>I(a.id),"is-active":a.isLiked},null,8,["onLike","is-active"]),v(H,{onSave:S=>x(a.id),"is-active":a.isSaved},null,8,["onSave","is-active"]),v(J,{onCart:S=>$(a.id),"is-active":a.isInCart},null,8,["onCart","is-active"])])]))),128))]),h.value>0?(c(),F(O,{key:0,"current-page":l.value+1,"total-pages":h.value,onPageChange:q},null,8,["current-page","total-pages"])):V("",!0)])):(c(),d("div",X,[s("div",Z,[v(y(Q),{size:40,color:"var(--muted-color)"}),e[1]||(e[1]=s("h3",{class:"mt-4"},"상품이 없습니다",-1)),e[2]||(e[2]=s("p",{class:"text-muted"},"해당하는 상품을 찾을 수 없습니다.",-1))])]))])}}},he=b(ce,[["__scopeId","data-v-d6931ab2"]]);export{he as default};
