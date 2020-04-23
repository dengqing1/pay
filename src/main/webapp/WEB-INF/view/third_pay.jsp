<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><br>
<div style="color:red">
交易类型:    ${requestScope.type}
</div>
<br><br>
<form action="${requestScope.url}" method="post">
版本号：<input name="version" value="${requestScope.par.version}" type="text"><br><br>
商户号：<input name="merch_id" value="${requestScope.par.merch_id}" type="text"><br><br>
业务代码：<input name="biz_code" value="${requestScope.par.biz_code}" type="text"><br><br>
签名信息：<input name="sign" value="${requestScope.par.sign}" type="text"><br><br>
时间：<input name="timestamp" value="${requestScope.par.timestamp}" type="text"><br><br>
业务集合：<input name="biz_data" value="${requestScope.par.biz_data}" type="text"><br><br>
<input type="submit" value="提交">
</form>




</body>
</html>