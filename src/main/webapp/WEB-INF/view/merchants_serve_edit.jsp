<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css" />
<title>商户开通服务配置</title>
</head>
<body>
		<form id="form_id" class="layui-form" style="width: 40%;padding-top: 20px;">
				<h3>商户开通服务配置</h3>
				<div class="layui-form-item">
					<label class="layui-form-label">商户执照名称</label>
					<div class="layui-input-block">
						<input type="text" name="merId" readonly="readonly" required lay-verify="required" placeholder="请输入商户号" autocomplete="off" class="layui-input" value="${mer.merId}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">商户简称</label>
					<div class="layui-input-block">
						<input type="text" name="contact" readonly="readonly" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${mer.contact}">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商户秘钥</label>
					<div class="layui-input-block">
						<input type="text" name="merSecretKey" readonly="readonly" required lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input" value="${mer.merSecretKey}">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">商户接入IP(白名单)</label>
					<div class="layui-input-block">
						<input type="text" name="merIpWhitelist" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.merIpWhitelist}">
					</div>
				</div>
				<h3>限额</h3>	
				<div class="layui-form-item">
				    <label class="layui-form-label">结算周期</label>
				    <div class="layui-input-block">
				      <input type="radio" name="t1" value="1" <c:if test="${mer.t1=='1'}">checked="checked"</c:if> title="T0">
				      <input type="radio" name="t1" value="2" <c:if test="${mer.t1=='2'}">checked="checked"</c:if> title="T1">
				    </div>
				 </div>
				 <div class="layui-form-item">
					<label class="layui-form-label">入金 每日限额(元)</label>
					<div class="layui-input-block">
						<input type="text" name="limitInMoneyDaily" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.limitInMoneyDaily}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">入金 单笔限额(元)</label>
					<div class="layui-input-block">
						<input type="text" name="limitInMoneySingle" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.limitInMoneySingle}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">代付 单笔限额(元)</label>
					<div class="layui-input-block">
						<input type="text" name="limitOutMoneySingle" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.limitOutMoneySingle}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">代付 单卡单日限额(元)</label>
					<div class="layui-input-block">
						<input type="text" name="limitOutMoneyCardDaily" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.limitOutMoneyCardDaily}">
					</div>
				</div>
				<h3>自动冻结(保证金)</h3>
				<div class="layui-form-item">
					<label class="layui-form-label">冻结比率(万分比‱)</label>
					<div class="layui-input-block">
						<input type="text" name="" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.contactPhone}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">冻结金额上限(元)</label>
					<div class="layui-input-block">
						<input type="text" name="" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.contactPhone}">
					</div>
				</div>
				<h3>快付通商户号</h3>
				<div class="layui-form-item">
					<label class="layui-form-label">1级商户号</label>
					<div class="layui-input-block">
						<input type="text" name="kftMerId" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.kftMerId}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">2级商户号</label>
					<div class="layui-input-block">
						<input type="text" name="kftSceMerIds" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="${mer.kftSceMerIds}">
					</div>
				</div>
				<h3>快付通商户号</h3>
				<div class="layui-form-item">
				    <label class="layui-form-label">开通状态</label>
				    <div class="layui-input-block">
				      <input type="radio" name="merOpenStatus" value="0" <c:if test="${mer.merOpenStatus=='0'}">checked="checked"</c:if> title="否">
				      <input type="radio" name="merOpenStatus" value="1" <c:if test="${mer.merOpenStatus=='1'}">checked="checked"</c:if> title="是">
				    </div>
				 </div>
				 <div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo" id="submit_button">立即提交</button>
					</div>
				 </div>
		  </form>
		  <script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		  <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
		  <script type="text/javascript">
			 layui.use(['form'], function() {
				var form = layui.form();
				form.render();
				//监听提交
				form.on('submit(formDemo)', function(data) {
					$("#submit_button").click(function(){
						$.ajax({
							url:"saveUser.do",
							type:"POST",
							data:$("#form_id").serialize(),
					    	dataType: 'json',
					    	async: true,
							success:function(result){
								if(result.code==100){
									alert("保存成功!");
								}
							}
						});
					 });
					return false;
				});
			});
				
		</script>
	
</body>
</html>