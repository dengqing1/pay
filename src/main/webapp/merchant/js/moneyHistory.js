
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
		$("#pageNum").val(parseInt(pageNum) + 1);
		search(parseInt(pageNum) + 1)
	}
}

$(function () {
	$(".search-btn").click(function(){
		search(1);
	});
});



function search(pageNum){
	if(pageNum == ''){
		pageNum = 1;
	}
	
	var date = $("#daterangepicker").val().split("/");
	var gateway = $("#gateway").val();
	if(gateway == 0){
		gateway = "";
	}
	var bankid = $("#bankid").val();
	if(bankid == 0){
		bankid = "";
	}
	
	var txnAmts = $("#txnAmts").val();
	if(txnAmts != ''){
		txnAmts = parseFloat(txnAmts)*100;
	}
	$.ajax({
		type: "post",
		url: "./moneyHistory/search.do",
        data: {
			"pageNum": pageNum,
			"pageSize": 10,
			"createAtTimeBegin" : date[0],
			"createAtTimeEnd" : date[1],
			"gateway" : gateway,
			"bankid" : bankid,
			"txnAmts" : txnAmts,
			"merOrderId" : $("#merOrderId").val(),
			"orderid" : $("#orderid").val()
        },
        dataTpye:"json",
        success: function (r) {
        	var arr = r.rows;
        	var html;
        	var list = $("#list").empty();
        	$.each(arr,function(index,item){
        		html += '<tr>'
    			html += '<td>'+arr[index].orderid+'</td>'	
    			html += '<td>'+arr[index].merchantid+'</td>'	
    			html += '<td>'+arr[index].msName+'</td>'	
    			html += '<td>'+arr[index].merorderid+'</td>'	
    			html += '<td>'+arr[index].txnAmts+'</td>'	
    			html += '<td>'+arr[index].inFee+'</td>'	
    			html += '<td>'+arr[index].types+'</td>'	
    			html += '<td>'+arr[index].createTime+'</td>'	
				html += '</tr>' 
        	});
        	list.append(html);
        	if(arr.length == 0){
            	$("#number").html('当前页 1/1 总页数');
            	$("#last-page").addClass("gray-icon-page");
            	$("#next-page").addClass("gray-icon-page");
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



