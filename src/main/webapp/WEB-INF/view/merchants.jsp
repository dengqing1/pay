<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css" />
<title>Insert title here</title>
</head>
<body>
	<h2>商户录入</h2>
    
	<h3>商户基本信息</h3>
	<form id="merchants_form" class="layui-form" style="width: 40%;padding-top: 20px;">
		<%-- <div class="layui-form-item">
		    <label class="layui-form-label">商户号</label>
		    <div class="layui-input-block">
		      <input type="text" name="merId" value="${mer.merId}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div> --%>
		 <input type="hidden" name="merId" value="${mer.merId}"/>
		 <div class="layui-form-item">
		    <label class="layui-form-label">开通产品</label>
		    <div class="layui-input-block">
		      <input type="checkbox" value="网银" name="openProductIds" <c:if test="${mer.openProductIds.contains('网银')}">checked="checked"</c:if> title="网银">
		      <input type="checkbox" value="代付" name="openProductIds" <c:if test="${mer.openProductIds.contains('代付')}">checked="checked"</c:if> title="代付">
		      <input type="checkbox" value="快捷" name="openProductIds" <c:if test="${mer.openProductIds.contains('快捷')}">checked="checked"</c:if> title="快捷">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">商户简称</label>
		    <div class="layui-input-block">
		      <input type="text" name="merShortName" value="${mer.merShortName}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">商户执照名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="merName" value="${mer.merName}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">商户执照号</label>
		    <div class="layui-input-block">
		      <input type="text" name="merRegNo" value="${mer.merRegNo}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">税务登记号</label>
		    <div class="layui-input-block">
		      <input type="text" name="merTaxNo" required value="${mer.merTaxNo}"  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">组织机构证</label>
		    <div class="layui-input-block">
		      <input type="text" name="merOrganizationCertificate" value="${mer.merOrganizationCertificate}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">工商注册地址</label>
		    <div class="layui-input-block">
		      <input type="text" name="merRegAddr" required value="${mer.merRegAddr}" lay-verify="required"  autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">商户注册资金(万元)</label>
		    <div class="layui-input-block">
		      <input type="text" name="merRegCapital" required value="${mer.merRegCapital}" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">公司性质</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merKind">
		        <option value=""></option>
		        <option value="国营" <c:if test="${mer.merKind=='国营'}">selected="true"</c:if>>国营</option>
		        <option value="私有" <c:if test="${mer.merKind=='私有'}">selected="true"</c:if>>私有</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">风险等级</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merRiskLevel">
		        <option value=""></option>
		        <option value="1" <c:if test="${mer.merRiskLevel=='1'}">selected="true"</c:if>>高</option>
		        <option value="2" <c:if test="${mer.merRiskLevel=='2'}">selected="true"</c:if>>中</option>
		        <option value="3" <c:if test="${mer.merRiskLevel=='3'}">selected="true"</c:if>>低</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label"> 执照有效期</label>
		    <div class="layui-input-block">
		      <input type="text" name="merLicenseValidDate" value="${mer.merLicenseValidDate}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">MCC类型</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merBusinessType" value="${mer.merBusinessType}">
		        <option value=""></option>
		        <option value="0001" <c:if test="${mer.merBusinessType=='0001'}">selected="true"</c:if>>彩票</option>
		        <option value="0003" <c:if test="${mer.merBusinessType=='0003'}">selected="true"</c:if>>游戏</option>
		        <option value="0004" <c:if test="${mer.merBusinessType=='0004'}">selected="true"</c:if>>旅游</option>
		        <option value="0005" <c:if test="${mer.merBusinessType=='0005'}">selected="true"</c:if>>合作型代理</option>
		        <option value="0006" <c:if test="${mer.merBusinessType=='0006'}">selected="true"</c:if>>便民类</option>
		        <option value="0007" <c:if test="${mer.merBusinessType=='0007'}">selected="true"</c:if>>物流</option>
		        <option value="0008" <c:if test="${mer.merBusinessType=='0008'}">selected="true"</c:if>>其他</option>
		        <option value="5399" <c:if test="${mer.merBusinessType=='5399'}">selected="true"</c:if>>其他综合零售</option>
		        <option value="9501" <c:if test="${mer.merBusinessType=='9501'}">selected="true"</c:if>>互联网金融</option>
		        <option value="9502" <c:if test="${mer.merBusinessType=='9502'}">selected="true"</c:if>>电子商务</option>
		        <option value="9503" <c:if test="${mer.merBusinessType=='9503'}">selected="true"</c:if>>票务服务</option>
		        <option value="9504" <c:if test="${mer.merBusinessType=='9504'}">selected="true"</c:if>>餐饮</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">省</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merProvinceId" value="${mer.merProvinceId}">
		        <option value=""></option>
		        <option value="0" <c:if test="${mer.merProvinceId=='0'}">selected="true"</c:if>>北京</option>
		        <option value="1" <c:if test="${mer.merProvinceId=='1'}">selected="true"</c:if>>上海</option>
		        <option value="2" <c:if test="${mer.merProvinceId=='2'}">selected="true"</c:if>>广州</option>
		        <option value="3" <c:if test="${mer.merProvinceId=='3'}">selected="true"</c:if>>深圳</option>
		        <option value="4" <c:if test="${mer.merProvinceId=='4'}">selected="true"</c:if>>杭州</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">市</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merCityId" value="${mer.merCityId}">
		        <option value=""></option>
		        <option value="0" <c:if test="${mer.merCityId=='0'}">selected="true"</c:if>>北京</option>
		        <option value="1" <c:if test="${mer.merCityId=='1'}">selected="true"</c:if>>上海</option>
		        <option value="2" <c:if test="${mer.merCityId=='2'}">selected="true"</c:if>>广州</option>
		        <option value="3" <c:if test="${mer.merCityId=='3'}">selected="true"</c:if>>深圳</option>
		        <option value="4" <c:if test="${mer.merCityId=='4'}">selected="true"</c:if>>杭州</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">办公地址</label>
		    <div class="layui-input-block">
		      <input type="text" name="merAddress" required value="${mer.merAddress}"  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
	   
	   <h3>商户法人信息</h3>
	   	<div class="layui-form-item">
		    <label class="layui-form-label">法人名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="merLegalPerson" value="${mer.merLegalPerson}" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
	      <div class="layui-form-item">
		    <label class="layui-form-label">证件类型</label>
		    <div class="layui-input-block">
		      <select lay-verify="required" name="merLegalIdType" value="${mer.merLegalIdType}">
		        <option value=""></option>
		        <option value="01" <c:if test="${mer.merLegalIdType=='01'}">selected="true"</c:if>>身份证</option>
		        <option value="02" <c:if test="${mer.merLegalIdType=='02'}">selected="true"</c:if>>港澳台身份证</option>
		        <option value="03" <c:if test="${mer.merLegalIdType=='03'}">selected="true"</c:if>>护照</option>
		      </select>
		    </div>
		</div>
	     <div class="layui-form-item">
		    <label class="layui-form-label"> 证件号码</label>
		    <div class="layui-input-block">
		      <input type="text" name="merLegalIdNumber" required value="${mer.merLegalIdNumber}" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">证件有效期</label>
		    <div class="layui-input-block">
		      <input type="text" name="merLegalIdValidDate" required value="${mer.merLegalIdValidDate}" lay-verify="required"  autocomplete="off" class="layui-input">
		    </div>
		 </div>
	   <h3>商户负责人信息</h3>
		   	<div class="layui-form-item">
			    <label class="layui-form-label">联系人名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="contact" required value="${mer.contact}" lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <label class="layui-form-label">联系人电话</label>
			    <div class="layui-input-block">
			      <input type="text" name="contactPhone" required value="${mer.contactPhone}" lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <label class="layui-form-label">Email</label>
			    <div class="layui-input-block">
			      <input type="text" name="contactEmail" required value="${mer.contactEmail}" lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
	   <h3>商户账户信息</h3>
	   		<div class="layui-form-item">
			    <label class="layui-form-label">开户行</label>
			    <div class="layui-input-block">
			      <select lay-verify="required" name="settBankId" value="${mer.settBankId}">
			        <option value=""></option>
				       <c:forEach items="${Banklist}" var="item" varStatus="status1">
								<option value="${item.bankName}" <c:if test="${mer.settBankId==item.bankName}">selected="true"</c:if>>${item.bankName}</option>
						</c:forEach>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">账户类型</label>
			    <div class="layui-input-block">
			      <select lay-verify="required" name="settBankType" value="${mer.settBankType}">
			        <option value=""></option>
			        <option value="2" <c:if test="${mer.settBankType=='2'}">selected="true"</c:if>>对私</option>
			        <option value="1" <c:if test="${mer.settBankType=='1'}">selected="true"</c:if>>对公</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">开户账号</label>
			    <div class="layui-input-block">
			      <input type="text" name="settBankAccountNo" required value="${mer.settBankAccountNo}"  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <label class="layui-form-label">开户名</label>
			    <div class="layui-input-block">
			      <input type="text" name="settAccountName" required value="${mer.settAccountName}" lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label"> 开户身份证号</label>
			    <div class="layui-input-block">
			      <input type="text" name="settCertifyId" required value="${mer.settCertifyId}" lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="formDemo" id="button">保存</button>
			    </div>
			 </div>
    	</form>
   <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
   <script type="text/javascript">
   layui.use(['form'], function() {
		var form = layui.form();
		form.render();
		//监听提交
		form.on('submit(formDemo)', function(data) {
   			$.ajax({
   		        url: "saveMerchants.do",
   		        type: "POST",
   		        data:$("#merchants_form").serialize(),
   		        dataType: "json",
   		        success: function (result) {
   		            alert("保存成功!");
   		        }
   		    })
		});
	});
   	
   </script>
   
</body>
</html>