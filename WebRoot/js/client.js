var str;
var arrClientName=new Array();
$(document).ready(function() {
	showmainclienttitle();
	shownodistribution();
//	alert(hasclientnocheck);
//	selectID
	str="<option value ='0'>选择员工号</option>";
	$.getJSON("./WGetClientStatisticalNumberServlet",function(outjson){
		
		for(var i=0;i<outjson.EmployeeList.length;i++){
			if(outjson.EmployeeList[i].EmployeeName.length<3){
				outjson.EmployeeList[i].EmployeeName+="&nbsp&nbsp&nbsp";
			}
			str=str+"<option value ="+outjson.EmployeeList[i].EmployeeId+">账号:"+outjson.EmployeeList[i].EmployeeAccount+"&nbsp员工名:"+outjson.EmployeeList[i].EmployeeName+"&nbsp分配客户:"+outjson.EmployeeList[i].ClientNumber+"</option>";
			
		}
 	
 	});
	clickdiandian();

})
function clickdiandian() {
	var clientcount=0;
	if(hasclientnocheck){
		$("#clienthasnocheckclientdivid").css("display","block");
	}else {
		$("#clienthasnocheckclientdivid").css("display","none");
	}
	if(clientcount!=100000){
		setTimeout(clickdiandian, 5000);
	}
}


function showmainclienttitle() {
	$(".right-title").html("");
	$(".right-title").append(
			"<img onclick='	showmainclienttitle(),shownodistribution()' src='./pic/daohang.png'  title='客户管理导航' style=' width:30px;height:30px; cursor:pointer; margin-left:100px;margin-bottom:-64px; margin-top:7px;' />"+
			"<div id=clienthasnocheckclientdivid style='display:none;font-family:Microsoft YaHei;cursor: pointer;position:relative;margin-left:180px; width:120px;height:50px; padding-top:10px;margin-bottom:-64px; margin-top:4px;'><font style='font-size:13px;color:rgb(255,255,255)'>您有未审核的客户</font><img id=clienttitlepoint src='./pic/redpoint.png' style='height:10px;width:10px;padding-bottom:3px; margin-top:-20px;;margin-left:109px;'><img/></div>"+
		 	
			"<div id=usermanagerserch class=zySearch> " +
 			"<input id=clientsearchameid class=search-input  placeholder='全员搜索或请输入客户名' ></input>"+
 			"<div class=search-btn  onclick='searchclient()'>搜索</div>"+
 			"</div>"+
 			"<div id=attenss style='height:25px;width:400px;float:right;margin-top:-15px; margin-left:700px;'>" +
 			"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative;' onclick='addclient()'>增加</div>"+
				"<div class=usebut  style=' cursor:pointer; position: relative; margin-left:50px;'> <select id=selectID onchange='showSelectClient()' class=useselect style='height:25px;'><option value='1'>未分配</option><option value='2'>已分配</option><option value='3'>未审核</option><option value='4'>已删除</option></select></div> "+
		 		
 			
 			"</div>"
 		
 			
	);
	
}

function searchclient() {
	alert("暂不开发");
}



$(function searchclientname () {
	setTimeout(searchclientname, 1000);
   var resarray=new Array();
   resarray=arrClientName;
  
$("#clientsearchameid").autocomplete(resarray,{
        minChars: 0, // 双击空白文本框时显示全部提示数据
        max:100,
        formatItem: function (data, i, total) {
            return  data[0] ; // 改变匹配数据显示的格式
        },
        formatMatch: function (data, i, total) {
            return data[0];
        },
        formatResult: function (data) {
            return data[0];
        }
    }).result(SearchCallback); 
    function SearchCallback(event, data, formatted) {
//$(".tip").show().html("您的选择是：" + (!data ? "空" : formatted));
    }
});



function showSelectClient() {
	var selectId=$("#selectID").val();
//	alert(selectId);
	if(selectId==1){
		shownodistribution();
		
	}else if(selectId==2){
		showdistribution();
	}else if(selectId==3){
		shownocheck();
	}else if(selectId==4){
		showdelclient();
	}
	$("#clientsearchameid").val("");
	
	
}

function showdelclient() {
//	
	
	$(".group-listclient").html("");
	$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
		'<div class="group-title"><font>客户已删列表</font>'+ 
		"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='disbutclient()' >分配客户</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
	"</div>"+
		'</div>'+
		'<table id="tabclient" style="font-size:13px;" width="100%">'+
        '<tbody>'+
         '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
         	 '<th>客户号</th>'+
         	 '<th>客户名</th>'+
             '<th>客户电话</th>'+
             '<th>客户区域</th>'+
             '<th>客户地址</th>'+
             '<th>客户公司</th>'+
         '</tr>'+
         '</tbody>'+
         
          
         '<tbody  class="thisgroup">'+
         	
         '</tbody>'+
         '</table>'+
      
         
         
	'</div>');
	
	$.getJSON("./WGetAllDelClientServlet",function(outjson){
		arrClientName=new Array();
		for ( var i = 0; i < outjson.ClientList.length; i++) {
			arrClientName.push(outjson.ClientList[i].ClientName);
			$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientId+'</th>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientPhone+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientArea+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientId+'</th>'+
					'</tr>')
		}
	});	
	
	curtab=null;
}

