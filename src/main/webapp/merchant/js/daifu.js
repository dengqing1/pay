
/**
 * 首页
 */
function firstPage(){
	search(1);
}

/**
 * 上一页
 */
function previousPage(){
	var pageNum = $("#pageNum").val();//当前页
	if(pageNum == 1){
		alert("当前为第一页！")
		return 
	}else{
		$("#pageNum").val(parseInt(pageNum) - 1);
		search(parseInt(pageNum) - 1)
	}
}

/**
 * 尾页
 */
function lastPage(pageNum){
	search(pageNum)
}

function nextPage(){
	var pageNum = $("#pageNum").val();//当前页
	var pages = $("#pages").val();//总页数
	if(pageNum == pages){
		alert("当前为最后一页！")
		return 
	}else{
		search(parseInt(pageNum) + 1)
	}
}

function search(pageNum){
	if(pageNum == ''){
		pageNum = 1;
	}
	$.ajax({
		type: "post",
		//url: "./daifu/search.do",
		url: "./daifu.do",
        data: {
			"pageNum": pageNum,
			"pageSize": 10
        },
        dataTpye:"json",
        success: function (r) {debugger;
        	var arr = r.rows;
        	var html;
        	var list = $("#list").empty();
        	$.each(arr,function(index,item){
        		alert("1111");
        		debugger;
        		html += '<tr>'
        		html += '<td>'+arr[index].gatherMerOrderId+'</td>'	
    			html += '<td>'+arr[index].txnAmts+'</td>'	
    			html += '<td>'+arr[index].gatherAccno+'</td>'	
    			
    			
    			var gatherPpflag = arr[index].gatherPpflag;
        		if(gatherPpflag == '00'){
        			html += '<td>对公</td>'	
        		}else if(gatherPpflag == '01'){
        			html += '<td>对私</td>'
        		}else{
        			html += '<td>其他</td>'
        		}
        		
        		html += '<td>'+arr[index].gatherCustomerName+'</td>'	    			
        		html += '<td>'+arr[index].bankName+'</td>'
        		html += '<td>'+arr[index].gatherTime+'</td>'
        		
        		var gatherState = arr[index].gatherState;
        		if(gatherState == '1'){
        			html += '<td class="table-grid-status table-grid-gray-status">审核中</td>'	
        		}else if(gatherState == '2'){
        			html += '<td class="table-grid-status table-grid-gray-status">审核通过</td>'
        		}else if(gatherState == '3'){
        			html += '<td class="table-grid-status table-grid-light-status">审核失败</td>'
        		}
				html += '</tr>' 
        	});
        	list.append(html);
        	if(arr.length == 0){
            	$("#number").html('当前页 1/1 总页数');
            	$("#last-page").addClass("gray-icon-page");
            	$("#last-page").addClass("gray-icon-page");
            	$("#search").val(1);
            	$("#pageNum").val(1);
            	$("#pages").val(1);
            	return
        	}else{
	        	var pages = r.pages;
	        	if(pages == 0){
	        		pages = 1;
	        	}
	        	$("#number").html('当前页 '+r.pageNum+'/'+pages+' 总页数');
	        	if(r.pageNum == pages){
	        		if(r.pageNum == 1){
	        			$("#first-page").addClass("gray-icon-page");
	        			$("#previous-page").addClass("gray-icon-page");
	        			$("#last-page").addClass("gray-icon-page");
	        			$("#next-page").addClass("gray-icon-page");
	        		}else{
	        			$("#first-page").removeClass("gray-icon-page");
	        			$("#previous-page").removeClass("gray-icon-page");
	        			
	        			$("#last-page").addClass("gray-icon-page");
	        			$("#next-page").addClass("gray-icon-page");
	        		}
	        	}else if(r.pageNum == 1){
	        		$("#first-page").addClass("gray-icon-page");
        			$("#previous-page").addClass("gray-icon-page");
        			
        			$("#last-page").removeClass("gray-icon-page");
        			$("#next-page").removeClass("gray-icon-page");
	        	}else{
	        		$("#first-page").removeClass("gray-icon-page");
        			$("#previous-page").removeClass("gray-icon-page");
	        		$("#last-page").removeClass("gray-icon-page");
        			$("#next-page").removeClass("gray-icon-page");
	        	}
        	}
        	
        	$("#search").val(pageNum);
        	$("#pageNum").val(pageNum);
        }
    });
}


//$(function () {
//	$(".search-btn").click(function(){
//	});
//});



function addShow(){
	var emailCode = $("input[name=emailCode]").val();
	var phoneCode = $("input[name=phoneCode]").val();
	
   if((emailCode == '' || emailCode == null) && (phoneCode == '' || phoneCode == null)){
       alert('请输入验证码！');
       return;
   }
   
   $.ajax({
       url:"./daifu/code.do",
       type:"post",
       data:{
    	   "emailCode" : emailCode,
    	   "phoneCode" : phoneCode
       },
       success:function(data){
       	 if (data.code == 0) {
       		 $('#myModal').modal();
            } else {
               alert(data.msg);
            }
       },
       error:function(e){
           alert("错误！！");
       	console.log(e)
       }
   });        
   

}



function addDaifu(){
	
	
	
    var form = new FormData(document.getElementById("form2"));
    $.ajax({
        url:"./daifu/addDaifu.do",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(data){
        	 if (data.code == 0) {
                alert("信息采集成功！");
                window.location.href = "./daifu.html";
             } else {
                alert(data.msg);
             }
        },
        error:function(e){
            alert("错误！！");
        	console.log(e)
        }
    });        
}

function downloads(id,merchId){
	
	location.href='./daifu/download.do?id='+id+"&merchId="+merchId;
	
	
}