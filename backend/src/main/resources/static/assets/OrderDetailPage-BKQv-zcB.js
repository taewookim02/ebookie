import{O as p}from"./OrderPricesSection-Bzxz-PHf.js";import{A as L}from"./ActionButton-B04Gm7r-.js";import{a as h,f as U}from"./format-D8BanFEv.js";import{g as f}from"./imgPath-CG_qeXUq.js";import{_ as w,o as l,c as I,a as A,F as S,y as G,A as Q,t as r,s as T,r as d,B as V,k as C,p as k,q as B,b as g,w as Y,C as x,g as b,d as z}from"./index-CbPe5fVT.js";var m;let M=null;const y={jsSdkUrl:(m=void 0)!==null&&m!==void 0?m:"https://cdn.portone.io/v2/browser-sdk.js"};function J(){return document.querySelector(`script[src="${y.jsSdkUrl}"]`)}function F(){const e=document.createElement("script");e.src=y.jsSdkUrl;const t=document.head||document.body;if(!t)throw new Error("[PortOne] Expected document.body not to be null");return t.appendChild(e)}function s(){return M??(M=new Promise((e,t)=>{if(window.PortOne)return e(window.PortOne);try{const o=J()||F();o.addEventListener("load",()=>{if(window.PortOne)return e(window.PortOne);t(new Error("[PortOne] Failed to load window.PortOne"))}),o.addEventListener("error",()=>{t(new Error("[PortOne] Failed to load window.PortOne"))})}catch(o){return t(o)}}))}function Z(e){return s().then(t=>t.requestIdentityVerification(e))}function W(e){return s().then(t=>t.requestIssueBillingKeyAndPay(e))}function j(e){return s().then(t=>t.requestIssueBillingKey(e))}function H(e){return s().then(t=>t.requestPayment(e))}function K(e,t){return s().then(o=>o.loadPaymentUI(e,t))}function X(e,t){return s().then(o=>o.loadIssueBillingKeyUI(e,t))}function q(e){return s().then(t=>t.updateLoadPaymentUIRequest(e))}function $(e){return s().then(t=>t.updateLoadIssueBillingKeyUIRequest(e))}const AA={HTML5_INICIS:"PG_PROVIDER_HTML5_INICIS",PAYPAL:"PG_PROVIDER_PAYPAL",INICIS:"PG_PROVIDER_INICIS",DANAL:"PG_PROVIDER_DANAL",NICE:"PG_PROVIDER_NICE",DANAL_TPAY:"PG_PROVIDER_DANAL_TPAY",UPLUS:"PG_PROVIDER_UPLUS",NAVERPAY:"PG_PROVIDER_NAVERPAY",SETTLE:"PG_PROVIDER_SETTLE",KCP:"PG_PROVIDER_KCP",MOBILIANS:"PG_PROVIDER_MOBILIANS",KAKAOPAY:"PG_PROVIDER_KAKAOPAY",NAVERCO:"PG_PROVIDER_NAVERCO",KICC:"PG_PROVIDER_KICC",EXIMBAY:"PG_PROVIDER_EXIMBAY",SMILEPAY:"PG_PROVIDER_SMILEPAY",PAYCO:"PG_PROVIDER_PAYCO",KCP_BILLING:"PG_PROVIDER_KCP_BILLING",ALIPAY:"PG_PROVIDER_ALIPAY",CHAI:"PG_PROVIDER_CHAI",BLUEWALNUT:"PG_PROVIDER_BLUEWALNUT",SMARTRO:"PG_PROVIDER_SMARTRO",PAYMENTWALL:"PG_PROVIDER_PAYMENTWALL",TOSSPAYMENTS:"PG_PROVIDER_TOSSPAYMENTS",KCP_QUICK:"PG_PROVIDER_KCP_QUICK",DAOU:"PG_PROVIDER_DAOU",GALAXIA:"PG_PROVIDER_GALAXIA",TOSSPAY:"PG_PROVIDER_TOSSPAY",KCP_DIRECT:"PG_PROVIDER_KCP_DIRECT",SETTLE_ACC:"PG_PROVIDER_SETTLE_ACC",SETTLE_FIRM:"PG_PROVIDER_SETTLE_FIRM",INICIS_UNIFIED:"PG_PROVIDER_INICIS_UNIFIED",KSNET:"PG_PROVIDER_KSNET",PAYPAL_V2:"PG_PROVIDER_PAYPAL_V2",SMARTRO_V2:"PG_PROVIDER_SMARTRO_V2",NICE_V2:"PG_PROVIDER_NICE_V2",TOSS_BRANDPAY:"PG_PROVIDER_TOSS_BRANDPAY",WELCOME:"PG_PROVIDER_WELCOME",TOSSPAY_V2:"PG_PROVIDER_TOSSPAY_V2",INICIS_V2:"PG_PROVIDER_INICIS_V2",KPN:"PG_PROVIDER_KPN",KCP_V2:"PG_PROVIDER_KCP_V2",HYPHEN:"PG_PROVIDER_HYPHEN",EXIMBAY_V2:"PG_PROVIDER_EXIMBAY_V2"};Object.entries(AA).reduce((e,[t,o])=>Object.assign(Object.assign({},e),{[o]:t}),{});const tA={requestIdentityVerification:Z,requestIssueBillingKeyAndPay:W,requestIssueBillingKey:j,requestPayment:H,loadPaymentUI:K,loadIssueBillingKeyUI:X,updateLoadPaymentUIRequest:q,updateLoadIssueBillingKeyUIRequest:$},eA="data:image/gif;base64,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",nA="data:image/gif;base64,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",oA="data:image/gif;base64,R0lGODlhMwAUAPcAAP7T0/21tfxgYPoVFf/5+f2RkfyEhP/8/P/t7f/29v/7+/7ExPxeXvsxMfxwcPxsbP2YmPxqav7U1PtCQv2OjvoaGvtJSfsuLv/9/fxra/tVVfszM/xlZftERPtSUvojI/syMvoiIvs+Pvs0NP7f3//4+PtKSv/6+vtRUf7l5f2qqv/19f7e3v/09P66uvx7e/xhYftTU//+/v7Gxv/y8vxnZ/7j4/7Dw/x1df2ZmfstLfx2dv2Njfx+fv68vP2zs/s2Nv7n5/7i4v7V1f7Fxf7h4fyDg/2bm/s6Ov2np/omJvohIfoYGPtYWPtaWv/u7vyGhvswMPoeHvokJPs7O/x/f/yIiP7b2/7AwPxdXfxfX/7d3fxiYv2Tk/2iov6/v//p6ftFRf2Xl/oXF/orK/7JyfxubvtMTPogIPtGRvtWVvxkZPocHPyHh/7IyP/x8f2trf7Pz/7Cwv/r6//q6v2UlP/o6P2Li/24uP/z8/tUVPs1Nfs4OP2MjPx4ePxzc/7a2volJf/v7/s9PfoZGf7c3P7Y2Px9ffyCgv2ysvxcXPxbW/7Ozv2oqPtAQP7R0ftPT/oqKvonJ/oUFP///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH/C1hNUCBEYXRhWE1QPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS4zLWMwMTEgNjYuMTQ1NjYxLCAyMDEyLzAyLzA2LTE0OjU2OjI3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDowNjg5MDE5QTVDRDUxMUU1QTAxQ0M2MDE1MjAzNkM4NCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDowNjg5MDE5QjVDRDUxMUU1QTAxQ0M2MDE1MjAzNkM4NCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjA2ODkwMTk4NUNENTExRTVBMDFDQzYwMTUyMDM2Qzg0IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjA2ODkwMTk5NUNENTExRTVBMDFDQzYwMTUyMDM2Qzg0Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+Af/+/fz7+vn49/b19PPy8fDv7u3s6+rp6Ofm5eTj4uHg397d3Nva2djX1tXU09LR0M/OzczLysnIx8bFxMPCwcC/vr28u7q5uLe2tbSzsrGwr66trKuqqainpqWko6KhoJ+enZybmpmYl5aVlJOSkZCPjo2Mi4qJiIeGhYSDgoGAf359fHt6eXh3dnV0c3JxcG9ubWxramloZ2ZlZGNiYWBfXl1cW1pZWFdWVVRTUlFQT05NTEtKSUhHRkVEQ0JBQD8+PTw7Ojk4NzY1NDMyMTAvLi0sKyopKCcmJSQjIiEgHx4dHBsaGRgXFhUUExIREA8ODQwLCgkIBwYFBAMCAQAAIfkEAAAAAAAsAAAAADMAFAAACP8AKQkcSLCgwYMIEypcyLChw4cQI4p5QNHBHQAFsQg4MhBCGwIESxiAMFCCAS4CDt0oeOOFAA4USAycRLPmJAMD84SguYDSFZo9CO6gOYeAA5s0m4CkgwLpGAMHKNFMk0UNG5pfBEKouYgSASCTBvSk5IOmBQw1aKJhoGUJTUMtBtHUsUZDBaBSJwUQCGjAJByUMDSYNCJsIUqP7kahgSDSpA9F5NDsgEAgDQMUZFih6QAkJRYgwgKguVfglEkcKP2gKUHJpB0Cc9DMIIBmIkoZwrI4eGHSBgUE3dB8QZpSggI0+1AyMSkGJUSTpAiiJMOJTcCUJkxCcvAJTT8GJU3n0oOU5gc7cYoL8VtAIAIdNEWUEEhlkqODYGhWMdjbQ3kLElCSVnkXAEdJIzQRMRADkxBSWUEY7DRBQVvQ9AdNPCwwQwoCpcBEeTTBIVAANGEkkAo0wRDViV0cYAZNdQyUgH+TLFAcQUZMUkEZAPQIwB6ThDFiiQMp0EF8PFDA3CQz2BAITSgUAMUGNAmQlwsFhZZBQbJNYgMlLtA0BEFBaIeUBS1QwggZ5SmSACURMLBCQQVAIhNBCQgQQVQrMBDBCQWd4EUMDYygQRIGUvJGAWc0wAcMeEQk6aSUVmrppZhmqummEAUEADs=",PA={class:"order__payment"},iA={class:"payment__method"},aA={class:"payment__method--list"},sA=["onClick"],cA=["src","alt"],rA={key:1},lA={class:"payment__info"},IA={class:"payment__info--price"},EA={__name:"OrderPaymentSection",props:{totalFinalPrice:Number,pgMethod:String},emits:["update:pgMethod"],setup(e,{emit:t}){const o=[{id:"toss",name:"Toss",icon:eA},{id:"kakao",name:"Kakao",icon:nA},{id:"payco",name:"Payco",icon:oA},{id:"bank-transfer",name:"계좌이체"},{id:"virtual-account",name:"무통장입금"}],u=t,c=E=>{u("update:pgMethod",E)};return(E,R)=>(l(),I("section",PA,[R[1]||(R[1]=A("h3",null,"결제방법",-1)),A("div",iA,[A("div",aA,[(l(),I(S,null,G(o,a=>A("div",{class:Q(["payment__method--list-item",{selected:e.pgMethod===a.id}]),key:a.id,onClick:O=>c(a.id)},[a.icon?(l(),I("img",{key:0,src:a.icon,alt:a.name},null,8,cA)):(l(),I("span",rA,r(a.name),1))],10,sA)),64))])]),A("div",lA,[R[0]||(R[0]=A("div",{class:"payment__info--text"},"최종 결제금액",-1)),A("div",IA,r(e.totalFinalPrice.toLocaleString())+"원",1)])]))}},RA=w(EA,[["__scopeId","data-v-86c1c84b"]]),DA={class:"order"},uA={class:"order__info"},dA={class:"table order__info--table"},CA=["src"],BA={class:"text-center align-middle"},gA={class:"text-center align-middle"},mA={class:"text-center align-middle"},MA={class:"text-center align-middle"},OA={class:"payment__action"},wA={__name:"OrderDetailPage",setup(e){const t=x(),o=T(),u=t.params.id,c=d([]),E=d("toss"),R=d({status:"PENDING"}),a=V(),O=async i=>{try{const n=await b.get(`/api/v1/orders/${i}`);c.value=n.data.orderDetailDtos}catch{o.error("로드 실패!")}},_=C(()=>c.value.reduce((i,n)=>i+n.originalPrice,0)),N=C(()=>c.value.reduce((i,n)=>{const P=n.originalPrice*n.discountRatePercentage/100;return i+P},0)),D=C(()=>c.value.reduce((i,n)=>{const P=n.originalPrice-n.originalPrice*n.discountRatePercentage/100;return i+P},0)),v=async()=>{console.log("pgMethod:",E.value),console.log("totalFinalPrice:",D.value),R.value={status:"PENDING"};const i="ASDFASDFADS";await tA.requestPayment({storeId:"store-40493184-ea60-455e-93b1-94dc0b39f87f",channelKey:"channel-key-bdb9666b-9eba-4cc3-bc3e-cca13a1ff2f9",paymentId:i,orderName:"orderName TEST",totalAmount:D.value,currency:"KRW",payMethod:"EASY_PAY",redirectUrl:`/payments/${i}`,productType:"PRODUCT_TYPE_DIGITAL",isCulturalExpense:!0,cusomer:{email:a.getMemberEmail()},customData:{item:123}})};return k(()=>{O(u)}),(i,n)=>(l(),I("div",DA,[A("section",uA,[n[4]||(n[4]=A("h3",null,"주문/결제",-1)),A("table",dA,[n[2]||(n[2]=A("colgroup",null,[A("col",{width:"10%"}),A("col",{width:"45%"}),A("col",{width:"10%"}),A("col",{width:"5%"}),A("col",{width:"10%"}),A("col",{width:"10%"}),A("col",{width:"10%"})],-1)),n[3]||(n[3]=A("thead",{class:"text-center"},[A("tr",null,[A("th",{colspan:"2"},"상품명"),A("th",null,"정가"),A("th",null,"수량"),A("th",null,"할인금액"),A("th",null,"합계"),A("th",null,"배송일")])],-1)),A("tbody",null,[(l(!0),I(S,null,G(c.value,P=>(l(),I("tr",null,[A("td",null,[A("img",{src:B(f)(P.thumbnail),alt:"상품 이미지"},null,8,CA)]),A("td",null,r(P.name),1),A("td",BA,r(P.originalPrice.toLocaleString())+"원 ",1),A("td",gA,r(P.quantity),1),A("td",mA,r(B(h)(P.originalPrice,P.discountRatePercentage))+"원 ",1),A("td",MA,r(B(U)(P.originalPrice,P.discountRatePercentage))+"원 ",1),n[1]||(n[1]=A("td",{class:"text-center align-middle"}," 무배송 (결제 후 다운로드) ",-1))]))),256))])])]),g(p,{"total-original-price":_.value,"total-discount-amount":N.value,"total-final-price":D.value},null,8,["total-original-price","total-discount-amount","total-final-price"]),g(RA,{"total-final-price":D.value,"pg-method":E.value,"onUpdate:pgMethod":n[0]||(n[0]=P=>E.value=P)},null,8,["total-final-price","pg-method"]),A("div",OA,[g(L,{class:"w-100 payment__action--button",onAction:v},{default:Y(()=>n[5]||(n[5]=[z("결제하기")])),_:1})])]))}},vA=w(wA,[["__scopeId","data-v-da9eead6"]]);export{vA as default};
