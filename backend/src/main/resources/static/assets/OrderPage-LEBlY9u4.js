import{_ as C,r as i,o as s,c as o,a as t,l as k,B as y,F as g,n as p,t as n,b as E,w as N,C as S,k as L,g as w,f as x,D as A}from"./index-Dqya9vcX.js";import{P as I}from"./Pagination-BfcRG7MM.js";const B={class:"page-controls d-flex justify-content-end"},O=["value"],F={key:0},R={key:1},V={class:"order-header"},z={class:"order-content"},U={class:"order-products"},$={__name:"OrderPage",setup(G){const l=i({orders:[],totalPages:0,totalElements:0,currentPage:0}),c=i(0),u=i(10),_=[5,10,20,50],d=async(r=0)=>{try{const a=await w.get(`/api/v1/orders?page=${r}&size=${u.value}`);l.value=a.data,c.value=a.data.currentPage}catch(a){console.log("fetchOrders() err:",a)}},h=r=>{d(r-1)},P=()=>{c.value=0,d(0)},f=r=>{switch(r){case"PENDING":return"결제대기";case"PAID":return"결제완료";case"CANCELLED":return"주문취소";case"REFUNDED":return"환불완료";default:return""}},m=r=>{switch(r){case"PENDING":return"#ff4444";case"PAID":return"#00C851";case"CANCELLED":return"#ff8800";case"REFUNDED":return"#666666";default:return"#000000"}};return d(),(r,a)=>{const D=x("RouterLink");return s(),o("div",null,[a[1]||(a[1]=t("h1",{class:"text-center"},"주문 목록",-1)),t("div",B,[k(t("select",{"onUpdate:modelValue":a[0]||(a[0]=e=>u.value=e),onChange:P},[(s(),o(g,null,p(_,e=>t("option",{key:e,value:e},n(e)+"개씩 보기 ",9,O)),64))],544),[[y,u.value]])]),l.value.orders.length===0?(s(),o("div",F," 주문 내역이 없습니다. ")):(s(),o("div",R,[(s(!0),o(g,null,p(l.value.orders,e=>(s(),o("div",{key:e.id,class:"order-item"},[E(D,{to:`/orders/${e.id}`},{default:N(()=>[t("div",V,[t("span",null,"주문번호: "+n(e.id),1),t("span",null,"주문일: "+n(new Date(e.createdAt).toLocaleDateString()),1)]),t("div",z,[t("span",null,"총 금액: "+n(e.totalPrice.toLocaleString())+"원",1),t("span",{class:"order-status",style:A({color:m(e.orderStatus)})}," 상태: "+n(f(e.orderStatus)),5)]),t("div",U,[(s(!0),o(g,null,p(e.products,v=>(s(),o("div",{key:v.productId},n(v.productName),1))),128))])]),_:2},1032,["to"])]))),128)),l.value.totalPages>1?(s(),S(I,{key:0,"current-page":c.value+1,"total-pages":l.value.totalPages,onPageChange:h},null,8,["current-page","total-pages"])):L("",!0)]))])}}},K=C($,[["__scopeId","data-v-e8ec3221"]]);export{K as default};