function shownocheck() {
//	
	$(".group-listclient").html("");
	$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
		'<div class="group-title"><font>客户未审核列表</font>'+ 
		"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='clientcheck()' >审核</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
	"</div>"+
		'</div>'+
		'<table id="tabclient" style="font-size:13px;" width="100%">'+
        '<tbody>'+
         '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
         	 '<th>客户号</th>'+
         	 '<th>客户名</th>'+
         	 '<th>员工帐名</th>'+
         	 '<th>员工名</th>'+
         	 '<th>客户提交时间</th>'+
             '<th>客户地址</th>'+
             '<th>客户公司</th>'+
         '</tr>'+
         '</tbody>'+
         
          
         '<tbody  class="thisgroup">'+
         	
         '</tbody>'+
         '</table>'+
      
         
         
	'</div>');
	
	$.getJSON("./WGerNocheckClientServlet",function(outjson){
		arrClientName=new Array();
		for ( var i = 0; i < outjson.ClientList.length; i++) {
			arrClientName.push(outjson.ClientList[i].ClientName);
			$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientId+'</th>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].EmployeeAccount+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].EmployeeName+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientSubmitTime+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientPhone+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientArea+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientState+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientSubmitId+'</th>'+
					'</tr>')
		}
	});	
	
	curtab=null;
}



function clientcheck() {
	var x=curtab;
	var clientid=$(x).find("th").eq(0).html();
	if(x==null){
		alert("请选择需要操作的客户")
	}else{
//		if(clientsubmitstate=="未审核"){
			$(".shadow").show();
			$(".pagehead font").html("审核客户")
			$(".showpage").css("width","280px");
			$(".showpage").css("height","100px");
			$(".showpage").css("margin-left","600px");
			$(".pagemain").html("<div class=userset>"+
		  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='checkclienet("+clientid+",1)'  class=but1>审核通过</button><button class=but1  onclick='checkclienet("+clientid+",2)'  >审核不通过</button></div>");
//		}else {
//			alert("客户已审核");
//		}
	}
//	tabclient=null;
	
}

function checkclienet(id,type) {
	
	$.getJSON("./WCheckClientServlet",{ClientId:id,ClientSubmitState:type},function(outjson){
			if(outjson.check){
				alert("成功！");
			}else{
				alert("失败！");
			}
			 closepage();
			 shownocheck();
		});
}




function showdistribution() {
//	
	$(".group-listclient").html("");
	$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
		'<div class="group-title"><font>客户已分配列表</font>'+ 
		"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='cancelDisbut()' >取消分配</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
//		"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
	"</div>"+
		'</div>'+
		'<table id="tabclient" style="font-size:13px;" width="100%">'+
        '<tbody>'+
         '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
         	 '<th>客户号</th>'+
         	 '<th>客户名</th>'+
         	 '<th>员工帐名</th>'+
         	 '<th>员工名</th>'+
             '<th>客户地址</th>'+
             '<th>客户公司</th>'+
         '</tr>'+
         '</tbody>'+
         
          
         '<tbody  class="thisgroup">'+
         	
         '</tbody>'+
         '</table>'+
      
         
         
	'</div>');
	
	$.getJSON("./WGetClientDistbutionServlet",function(outjson){
		arrClientName=new Array();
		for ( var i = 0; i < outjson.ClientList.length; i++) {
			arrClientName.push(outjson.ClientList[i].ClientName);
			$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientId+'</th>'+
					'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].EmployeeAccount+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].EmployeeName+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
				    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientPhone+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientArea+'</th>'+
					'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientState+'</th>'+
					'</tr>')
		}
	});	
	
	curtab=null;
}






