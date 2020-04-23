
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>

<style>
input{
width:300px;
}

</style>
<body>
<div style="color:red">
交易类型:  代付
</div>
<br><br>
<form action="../juhe/testDaifu" method="post">
商户号：<input name="merchantId"  type="text" value="600000000000002"><br><br>
商户订单号：<input name="merOrderId"  type="text"><br><br>
交易金额 单位为分：<input name="txnAmt"  type="text" value="30000"><br><br>
后台通知地址：<input name="backUrl"  type="text" value="http://154.223.71.4:8080/fengmai/mechrtklt/callback"><br><br>
银行编号 8位银行编码：<input name="bankId"  type="text" value="04100000"><br><br>
用途：<input name="purpose"  type="text" value="交易"><br><br>
商品标题：<input name="subject"  type="text" value="标题"><br><br>
账号 银行卡卡号：<input name="accNo"  type="text" value="6216261000000000018"><br><br>
身份信息：<input name="customerNm"  type="text" value="全渠道"><br><br>
手机号 ：<input name="phoneNo"  type="text" value="13552535506"><br><br>
商品描述：<input name="body"  type="text" value="描述"><br><br>
网关 daifu：<input name="gateway"  type="text" value="daifu"><br><br>
消息版本号 取值：1.0.0：<input name="version"  type="text" value="1.0.0"><br><br>
交易类型 取值：12：<input name="txnType"  type="text" value="1"><br><br>
交易子类型 取值：01：<input name="txnSubType"  type="text" value="0"><br><br>
产品类型 取值：000401：<input name="bizType"  type="text" value="001"><br><br>
接入类型 取值：0：<input name="accessType"  type="text" value="0"><br><br>
接入方式 取值：01：<input name="accessMode"  type="text" value="0"><br><br>
对公对私标志取值：00：对公 01：对私：<input name="ppFlag"  type="text" value="01"><br><br>
开户行名 如果ppFlag为00对公的时候必填：<input name="issInsName"  type="text" value="建设银行"><br><br>
订单发送时间 YYYYMMDDHHMMSS：<input name="txnTime"  type="text" value="111"><br><br>
交易币种：CNY<input name="currency"  type="text" value="CNY" ><br><br>
支付方式：<input name="payType" type="text" value="0401"><br><br>
秘钥：<input name="key" type="text" value="682807c82e2716452bd069aaf72d48dc"><br><br>
<input type="submit" value="提交">
</form>
</body>
</html>
