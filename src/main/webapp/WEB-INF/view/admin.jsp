<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css"/>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo">
				</div>
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="iconfont">&#xe606;</i>商户管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../merchants/edit.do" data-id='1' data-text="商户录入"><span class="l-line"></span>商户录入</a></dd>
				      <dd><a href="javascript:;" data-url="../merchants/findAll.do" data-id='2' data-text="商户信息查询"><span class="l-line"></span>商户信息查询</a></dd>
				      <dd><a href="javascript:;" data-url="../merchants/findAllByAccount.do" data-id='3' data-text="商户账户余额"><span class="l-line"></span>商户账户余额</a></dd>
				      <dd><a href="javascript:;" data-url="../merchants/statementList.do" data-id='33' data-text="商户对账查询"><span class="l-line"></span>商户对账查询</a></dd>
				    </dl>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="商户交易查询"><i class="iconfont">&#xe606;</i>商户交易查询</a>
				    <dl class="layui-nav-child">
				      <!-- <dd><a href="javascript:;" data-url="" data-id='4' data-text="交易汇总查询"><span class="l-line"></span>交易汇总查询</a></dd>
				      <dd><a href="javascript:;" data-url="" data-id='5' data-text="代付汇总查询"><span class="l-line"></span>代付汇总查询</a></dd> -->
				      <dd><a href="javascript:;" data-url="../channel/orderQuery" data-id='6' data-text="交易查询"><span class="l-line"></span>交易查询</a></dd>
				      <dd><a href="javascript:;" data-url="../channel/orderCallback" data-id='7' data-text="补发回调"><span class="l-line"></span>补发回调</a></dd>
				    </dl>
				  </li>
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="商户用户"><i class="iconfont">&#xe606;</i>商户用户</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../merchants/findAllByUser" data-id='8' data-text="账户"><span class="l-line"></span>账户</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="" data-id='9' data-text="商户登录解锁"><span class="l-line"></span>商户登录解锁</a></dd> -->
				    </dl>
				  </li>
				 <!--  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="商户开通服务管理"><i class="iconfont">&#xe606;</i>商户开通服务管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../merchants/findAllByServe.do" data-id='10' data-text="商户服务开通"><span class="l-line"></span>商户服务开通</a></dd>
				    </dl>
				  </li> -->
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="通道管理"><i class="iconfont">&#xe606;</i>通道管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../channel/findChannelAll.do" data-id='11' data-text="通道配置"><span class="l-line"></span>通道配置</a></dd>
				      <dd><a href="javascript:;" data-url="../channel/reconciliationlist.do" data-id='110' data-text="通道账户余额"><span class="l-line"></span>通道账户余额</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="" data-id='12' data-text="代付通道选择"><span class="l-line"></span>代付通道选择</a></dd>
				      <dd><a href="javascript:;" data-url="" data-id='13' data-text="代付轮切记录"><span class="l-line"></span>代付轮切记录</a></dd> -->
				    </dl>
				  </li>
				  
				  <!-- <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="网银"><i class="iconfont">&#xe606;</i>网银</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="" data-id='14' data-text="支持银行列表"><span class="l-line"></span>支持银行列表</a></dd>
				    </dl>
				  </li> -->
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="财务"><i class="iconfont">&#xe606;</i>财务</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../Oplogs/Oplogslist.do" data-id='15' data-text="调账"><span class="l-line"></span>调账</a></dd>
				      <dd><a href="javascript:;" data-url="../Oplogs/findAll.do" data-id='16' data-text="异常订单列表"><span class="l-line"></span>异常订单列表</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="" data-id='17' data-text="商户对账"><span class="l-line"></span>商户对账</a></dd> -->
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="风控管理"><i class="iconfont">&#xe606;</i>风控管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../balance/freeze.do" data-id='18' data-text="冻结资金"><span class="l-line"></span>冻结资金</a></dd>
				      <dd><a href="javascript:;" data-url="../balance/thaw.do" data-id='19' data-text="解冻资金"><span class="l-line"></span>解冻资金</a></dd>
				      <dd><a href="javascript:;" data-url="../balance/freezelist.do" data-id='20' data-text="冻结资金列表"><span class="l-line"></span>冻结/解冻资金列表</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="../balance/thawlist.do" data-id='21' data-text="解冻资金列表"><span class="l-line"></span>解冻资金列表</a></dd> -->
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="入金路由"><i class="iconfont">&#xe606;</i>入金路由</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../routes/routeslist.do" data-id='22' data-text="入金路由列表"><span class="l-line"></span>入金路由列表</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="../temp/templist.do" data-id='23' data-text="上传EXCEL"><span class="l-line"></span>上传EXCEL</a></dd> -->
				      <!-- <dd><a href="javascript:;" data-url="../history/historylist.do" data-id='24' data-text="路由历史列表"><span class="l-line"></span>路由历史列表</a></dd> -->
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="入金路由"><i class="iconfont">&#xe606;</i>出金路由</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../daifuroutes/routeslist.do" data-id='25' data-text="入金路由列表"><span class="l-line"></span>出金路由列表</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="../daifutemp/templist.do" data-id='26' data-text="上传EXCEL"><span class="l-line"></span>上传EXCEL</a></dd> -->
				      <!-- <dd><a href="javascript:;" data-url="../daifuhistory/historylist.do" data-id='27' data-text="路由历史列表"><span class="l-line"></span>路由历史列表</a></dd> -->
				    </dl>
				  </li>
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="系统公告管理"><i class="iconfont">&#xe606;</i>系统公告管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../notice/noticelist" data-id='28' data-text="公告列表"><span class="l-line"></span>公告列表</a></dd>
				      <!-- <dd><a href="javascript:;" data-url="" data-id='29' data-text="公告编辑"><span class="l-line"></span>公告编辑</a></dd> -->
				    </dl>
				  </li>
				  
				  <!-- <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="账户管理"><i class="iconfont">&#xe606;</i>账户管理</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="" data-id='30' data-text="密码修改"><span class="l-line"></span>密码修改</a></dd>
				    </dl>
				  </li> -->
				  
				  <!-- <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="监控"><i class="iconfont">&#xe606;</i>监控</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="" data-id='31' data-text="入金订单"><span class="l-line"></span>入金订单</a></dd>
				    </dl>
				  </li> -->
				  
				  <li class="layui-nav-item">
				    <a href="javascript:;" data-url="" data-id='5' data-text="批量代付审核"><i class="iconfont">&#xe606;</i>批量代付审核</a>
				    <dl class="layui-nav-child">
				      <dd><a href="javascript:;" data-url="../daifu/daifulist.do" data-id='32' data-text="列表"><span class="l-line"></span>列表</a></dd>
				    </dl>
				  </li>
				</ul>
			</div>
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a id="exit">退出</a></li>
					</ul>
				</div>
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">后台主页</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<img src="../static/admin/images/bg.jpg">
					    	<!-- <iframe src="" width="100%" height="100%" name="iframe" id="iframe" scrolling="auto" class="iframe" framborder="0"></iframe> -->
					    	<!--1end-->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<!--遮罩-->
			<div class="main-mask">
				
			</div>
		</div>
		 <script type="text/javascript">
		 	$("#exit").click(function(){
		 		window.location.href = '../manager/exit.htm';
		 	});
		 
			var scope={
				link:'../dome/menu1.htm'
			}
		</script> 
		<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
		<script src="../static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
		
	</body>
</html>