function addclient() {
//	alert(1);
	$(".group-listclient").html("");
	$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>增加客户</font></div></div>'+
			'<div class="ground-one-left" style="float:left;width:50%;height:100%;">'+
				'<div style="position:relative; height:500px;margin-left:100px;">'+
//					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">客户号</font></div>'+	
//					'<div class="main-input" style="margin-left:10px;margin-top:5px;margin-top:5px;padding-top:7px;" ></div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px">'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户公司</font></div>'+	
					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" >'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户号码</font></div>'+	
					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" >'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户区域</font></div>'+	
					'<input id=updateclientarea class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px">'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户地址</font></div>'+	
					'<input id=updateclientaddress class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" >'+
					
				'</div>'+
				'<div style="margin-left:113px;margin-top:-70px;"><div class="usebut" onclick="addclientok(this)" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="showback()">取消</div></div>'+
			'</div>'+
			
			'<div class="ground-one-right" style="float:left;width:50%;height:100%;">'+
				'<div id=updateclientcontainer  style="width:100%;height:95%;"></div>'+
			'</div>'
	);
	
	withmapno();
}

function addclientok(x) {
	var clientname=$(x).parent().parent().find("div").eq(0).find("input").eq(0).val();
 	var clientcompany=$(x).parent().parent().find("div").eq(0).find("input").eq(1).val();
 	var clientnumber=$(x).parent().parent().find("div").eq(0).find("input").eq(2).val();
 	var clientarea=$(x).parent().parent().find("div").eq(0).find("input").eq(3).val();
 	var clientaddress=$(x).parent().parent().find("div").eq(0).find("input").eq(4).val();
 	$.getJSON("./WAddClientServlet",{ClientName:clientname,ClientCompany:clientcompany,ClientPhone:clientnumber,ClientArea:clientarea,ClientAddress:clientaddress},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 		}else {
 			alert("失败！");
 		}
 		
 		var seleed=$("#selectID").val();
 		if(seleed==1){
 			shownodistribution();
 		}else if(seleed==2){
 			showdistribution();
 		}else if(seleed==3){
 			shownocheck();
 		}else if(seleed==4){
 			showdelclient();
 		}
 		var op=parseInt(seleed);
 		$("#selectID").find("option").eq(op-1).attr("selected","selected");
 		
 	})
}


function seeclient() {
		var x=curtab;

		var clientid;
		var clientname;
		var clientnumber;
		var clientarea;
		var clientaddress;
		var clientcompany;
		var seleed=$("#selectID").val();
 		if(seleed==1||seleed==4){
 			clientid=$(x).find("th").eq(0).html();
 			clientname=$(x).find("th").eq(1).html();
 			clientnumber=$(x).find("th").eq(2).html();
 			clientarea=$(x).find("th").eq(3).html();
 			clientaddress=$(x).find("th").eq(4).html();
 			clientcompany=$(x).find("th").eq(5).html();
 			
 		}else if(seleed==2){
 			clientid=$(x).find("th").eq(0).html();
 			clientname=$(x).find("th").eq(1).html();
 			clientnumber=$(x).find("th").eq(6).html();
 			clientarea=$(x).find("th").eq(7).html();
 			clientaddress=$(x).find("th").eq(4).html();
 			clientcompany=$(x).find("th").eq(5).html();
 		}else if(seleed==3){
 			clientid=$(x).find("th").eq(0).html();
 			clientname=$(x).find("th").eq(1).html();
 			clientnumber=$(x).find("th").eq(7).html();
 			clientarea=$(x).find("th").eq(8).html();
 			clientaddress=$(x).find("th").eq(5).html();
 			clientcompany=$(x).find("th").eq(6).html();
 		}
		
		if(x==null){
			alert("请选中需要操作的客户")
		}else{
			$(".group-listclient").html("");
			$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>客户详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%;">'+
	  				'<div style="position:relative; height:500px;margin-left:100px;">'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">客户号</font></div>'+	
	  					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+clientid+'</div>'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientname+'</div>'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户公司</font></div>'+	
	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientcompany+'</div>'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户号码</font></div>'+	
	  					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientnumber+'</div>'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户区域</font></div>'+	
	  					'<div id=updateclientarea class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clientarea+'</div>'+
	  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户地址</font></div>'+	
	  					'<div id=updateclientaddress class="main-input" style="margin-left:10px;width:289px;margin-top:10px;padding-top:10px;">'+clientaddress+'</div>'+
	  					
	  				'</div>'+
//	  				'<div style="margin-left:113px;"><div class="usebut" onclick="changecustomerok(this)" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="updateclientclean()">取消</div></div>'+
	  			'</div>'+
	  			
	  			
	  			
	  			'<div class="ground-one-right" style="float:left;width:50%;height:100%;">'+
	  				'<div id=updateclientcontainer  style="width:100%;height:95%;"></div>'+
	  			'</div>'
			
			
			
			
			);
			
			withmapsee(clientaddress,clientarea);
		}
		
		
}

function cancelDisbut() {
	
	
	var x=curtab;
	var clientid=$(x).find("th").eq(0).html();
//	clientname=$(x).find("th").eq(1).html();
	if(x==null){
		alert("请选择需要操作的客户")
	}else{
		$(".shadow").show();
		$(".pagehead font").html("取消分配")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","100px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='cancelDisbutOK("+clientid+")'  class=but1>确定</button><button class=but1  onclick='closepage()'  >取消</button></div>");
	}
}

