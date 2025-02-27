import{_ as M,r as d,a as E,j as Q,I as j,s as b,o as n,c as i,k as m,b as t,l as T,B as Y,F as H,n as K,t as c,d as g,w as C,p as P,C as G,H as J,x as O,h,g as W,q as X,e as x}from"./index-CViMo7yw.js";import{A as Z}from"./ActionButton-_kzWKM-T.js";import{C as ee,L as se,S as te}from"./CartButton-D3GaFDP-.js";import{b as ae}from"./format-B6-WTpBz.js";import{P as oe}from"./Pagination-Bv--xvo8.js";/* empty css                                                                     */const ne={class:"product-list-page container py-5"},ie={key:0,class:"mb-4 text-center"},le={key:1,class:"mb-4 text-center"},re={key:2,class:"mb-4 text-center"},ce={class:"d-flex justify-content-end mb-4"},ue={key:3,class:"text-center py-5"},de={key:4,class:"text-center py-5"},ve={key:5,class:"product-list"},pe={class:"product-rank"},ge={class:"product-image"},me=["src"],he={class:"product-info"},fe={class:"product-name"},ye={class:"product-meta"},_e={class:"product-price-info"},ke={class:"original-price"},Le={class:"discounted-price"},Se={key:0,class:"discount-badge"},be={class:"product-sold"},Ce={class:"product-actions"},Ne={__name:"ProductRankingListPage",setup(Pe){const v=d([]),y=d(0),w=d(0),_=d(0),k=d(!1),l=d(20),L=J(),u=O(),p=E(),f=d(L.query),r=d(new URLSearchParams(f.value).toString());console.log(r.value);const N=async()=>{k.value=!0;try{const s=await h.get(`/api/v1/products?${r.value}&size=${l.value}`);v.value=s.data.products,y.value=s.data.totalPages,w.value=s.data.totalElements,_.value=s.data.currentPage}catch(s){console.error("Failed to fetch products:",s),v.value=[]}finally{k.value=!1}};Q(()=>{const s=Number(L.query.size);s&&(l.value=s),N()}),j(L,(s,e)=>{f.value=s.query,r.value=new URLSearchParams(f.value).toString();const o=Number(s.query.size);o&&o!==l.value&&(l.value=o),N()},{deep:!0});const I=b(()=>r.value.includes("sold")&&r.value.includes("desc")),R=b(()=>r.value.includes("publishedDate")&&r.value.includes("desc")),B=b(()=>r.value.includes("discountRate")&&r.value.includes("desc")),$=async s=>{try{await h.post(`/api/v1/saved/${s}`);const e=v.value.find(o=>o.id===s);e&&(e.isSaved=!e.isSaved,e.isSaved?p.success("찜 목록에 저장 완료!"):p.info("찜 목록에서 삭제 완료!"))}catch(e){console.log("handleSave() err:",e),e.status===401&&u.push("/login")}},z=async s=>{try{await h.post(`/api/v1/liked/${s}`);const e=v.value.find(o=>o.id===s);e&&(e.isLiked=!e.isLiked,e.isLiked?p.success("좋아요 목록에 저장 완료!"):p.info("좋아요 목록에서 삭제 완료!"))}catch(e){console.log("handleLike() err:",e),e.status===401&&u.push("/login")}},q=async s=>{try{await h.post(`/api/v1/cart/${s}`);const e=v.value.find(o=>o.id===s);e&&(e.isInCart=!e.isInCart,e.isInCart?p.success("장바구니 목록에 저장 완료!"):p.info("장바구니 목록에서 삭제 완료!"))}catch(e){console.log("handleCartAdd() err:",e),e.status===401&&u.push("/login")}},A=async s=>{try{console.log("handleBuy");const e=[s],a=(await h.post("/api/v1/orders",{productIds:e})).data.id;u.push(`/orders/${a}`)}catch(e){console.log("handleBuy() err:",e),e.status===401&&u.push("/login")}},D=s=>{const e=new URLSearchParams(f.value);return e.set("page",s),e.set("size",l.value),e.toString()},U=s=>{u.push(`/products?${D(s-1)}`)},V=()=>{const s=u.currentRoute.value.query;u.push({path:"/products",query:{page:"0",size:l.value,sort:s.sort||"publishedDate,desc"}})};return(s,e)=>{const o=W("RouterLink");return n(),i("div",ne,[I.value?(n(),i("h1",ie,"베스트셀러 순위")):m("",!0),R.value?(n(),i("h1",le,"신상품 순위")):m("",!0),B.value?(n(),i("h1",re,"세일 순위")):m("",!0),t("div",ce,[T(t("select",{class:"form-select form-select-sm",style:{width:"150px"},"onUpdate:modelValue":e[0]||(e[0]=a=>l.value=a),onChange:V},e[1]||(e[1]=[t("option",{value:"10"},"10개씩 보기",-1),t("option",{value:"20"},"20개씩 보기",-1),t("option",{value:"50"},"50개씩 보기",-1)]),544),[[Y,l.value]])]),k.value?(n(),i("div",ue,e[2]||(e[2]=[t("div",{class:"spinner-border text-primary",role:"status"},[t("span",{class:"visually-hidden"},"Loading...")],-1)]))):v.value.length?(n(),i("div",ve,[(n(!0),i(H,null,K(v.value,(a,F)=>(n(),i("div",{class:"product-item",key:a.id},[t("div",pe,[t("span",null,c(_.value*l.value+F+1),1)]),t("div",ge,[g(o,{to:`/products/${a.id}`},{default:C(()=>[t("img",{src:P(X)(a.thumbnail.fileName),alt:"상품 이미지"},null,8,me)]),_:2},1032,["to"])]),t("div",he,[t("h2",fe,[g(o,{to:`/products/${a.id}`,class:"link-dark"},{default:C(()=>[x(c(a.name),1)]),_:2},1032,["to"])]),t("div",ye,[t("span",null,c(a.authorNames)+" 저",1),e[4]||(e[4]=t("small",null,"|",-1)),t("span",null,c(a.publisherName),1),e[5]||(e[5]=t("small",null,"|",-1)),t("span",null,c(P(ae)(a.publishedDate)),1)]),t("div",_e,[t("small",ke,c(a.price.toLocaleString())+"원",1),t("span",Le,c((a.price-a.price*a.discountRate/100).toLocaleString())+"원",1),a.discountRate?(n(),i("span",Se,c(a.discountRate)+"%",1)):m("",!0)]),t("div",be,[t("span",null,"판매량 "+c(a.sold.toLocaleString())+"부",1)])]),t("div",Ce,[g(ee,{onCart:S=>q(a.id),"is-active":a.isInCart},null,8,["onCart","is-active"]),g(se,{onLike:S=>z(a.id),"is-active":a.isLiked},null,8,["onLike","is-active"]),g(te,{onSave:S=>$(a.id),"is-active":a.isSaved},null,8,["onSave","is-active"]),g(Z,{onAction:S=>A(a.id),class:"buy-button"},{default:C(()=>e[6]||(e[6]=[x("구매하기")])),_:2},1032,["onAction"])])]))),128))])):(n(),i("div",de,e[3]||(e[3]=[t("div",{class:"empty-state"},[t("i",{class:"bi bi-book",style:{"font-size":"4rem",color:"var(--primary-color)"}}),t("h3",{class:"mt-4"},"상품이 없습니다"),t("p",{class:"text-muted"},"해당하는 상품을 찾을 수 없습니다.")],-1)]))),y.value>0?(n(),G(oe,{key:6,"current-page":_.value+1,"total-pages":y.value,onPageChange:U,class:"mt-4"},null,8,["current-page","total-pages"])):m("",!0)])}}},ze=M(Ne,[["__scopeId","data-v-b57497ae"]]);export{ze as default};
