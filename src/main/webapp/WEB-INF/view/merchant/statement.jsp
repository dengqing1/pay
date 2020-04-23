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
	<script src="./merchant/js/logout.js"></script>
	
	
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	
</head>
	
	
	
	
<body>
	<div class="top-header">
	<div class="header-container flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">丰迈</label>
<!-- 			<label class="beta-label">BETA</label> -->
		</div>
		<div class="header-right flex flex-align-center">
			<p class="header-right-welcome">欢迎&nbsp;${user.name}</p>
			<a class="header-right-logout" href="javascript:void(0);">退出</a>
		</div>
	</div>
</div>
<div class="main">
    <div class="flex">
		<div class="main-logo">
			<label class="header-title">支付平台</label>
			<label class="header-english">PAYMENT PLATFORM</label>
		</div>
		<div class="main-logo">
		</div>
    </div>
	<div class="flex-v">
        <label class="separate-line"></label>
	</div>
	<div class="main-content flex">

    		<div class="main-left" style="height: 594px;">

         	<div class="first-header flex flex-align-center">
	            <div class="first-header-dot"></div>
	            <a class="first-header-text" href="javascript:void(0);">欢迎</a>
        	</div>
        	
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./merchant.html">欢迎</a>
          </div>
          
         <div class="second-header flex flex-align-center ">
	          <div class="second-header-dot"></div>
	          <a class="second-header-text" href="./user.html">修改密码</a>
         </div>
              <div class="first-header flex flex-align-center">
         <div class="first-header-dot"></div>
       		<a class="first-header-text" href="javascript:void(0);">账务</a>
    	 </div>
    	 
    	 
         <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               	<a class="second-header-text" href="./moneyHistory.html">钱流水</a>
      	 </div>
      	 
      	 
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./orderHistory.html">交易查询</a>
          </div>
          
          
          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./withdrawHistory.html">账务查询</a>
          </div>
          
          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./balanceLogs.html">调账查询</a>
          </div>
          
          
           <div class="second-header flex flex-align-center ">
               
          <div class="first-header-dot"></div>
            <a class="first-header-text" href="javascript:void(0);">代付</a>
       	  </div>
       	  
       	  
           <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifu.html">批量代付</a>
           </div>
           
 			<div class="second-header flex flex-align-center second-header-select  ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifuStatement.html">账目查询</a>
            </div> 
        </div>
	
	
	<div class="main-right">
	<div class="right-container flex flex-align-center">
		<p class="right-header-title">账目查询 </p>
		<p class="right-header-separate"></p>
		<p class="right-header-title">BATCH PAY</p>
	</div>
	<div class="right-separate"></div>

	<div class="content-container">
		<div class="top-container"></div>
		<div class="ace-widget-content">
			<table id="user-grid" class="table table-striped ace-grid" style="min-width: 765px;">
				<thead>
					<tr>
					<th><span>商户号</span></th>
					<th><span>日期</span></th>
					<th><span>单日入金</span></th>
					<th><span>单日出金</span></th>
					<th><span>待支付</span></th>
					<th><span>支付失败</span></th>
					</tr>
				</thead>
				<tbody id="list">
					 <c:forEach items="${pageInfo.list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.staMerchantId}</td>
		                     <td align="center">${l.date}</td>
		                     <td align="center">${l.golden}</td>
		                     <td align="center">${l.withdraw}</td>
		                     <td align="center">${l.unpaid}</td>
		                     <td align="center">${l.nothing}</td>
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
			<input id="pageNum" type="hidden" value='${pageInfo.pageNum}' />
			<input id="pages" type="hidden" value='${pageInfo.pages}' />
			
			</div>
    </div>
    
   <script type="text/javascript">
   
   function page(page){
	 location.href="daifuStatement.do?page="+page;
   }
   
   </script>
</div>
</body>
</html>