function cancelDisbutOK(clientid) {
	$.getJSON("./WCancleClientDistuServlet",{ClientId:clientid},function(outjson){
		if(outjson.check){
			alert("成功！");
			

		}else{
			alert("失败！");
		}
		 closepage();
		 showdistribution();
	});
}

function updateclient() {
	var x=curtab;

	
	var clientid;
	var clientname;
	var clientnumber;
	var clientarea;
	var clientaddress;
	var clientcompany;
	var seleed=$("#selectID").val();
		if(seleed==1||seleed==4){
			clientid=$(x).find("th").eq(0).html();
			clientname=$(x).find("th").eq(1).html();
			clientnumber=$(x).find("th").eq(2).html();
			clientarea=$(x).find("th").eq(3).html();
			clientaddress=$(x).find("th").eq(4).html();
			clientcompany=$(x).find("th").eq(5).html();
			
		}else if(seleed==2){
			clientid=$(x).find("th").eq(0).html();
			clientname=$(x).find("th").eq(1).html();
			clientnumber=$(x).find("th").eq(6).html();
			clientarea=$(x).find("th").eq(7).html();
			clientaddress=$(x).find("th").eq(4).html();
			clientcompany=$(x).find("th").eq(5).html();
		}else if(seleed==3){
 			clientid=$(x).find("th").eq(0).html();
 			clientname=$(x).find("th").eq(1).html();
 			clientnumber=$(x).find("th").eq(7).html();
 			clientarea=$(x).find("th").eq(8).html();
 			clientaddress=$(x).find("th").eq(5).html();
 			clientcompany=$(x).find("th").eq(6).html();
 		}
	
	
	if(x==null){
		alert("请选中需要操作的客户")
	}else{
		$(".group-listclient").html("");
		$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showback()"></img><div style="margin-top:-25px;"><font>修改客户</font></div></div>'+
  			'<div class="ground-one-left" style="float:left;width:50%;height:100%;">'+
  				'<div style="position:relative; height:500px;margin-left:100px;">'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">客户号</font></div>'+	
  					'<div class="main-input" style="margin-left:10px;margin-top:5px;margin-top:5px;padding-top:7px;" >'+clientid+'</div>'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
  					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px"value='+clientname+' ">'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户公司</font></div>'+	
  					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px"value='+clientcompany+' >'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户号码</font></div>'+	
  					'<input  class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientnumber+'>'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户区域</font></div>'+	
  					'<input id=updateclientarea class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientarea+'>'+
  					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户地址</font></div>'+	
  					'<input id=updateclientaddress class="main-input" style="margin-left:10px;margin-top:0px;margin-top:5px; width:289px" value='+clientaddress+'>'+
  					
  				'</div>'+
  				'<div style="margin-left:113px;"><div class="usebut" onclick="changecustomerok(this)" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;" onclick="updateclientclean()">取消</div></div>'+
  			'</div>'+
  			
  			
  			
  			'<div class="ground-one-right" style="float:left;width:50%;height:100%;">'+
  				'<div id=updateclientcontainer  style="width:100%;height:95%;"></div>'+
  			'</div>'
		
		
		
		
		
		);
		
		withmap(clientaddress,clientarea);
	}
	
}


