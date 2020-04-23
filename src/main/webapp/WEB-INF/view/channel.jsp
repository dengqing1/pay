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
<style>
	#box{
		width: 200%;
	}
	.flexB{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.flexB input{
		
	}
</style>
</head>
<body>
	<h3>通道编辑</h3>
		<form id="channel_form" class="layui-form" style="width: 40%;padding-top: 20px;">
			<%-- <div class="layui-form-item">
			    <label class="layui-form-label">编号</label>
			    <div class="layui-input-block">
			      <input type="text" name="channelId" value="${channel.channelId}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div> --%>
			 <input type="hidden" name="channelId" value="${channel.channelId}" />
			 <div class="layui-form-item">
			    <label class="layui-form-label">平台支付网关</label>
			    <div class="layui-input-block">
			      <select lay-verify="required" name="gateway" value="${channel.gateway}">
			        <option value="bank" <c:if test="${channel.gateway=='bank'}">selected="true"</c:if>>网银</option>
			        <option value="daifu" <c:if test="${channel.gateway=='daifu'}">selected="true"</c:if>>代付</option>
			        <option value="kuaijie" <c:if test="${channel.gateway=='kuaijie'}">selected="true"</c:if>>快捷</option>
			      </select>
			    </div>
			</div>
		    <div class="layui-form-item">
			    <label class="layui-form-label">名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="name" value="${channel.name}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
		     <div class="layui-form-item">
			    <label class="layui-form-label">渠道商户名缩写</label>
			    <div class="layui-input-block">
			    	<input type="text" name="channelMerAbbr" value="${channel.channelMerAbbr}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">渠道渠道号</label>
			    <div class="layui-input-block">
			      <input type="text" name="channelChannelId" value="${channel.channelChannelId}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">渠道商户号</label>
			    <div class="layui-input-block">
			      <input type="text" name="channelMerId" value="${channel.channelMerId}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">渠道秘钥</label>
			    <div class="layui-input-block">
			      <input type="text" name="channelSecretKey" value="${channel.channelSecretKey}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <label class="layui-form-label">费率类型</label>
			    <div class="layui-input-block">
			      <select lay-verify="required" name="feeType" value="${channel.feeType}">
			        <option value="percent" <c:if test="${channel.feeType=='percent'}">selected="true"</c:if>>万分比</option>
			        <option value="fix" <c:if test="${channel.feeType=='fix'}">selected="true"</c:if>>固定金额</option>
			      </select>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">费率</label>
			    <div class="layui-input-block">
			      <input type="text" name="feeAmount" value="${channel.feeAmount}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">渠道商户代理地址</label>
			    <div class="layui-input-block">
			      <input type="text" name="proxyurl" value="${channel.proxyurl}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <label class="layui-form-label">清算模式</label>
			    <div class="layui-input-block">
			      <input type="radio" name="clearCycle" value="T1" <c:if test="${channel.clearCycle=='T1'}">checked="checked"</c:if> title="T1">
			      <input type="radio" name="clearCycle" value="T0" <c:if test="${channel.clearCycle=='T0'}">checked="checked"</c:if> title="T0">
			      <input type="radio" name="clearCycle" value="DO" <c:if test="${channel.clearCycle=='D0'}">checked="checked"</c:if> title="D0">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">回调地址</label>
			    <div class="layui-input-block">
			      <input type="text" name="notifyurl" value="${channel.notifyurl}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			  <div class="layui-form-item">
			    <label class="layui-form-label">最小交易金额</label>
			    <div class="layui-input-block">
			      <input type="text" name="minAmount" value="${channel.minAmount}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			  <div class="layui-form-item">
			    <label class="layui-form-label">累计金额</label>
			    <div class="layui-input-block">
			      <input type="text" name="accumulative" value="${channel.accumulative}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			  <div class="layui-form-item">
			    <label class="layui-form-label">最大交易金额</label>
			    <div class="layui-input-block">
			      <input type="text" name="maxAmount" value="${channel.maxAmount}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">费率区间</label>
			    <div class="layui-input-block">
			      <input type="text" name="feeRange" value="${channel.feeRange}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    	<!-- <div id="box"></div> -->
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">附加字段</label>
			    <div class="layui-input-block">
			      <input type="text" name="extra" value="${channel.extra}" required  lay-verify="required" autocomplete="off" class="layui-input">
			    </div>
			 </div>
			 
			 <div class="layui-form-item">
			    <label class="layui-form-label">状态</label>
			    <div class="layui-input-block" value="${channel.status}">
			      <input type="radio" name="status" value="0" <c:if test="${channel.status=='0'}">checked="checked"</c:if> title="关闭">
			      <input type="radio" name="status" value="1" <c:if test="${channel.status=='1'}">checked="checked"</c:if> title="开启">
			    </div>
			 </div>
			 <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="formDemo" id="channel_save">保存</button>
			    </div>
			 </div>
	   </form>
	   <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
	   <script type="text/javascript">
//		点击增加
		$(document).on('click','.add',function(){
			var div_item = $(".flexB:first-child")[0];
			var div_item2 = div_item.cloneNode(true);
			console.log(div_item2);
			$("#box").append(div_item2);
		})
//		点击删除
		$(document).on('click','.del',function(){
			$(this).parent('div').remove();
		})
		
//		增加的方法
		additem();
		function additem(){
			var divItem = `
			<div class="flexB">
				<input type='text' />-
				<input type='text' />
				<select>
					<option>万分比</option>
					<option>固定金额</option>
				</select>
				<input type='text' />
				<button class='add'>增加</button><button class='del'>删除</button>
			</div>`;
			$("#box").append(divItem);
		}
	   
	   
	   layui.use(['form'], function() {
			var form = layui.form();
			form.render();
			//监听提交
			form.on('submit(formDemo)', function(data) {
	   			$.ajax({
	   		        url: "savechannelchants.do",
	   		        type: "POST",
	   		        data:$("#channel_form").serialize(),
	   		        dataType: "json",
	   		        success: function (result) {
	   		            alert("保存成功!");
	   		            location.reload();
	   		        }
	   		    })
			});
		});
	   </script>

</body>
</html>