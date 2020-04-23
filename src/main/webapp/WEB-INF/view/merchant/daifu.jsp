<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎 - 支付平台 - ACE 3</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="./merchant/css/normalize.css" rel="stylesheet">
	<link href="./merchant/css/header.css" rel="stylesheet">
	<link href="./merchant/css/page.css" rel="stylesheet">
	<link href="./merchant/css/liMarquee.css" rel="stylesheet">
	
	<link href="./merchant/css/batchPay.css" rel="stylesheet">
	
	<script src="./merchant/js/jquery-1.8.3.min.js"></script>
	<script src="./merchant/js/ckeditor.js"></script>
	
	<link href="//cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.css" rel="stylesheet">
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/moment.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.js"></script>
	
	<script src="./merchant/js/bootstrap.min.js"></script>
	<script src="./merchant/js/bootstrap-datepicker.js"></script>
	<script src="./merchant/js/daifu.js"></script>
	<script src="./merchant/js/logout.js"></script>
	
	
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	
</head>
	
	
	
	
<body>
<%@ include file="../merchant/left.jsp" %>

	<div class="main-right">
	<div class="right-container flex flex-align-center">
		<p class="right-header-title">批量代付</p>
		<p class="right-header-separate"></p>
		<p class="right-header-title">BATCH PAY</p>
	</div>
	<div class="right-separate"></div>
	<div class="options-container">
		<div class="options-container-content">
			<form action="./daifu/upload" enctype="multipart/form-data" method="post" name="uploadfile" id="form1">
				<div class="options-th-container flex flex-align-center">
					<p class="options-title right-position"></p>
				</div>
				 <div class="options-th-container flex control-group">
                    <label class="control-label" for="inputEmail">上传EXCLE文件</label>
                    <div class="controls">
					    <input type="file" name="upfile" />
                    </div>
				</div> 
				<input  type="hidden"  value="${user.merchantid}" name="merchantid" />

				<div class="options-th-container flex control-group">
                    <label class="control-label" for="inputEmail">邮箱帐号</label>
                    <div class="controls">
					    <input readonly="readonly" type="text" class="right-position options-input options-input-trade-money" value="${user.email}" id="username" name="username" />
                    </div>
				</div>
                <div class="options-th-container flex control-group">
                    <label class="control-label" for="inputEmail">验证方式</label>
                    <div class="controls">
                        <label class="radio">
                            <input id="captcha_email" type="radio" name="captcha_type" value="0" checked />&nbsp;邮箱验证 &nbsp;&nbsp;
                        </label>
                        <label class="radio">
                            <input id="captcha_phone" type="radio" name="captcha_type" value="1" />&nbsp;手机验证
                        </label>
                    </div>
                </div>
				<!-- <div id="email-captcha-div" class="options-th-container flex control-group message-show">
                    <label class="control-label" for="inputEmail">邮箱验证</label>
                    <div class="controls">
                        <input type="text" class="left-position options-input options-input-order" value="" name="emailCode" placeholder="请输入邮箱验证码" />
                    </div>
                 	 <label>
                        <a class="captcha-btn trade-btn" id="send_email_captcha" href="javascript:void(0);">发送验证码</a>
                        <a class="captcha-btn trade-btn" style="display:none; width: 138px;" id="send_email_tip"></a>
                    </label> 
				</div>
                <div id="phone-captcha-div" class="options-th-container flex control-group message-hide">
                    <label class="control-label" for="inputEmail">手机验证</label>
                    <div class="controls">
                        <input type="text" class="left-position options-input options-input-order" value="" name="phoneCode" placeholder="请输入手机验证码" />
                    </div>
                    <label>
                        <a class="captcha-btn trade-btn" id="send_phone_captcha" href="javascript:void(0);">发送验证码</a>
                        <a class="captcha-btn trade-btn" style="display:none; width: 138px;" id="send_phone_tip"></a>
                    </label>
                </div> -->
				<div class="options-th-container flex control-group">
                    <div class="controls">
					    <input type="text" value="确定上传" class="search-btn trade-btn"  id="aa"  style="margin: 10px 0px 0px 355px"/>
					    <a class="search-btn trade-btn"  onclick="show()" style="margin: 10px 0px 0px 355px">信息采集</a>