function delclient() {
	var x=curtab;
//	var clientsubmitstate=$(x).find("th").eq(7).html();
	var clienid=$(x).find("th").eq(0).html();
	if(x==null){
		alert("请选择需要操作的客户")
	}else{
		$(".shadow").show();
		$(".pagehead font").html("删除客户")
		$(".showpage").css("width","280px");
		$(".showpage").css("height","100px");
		$(".showpage").css("margin-left","600px");
		$(".pagemain").html("<div class=userset>"+
	  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='delclientOK("+clienid+")'  class=but1>确定</button><button class=but1  onclick='closepage()'  >取消</button></div>");
	}
}

function delclientOK(clientid) {
//	var x=usedetailtab;
	$.getJSON("./WDelClientServlet",{ClientId:clientid},function(outjson){

		if(outjson.check){
 			alert("成功！");
 		}else {
 			alert("失败！");
 		}
 		closepage();
 		var seleed=$("#selectID").val();
 		if(seleed==1){
// 			showmainclienttitle();
 			shownodistribution();
 		}else if(seleed==2){
 			showdistribution();
 		}else if(seleed==3){
 			shownocheck();
 		}else if(seleed==4){
 			showdelclient();
 		}
 		var op=parseInt(seleed);
 		$("#selectID").find("option").eq(op-1).attr("selected","selected");
 		
	});
}

function showback() {
	var seleed=$("#selectID").val();
		if(seleed==1){
			shownodistribution();
		}else if(seleed==2){
			showdistribution();
		}else if(seleed==3){
			shownocheck();
		}else if(seleed==4){
 			showdelclient();
 		}
		showmainclienttitle();
		var op=parseInt(seleed);
		$("#selectID").find("option").eq(op-1).attr("selected","selected");
}


function changecustomerok(x) {
 	var clientid=$(x).parent().parent().find("div").eq(0).find("div").eq(1).html();
 	var clientname=$(x).parent().parent().find("div").eq(0).find("input").eq(0).val();
 	var clientcompany=$(x).parent().parent().find("div").eq(0).find("input").eq(1).val();
 	var clientnumber=$(x).parent().parent().find("div").eq(0).find("input").eq(2).val();
 	var clientarea=$(x).parent().parent().find("div").eq(0).find("input").eq(3).val();
 	var clientaddress=$(x).parent().parent().find("div").eq(0).find("input").eq(4).val();
 	$.getJSON("./WUpdateClientServlet",{ClientName:clientname,ClientId:clientid,ClientCompany:clientcompany,ClientPhone:clientnumber,ClientArea:clientarea,ClientAddress:clientaddress},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 		}else {
 			alert("失败！");
 		}
 		
 		var seleed=$("#selectID").val();
 		if(seleed==1){
 			shownodistribution();
 		}else if(seleed==2){
 			showdistribution();
 		}else if(seleed==3){
 			shownocheck();
 		}else if(seleed==4){
 			showdelclient();
 		}
 		var op=parseInt(seleed);
 		$("#selectID").find("option").eq(op-1).attr("selected","selected");
 		
 	})
 }

function shownodistribution() {
	$(".group-listclient").html("");
		$(".group-listclient").append('<div class="group-one" style="height:100%;background-color: rgb(248,254,254);"> '+
			'<div class="group-title"><font>客户未分配列表</font>'+ 
			"<div id=butclient style='height:25px;width:350px;float:right;margin-top:-20px; margin-left:900px;'>" +
			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='disbutclient()' >分配客户</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='updateclient()'>修改</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='seeclient()'>查看</div>"+
//			"<div class=clientbut  style=' cursor:pointer; position: relative;' onclick='delclient()'>删除</div>"+
		"</div>"+
			'</div>'+
			'<table id="tabclient" style="font-size:13px;" width="100%">'+
            '<tbody>'+
             '<tr style="height:20x;background-color:rgb(178,243,238);font-size:13px;color:rgb(60,60,60;">'+
             	 '<th>客户号</th>'+
             	 '<th>客户名</th>'+
                 '<th>客户电话</th>'+
                 '<th>客户区域</th>'+
                 '<th>客户地址</th>'+
                 '<th>客户公司</th>'+
             '</tr>'+
             '</tbody>'+
             
              
             '<tbody  class="thisgroup">'+
             	
             '</tbody>'+
             '</table>'+
          
             
             
		'</div>');
		
		$.getJSON("./WGetAllNodistributionClientServlet",function(outjson){
				arrClientName=new Array();
				for ( var i = 0; i < outjson.ClientList.length; i++) {
					arrClientName.push(outjson.ClientList[i].ClientName);
				$(".thisgroup").append('<tr onclick=do_onclick(this) ondblclick=do_blcclick(this) onmousemove="changeTrColorone(this)" onmouseout="changeTrColortwo(this)"   onselectstart="return false;" style="height:20x;font-size:12px;font-weight: normal;background-color: rgb(232,251,250);-moz-user-select:none;" ClientId='+outjson.ClientList[i].ClientId+'>'+
						'<th style="font-weight: normal">'+outjson.ClientList[i].ClientId+'</th>'+
						'<th style="font-weight: normal">'+outjson.ClientList[i].ClientName+'</th>'+
					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientPhone+'</th>'+
					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientArea+'</th>'+
					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientAddress+'</th>'+
					    '<th style="font-weight: normal">'+outjson.ClientList[i].ClientCompany+'</th>'+
						'<th style="font-weight: normal; display:none;">'+outjson.ClientList[i].ClientId+'</th>'+
						'</tr>')
			}
		});	
		
		curtab=null;
}


