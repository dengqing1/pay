
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


$(function () {
	$(".search-btn").click(function(){
		search(1);
	});
	
	$(".export-btn").click(function(){
		exportOrder();
	});
});



function search(pageNum){
	if(pageNum == ''){
		pageNum = 1;
	}
	
	var date = $("#daterangepicker").val().split("/");
	
	var status = $("#status").val();
	if(status == 0){
		status = "";
	}
	
	var txnAmts = $("#txnAmts").val();
	if(txnAmts != ''){
		txnAmts = parseFloat(txnAmts)*100;
	}
	
	var inFee = $("#inFee").val();
	if(inFee != ''){
		inFee = parseFloat(inFee)*100;
	}
	
	
	$.ajax({
		type: "post",
		url: "./withdrawHistory/search.do",
		data: {
			"pageNum": pageNum,
			"pageSize": 10,
			"createAtTimeBegin" : date[0],
			"createAtTimeEnd" : date[1],
			"txnAmts" : txnAmts,
			"inFee" : inFee,
			"customerNm" : $("#customerNm").val(),
			"status" : status,
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
        		html += '<td>'+arr[index].createTime+'</td>'	
    			html += '<td>'+arr[index].orderid+'</td>'	
    			html += '<td>'+arr[index].merchantid+'</td>'	
    			html += '<td>'+arr[index].msName+'</td>'	
    			html += '<td>'+arr[index].merorderid+'</td>'	
    			html += '<td>'+arr[index].txnAmts+'</td>'	
    			html += '<td>'+arr[index].inFee+'</td>'	
    			
    			html += '<td>'+arr[index].accno+'</td>'	
    			html += '<td>'+arr[index].customerNm+'</td>'	
    			
    			var status = arr[index].status;
        		if(status == 1000){
        			html += '<td><span class="table-grid-status table-grid-gray-status">初始状态</span></td>'	
        		}else if(status == 1001){
        			html += '<td><span class="table-grid-status table-grid-light-status">交易成功</span></td>'
        		}else if(status == 1002){
        			html += '<td><span class="table-grid-status table-grid-gray-status">交易失败</span></td>'
        		}else if(status == 1111){
        			html += '<td><span class="table-grid-status table-grid-gray-status">进行中</span></td>'
        		}
        		
    			html += '<td>'+arr[index].statusdesc+'</td>'	
    			
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
            	return;
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


function exportOrder(){
	
	var date = $("#daterangepicker").val().split("/");
	
	var status = $("#status").val();
	if(status == 0){
		status = "";
	}
	
	var txnAmts = $("#txnAmts").val();
	if(txnAmts != ''){
		txnAmts = parseFloat(txnAmts)*100;
	}
	
	var inFee = $("#inFee").val();
	if(inFee != ''){
		inFee = parseFloat(inFee)*100;
	}
	
	
	var url = "./withdrawHistory/export.do?" +
			"createAtTimeBegin="+date[0]+"&" +
			"createAtTimeEnd="+date[1]+"&" +
			"txnAmts="+txnAmts+"&" +
			"inFee="+inFee+"&" +
			"customerNm="+$("#customerNm").val()+"&" +
			"status="+status+"&" +
			"merOrderId="+$("#merOrderId").val()+"&" +
			"orderid="+$("#orderid").val();    
	
	 location.href=url;
}
