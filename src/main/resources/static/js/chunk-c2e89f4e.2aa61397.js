(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c2e89f4e"],{1148:function(e,t,a){"use strict";var s=a("a691"),i=a("1d80");e.exports="".repeat||function(e){var t=String(i(this)),a="",r=s(e);if(r<0||r==1/0)throw RangeError("Wrong number of repetitions");for(;r>0;(r>>>=1)&&(t+=t))1&r&&(a+=t);return a}},"408a":function(e,t,a){var s=a("c6b6");e.exports=function(e){if("number"!=typeof e&&"Number"!=s(e))throw TypeError("Incorrect invocation");return+e}},b680:function(e,t,a){"use strict";var s=a("23e7"),i=a("a691"),r=a("408a"),l=a("1148"),o=a("d039"),n=1..toFixed,c=Math.floor,d=function(e,t,a){return 0===t?a:t%2===1?d(e,t-1,a*e):d(e*e,t/2,a)},p=function(e){var t=0,a=e;while(a>=4096)t+=12,a/=4096;while(a>=2)t+=1,a/=2;return t},u=n&&("0.000"!==8e-5.toFixed(3)||"1"!==.9.toFixed(0)||"1.25"!==1.255.toFixed(2)||"1000000000000000128"!==(0xde0b6b3a7640080).toFixed(0))||!o((function(){n.call({})}));s({target:"Number",proto:!0,forced:u},{toFixed:function(e){var t,a,s,o,n=r(this),u=i(e),h=[0,0,0,0,0,0],f="",m="0",v=function(e,t){var a=-1,s=t;while(++a<6)s+=e*h[a],h[a]=s%1e7,s=c(s/1e7)},g=function(e){var t=6,a=0;while(--t>=0)a+=h[t],h[t]=c(a/e),a=a%e*1e7},w=function(){var e=6,t="";while(--e>=0)if(""!==t||0===e||0!==h[e]){var a=String(h[e]);t=""===t?a:t+l.call("0",7-a.length)+a}return t};if(u<0||u>20)throw RangeError("Incorrect fraction digits");if(n!=n)return"NaN";if(n<=-1e21||n>=1e21)return String(n);if(n<0&&(f="-",n=-n),n>1e-21)if(t=p(n*d(2,69,1))-69,a=t<0?n*d(2,-t,1):n/d(2,t,1),a*=4503599627370496,t=52-t,t>0){v(0,a),s=u;while(s>=7)v(1e7,0),s-=7;v(d(10,s,1),0),s=t-1;while(s>=23)g(1<<23),s-=23;g(1<<s),v(1,1),g(2),m=w()}else v(0,a),v(1<<-t,0),m=w()+l.call("0",u);return u>0?(o=m.length,m=f+(o<=u?"0."+l.call("0",u-o)+m:m.slice(0,o-u)+"."+m.slice(o-u))):m=f+m,m}})},b718:function(e,t,a){},ba88:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-container",[a("el-header",[e._v(" 上传遥测水位 ")]),a("el-main",[a("el-row",{attrs:{gutter:15}},[a("el-col",{class:{"col-17-step2":2===e.step},attrs:{span:18}},[a("el-card",{directives:[{name:"show",rawName:"v-show",value:1===e.step,expression:"step===1"}],attrs:{shadow:"hover"}},[a("el-form",{attrs:{"label-position":"right","label-width":"20%"}},[a("el-form-item",{attrs:{label:"水库名称"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.selectedReservoir,callback:function(t){e.$set(e.form,"selectedReservoir",t)},expression:"form.selectedReservoir"}},e._l(e.reservoirList,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.name}},[e._v(" "+e._s(t.name)+" ")])})),1)],1),a("el-form-item",{attrs:{label:"卫星名称"}},[a("el-input",{staticStyle:{width:"60%"},attrs:{placeholder:"请输入卫星名称"},model:{value:e.form.satelliteName,callback:function(t){e.$set(e.form,"satelliteName",t)},expression:"form.satelliteName"}})],1),a("el-form-item",{attrs:{label:"卫星周期"}},[a("el-input",{staticStyle:{width:"60%"},attrs:{placeholder:"请输入周期"},model:{value:e.form.cycle,callback:function(t){e.$set(e.form,"cycle",t)},expression:"form.cycle"}})],1),a("el-form-item",{attrs:{label:"日期"}},[a("el-date-picker",{attrs:{type:"date",placeholder:"请输入日期"},model:{value:e.form.date,callback:function(t){e.$set(e.form,"date",t)},expression:"form.date"}})],1),a("el-form-item",{attrs:{label:"左上角经纬度"}},[a("el-input",{staticStyle:{width:"60%"},attrs:{placeholder:'以空格分隔经纬度，例如"111.01 32.97"'},on:{input:e.stepOneSetTest},model:{value:e.form.topLeft,callback:function(t){e.$set(e.form,"topLeft",t)},expression:"form.topLeft"}})],1),a("el-form-item",{attrs:{label:"右下角经纬度"}},[a("el-input",{staticStyle:{width:"60%"},attrs:{placeholder:'以空格分隔经纬度，例如"111.75 32.93"'},on:{input:e.stepOneSetTest},model:{value:e.form.lowerRight,callback:function(t){e.$set(e.form,"lowerRight",t)},expression:"form.lowerRight"}})],1),a("el-form-item",{attrs:{label:"选择文件"}},[a("el-upload",{staticClass:"upload-drag-list",attrs:{drag:"",action:"","auto-upload":!1,"show-file-list":!0,"on-change":e.handleFileChanges,"on-remove":e.handleFileChanges,multiple:!0,"file-list":e.fileList}},[a("i",{staticClass:"el-icon-upload"}),a("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或"),a("em",[e._v("点击上传")])])])],1),a("el-button",{attrs:{disabled:e.stepOneClickNextDisable},domProps:{textContent:e._s(e.showUploadPercentage)},on:{click:e.stepOneClickNext}})],1)],1),a("el-card",{directives:[{name:"show",rawName:"v-show",value:2===e.step,expression:"step===2"},{name:"loading",rawName:"v-loading",value:e.translating,expression:"translating"}],staticClass:"el-card-step2",attrs:{shadow:"hover","body-style":{flex:"1",display:"flex",flexDirection:"column",padding:"0"}}},[a("baidu-map",{staticClass:"radar-map",style:e.zoomBack,attrs:{"scroll-wheel-zoom":!0,center:e.centerPoint,zoom:e.zoom,"continuous-zoom":!0},on:{moving:e.stepTwoUpdateMapZoom,moveend:e.stepTwoUpdateMapZoom,zoomend:e.stepTwoUpdateMapZoom}},e._l(e.resultList,(function(t){return a("bm-marker",{key:t.index,attrs:{position:{lng:t.translatedLng,lat:t.translatedLat},icon:e.pointer,offset:{height:0,width:15}},on:{click:function(a){return e.stepTwoHandleMarkerClick(t.index)}}},[a("bm-label",{attrs:{content:t.index+1+"",offset:{width:15,height:-10}}})],1)})),1),a("div",{style:{display:"flex",alignItems:"center",margin:"10px"}},[a("el-select",{staticStyle:{"flex-basis":"100px","margin-right":"15px"},on:{change:e.stepTwoHandleSelectChange},model:{value:e.selectRadar.index,callback:function(t){e.$set(e.selectRadar,"index",t)},expression:"selectRadar.index"}},e._l(e.resultList,(function(t){return a("el-option",{key:t.index,attrs:{value:t.index,label:t.index+1}},[e._v(" "+e._s(t.index+1)+" ")])})),1),a("div",{directives:[{name:"show",rawName:"v-show",value:""!==e.selectRadar.index,expression:"selectRadar.index!==''"}],staticStyle:{flex:"1"}},[e._v(" 经度："),a("span",{staticClass:"el-card-step2-span",domProps:{textContent:e._s(e.selectRadar.lng)}})]),a("div",{directives:[{name:"show",rawName:"v-show",value:""!==e.selectRadar.index,expression:"selectRadar.index!==''"}],staticStyle:{flex:"1"}},[e._v(" 纬度："),a("span",{staticClass:"el-card-step2-span",domProps:{textContent:e._s(e.selectRadar.lat)}})]),a("div",{directives:[{name:"show",rawName:"v-show",value:""!==e.selectRadar.index,expression:"selectRadar.index!==''"}],staticStyle:{flex:"1"}},[e._v(" 水位："),a("span",{staticClass:"el-card-step2-span",domProps:{textContent:e._s(e.selectRadar.level)}})]),a("div",{staticStyle:{flex:"2"}},[a("el-button",{staticStyle:{"margin-bottom":"0"},attrs:{type:"success"},on:{click:e.stepTwoClickSave}},[e._v(" 确认并保存 ")]),a("el-button",{staticStyle:{"margin-bottom":"0"},on:{click:e.stepTwoClickBack}},[e._v(" 上一步 ")])],1)],1)],1),a("el-card",{directives:[{name:"show",rawName:"v-show",value:3===e.step,expression:"step===3"}]},[a("i",{staticClass:"el-icon-success"}),e._v(" 数据已保存 "),a("el-button",{on:{click:e.stepThreeClickWatchData}},[e._v(" 查看数据 ")])],1)],1),a("el-col",{attrs:{span:6}},[a("el-card",{attrs:{shadow:"hover"}},[a("el-steps",{attrs:{direction:"vertical",active:e.step-1}},[a("el-step",{attrs:{title:"上传文件",description:"输入信息，上传遥测水位文件"}}),a("el-step",{attrs:{title:"选择并确认数据",description:"选择合适的水位数据"}}),a("el-step",{attrs:{title:"上传完成"}})],1)],1)],1)],1)],1)],1)],1)},i=[],r=(a("b680"),{name:"uploadRadarLevel",data:function(){return{fileList:[],reservoirList:[],form:{selectedReservoir:"",satelliteName:"",cycle:"",date:"",topLeft:"",lowerRight:""},selectRadar:{index:"",lng:"",lat:"",level:""},uploadPercentage:0,uploading:!1,translating:!1,resultList:[],step:1,pointer:{url:"/marker.png",size:{width:50,height:50}},centerPoint:{lng:"",lat:""},zoom:15}},computed:{stepOneClickNextDisable:function(){return""===this.form.selectedReservoir||""===this.form.satelliteName||""===this.form.cycle||""===this.form.date||""===this.form.topLeft||""===this.form.lowerRight||4!==this.fileList.length||this.uploading},showUploadPercentage:function(){return this.uploading?100===this.uploadPercentage?"正在处理数据":"正在上传："+this.uploadPercentage+"%":"下一步"},zoomBack:function(){return{zoom:Math.min(1600/window.screen.width,1)+""}}},methods:{handleFileChanges:function(e,t){this.fileList=t},stepOneClickNext:function(){var e=this;this.uploading=!0;var t=new FormData;t.append("reservoirName",this.form.selectedReservoir),t.append("satelliteName",this.form.satelliteName),t.append("cycle",this.form.cycle),t.append("date",this.form.date),t.append("topLeft",this.form.topLeft),t.append("lowerRight",this.form.lowerRight);for(var a=0;a<this.fileList.length;a++)t.append("radarFile",this.fileList[a].raw);this.axios({url:"/api/upload/radar",method:"post",data:t,headers:{"Content-Type":"multipart/form-data;charset=utf-8"},onUploadProgress:function(t){e.uploadPercentage=t.loaded/t.total*100|0}}).then((function(t){if(200===t.status){e.resultList=t.data;var a=e;a.step++,e.translating=!0,doPointTranslate(e.resultList,(function(e){for(var t=0;t<e.length;t++)a.resultList[t].translatedLng=e[t].lng,a.resultList[t].translatedLat=e[t].lat;a.getCenterPoint(),a.uploading=!1,a.translating=!1}))}}))},stepOneSetTest:function(){"//"!==this.form.topLeft&&"//"!==this.form.lowerRight||(this.form.topLeft="113.43 32.97",this.form.lowerRight="114.12 32.93")},getCenterPoint:function(){for(var e=0,t=0,a=0,s=0;s<this.resultList.length;s++)e++,t+=this.resultList[s].translatedLng,a+=this.resultList[s].translatedLat;this.centerPoint.lng=t/e,this.centerPoint.lat=a/e},stepTwoClickBack:function(){this.step--},stepTwoHandleSelectChange:function(){var e=this.resultList[this.selectRadar.index];this.selectRadar.lng=e.lng,this.selectRadar.lat=e.lat,this.centerPoint.lng=e.translatedLng,this.centerPoint.lat=e.translatedLat,this.selectRadar.level=e.level.toFixed(3)+"m"},stepTwoHandleMarkerClick:function(e){this.selectRadar.index=e,this.stepTwoHandleSelectChange()},stepTwoUpdateMapZoom:function(e){this.zoom=e.target.getZoom()},stepTwoClickSave:function(){var e=this;this.axios.get("/api/upload/radar/choose?index="+this.selectRadar.index).then((function(t){"success"===t.data?e.step++:console.log("error")}))},stepThreeClickWatchData:function(){this.$router.push("/dm")}},mounted:function(){var e=this;this.axios.get("/api/reservoirInfo/reservoir").then((function(t){200===t.status?e.reservoirList=t.data:console.log("error")}));var t=document.getElementsByClassName("el-upload__input");t[0].setAttribute("webkitdirectory","true"),this.form.selectedReservoir=this.$store.getters.getReservoir}}),l=r,o=(a("ccc0"),a("2877")),n=Object(o["a"])(l,s,i,!1,null,"5ecdb2aa",null);t["default"]=n.exports},ccc0:function(e,t,a){"use strict";var s=a("b718"),i=a.n(s);i.a}}]);
//# sourceMappingURL=chunk-c2e89f4e.2aa61397.js.map