function disbutclient() {


	var x=curtab;
	$(".shadow").show();
	$(".pagehead font").html("分配客户")
	$(".showpage").css("width","280px");
	$(".showpage").css("height","250px");
	$(".showpage").css("margin-left","600px");
	$(".pagemain").html("<div class=userset>"+
  				"<div><a>客户号:</a><input id=discliennumbertid disabled='disabled' type='text' style='margin-left: 29px;'value='"+$(x).find("th").eq(0).html()+"' /></div>"+
  				"<div><a>客户名:</a><input id=dicclientnameid disabled='disabled' type='text' style='margin-left: 29px;' value='"+$(x).find("th").eq(1).html()+"'/></div>"+
  				"<div><a>客户公司：</a><input disabled='disabled' type='text' value='"+$(x).find("th").eq(5).html()+"'/></div>"+
  				"<div><a>员工号:</a><select id=disclentid style='margin-left: 29px; width:160px;'></select></div>"+
  				"<div style='margin-left: 30px;margin-top: 10px;'><button onclick='disbutclientOK()' class=but1>确定</button><button class=but1 onclick='closepage()'>取消</button></div>"+
  				"</div>");
	
	$("#disclentid").append(str);
}
var iscycletypeplayday;
function disbutclientOK() {
	var  clientid=$("#discliennumbertid").val();
	var clentname=$("#dicclientnameid").val();
	var employeeid=$("#disclentid").val();
	var date=new Date();
	var pubdate=date.format("yyyy/MM/dd");
	if(employeeid==0){
		alert("请选择员工号");
	}else {
		closepage();
		$(".group-listclient").html("");

		$(".group-listclient").append('<div class="group-one" style="height:100%;overflow-x:hidden;background-color: rgb(248,254,254);"> '+
	  			'<div class="group-title" ><img src="./pic/back.png" style="height:30px;width:20px;cursor: pointer;margin-left:-1000px;" onclick="showvisitplan()"></img><div style="margin-top:-25px;"><font>拜访计划详情</font></div></div>'+
	  			'<div class="ground-one-left" style="float:left;width:50%;height:100%; 	">'+
   				'<div style="position:relative; height:500px;margin-left:100px;">'+
//   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;margin-top:10px;">员工帐号</font></div>'+	
//					'<div class="main-input" style="margin-left:10px;width:289px;padding-top:7px;margin-top:10px;padding-top:10px;" >'+visitplanid+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">客户名</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+clentname+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">发布时间</font></div>'+	
					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;" >'+pubdate+'</div>'+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">开始时间</font></div>'+	
					"<input id=visitstartid  onclick=SelectDate(this,\'yyyy/MM/dd\') class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入开始日期' ></input>"+
					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">截止时间</font></div>'+	
					"<input id=visitendid onclick=SelectDate(this,\'yyyy/MM/dd\') class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入截止日期' ></input>"+
//					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">拜访状态</font></div>'+	
//					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+state+'</div>'+
//					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">出差绑定号</font></div>'+	
//					'<div  class="main-input" style="margin-left:10px; width:289px;margin-top:10px;padding-top:10px;">'+bbandstate+'</div>'+
					
					'<div style="margin-left:53px; margin-top:50px;"><div class="usebut" onclick="disclietvisitOK()" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;">提交</div><div class="usebut" style="font-size: 14px;width: 76px;height: 35px;line-height: 33px;margin-left:50px;" onclick="updateclientclean()">取消</div></div>'+
				'</div>'+
   			'</div>'+
   			
   			'<div class="ground-one-right" style="float:left;width:49%;height:100%;border-left:2px;border-left-color:black;border-left-style:dashed;">'+
   			'<div style="position:relative; height:500px;margin-left:100px;">'+
   				'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环拜访</font></div>'+	
   				'<div id=cyclebox style="margin-top:10px;width:289px;height:50px;">'+
   					'<div id="cycleboxone" class="visiitbox" style="margin-right:10px;" onclick="iscycle(1)">循环</div>'+
   					'<div id="cycleboxtwo" class="visiitbox" onclick="iscycle(2)">不循环</div>'+
   				'</div>'+
   				
   				'<div style="display: block;">'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环类型</font></div>'+	
   					'<div style="height:30px; width:300px;margin-top:10px;">'+
   						'<div id=cycletypebox  style="margin-top:10px;width:389px;height:50px;">'+
   							'<div class="visiitbox" onclick="iscycletype(0)" style="margin-right:10px;">每日</div>'+
   							'<div class="visiitbox" onclick="iscycletype(1)" style="margin-right:10px;">每周</div>'+
   							'<div class="visiitbox" onclick="iscycletype(2)" style="margin-right:10px;">每月</div>'+
   							'<div class="visiitbox" onclick="iscycletype(3)" style="margin-right:10px;">自定义</div>'+
   						'</div>'+
   					'</div>'+
   					'<div style="margin-top:50px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">循环点</font></div>'+	
   					"<div id=visitdian style='margin-top:10px;width:300px;height:50px;'><select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select></div>"+
   					'<div style="margin-top:10px;"><font  style="font-size:15px;font-weight: bold;margin-left:10px;">限定天数</font></div>'+	
   					"<input id=visitponitid class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入限定天数' ></input>"+
				'</div>'+
   			'</div>'+
	  		'</div>');
	
		
		iscycletypeplayday=$("#visitponitid").val()

		
	}
}