<!-- 					    <a class="search-btn trade-btn" href="javascript:void(0);"  onclick="addShow()" style="margin: 10px 0px 0px 355px">信息采集</a> -->
<!-- 					    <a class="download-btn trade-btn" href="javascript:void(0);"   onclick="location.href='./daifu/download?fileName=c2FtcGxlLnhsc3g=';return false;">模板下载</a> -->
				    </div>
                </div>
			</form>
		</div>
	</div>

	<div class="content-container">
		<div class="top-container"></div>
		<div class="ace-widget-content">
			<table id="user-grid" class="table table-striped ace-grid" style="min-width: 765px;">
				<thead>
					<tr>
					<th><span>商户订单号</span></th>
					<th><span>交易金额(元)</span></th>
					<th><span>银行卡号</span></th>
					<th><span>对公对私标志</span></th>
					<th><span>持卡人姓名</span></th>
					<th><span>开户银行</span></th>
					<th><span>日期</span></th>
					<th><span>状态</span></th>
					</tr>
				</thead>
				<tbody id="list">
					<c:forEach items="${pageInfo.list}" var="obj" varStatus="index"  >	
						<tr>
							<td>${obj.gatherMerOrderId}</td>
							<td>${obj.txnAmts}</td>
							<td>${obj.gatherAccno}</td>
							<c:choose>
						      <c:when test="${obj.gatherPpflag == 00 }">
						      	<td>对公</td>
						      </c:when>
						      <c:when test="${obj.gatherPpflag == 01 }">
								<td>对私</td>
						      </c:when>
						      <c:otherwise>
								<td>其他</td>
						      </c:otherwise>
							</c:choose> 
							<td>${obj.gatherCustomerName}</td>
							<td>${obj.bankName}</td>
							<td>${obj.gatherTime}</td>
							<c:choose>
						      <c:when test="${obj.gatherState == 1 }">
						      	<td><span class="table-grid-status table-grid-gray-status">审核中</span></td>
						      </c:when>
						      <c:when test="${obj.gatherState == 2 }">
								<td><span class="table-grid-status table-grid-gray-status">审核通过</span></td>
						      </c:when>
						      <c:when test="${obj.gatherState == 3 }">
								<td><span class="table-grid-status table-grid-light-status">审核失败</span></td>
						      </c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		
		
			<div class="page-container flex flex-align-center">
				<p class="page-desc" id="number">当前页${pageInfo.pageNum }/${pageInfo.pages } 总页数 </p>
				
				<c:choose>
			      <c:when test="${pageInfo.hasPreviousPage}">
			    	  <a id="first-page"  class="first-page" href="javascript:void(0);" onclick="page(1)"></a>
					  <a id="previous-page"  class="previous-page"  href="javascript:void(0);" onclick="page(${pageInfo.pageNum - 1})"></a>
			      </c:when>
			      <c:otherwise>
					<a id="first-page"  class="first-page gray-icon-page" href="javascript:void(0);" onclick="page(1)"></a>
					<a id="previous-page"  class="previous-page gray-icon-page"  href="javascript:void(0);" onclick="page(${pageInfo.pageNum - 1})"></a>
			      </c:otherwise>
			</c:choose> 
				
				
					<input class="page-num" id="search"  name="page" type="text" value='${pageInfo.pageNum }' />
			<c:choose>
			      <c:when test="${pageInfo.hasNextPage}">
			      	<a id="last-page" class="last-page" href="javascript:void(0);" onclick="page(${pageInfo.pageNum + 1})"></a>
					<a id="next-page"  class="next-page" href="javascript:void(0);" onclick="page(${pageInfo.pages})"></a>
			      </c:when>
			      <c:otherwise>
						<a id="last-page" class="last-page gray-icon-page" href="javascript:void(0);" onclick="page(${pageInfo.pageNum + 1})"></a>
						<a id="next-page"  class="next-page gray-icon-page" href="javascript:void(0);" onclick="page(${pageInfo.pages})"></a>
			      </c:otherwise>
			</c:choose> 
			
			</div>
		
		
		
		
		<!--  	<div class="page-container flex flex-align-center">
				<p class="page-desc" id="number">当前页 ${list.pageNum}/${list.pages} 总页数 </p>
				<a id="first-page"  class="first-page gray-icon-page" href="javascript:void(0);" onclick="firstPage()"></a>
				<a id="previous-page"  class="previous-page gray-icon-page"  href="javascript:void(0);" onclick="previousPage()"></a>
				<form id='form1' action='/pay/money-history?page=1' method='get'>
					<input class="page-num" id="search"  name="page" type="text" value='${list.pageNum}' />
				</form>
			<c:choose>
			      <c:when test="${list.pages == 1}">
			      	<a id="last-page" class="last-page gray-icon-page" href="javascript:void(0);" onclick="nextPage()"></a>
					<a id="next-page"  class="next-page gray-icon-page" href="javascript:void(0);" onclick="lastPage(${list.pages})"></a>
			      </c:when>
			      <c:otherwise>
						<a id="last-page" class="last-page" href="javascript:void(0);" onclick="nextPage()"></a>
						<a id="next-page"  class="next-page" href="javascript:void(0);" onclick="lastPage(${list.pages})"></a>
			      </c:otherwise>
			</c:choose> 
			
			</div>
			<input id="pageNum" type="hidden" value='${list.pageNum}' />
			<input id="pages" type="hidden" value='${list.pages}' />
			-->
			
			
			</div>
    </div>
    
   <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					信息采集
				</h4>
			</div>
			<div class="modal-body">
				
			<div id="main" role="main">
	            <form id="form2"  action="#">
					<p>交易金额(单位为分):</p>
	                <input type="number" id="" name="gatherTxnAmt" value=""/>
					<p>银行卡号:</p>
	                <input type="number" id="" name="gatherAccno" value=""/>
	                <p>对公对私标志(00-对公,01-对私):</p>
	                <input type="number" id="" name="gatherPpflag" value=""/>
	                <p>持卡人姓名:</p>
	                <input type="text" id="" name="gatherCustomerName" value=""/>
	                <p>开户银行:</p>
