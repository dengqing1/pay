<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css" />
<title>Insert title here</title>
</head>
	<body>
		<div class="wrap-container">
			<form id="form_id" class="layui-form" style="width: 40%;padding-top: 20px;">
					 <input type="hidden" name="role" value="${user.role}" />
			 		 <input type="hidden" name="locked" value="${user.locked}" />
			 		 <input type="hidden" name="id" value="${user.id}" />
					<div class="layui-form-item">
						<label class="layui-form-label">商户号</label>
						<div class="layui-input-block">
							<input type="text" name="merId" readonly="readonly" required lay-verify="required" placeholder="请输入商户号" autocomplete="off" class="layui-input" value="${user.merId}">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${user.name}">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">账号</label>
						<div class="layui-input-block">
							<input type="text" name="email" required lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input" value="${user.email}">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input type="text" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
						</div>
					</div>
					<input type="hidden" name="merShortName" value="${mer.merShortName}"/>
					<div class="layui-form-item">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="text" name="phone" required lay-verify="required" placeholder="请输入手机" autocomplete="off" class="layui-input" value="${user.phone}">
						</div>
					</div>
					
					<div class="layui-form-item">
							<label class="layui-form-label">锁定</label>
							<div class="layui-input-block">
								<input type="radio" name="user_sex" value="是" title="是" checked="">
								<input type="radio" name="user_sex" value="否" title="否">
							</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo" id="submit_button">立即提交</button>
						</div>
					</div>
			</form>
		</div>

		<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
		
			 layui.use(['form'], function() {
				var form = layui.form();
				form.render();
				//监听提交
				form.on('submit(formDemo)', function(data) {
						$.ajax({
							url:"updateMerUser.do",
							type:"POST",
							data:$("#form_id").serialize(),
					    	dataType: 'json',
					    	async: true,
							success:function(result){
								if(result.code==100){
									alert("保存成功!");
									location.reload();
								}
							}
						});
				});
			});
				
		</script>
	</body>
</html>