var cycle=1;
function iscycle(num) {
	
	if(num==1){
		$("#cycleboxone").attr("class","visiitboxtwo");
		$("#cycleboxtwo").attr("class","visiitbox");
	}else if(num==2){
		$("#cycleboxone").attr("class","visiitbox");
		$("#cycleboxtwo").attr("class","visiitboxtwo");
		for(var i=0;i<4;i++){
			$("#cycletypebox").find("div").eq(i).attr("class","visiitbox");
		}
		$("#visitdian").html("");
		$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
	}
	cycle=num-1;
	
}
var cycletype=0;
function iscycletype(num) {
	if(cycle==1){
		alert("请选择循环拜访为循环")
	}else if(cycle==0){
		for(var i=0;i<4;i++){
			$("#cycletypebox").find("div").eq(i).attr("class","visiitbox");
		}
		$("#cycletypebox").find("div").eq(num).attr("class","visiitboxtwo");
		
		cycletype=num;
		if(cycletype==0){
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append("<option  value ='1'>1</option>");
		}else if(cycletype==1){
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append("<option  value ='1'>1</option><option  value ='2'>2</option><option  value ='3'>3</option><option  value ='4'>4</option><option  value ='5'>5</option><option  value ='6'>6</option><option  value ='7'>7</option>");
			$("#visitplanse").find("option").eq(iscycletypeplayday-1).attr("selected","selected");
		}else if(cycletype==2){
			var str;
			for(var i=1;i<=31;i++){
				str+="<option  value ='"+i+"'>"+i+"</option>";
			}
			$("#visitdian").html("");
			$("#visitdian").append("<select id=visitplanse class=useselect  style='cursor:pointer; position: relative;margin-left:4px;width:240px;height:50px;font-size: 15px;'></select>");
			$("#visitplanse").html("");
			$("#visitplanse").append(str);
			$("#visitplanse").find("option").eq(iscycletypeplayday-1).attr("selected","selected");
		}else if(cycletype==3){
			$("#visitdian").html("");
			$("#visitdian").append(	"<input  class='main-input' style='margin-left:10px; width:309px;margin-top:10px;padding-top:-10px;height:50px;padding-top:-10px; font-size:18px;' placeholder='请输入限定天数' ></input>");
		}
		
		if(cycletype==0){
			$("#visitponitid").val(1);
		}
		
	}
}


function disclietvisitOK() {
	var  clientid=$("#discliennumbertid").val();
	var employeeid=$("#disclentid").val();
	var date=new Date();
	var pubdate=date.format("yyyy/MM/dd");
//	cycle
//	cycletype
	var starttime=$("#visitstartid").val();
	var endtime=$("#visitendid").val();
	
	
	var cyclepoint;
	var playdays;
	
	if(cycle==0){
		cyclepoint=$("#visitplanse").val();
		playdays=$("#visitponitid").val();
	}else if(cycle==1){
		cyclepoint=0;
		playdays=0;
	}
	
	//两个的判断写反了，就这个改改好了
	if(cycle==0){
		cycle=1;
	}else if(cycle==1){
		cucle=0;
	}
	
//	alert(cycle)
	
//	alert(clientid+"---"+employeeid+"----"+pubdate+"---"+starttime+"---"+endtime+"---"+cycle+"---"+cycletype+"---"+cyclepoint+"---"+playdays);
	$.getJSON("./WDistributionClientServlet",{EmployeeId:employeeid,
		ClientId:clientid,VisitPlanPubdate:pubdate,VisitPlanStartTime:starttime,VisitPlanEndTime:endtime,
		VisitPlanState:0,VisitPlanCycle:cycle,VisitPlanCycleType:cycletype,VisitPlanCycleNumber:cyclepoint,VisitPlanDays:playdays},function(outjson){
 		if(outjson.check){
 			alert("成功！");
 		}else {
 			alert("失败！");
 		}
 		var seleed=$("#selectID").val();
 		if(seleed==1){
// 			showmainclienttitle();
 			shownodistribution();
 		}else if(seleed==2){
 			showdistribution();
 		}
 		var op=parseInt(seleed);
 		$("#selectID").find("option").eq(op-1).attr("selected","selected");
 		
 	})
}

function tocheckclient() {
//	alert(1);
}
















function changeTrColorone(obj){    

	    var _table=obj.parentNode;

	    for (var i=0;i<_table.rows.length;i++){


	    }    
	    var x=69;
	    var y=205;
	    var z=195;
	    obj.style.backgroundColor=setColor(x, y, z);

	}