<!-- 	                <input type="text" id="" name="bankName" value=""/> -->
	                
	                <select  name="bankName" >
	                    <option value="0">所有</option>
	                   	<c:forEach items="${banks}" var="obj"  >	
	                     	  <option value="${obj.bankName}">${obj.bankName}</option>
						</c:forEach>
					</select>
	                <p>手机号:</p>
	                <input type="number" id="" name="gatherPhoneNo" value=""/>
	                
	            </form>
	        </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" 
							data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="addDaifu()">
						提交
					</button>
				</div>
		</div>
	</div>
</div>
</div>  
    
    <script>

    
    
    function page(page){
   	 location.href="daifu.do?page="+page;
      }
      
    
    function show(){
    	 $('#myModal').modal();
    }
    
	$(function () {
		var emailEle = $("input[name=username]");

        $("#captcha_email").click(function(){
            $("#email-captcha-div").removeClass('message-hide').addClass('message-show');
            $("#phone-captcha-div").removeClass('message-show').addClass('message-hide');
        });

        $("#captcha_phone").click(function(){
            $("#email-captcha-div").removeClass('message-show').addClass('message-hide');
            $("#phone-captcha-div").removeClass('message-hide').addClass('message-show');
        });

        $('#send_email_captcha').click(function () {
            var email = $("input[name=username]").val();
            if (email == '' || email == null) {
                $("#email_tip").html("请输入邮箱").show();
                return;
            }
            $.ajax({
                type: "POST",
                url: "./login/sedEmail.do",
                data: {
                	"email" : email	
                },success: function (data) {
                    alert(data.msg);
                    location.replace(location);
                }, error: function (e) {
                   alert("请求出错！");
                   location.replace(location);
                }
            });
        });
	});

    $('#send_phone_captcha').click(function () {
        var email = $("input[name=username]").val();
        if (email == '' || email == null) {
            $("#email_tip").html("请输入邮箱").show();
            return;
        }
        $.ajax({
            type: "POST",
            url: "./login/sedPhone.do",
            data: "email=" + email,
            success: function (data) {
                 alert(data.msg);
                 location.replace(location);
            },
            error: function (e) {
                  alert("请求出错！");
                  location.replace(location);
            }
        });
    });
    
    
    
    $('#aa').click(function () {
    	var form = new FormData(document.getElementById("form1"));
        $.ajax({
            type: "POST",
            url: "./daifu/upload.do",
            data: form,
            /**
             *必须false才会自动加上正确的Content-Type
             */
             contentType: false,
             /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
             processData: false,
            success: function (data) {
                 alert(data.msg);
                 location.replace(location);
            },
            error: function (e) {
                  alert("请求出错！");
                  location.replace(location);
            }
        });
    });
    
</script>
    

    
</div>
</body>
</html>