var curtab = null;
function do_onclick(tab){
	 if(curtab==tab){
		tab.style.backgroundColor =setColor(232,251,250);
		curtab = null;
	 }else{
		 var x=69;
		 var y=205;
  	     var z=195;
    	 tab.style.backgroundColor = setColor(x, y, z);
    	 
         if(curtab != null) curtab.style.backgroundColor = setColor(232,251,250);
         curtab = tab;
	 }
	
}

function do_blcclick(tab) {
	 tab.style.backgroundColor = setColor(255, 255, 255);
	 curtab=null;
}

function setColor(x,y,z){
		return "#"  + x.toString(16) + y.toString(16) + z.toString(16);
		}
function changeTrColortwo(obj) {
	  var _table=obj.parentNode;

    for (var i=0;i<_table.rows.length;i++){

        _table.rows[i].style.backgroundColor=setColor(232,251,250);

    }    
	var x=69;
    var y=205;
    var z=195;
    obj.style.backgroundColor=setColor(232,251,250);
  if(curtab != null) curtab.style.backgroundColor = setColor(x, y, z);
}

function withmapno() {
 	var map = new BMap.Map("updateclientcontainer"); // 创建地图实例  
 	map.centerAndZoom(new BMap.Point(121.56, 29.86), 10);  // 初始化地图,设置中心点坐标和地图级别
 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
 	map.setCurrentCity("宁波");          // 设置地图显示的城市 此项是必须设置的
 	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
 	
 	
 	
 	
 	var geoc = new BMap.Geocoder();    
 	map.addEventListener("click", function(e){      
 		map.clearOverlays();        
 		var pt = e.point;
 		
 		
 		geoc.getLocation(pt, function(rs){
 			var addComp = rs.addressComponents;
 			var address=addComp.province  + addComp.city  + addComp.district + addComp.street  + addComp.streetNumber;
 			
 			document.getElementById("updateclientarea").value=addComp.province;
 			document.getElementById("updateclientaddress").value=address; 
 			
 			var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
 			map.addOverlay(marker);    
 		});     
 	});
 	
}



function withmap(address,area) {
 	var map = new BMap.Map("updateclientcontainer"); // 创建地图实例  
 	map.centerAndZoom(new BMap.Point(121.56, 29.86), 10);  // 初始化地图,设置中心点坐标和地图级别
 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
 	map.setCurrentCity("宁波");          // 设置地图显示的城市 此项是必须设置的
 	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
 	
 	
 	var myGeo = new BMap.Geocoder();
 	// 将地址解析结果显示在地图上,并调整地图视野
 	myGeo.getPoint(address, function(point){
 		if (point) {
 			map.centerAndZoom(point, 16);
 			map.addOverlay(new BMap.Marker(point));
 		}else{
 			alert("您选择地址没有解析到结果!");
 		}
 	}, area);
 	
 	var geoc = new BMap.Geocoder();    
 	map.addEventListener("click", function(e){      
 		map.clearOverlays();        
 		var pt = e.point;
 		
 		
 		geoc.getLocation(pt, function(rs){
 			var addComp = rs.addressComponents;
 			var address=addComp.province  + addComp.city  + addComp.district + addComp.street  + addComp.streetNumber;
 			
 			document.getElementById("updateclientarea").value=addComp.province;
 			document.getElementById("updateclientaddress").value=address; 
 			
 			var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
 			map.addOverlay(marker);    
 		});     
 	});
 	
 	
 }
 
 
 function withmapsee(address,area) {
	 	var map = new BMap.Map("updateclientcontainer"); // 创建地图实例  
	 	map.centerAndZoom(new BMap.Point(121.56, 29.86), 10);  // 初始化地图,设置中心点坐标和地图级别
	 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	 	map.setCurrentCity("宁波");          // 设置地图显示的城市 此项是必须设置的
	 	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	 	
	 	
	 	var myGeo = new BMap.Geocoder();
	 	// 将地址解析结果显示在地图上,并调整地图视野
	 	myGeo.getPoint(address, function(point){
	 		if (point) {
	 			map.centerAndZoom(point, 16);
	 			map.addOverlay(new BMap.Marker(point));
	 		}else{
	 			alert("您选择地址没有解析到结果!");
	 		}
	 	}, area);
	 	
	 	
}




 Date.prototype.format = function (format) {
            var args = {
                "M+": this.getMonth() + 1,
                "d+": this.getDate(),
                "h+": this.getHours(),
                "m+": this.getMinutes(),
                "s+": this.getSeconds(),
                "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
                "S": this.getMilliseconds()
            };
            if (/(y+)/.test(format))
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var i in args) {
                var n = args[i];
                if (new RegExp("(" + i + ")").test(format))
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
            }
            return format;
        };

