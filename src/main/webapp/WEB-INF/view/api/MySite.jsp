
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--[if lt IE 7]>       <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>          <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>          <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->  <html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <title>Site - Site Docs</title>
    <meta name="description" content="The Easiest Way To Document Your Project" />
    <meta name="author" content="No One">
    <meta charset="UTF-8">
    <link rel="icon" href="//merchant.Site.com/docs/themes/daux/img/favicon-blue.png" type="image/x-icon">
    <!-- Mobile -->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Font -->
    
    <!-- CSS -->
    <link href='../css/theme-blue.min_1.css' rel='stylesheet' type='text/css'>
    
    <!--[if lt IE 9]>
    <script src="js/html5.js"></script>
    <![endif]-->
</head>
<body class=" ">
    <div class="Columns content">
    <aside class="Columns__left Collapsible">
        <button type="button" class="Button Collapsible__trigger">
            <span class="Collapsible__trigger--bar"></span>
            <span class="Collapsible__trigger--bar"></span>
            <span class="Collapsible__trigger--bar"></span>
        </button>

        <a class="Brand" href="../manager/api.do">Site Docs</a>


        <div class="Collapsible__content">
            <!-- Navigation -->
<!--             <ul class='Nav'><li class='Nav__item Nav__item--active'><a href="">MySite</a></li><li class='Nav__item '><a href="http://merchant.oznjv.cn/index/docs">Site PHP Demos</a></li></ul> -->
            <ul class='Nav'><li class='Nav__item Nav__item--active'><a href="">MySite</a></li></ul>

            <div class="Links">
                
                
                            </div>
        </div>
    </aside>
    <div class="Columns__right ">
        <div class="Columns__right__content">
            <div class="doc_content">
                <article class="Page">

    <div class="Page__header">
        <h1><a href="../manager/api.do">Site</a></h1>
                <span style="float: left; font-size: 10px; color: gray;">
                        2018-09-21 17:47:35        </span>
                    </div>


    <div class="s-content">
        <ul class="TableOfContents">
<li>
<p><a href="#page_">前言：</a></p>
<ul class="TableOfContents">
<li>
<p><a href="#page_Sign">Sign签名机制</a></p>
</li>
<li>
<p><a href="#page_Example">Example各语言签名方法参考</a></p>
</li>
</ul>
</li>
<li>
<p><a href="#page_1">1. 网银支付</a></p>
<ul class="TableOfContents">
<li>
<p><a href="#page_11">1.1 下单</a></p>
</li>
<li>
<p><a href="#page_12">1.2 支付回调</a></p>
</li>
<li>
<p><a href="#page_13">1.3 回调示例</a></p>
</li>
</ul>
</li>
<li>
<p><a href="#page_100">100.代付</a></p>
</li>
<li>
<p><a href="#page_200">200.订单查询</a></p>
</li>
<li>
<p><a href="#page_201">201.余额查询</a></p>
</li>
<li>
<p><a href="#page_1001_Others">1001 Others附录</a></p>
<ul class="TableOfContents">
<li>
<p><a href="#page_1011">101.1 状态码</a></p>
</li>
<li>
<p><a href="#page_1012">101.2 银行字典</a></p>
</li>
<li>
<p><a href="#page_1013">101.3 银行卡认证应答码</a></p>
</li>
</ul>
</li>
</ul>
<h2 id="page_">前言：</h2>
<p>目前SitePay支持的支付方式有网银（部分银行的借记卡与信用卡）支付，京东扫码支付，qq扫码支付</p>
<p>测试账号：600000000000002，账号对应的secret key 由运营人员提供</p>
<h3 id="page_Sign">Sign签名机制</h3>
<p>在请求参数列表中，除去signMethod、signature 两个参数外，其他需要使用到的参数皆是要签名的参数。</p>
<p>在应答参数列表中，除去signMethod、signature 两个参数外，凡是应答回来的参数皆是要签名的参数。</p>
<p>示例, 需要传递的参数:</p>
<pre><code>d=dd, c=, aa=aa1aa2, ab=ab1ab2, subject=张大爷的秋裤blue
</code></pre>
<p>1, 对请求参数数组里的每一个键值对(包括空值)，按照键名A-Z-a-z（<font color='red'>ASCII码从小到大排序</font>）的顺序排序，若遇到相同首字母，则看第二个字母，以此类推。排序完成之后，把所有的键值连接起来。</p>
<p>示例结果:</p>
<pre><code>aa=aa1aa2&amp;ab=ab1ab2&amp;c=&amp;d=dd&amp;subject=张大爷的秋裤blue
</code></pre>
<p>2, 签名原始串中，字段名和字段值都采用原始值，不进行url_encode, 不进行base64编码</p>
<p>3, 把密钥直接根在后面(假设密钥88888888)</p>
<p>示例结果:</p>
<pre><code>aa=aa1aa2&amp;ab=ab1ab2&amp;c=&amp;d=dd&amp;subject=张大爷的秋裤blue88888888
</code></pre>
<p>4, MD5签名</p>
<ul>
<li>签名：</li>
</ul>
<p>利用 MD5签名函数（<font color='red'>注意：此处MD5结果非字符串，而是MD5字节数组</font>）进行加密运算，再进行base64编码 进而得到 最终签名结果字符串。</p>
<p>示例结果(编码设置为UTF-8)：</p>
<pre><code>signature = 'sT79c9DYlucxhLVZf45llw==';
</code></pre>
<p>5, 发送post或者get数据时<font color='red'>subject,body,customerInfo</font>均需要<font color='red'>base64编码</font>, 所有参数没有顺序要求。</p>
<p>示例结果(编码设置为UTF-8)</p>
<pre><code>d=dd&amp;c=&amp;aa=aa1aa2&amp;ab=ab1ab2&amp;subject=5byg5aSn54i355qE56eL6KOkYmx1ZQ==&amp;signature=sT79c9DYlucxhLVZf45llw==
</code></pre>
<p> </p>
<h3 id="page_Example">Example各语言签名方法参考</h3>
<p>根据以往的经验，接入过程中比较耗时的是签名计算。由于各个商户编程语言不一样，我们仅能提供有限的签名方法函数。包括 php，java ，c#，其他的编程语言待完善</p>
<p>php版本(排序+签名)：</p>
<pre><code>function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    return base64_encode(md5($uri, TRUE));
}
</code></pre>
<p><a href="http://php.net/manual/en/function.md5.php" class="external">重点在md5($word, true)</a>
当md5 函数第二个参数为true的时候，得到的是原始16字符二进制格式</p>
<p>java版本（需要引入commons-codec-1.x.jar、commons-lang-2.x.jar）：</p>
<pre><code>SortedMap&lt;String, Object&gt; parameters = new TreeMap&lt;String,Object&gt;();

public static String sign(SortedMap&lt;String, Object&gt; parameters, String key) {
    List&lt;String&gt; sb = new ArrayList&lt;String&gt;();
    Set&lt;Entry&lt;String, Object&gt;&gt; es = parameters.entrySet();
    Iterator&lt;Entry&lt;String, Object&gt;&gt; it = es.iterator();
    while (it.hasNext()) {
        Entry&lt;String, Object&gt; entry = it.next();
        String k = (String) entry.getKey();
        Object v = entry.getValue();
        if (null != v &amp;&amp; !&quot;signMethod&quot;.equals(k) &amp;&amp; !&quot;signature&quot;.equals(k)) {
            sb.add(k + &quot;=&quot; + v);
        }
    }
    byte[] digest = null;
    try {
        byte[] data = (StringUtils.join(sb, &quot;&amp;&quot;) + key).getBytes(&quot;UTF-8&quot;);
        MessageDigest messageDigest = MessageDigest.getInstance(&quot;MD5&quot;);
        messageDigest.reset();
        messageDigest.update(data);
        digest = messageDigest.digest();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new String(Base64.encodeBase64(digest));
}
</code></pre>
<p>c# 版本：（signText是需要商户进行排序并追加key）</p>
<pre><code>public static string EncryptByMD5To16Digits(string signText)
{
    MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
    var sign = md5.ComputeHash(UTF8Encoding.UTF8.GetBytes(signText));
    return Convert.ToBase64String(sign);
}
</code></pre>
<p>python 版本：</p>
<pre><code>import hashlib
import base64
def sort_params(p):
    sortedtext = ''
    for key in sorted(p.iterkeys()):
        if sortedtext == '':
            sortedtext = sortedtext + key + &quot;=&quot; + p[key]
        else:
            sortedtext = sortedtext + &quot;&amp;&quot; + key + &quot;=&quot; + p[key]
    return sortedtext

def md5(s, raw_output=False):
    &quot;&quot;&quot;Calculates the md5 hash of a given string&quot;&quot;&quot;
    res = hashlib.md5(s.encode())
    if raw_output:
        return res.digest()
    return res.hexdigest()
def novoxpay_sign(params,key):
    text = sort_params(params)
    text = text + key
    return base64.b64encode(md5(text,True))
if __name__ == &quot;__main__&quot;:
    #params to post to npya, dict
    params = {}
    params['d'] = 'dd'
    params['subject'] = 'blue88888888'
    params['ab'] = 'b1ab2'
    params['aEkk'] ='AEkkkk'
    params['aa'] = 'aa1aa2'
    params['c'] = ''
    #secrect key from Site
    key = 'JLadfareurein7836436dfsdafd'
    #caculate signature
    sign = novoxpay_sign(params,key)
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_1">1. 网银支付</h2>
<p>此接口用户在商户端选择网银支付使用的银行，商户端提交信息到支付平台的前台交易地址后，支付平台引导用户浏览器自动转向到银行页面</p>
<p>当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败</p>
<p>商户需要提供两个通知接口：</p>
<p>1）frontUrl 用于前端通知，backUrl用户后台通知</p>
<p>2）frontUrl与backUrl需要提供get访问方式，不能含有参数，即”?”和“&amp;”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/frontUrl 是合法的，而http://www.aamercharn.com/frontUrl?a=6&amp;b=7是不合法的</p>
<h3 id="page_11">1.1 下单</h3>
<ul>
<li>线上接口地址：<code>http://epay.Site.com/pay</code>
</li>
<li>测试接口地址：<code>http://epay-testing.Site.com/pay</code>
</li>
<li>请求方式：<code>POST</code>请求</li>
<li>传参方式：<code>form表单</code>提交<code>POST</code>参数</li>
<li>参数：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名方法</td>
<td>signMethod</td>
<td>M</td>
<td>取值：MD5</td>
</tr>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>签名信息，详见签名机制</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号，在商户系统中唯一</td>
</tr>
<tr>
<td>交易金额</td>
<td>txnAmt</td>
<td>M</td>
<td>交易单位为分</td>
</tr>
<tr>
<td>前台通知地址</td>
<td>frontUrl</td>
<td>M</td>
<td>用户在银行端支付完成后返回商户端的地址</td>
</tr>
<tr>
<td>后台通知地址</td>
<td>backUrl</td>
<td>M</td>
<td>商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知</td>
</tr>
<tr>
<td>银行编号</td>
<td>bankId</td>
<td>M</td>
<td>当gateway取值bank时必须，8位银行编码，详见数据字典</td>
</tr>
<tr>
<td>借贷类型</td>
<td>dcType</td>
<td>M</td>
<td>当gateway取值bank时必须，取值：<br>0：借记卡</td>
</tr>
<tr>
<td>商品标题</td>
<td>subject</td>
<td>M</td>
<td>上送报文时需BASE64编码，参与签名计算时不需要编码</td>
</tr>
<tr>
<td>账号</td>
<td>accNo</td>
<td>M</td>
<td>银行卡卡号</td>
</tr>
<tr>
<td>开户姓名</td>
<td>customerNm</td>
<td>M</td>
<td>开户账号名</td>
</tr>
<tr>
<td>手机号</td>
<td>phoneNo</td>
<td>M</td>
<td>银行开户预留手机号</td>
</tr>
<tr>
<td>商户用户id</td>
<td>userId</td>
<td>O</td>
<td>商户用户系统中的Id</td>
</tr>
<tr>
<td>终端用户IP</td>
<td>customerIp</td>
<td>O</td>
<td>终端用户IP地址</td>
</tr>
<tr>
<td>商品描述</td>
<td>body</td>
<td>M</td>
<td>上送报文时需BASE64编码，参与签名计算时不需要编码</td>
</tr>
<tr>
<td>网关</td>
<td>gateway</td>
<td>M</td>
<td>网关类型： bank</td>
</tr>
</tbody>
</table>
<p> </p>
<h3 id="page_12">1.2 支付回调</h3>
<ul>
<li>接口地址: <code>商户提供</code>
</li>
<li>方法：<code>GET</code>
</li>
<li>参数：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>必须验证签名正确性</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号，在商户系统中唯一</td>
</tr>
<tr>
<td>交易金额</td>
<td>txnAmt</td>
<td>M</td>
<td>单位分</td>
</tr>
<tr>
<td>应答码</td>
<td>respCode</td>
<td>M</td>
<td>订单状态码，参见状态码表</td>
</tr>
<tr>
<td>应答信息</td>
<td>respMsg</td>
<td>M</td>
<td>订单状态描述</td>
</tr>
</tbody>
</table>
<p> </p>
<h3 id="page_13">1.3 回调示例</h3>
<p>前端：</p>
<pre><code>http://epay.Site.com/callback/testfront?merchantId=92900009502060400001&amp;merOrderId=20170810A45D34FG&amp;txnAmt=2&amp;respCode=1001&amp;respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&amp;signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D    
</code></pre>
<p>后台:</p>
<pre><code>http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&amp;merOrderId=20170810A45D34FG&amp;txnAmt=2&amp;respCode=1001&amp;respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&amp;signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D   
</code></pre>
<p> </p>
<p> </p>
<!--
## 2. 扫码支付
此接口实时返回支付链接，如京东或者qq的支付链接，**用户在拿到该链接后，需要将其生成二维码进行展示**，使用对应的扫码客户端，如京东金融或者qq或者银联钱包扫该二维码，完成支付
  
当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败

商户需要提供两个通知接口：

1）frontUrl 用于前端通知，backUrl用户后台通知

2）frontUrl与backUrl需要提供get访问方式，不能含有参数，即”?”和“&”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/frontUrl 是合法的，而http://www.aamercharn.com/frontUrl?a=6&b=7是不合法的

### 2.1 下单

* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 参数：

| 报文域| 变量命名 | 是否必传(Must,Optional)| 说明 |
| ------| ------ | ------ |------|
|签名方法 | signMethod | M | 取值：MD5|
|签名信息 | signature | M | 签名信息，详见签名机制|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 交易单位为分|
|前台通知地址 | frontUrl | M | 用户在银行端支付完成后返回商户端的地址|
|后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知|
|商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|商户用户id | userId | O | 商户用户系统中的Id|
|商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|网关 | gateway | M | 微信支付(扫码)：wxpay<br>支付宝支付(扫码)：alipay<br>qq支付(扫码)：qqpay<br>京东支付(扫码)：jdpay<br>银联钱包扫码:unionpayqr|

&nbsp;

### 2.2 返回结果(同步返回支付链接+异步通知)

&nbsp;

#### 2.2.1 同步应答返回结果(json格式)

报文域| 变量命名 | 类型|是否必传(Must,Optional)|说明
:------| ------ | ------ |------|----
执行结果 | success | int | M | success为1的时候可以解析业务相关字段（merOrderId，txnAmt，payLink）<br>为0 的时候解析code（错误码）及msg（错误消息）一般而言msg会说明出错的结果，不需要关心code的值
执行代码 | code | int | M | 0：执行成功代码 <br>非0：参见执行代码说明
时间戳 | timestamp | string | M |
签名 | signature | string | M | 参见签名规则
支付链接 | payLink | string | M | 支付链接，用户需将其转化为二维码
执行结果描述 | msg | string |  | 
商户号订单号 | merOrderId | string |M  |商户订单号，商户平台内部唯一
支付总额 | txnAmt | int | M |支付金额（分）

&nbsp;

#### 2.2.2 异步通知

* 接口地址: `商户提供`
* 方法：`GET`
* 异步通知报文：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明 
:------| ------ | ------ |------
签名信息 | signature | M | 必须验证签名正确性
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一
交易金额 | txnAmt | M | 单位分
应答码 | respCode | M | 订单状态码，参见状态码表
应答信息 | respMsg | M | 订单状态描述

&nbsp;

&nbsp;


#### 2.2.3 返回结果示例

前端：

    http://epay.Site.com/callback/testfront?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D

后台:

    http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D


* 返回结果代码：

code         |  说明
:--------------|:----------------
1000  | 待支付
1001  | 支付成功
1111  | 交易进行中
1002  | 支付失败



&nbsp;

&nbsp;


## 3. H5支付

此接口实时返回支付链接，如wx或者qq的支付链接，**用户在拿到该链接后，用户手机浏览器打开, 会唤起wx/qq来完成支付**
  
当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败

商户需要提供两个通知接口：

1）frontUrl 支付完成页面同步跳转通知的url，backUrl支付结果异步通知的url

2）frontUrl与backUrl需要提供get访问方式，不能含有参数，即”?”和“&”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/frontUrl 是合法的，而http://www.aamercharn.com/frontUrl?a=6&b=7是不合法的

### 3.1 下单

* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 参数：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
签名方法 | signMethod | M | 取值：MD5
签名信息 | signature | M | 签名信息，详见签名机制
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一
交易金额 | txnAmt | M | 交易单位为分
前台通知地址 | frontUrl | M | 用户在支付完成后返回商户端的地址
后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在返回最终交易结果后支付平台通过该地址进行异步通知
商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码
商户用户id | userId | O | 商户用户系统中的Id
商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码
网关 | gateway | M | 微信H5支付(调起微信)：wxpayh5<br>QQ钱包H5支付(调起QQ)：qqpayh5<br>

* 返回结果：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
是否成功 | success | M | 1,0
状态码 | code | M | 0:成功, 其他: 见状态码附录
消息 | msg | M | 成功消息, 失败原因
时间戳 | timestamp | M | 
预下单URL | prepayUrl | M | 预下单URL<font color='red'>(注意:此URL只有第一次访问有效, 不可重复访问)</font>
商户订单号 | merOrderId | M | 
交易金额 | txnAmt | M | 交易单位为分
签名信息 | signature | M | 必须验证签名正确性
&nbsp;

### 3.2 返回结果(异步通知)

&nbsp;

#### 3.2.1 异步通知

* 接口地址: `商户提供`
* 方法：`GET`
* 异步通知报文：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
签名信息 | signature | M | 必须验证签名正确性
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一
交易金额 | txnAmt | M | 单位分
应答码 | respCode | M | 订单状态码，参见状态码表
应答信息 | respMsg | M | 订单状态描述

&nbsp;

&nbsp;


#### 3.2.3 返回结果示例


后台:

    http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D


* 返回结果代码：

code         |  说明
:--------------|:----------------
1000  | 待支付
1001  | 支付成功
1111  | 交易进行中
1002  | 支付失败



&nbsp;

&nbsp;


## 4. 快捷支付

商户发起 4.1.1预下单请求, 如果成功, 会返回tn(预下单号), 同时收到手机短信验证码, 然后拿tn和手机验证码请求4.2.1完成支付
  
### 4.1 预下单

* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 方法：`POST`请求提交参数
* 参数：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
签名方法 | signMethod | M | 取值: MD5
签名信息 | signature | M | 签名信息, 详见签名机制
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 商户订单号, 在商户系统中唯一
交易金额 | txnAmt | M | 交易单位为分
后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在返回最终交易结果后支付平台通过该地址进行异步通知
商品标题 | subject | M | 上送报文时需BASE64编码, 参与签名计算时不需要编码
商户用户id | userId | O | 商户用户系统中的Id
商品描述 | body | M | 上送报文时需BASE64编码, 参与签名计算时不需要编码
账号 | accNo | M | 银行卡卡号
开户姓名 | customerNm | M | 开户账号名
开户证件类型 | customerCertifyType | M | 01：身份证, 02：军官证, 03：护照, 04：回乡证, 05：台胞证, 06：警官证, 07：士兵证, 99：其它证件
开户证件号码 | customerCertifyId | M | 
银行卡过期时间 | expired | O | 格式: YYMM(<font color='red'>如果是信用卡, 必填</font>)
cvv2 | cvv2 | O | 银行卡背面3位信用卡安全码(<font color='red'>如果是信用卡, 必填</font>)
手机号 | phoneNo | M | 银行开户预留手机号(用以接受验证码)
网关 | gateway | M | 取值: kuaijie
子网关 | subGateway | M | 取值: prePay

* 返回结果：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
是否成功 | success | M | 1,0
状态码 | code | M | 0:成功, 其他: 见状态码附录
消息 | msg | M | 成功消息, 失败原因
时间戳 | timestamp | M | 
预下单号 | tn | M | 预下单号, 后续 真实支付的时候用到
商户订单号 | merOrderId | M | 
交易金额 | txnAmt | M | 交易单位为分
签名信息 | signature | M | 必须验证签名正确性

&nbsp;

### 4.2 支付

* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 方法：`POST`请求提交参数
* 参数：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
签名方法 | signMethod | M | 取值: MD5
签名信息 | signature | M | 签名信息, 详见签名机制
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 预下单商户订单号
预下单号 | tn | M | 预下单号, 在prePay成功后返回
手机短信验证码 | smsCode | M |
网关 | gateway | M | 取值: kuaijie
子网关 | subGateway | M | 取值: doPay

* 返回结果：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
是否成功 | success | M | 1,0
状态码 | code | M | 0:成功, 其他: 见状态码附录
消息 | msg | M | 成功消息, 失败原因
时间戳 | timestamp | M | 
预下单号 | tn | M | 预下单号, 后续 真实支付的时候用到
商户订单号 | merOrderId | M | 
交易金额 | txnAmt | M | 交易单位为分
签名信息 | signature | M | 必须验证签名正确性

&nbsp;

### 4.3 异步通知

* 接口地址: `商户提供`
* 方法：`GET`
* 异步通知报文：

报文域| 变量命名 | 是否必传(Must,Optional)| 说明
:------| ------ | ------ |------
签名信息 | signature | M | 必须验证签名正确性
商户号 | merchantId | M | 由支付平台分配给商户的唯一编号
商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一
交易金额 | txnAmt | M | 单位分
应答码 | respCode | M | 订单状态码，参见状态码表
应答信息 | respMsg | M | 订单状态描述


示例:

    http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D


* 返回结果代码：

code         |  说明
:--------------|:----------------
1000  | 待支付
1001  | 支付成功
1111  | 交易进行中
1002  | 支付失败



&nbsp;

&nbsp;

## 5. 银联快捷

商户端提交信息到支付平台的前台交易地址后，支付平台引导用户浏览器自动转向到银联支付页面
  
当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败

商户需要提供两个通知接口：

1）frontUrl 用于前端通知，backUrl用户后台通知

2）frontUrl与backUrl需要提供get访问方式，不能含有参数，即”?”和“&”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/frontUrl 是合法的，而http://www.aamercharn.com/frontUrl?a=6&b=7是不合法的


### 5.1 下单

* 线上接口地址：`http://epay.Site.com/pay`
* 测试接口地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 参数：

| 报文域 | 变量命名 | 是否必传(Must,Optional)  |说明|
| ------| ------ | ------ |------ |
|签名方法 | signMethod | M | 取值：MD5|
|签名信息 | signature | M | 签名信息，详见签名机制|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 交易单位为分|
|前台通知地址 | frontUrl | M | 用户在银行端支付完成后返回商户端的地址|
|后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知|
|商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|商户用户id | userId | O | 商户用户系统中的Id|
|商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|网关 | gateway | M | 网关类型： kuaijie_unionpay|

&nbsp;

### 5.2 支付回调(异步通知)

* 接口地址: `商户提供`
* 方法：`GET`
* 参数：

| 报文域| 变量命名 | 是否必传(Must,Optional)| 说明 |
| ------| ------ | ------ |------|
|签名信息 | signature | M | 必须验证签名正确性|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 单位分|
|应答码 | respCode | M | 订单状态码，参见状态码表|
|应答信息 | respMsg | M | 订单状态描述|

&nbsp;

### 5.3 回调示例

前端：

    http://epay.Site.com/callback/testfront?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D    

后台:

    http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D   

&nbsp;

&nbsp;


## 6.信用卡转账
该功能分两步：
1）商户端提交付款请求到支付平台的前台交易地址后，支付平台会使用指定信用卡支付；
2）付款成功后，提交提款请求，提款到指定的借记卡（信用卡与借记卡均在同一人名下）

当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败

商户需要提供两个通知接口：

1)frontUrl 用于前端通知，backUrl用户后台通知

2)frontUrl与backUrl需要提供get访问方式，不能含有参数，即”?”和“&”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/frontUrl 是合法的，而http://www.aamercharn.com/frontUrl?a=6&b=7是不合法的

3)付款/提款结果异步通知

### 6.1 信用卡付款
* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 请求参数：

| 报文域 | 变量命名 | 是否必传(Must,Optional)  |说明|
| ------| ------ | ------ |------ |
|签名方法 | signMethod | M | 取值：MD5|
|签名信息 | signature | M | 签名信息，详见签名机制|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 交易单位为分|
|前台通知地址 | frontUrl | M | 用户在银行端支付完成后返回商户端的地址|
|后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知|
|商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|商户用户id | userId | O | 商户用户系统中的Id|
|商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|网关 | gateway | M | 网关类型： transfer |
|提款金额| withDrawAmt |M|用户提款额度，计算公式：交易金额-手续费|
|持卡人姓名| name | M | 信用卡和借记卡需是同一持卡2018-03-21 18:52:40.117561+0800 FKForum[2605:1463180] ++++++++LastSHow adid:976+++++=
                                2018-03-21 18:52:40.117653+0800 FKForum[2605:1463180] ========lastshow index:1=========
                                2018-03-21 18:52:40.117682+0800 FKForum[2605:1463180] ~~~~~~~~Select to show  adid:975~~~~~~~~人 |
|持卡人身份证| idCardNo | M |借记卡与信用卡持卡人身份证号 |
|支付卡号| bankCardNo | M | 支付用信用卡卡号 |
|支付卡持卡人预留手机号| phone |  M| |
|有效年| cardExpYear |M  |信用卡有效年份 |
|有效月| cardExpMonth | M |信用卡有效月份|
|cvv| lastThreeNo | M | 信用卡三位cvv|
|转账到借记卡卡号| settleCardNo | M | 转账到借记卡卡号,跟支付用信用卡是同一持卡人 |
|转账/支付标示| withdraw | M | 0 |
|转账到银行卡持卡人预留手机号| settlePhone | M | |



### 6.2 借记卡提款
* 线上：`http://epay.Site.com/pay`
* 测试地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 请求参数：

| 报文域 | 变量命名 | 是否必传(Must,Optional)  |说明|
| ------| ------ | ------ |------ |
|签名方法 | signMethod | M | 取值：MD5|
|签名信息 | signature | M | 签名信息，详见签名机制|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|前台通知地址 | frontUrl | M | 用户在银行端支付完成后返回商户端的地址|
|后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知|
|商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|网关 | gateway | M | 网关类型： transfer |
|信用卡付款单号| payOrderId |M|该单号是信用卡付款订单对应的单号|
|转账/支付标示| withdraw | M | 1 |


* 付款/提款接口实时返回结果(json)：

| 报文域| 变量命名 | 是否必传(Must,Optional)| 类型 | 说明 |
| ------| ------ | ------ |------|-----|
|执行结果 | success | int | M | 1：执行成功<br>0：执行失败|
|执行代码 | code | int | M | 参见执行代码说明|
|时间戳 | timestamp | string | M | 时间戳|
|签名 | signature | string | M | 参见签名规则|
|执行结果描述 | msg | string | M | |

* 异步通知结果(GET参数)：

| 报文域| 变量命名 | 是否必传(Must,Optional)| 说明 |
| ------| ------ | ------ |------|
|签名信息 | signature | M | 必须验证签名正确性|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 单位分|
|应答码 | respCode | M | 订单状态码，参见状态码表|
|应答信息 | respMsg | M | 订单状态描述|

示例:

     http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D


* code代码：

code         |  说明
:--------------|:----------------
1000  | 待支付
1001  | 支付成功
1111  | 交易进行中
1002  | 支付失败

&nbsp;

&nbsp;

## 7. SD快捷

商户端提交信息到支付平台的前台交易地址后，支付平台引导用户浏览器自动转向到快捷支付页面
  
当通知方式为服务器后台异步通知时，商户端在收到通知并处理完成后需返回”success”这个字符串，如果不返回或者非该字符串，支付平台系统认为通知失败

商户需要提供两个通知接口：

1）frontUrl 用于前端返回商户，backUrl用户后台通知

2）backUrl需要提供get访问方式，不能含有参数，即”?”和“&”这两个特殊字符及其引导的字符。如http://www.aamercharn.com/backUrl 是合法的，而http://www.aamercharn.com/backUrl?a=6&b=7是不合法的


### 7.1 下单

* 线上接口地址：`http://epay.Site.com/pay`
* 测试接口地址：`http://epay-testing.Site.com/pay`
* 请求方式：`POST`请求
* 传参方式：`form表单`提交`POST`参数
* 参数：

| 报文域 | 变量命名 | 是否必传(Must,Optional)  |说明|
| ------| ------ | ------ |------ |
|签名方法 | signMethod | M | 取值：MD5|
|签名信息 | signature | M | 签名信息，详见签名机制|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 交易单位为分|
|前台通知地址 | frontUrl | M | 用户在银行端支付完成后返回商户端的地址(<font color='red'>注意返回商户没有通知参数</font>)|
|后台通知地址 | backUrl | M | 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知|
|商品标题 | subject | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|商户用户id | userId | O | 商户用户系统中的Id(<font color='red'>最长6位, 注意同一用户, 账号信息会被记录</font>)|
|商品描述 | body | M | 上送报文时需BASE64编码，参与签名计算时不需要编码|
|网关 | gateway | M | 网关类型： kuaijie_sd|

&nbsp;

### 7.2 支付回调

* 接口地址: `商户提供`
* 方法：`GET`
* 参数：

| 报文域| 变量命名 | 是否必传(Must,Optional)| 说明 |
| ------| ------ | ------ |------|
|签名信息 | signature | M | 必须验证签名正确性|
|商户号 | merchantId | M | 由支付平台分配给商户的唯一编号|
|商户订单号 | merOrderId | M | 商户订单号，在商户系统中唯一|
|交易金额 | txnAmt | M | 单位分|
|应答码 | respCode | M | 订单状态码，参见状态码表|
|应答信息 | respMsg | M | 订单状态描述|

&nbsp;

### 7.3 回调示例

前端：

    http://epay.Site.com/callback/testfront

后台:

    http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&merOrderId=20170810A45D34FG&txnAmt=2&respCode=1001&respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D   

&nbsp;

&nbsp;
-->
<h2 id="page_100">100.代付</h2>
<p>通过代付可向指定账号发起转账.</p>
<p>处理结果: 以状态码为准. 1, 同步返回: 状态码为1001(成功)或者1002(失败), 则可以同步结果处理. 2, 异步通知: 如果同步返回状态码非终态, 异步通知</p>
<ul>
<li>线上地址：<code>http://epay.Site.com/pay</code>
</li>
<li>测试地址：<code>http://epay-testing.Site.com/pay</code>
</li>
<li>请求方式：<code>POST</code>请求</li>
<li>传参方式：<code>form表单</code>提交<code>POST</code>参数</li>
<li>请求参数：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名方法</td>
<td>signMethod</td>
<td>M</td>
<td>取值：MD5</td>
</tr>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>签名信息，详见签名机制</td>
</tr>
<tr>
<td>消息版本号</td>
<td>version</td>
<td>M</td>
<td>取值：1.0.0</td>
</tr>
<tr>
<td>交易类型</td>
<td>txnType</td>
<td>M</td>
<td>取值：12</td>
</tr>
<tr>
<td>交易子类型</td>
<td>txnSubType</td>
<td>M</td>
<td>取值：01</td>
</tr>
<tr>
<td>产品类型</td>
<td>bizType</td>
<td>M</td>
<td>取值：000401</td>
</tr>
<tr>
<td>接入类型</td>
<td>accessType</td>
<td>M</td>
<td>取值：0</td>
</tr>
<tr>
<td>接入方式</td>
<td>accessMode</td>
<td>M</td>
<td>取值：01</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号</td>
</tr>
<tr>
<td>账号</td>
<td>accNo</td>
<td>M</td>
<td>银行卡卡号</td>
</tr>
<tr>
<td>身份信息</td>
<td>customerNm</td>
<td>M</td>
<td>开户账号名</td>
</tr>
<tr>
<td>手机号</td>
<td>phoneNo</td>
<td>M</td>
<td>银行开户预留手机号</td>
</tr>
<tr>
<td>对公对私标志</td>
<td>ppFlag</td>
<td>M</td>
<td>取值：<br>00：对公<br>01：对私</td>
</tr>
<tr>
<td>开户行名</td>
<td>issInsName</td>
<td>M</td>
<td>如果ppFlag为00对公的时候必填</td>
</tr>
<tr>
<td>订单发送时间</td>
<td>txnTime</td>
<td>M</td>
<td>YYYYMMDDHHMMSS举例：20171128090356</td>
</tr>
<tr>
<td>交易金额</td>
<td>txnAmt</td>
<td>M</td>
<td>单位为分</td>
</tr>
<tr>
<td>交易币种</td>
<td>currency</td>
<td>M</td>
<td>三位 ISO 货币代码，目前仅支持人民币 CNY</td>
</tr>
<tr>
<td>后台通知地址</td>
<td>backUrl</td>
<td>M</td>
<td>商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知</td>
</tr>
<tr>
<td>支付方式</td>
<td>payType</td>
<td>M</td>
<td>取值：0401</td>
</tr>
<tr>
<td>银行编号</td>
<td>bankId</td>
<td>M</td>
<td>8位银行编码，详见数据字典</td>
</tr>
<tr>
<td>网关</td>
<td>gateway</td>
<td>M</td>
<td>daifu</td>
</tr>
<tr>
<td>商品标题</td>
<td>subject</td>
<td>M</td>
<td>上送报文时需BASE64编码，参与签名计算时不需要编码</td>
</tr>
<tr>
<td>商品描述</td>
<td>body</td>
<td>M</td>
<td>上送报文时需BASE64编码，参与签名计算时不需要编码</td>
</tr>
<tr>
<td>用途</td>
<td>purpose</td>
<td>O</td>
<td></td>
</tr>
</tbody>
</table>
<ul>
<li>实时返回结果(json)：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>执行结果</td>
<td>success</td>
<td>int</td>
<td>M</td>
<td>1：执行成功 0：执行失败, (注意此处非交易成功失败, 有可能向银行发请求在等待接受数据时候,发生网络故障,不能正常接受到返回数据,也为执行失败, 但交易有可能成功)</td>
</tr>
<tr>
<td>执行代码</td>
<td>code</td>
<td>int</td>
<td>M</td>
<td>参见执行代码说明</td>
</tr>
<tr>
<td>时间戳</td>
<td>timestamp</td>
<td>string</td>
<td>M</td>
<td>时间戳</td>
</tr>
<tr>
<td>签名</td>
<td>signature</td>
<td>string</td>
<td>M</td>
<td>参见签名规则</td>
</tr>
<tr>
<td>执行结果描述</td>
<td>msg</td>
<td>string</td>
<td>M</td>
<td></td>
</tr>
</tbody>
</table>
<ul>
<li>异步通知结果(GET参数)：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>必须验证签名正确性</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号，在商户系统中唯一</td>
</tr>
<tr>
<td>交易金额</td>
<td>txnAmt</td>
<td>M</td>
<td>单位分</td>
</tr>
<tr>
<td>应答码</td>
<td>respCode</td>
<td>M</td>
<td>订单状态码，参见状态码表</td>
</tr>
<tr>
<td>应答信息</td>
<td>respMsg</td>
<td>M</td>
<td>订单状态描述</td>
</tr>
</tbody>
</table>
<p>示例:</p>
<pre><code> http://epay.Site.com/callback/testbackend?merchantId=92900009502060400001&amp;merOrderId=20170810A45D34FG&amp;txnAmt=2&amp;respCode=1001&amp;respMsg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&amp;signature=qj93BF70V7UQfUF1kvTJMQ%3D%3D   
</code></pre>
<ul>
<li>code代码：</li>
</ul>
<table>
<thead>
<tr>
<th align="left">code</th>
<th align="left">说明</th>
</tr>
</thead>
<tbody>
<tr>
<td align="left">1000</td>
<td align="left">待支付</td>
</tr>
<tr>
<td align="left">1001</td>
<td align="left">支付成功</td>
</tr>
<tr>
<td align="left">1111</td>
<td align="left">交易进行中</td>
</tr>
<tr>
<td align="left">1002</td>
<td align="left">支付失败</td>
</tr>
<tr>
<td align="left">5001</td>
<td align="left">通信异常, 最终交易可能成功/失败</td>
</tr>
</tbody>
</table>
<p> </p>
<p> </p>
<h2 id="page_200">200.订单查询</h2>
<p>通过该接口用户可以查询订单状态，商户发起一笔交易查询到支付平台，支付平台返回该笔支付的相关信息</p>
<ul>
<li>线上：<code>http://epay.Site.com/order/status</code>
</li>
<li>测试地址：<code>http://epay-testing.Site.com/order/status</code>
</li>
<li>请求方式：<code>POST</code>请求</li>
<li>传参方式：<code>form表单</code>提交<code>POST</code>参数</li>
<li>请求参数：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名方法</td>
<td>signMethod</td>
<td>M</td>
<td>签名方法</td>
</tr>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>签名信息，详见签名机制</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号</td>
</tr>
</tbody>
</table>
<ul>
<li>返回结果(json)：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>执行结果</td>
<td>success</td>
<td>M</td>
<td>success为1的时候可以解析业务相关字段（merchantId，merOrderId，txnAmt，status，statusDesc）<br>为0 的时候解析code（错误码）及msg（错误消息）一般而言msg会说明出错的结果</td>
</tr>
<tr>
<td>执行代码</td>
<td>code</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>时间戳</td>
<td>timestamp</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>错误消息</td>
<td>msg</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>签名信息，详见签名机制</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>商户号</td>
</tr>
<tr>
<td>商户订单号</td>
<td>merOrderId</td>
<td>M</td>
<td>商户订单号</td>
</tr>
<tr>
<td>交易金额</td>
<td>txnAmt</td>
<td>M</td>
<td>订单金额</td>
</tr>
<tr>
<td>订单状态码</td>
<td>status</td>
<td>M</td>
<td>订单状态码</td>
</tr>
<tr>
<td>订单状态描述</td>
<td>statusDesc</td>
<td>M</td>
<td>订单状态描述</td>
</tr>
</tbody>
</table>
<ul>
<li>订单状态返代码status：</li>
</ul>
<table>
<thead>
<tr>
<th align="left">status</th>
<th align="left">说明</th>
</tr>
</thead>
<tbody>
<tr>
<td align="left">1000</td>
<td align="left">待支付</td>
</tr>
<tr>
<td align="left">1001</td>
<td align="left">支付成功</td>
</tr>
<tr>
<td align="left">1111</td>
<td align="left">交易进行中</td>
</tr>
<tr>
<td align="left">1002</td>
<td align="left">支付失败</td>
</tr>
</tbody>
</table>
<p> </p>
<p> </p>
<h2 id="page_201">201.余额查询</h2>
<p>通过该接口用户可以查看自己的商户余额</p>
<ul>
<li>线上：<code>http://epay.Site.com/balance</code>
</li>
<li>测试地址：<code>http://epay-testing.Site.com/balance</code>
</li>
<li>请求方式：<code>POST</code>请求</li>
<li>传参方式：<code>form表单</code>提交<code>POST</code>参数</li>
<li>请求参数：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>签名方法</td>
<td>signMethod</td>
<td>M</td>
<td>取值：MD5</td>
</tr>
<tr>
<td>签名信息</td>
<td>signature</td>
<td>M</td>
<td>签名信息，详见签名机制</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>M</td>
<td>由支付平台分配给商户的唯一编号</td>
</tr>
</tbody>
</table>
<ul>
<li>返回结果(json)：</li>
</ul>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
<th>类型</th>
<th>是否必传(Must,Optional)</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>执行结果</td>
<td>success</td>
<td>int</td>
<td>M</td>
<td>success为1的时候可以解析业务相关字段（merchantId，balance）<br>为0 的时候解析code（错误码）及msg（错误消息）一般而言msg会说明出错的结果，不需要关心code的值</td>
</tr>
<tr>
<td>执行代码</td>
<td>code</td>
<td>int</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>时间戳</td>
<td>timestamp</td>
<td>string</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>签名</td>
<td>signature</td>
<td>string</td>
<td>M</td>
<td>参见签名规则</td>
</tr>
<tr>
<td>执行结果描述</td>
<td>msg</td>
<td>string</td>
<td>M</td>
<td></td>
</tr>
<tr>
<td>余额</td>
<td>balance</td>
<td>string</td>
<td>M</td>
<td>分为单位</td>
</tr>
<tr>
<td>商户号</td>
<td>merchantId</td>
<td>string</td>
<td>M</td>
<td>商户平台分配的商户号</td>
</tr>
</tbody>
</table>
<p> </p>
<!--
## 301.银行卡认证
通过该接口可以认证用户身份信息是否正确

* 线上：`http://epay.Site.com/auth`
* 测试地址：`http://epay-testing.Site.com/auth`
* 方法：`POST`请求， `form表单`提交参数
* 请求参数：

| 报文域| 变量命名 | 是否必传(Must,Optional) | 说明 |
| ------| ------ | ------ |-----|
|签名方法|signMethod|M|取值：MD5|
|签名信息|signature|M|签名信息，详见签名机制|
|商户号|merchantId|M|由支付平台分配给商户的唯一编号|
|认证类型|authType|M|2：银行卡二要素认证（姓名+银行卡号）<br>3：银行卡三要素认证（姓名+银行卡号+身份证号）<br>4：银行卡四要素认证（姓名+银行卡号+身份证号+手机号）|
|姓名|customerNm|M|姓名|
|银行卡号|accNo|M|银行卡号|
|身份证号|idNo|O|身份证号，当authType=3的时候，必传|
|手机号|phoneNo|O|手机号，当authType=4的时候，必传|

* 返回结果(json)：

| 报文域| 变量命名 | 是否必传(Must,Optional) | 说明 |
| ------| ------ | ------ |-----|
|执行结果|success|M|success为1的时候可以解析业务相关字段（merchantId，merOrderId，txnAmt，status，statusDesc）<br>为0 的时候解析code（错误码）及msg（错误消息）一般而言msg会说明出错的结果|
|执行代码|code|M||
|时间戳|timestamp|M||
|错误消息|msg|M||
|签名信息|signature|M|签名信息，详见签名机制|
|认证代码|respCode|M|00认证成功，其他代码详见附录101.3|
|认证信息|respMsg|M|详见附录101.3|
|银行卡类型|CardType|M|1表示借记卡<br>2表示贷记卡<br>3表示准贷记卡<br>4表示预付费卡|
|银行名称|bankName|M|银行卡对应银行名称|

&nbsp;
-->
<h2 id="page_1001_Others">1001 Others附录</h2>
<h3 id="page_1011">101.1 状态码</h3>
<table>
<thead>
<tr>
<th>应答码</th>
<th>应答描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>0000</td>
<td>接受通知成功（异步交易时才会出现）</td>
</tr>
<tr>
<td>0001</td>
<td>参数错误</td>
</tr>
<tr>
<td>0002</td>
<td>签名错误</td>
</tr>
<tr>
<td>1000</td>
<td>订单待支付</td>
</tr>
<tr>
<td>1001</td>
<td>交易成功</td>
</tr>
<tr>
<td>1002</td>
<td>交易失败</td>
</tr>
<tr>
<td>1003</td>
<td>已退款</td>
</tr>
<tr>
<td>1111</td>
<td>交易进行中</td>
</tr>
<tr>
<td>2000</td>
<td>无效商户</td>
</tr>
<tr>
<td>2001</td>
<td>重复交易</td>
</tr>
<tr>
<td>2002</td>
<td>查无此交易</td>
</tr>
<tr>
<td>2003</td>
<td>交易金额超限</td>
</tr>
<tr>
<td>2004</td>
<td>原交易不存在或状态不正确</td>
</tr>
<tr>
<td>2005</td>
<td>与原交易信息不符</td>
</tr>
<tr>
<td>2006</td>
<td>已超过最大查询次数或操作过于频繁</td>
</tr>
<tr>
<td>2007</td>
<td>风控受限</td>
</tr>
<tr>
<td>2008</td>
<td>交易不在受理时间范围内</td>
</tr>
<tr>
<td>2009</td>
<td>扣款成功但交易超过指定支付时间</td>
</tr>
<tr>
<td>2010</td>
<td>订单金额不匹配</td>
</tr>
<tr>
<td>2011</td>
<td>订单币种不匹配</td>
</tr>
<tr>
<td>2012</td>
<td>卡信息或银行预留手机号有误</td>
</tr>
<tr>
<td>2013</td>
<td>密码错误次数超限</td>
</tr>
<tr>
<td>2014</td>
<td>密码验证失败</td>
</tr>
<tr>
<td>2015</td>
<td>短信验证码错误</td>
</tr>
<tr>
<td>2016</td>
<td>CVN验证失败</td>
</tr>
<tr>
<td>2017</td>
<td>身份证号有误</td>
</tr>
<tr>
<td>2018</td>
<td>短信验证码发送次数超限</td>
</tr>
<tr>
<td>2019</td>
<td>短信验证码发送频率过高</td>
</tr>
<tr>
<td>2020</td>
<td>短信验证码验证错误次数超限</td>
</tr>
<tr>
<td>2021</td>
<td>短信校验码已过期</td>
</tr>
<tr>
<td>2022</td>
<td>姓名有误</td>
</tr>
<tr>
<td>2023</td>
<td>银行预留手机号有误</td>
</tr>
<tr>
<td>2024</td>
<td>银行卡无效或状态有误</td>
</tr>
<tr>
<td>2025</td>
<td>余额不足</td>
</tr>
<tr>
<td>2026</td>
<td>请持卡人与发卡银行联系</td>
</tr>
<tr>
<td>5001</td>
<td>支付渠道网络异常, 具体状态待查询</td>
</tr>
<tr>
<td>9999</td>
<td>系统繁忙</td>
</tr>
</tbody>
</table>
<p> </p>
<p> </p>
<h3 id="page_1012">101.2 银行字典</h3>
<table>
<thead>
<tr>
<th>报文域</th>
<th>变量命名</th>
</tr>
</thead>
<tbody>
<tr>
<td>邮储银行</td>
<td>01000000</td>
</tr>
<tr>
<td>徽商银行</td>
<td>04403600</td>
</tr>
<tr>
<td>工商银行</td>
<td>01020000</td>
</tr>
<tr>
<td>农业银行</td>
<td>01030000</td>
</tr>
<tr>
<td>中国银行</td>
<td>01040000</td>
</tr>
<tr>
<td>建设银行</td>
<td>01050000</td>
</tr>
<tr>
<td>交通银行</td>
<td>03010000</td>
</tr>
<tr>
<td>中信银行</td>
<td>03020000</td>
</tr>
<tr>
<td>光大银行</td>
<td>03030000</td>
</tr>
<tr>
<td>华夏银行</td>
<td>03040000</td>
</tr>
<tr>
<td>民生银行</td>
<td>03050000</td>
</tr>
<tr>
<td>广发银行</td>
<td>03060000</td>
</tr>
<tr>
<td>深发银行</td>
<td>03070000</td>
</tr>
<tr>
<td>招商银行</td>
<td>03080000</td>
</tr>
<tr>
<td>兴业银行</td>
<td>03090000</td>
</tr>
<tr>
<td>浦发银行</td>
<td>03100000</td>
</tr>
<tr>
<td>恒丰银行</td>
<td>03110000</td>
</tr>
<tr>
<td>齐鲁银行</td>
<td>03134500</td>
</tr>
<tr>
<td>浙商银行</td>
<td>03160000</td>
</tr>
<tr>
<td>上海银行</td>
<td>04012900</td>
</tr>
<tr>
<td>厦门银行</td>
<td>04023930</td>
</tr>
<tr>
<td>北京银行</td>
<td>04031000</td>
</tr>
<tr>
<td>烟台银行</td>
<td>04044560</td>
</tr>
<tr>
<td>长春银行</td>
<td>04062410</td>
</tr>
<tr>
<td>镇江银行</td>
<td>04073140</td>
</tr>
<tr>
<td>宁波银行</td>
<td>04083320</td>
</tr>
<tr>
<td>济南银行</td>
<td>04094510</td>
</tr>
<tr>
<td>平安银行</td>
<td>04100000</td>
</tr>
<tr>
<td>深圳银行</td>
<td>04105840</td>
</tr>
<tr>
<td>焦作银行</td>
<td>04115010</td>
</tr>
<tr>
<td>温州银行</td>
<td>04123330</td>
</tr>
<tr>
<td>广州银行</td>
<td>04135810</td>
</tr>
<tr>
<td>武汉银行（汉口银行）</td>
<td>04145210</td>
</tr>
<tr>
<td>恒丰银行</td>
<td>04154560</td>
</tr>
<tr>
<td>齐齐哈尔银行</td>
<td>04162640</td>
</tr>
<tr>
<td>沈阳银行</td>
<td>04172210</td>
</tr>
<tr>
<td>洛阳银行</td>
<td>04184930</td>
</tr>
<tr>
<td>辽阳银行</td>
<td>04192310</td>
</tr>
<tr>
<td>大连银行</td>
<td>04202220</td>
</tr>
<tr>
<td>苏州银行</td>
<td>04213050</td>
</tr>
<tr>
<td>石家庄银行</td>
<td>04221210</td>
</tr>
<tr>
<td>杭州银行</td>
<td>04233310</td>
</tr>
<tr>
<td>南京银行</td>
<td>04243010</td>
</tr>
<tr>
<td>东莞银行</td>
<td>04256020</td>
</tr>
<tr>
<td>金华银行</td>
<td>04263380</td>
</tr>
<tr>
<td>乌鲁木齐银行</td>
<td>04278810</td>
</tr>
<tr>
<td>绍兴银行</td>
<td>04283370</td>
</tr>
<tr>
<td>成都银行</td>
<td>04296510</td>
</tr>
<tr>
<td>抚顺银行</td>
<td>04302240</td>
</tr>
<tr>
<td>临沂银行</td>
<td>04314730</td>
</tr>
<tr>
<td>宜昌银行</td>
<td>04325250</td>
</tr>
<tr>
<td>葫芦岛银行</td>
<td>04332350</td>
</tr>
<tr>
<td>天津银行</td>
<td>04341100</td>
</tr>
<tr>
<td>郑州银行</td>
<td>04354910</td>
</tr>
<tr>
<td>银川银行</td>
<td>04368710</td>
</tr>
<tr>
<td>珠海银行</td>
<td>04375850</td>
</tr>
<tr>
<td>淄博银行</td>
<td>04384530</td>
</tr>
<tr>
<td>锦州银行</td>
<td>04392270</td>
</tr>
<tr>
<td>合肥银行</td>
<td>04403610</td>
</tr>
<tr>
<td>重庆银行</td>
<td>04416530</td>
</tr>
<tr>
<td>哈尔滨银行</td>
<td>04422610</td>
</tr>
<tr>
<td>贵阳银行</td>
<td>04437010</td>
</tr>
<tr>
<td>西安银行</td>
<td>04447910</td>
</tr>
<tr>
<td>无锡银行</td>
<td>04453020</td>
</tr>
<tr>
<td>丹东银行</td>
<td>04462260</td>
</tr>
<tr>
<td>兰州银行</td>
<td>04478210</td>
</tr>
<tr>
<td>南昌银行（江西银行）</td>
<td>04484210</td>
</tr>
<tr>
<td>太原银行</td>
<td>04491610</td>
</tr>
<tr>
<td>青岛银行</td>
<td>04504520</td>
</tr>
<tr>
<td>吉林银行</td>
<td>04512420</td>
</tr>
<tr>
<td>南通银行</td>
<td>04523060</td>
</tr>
<tr>
<td>扬州银行</td>
<td>04533120</td>
</tr>
<tr>
<td>九江银行</td>
<td>04544240</td>
</tr>
<tr>
<td>日照银行</td>
<td>04554732</td>
</tr>
<tr>
<td>鞍山银行</td>
<td>04562230</td>
</tr>
<tr>
<td>秦皇岛银行</td>
<td>04571260</td>
</tr>
<tr>
<td>西宁银行</td>
<td>04588510</td>
</tr>
<tr>
<td>台州银行</td>
<td>04593450</td>
</tr>
<tr>
<td>盐城银行</td>
<td>04603110</td>
</tr>
<tr>
<td>长沙银行</td>
<td>04615510</td>
</tr>
<tr>
<td>潍坊银行</td>
<td>04624580</td>
</tr>
<tr>
<td>赣州银行</td>
<td>04634280</td>
</tr>
<tr>
<td>泉州银行</td>
<td>04643970</td>
</tr>
<tr>
<td>营口银行</td>
<td>04652280</td>
</tr>
<tr>
<td>昆明银行</td>
<td>04667310</td>
</tr>
<tr>
<td>阜新银行</td>
<td>04672290</td>
</tr>
<tr>
<td>常州银行</td>
<td>04683040</td>
</tr>
<tr>
<td>淮安银行</td>
<td>04693080</td>
</tr>
<tr>
<td>嘉兴银行</td>
<td>04703350</td>
</tr>
<tr>
<td>芜湖银行</td>
<td>04713620</td>
</tr>
<tr>
<td>廊坊银行</td>
<td>04721460</td>
</tr>
<tr>
<td>浙江泰隆商业银行</td>
<td>04733450</td>
</tr>
<tr>
<td>呼和浩特市城银行</td>
<td>04741900</td>
</tr>
<tr>
<td>呼和浩特市城银行</td>
<td>04741910</td>
</tr>
<tr>
<td>湖州银行</td>
<td>04753360</td>
</tr>
<tr>
<td>马鞍山银行</td>
<td>04773650</td>
</tr>
<tr>
<td>南宁银行</td>
<td>04786110</td>
</tr>
<tr>
<td>包头银行</td>
<td>04791920</td>
</tr>
<tr>
<td>连云港银行</td>
<td>04803070</td>
</tr>
<tr>
<td>威海银行</td>
<td>04814650</td>
</tr>
<tr>
<td>淮北银行</td>
<td>04823660</td>
</tr>
<tr>
<td>攀枝花银行</td>
<td>04836560</td>
</tr>
<tr>
<td>安庆银行</td>
<td>04843680</td>
</tr>
<tr>
<td>绵阳银行</td>
<td>04856590</td>
</tr>
<tr>
<td>泸州银行</td>
<td>04866570</td>
</tr>
<tr>
<td>大同银行</td>
<td>04871620</td>
</tr>
<tr>
<td>三门峡城市信用社</td>
<td>04885050</td>
</tr>
<tr>
<td>湛江银行</td>
<td>04895910</td>
</tr>
<tr>
<td>张家口银行</td>
<td>04901380</td>
</tr>
<tr>
<td>桂林银行</td>
<td>04916170</td>
</tr>
<tr>
<td>大庆银行</td>
<td>04922690</td>
</tr>
<tr>
<td>靖江市长江城市信用社</td>
<td>04933123</td>
</tr>
<tr>
<td>徐州银行</td>
<td>04943030</td>
</tr>
<tr>
<td>柳州银行</td>
<td>04956140</td>
</tr>
<tr>
<td>南充银行</td>
<td>04966730</td>
</tr>
<tr>
<td>邯郸市商业银行</td>
<td>05171270</td>
</tr>
<tr>
<td>上海农村银行</td>
<td>14012900</td>
</tr>
<tr>
<td>昆山市农村信用合作社联合社</td>
<td>14023052</td>
</tr>
<tr>
<td>常熟市农村银行</td>
<td>14033055</td>
</tr>
<tr>
<td>深圳市农村信用合作社联合社</td>
<td>14045840</td>
</tr>
<tr>
<td>广州市农村信用合作社联合社</td>
<td>14055810</td>
</tr>
<tr>
<td>杭州市萧山区农村信用合作社联合社</td>
<td>14063317</td>
</tr>
<tr>
<td>南海市农村信用合作社联合社</td>
<td>14075882</td>
</tr>
<tr>
<td>顺德市农村信用合作社联合社</td>
<td>14085883</td>
</tr>
<tr>
<td>昆明市农村信用合作社联合社</td>
<td>14097310</td>
</tr>
<tr>
<td>武汉市农村信用合作社联合社</td>
<td>14105210</td>
</tr>
<tr>
<td>徐州市市郊农村信用合作社联合社</td>
<td>14113030</td>
</tr>
<tr>
<td>江阴市农村银行</td>
<td>14123022</td>
</tr>
<tr>
<td>重庆市农村信用合作社联合社</td>
<td>14136530</td>
</tr>
<tr>
<td>山东省市农村信用社</td>
<td>14144500</td>
</tr>
<tr>
<td>青岛农村信用社</td>
<td>14144520</td>
</tr>
<tr>
<td>东莞农村信用合作社联合社</td>
<td>14156020</td>
</tr>
<tr>
<td>张家港市农村银行</td>
<td>14163056</td>
</tr>
<tr>
<td>厦门市农村信用合作社联合社</td>
<td>14173930</td>
</tr>
<tr>
<td>北京农村信用联社</td>
<td>14181000</td>
</tr>
<tr>
<td>天津市农村信用合作社联合社</td>
<td>14191100</td>
</tr>
<tr>
<td>宁波鄞州农村合作银行</td>
<td>14203320</td>
</tr>
<tr>
<td>佛山市三水区农村信用合作社联合社</td>
<td>14215881</td>
</tr>
<tr>
<td>成都市农村信用合作社联合社</td>
<td>14226510</td>
</tr>
<tr>
<td>沧州市农村信用合作社联合社</td>
<td>14231440</td>
</tr>
<tr>
<td>江苏省农村信用合作社联合社</td>
<td>14243000</td>
</tr>
<tr>
<td>江门市新会农村信用合作社联合社</td>
<td>14255890</td>
</tr>
<tr>
<td>高要市农村信用合作社联合社</td>
<td>14265930</td>
</tr>
<tr>
<td>佛山市禅城区农村信用社联合社</td>
<td>14275880</td>
</tr>
<tr>
<td>江苏吴江农村银行</td>
<td>14283050</td>
</tr>
<tr>
<td>浙江省农村信用社联合社</td>
<td>14293300</td>
</tr>
<tr>
<td>江苏东吴农村银行</td>
<td>14303050</td>
</tr>
<tr>
<td>珠海市农村信用合作社联合社</td>
<td>14315850</td>
</tr>
<tr>
<td>中山农村信用合作社联合社</td>
<td>14326030</td>
</tr>
<tr>
<td>江苏太仓农村银行股份有限公司</td>
<td>14333051</td>
</tr>
<tr>
<td>临汾市尧都区信用合作社联合社</td>
<td>14341770</td>
</tr>
<tr>
<td>江苏武进农村银行股份有限公司</td>
<td>14353041</td>
</tr>
<tr>
<td>贵州省农村信用合作作联合社</td>
<td>14367000</td>
</tr>
<tr>
<td>江苏锡州农村银行股份有限公司</td>
<td>14373020</td>
</tr>
<tr>
<td>湖南省农村信用社联合社</td>
<td>14385500</td>
</tr>
<tr>
<td>江西农信联合社</td>
<td>14394200</td>
</tr>
<tr>
<td>河南省农村信用社联合社</td>
<td>14404900</td>
</tr>
<tr>
<td>河北省农村信用社联合社</td>
<td>14411200</td>
</tr>
<tr>
<td>陕西省农村信用社联合社</td>
<td>14427900</td>
</tr>
<tr>
<td>广西农村信用社联合社</td>
<td>14436100</td>
</tr>
<tr>
<td>新疆维吾尔自治区农村信用社联合</td>
<td>14448800</td>
</tr>
<tr>
<td>吉林农信联合社</td>
<td>14452400</td>
</tr>
<tr>
<td>黄河农村银行</td>
<td>14468700</td>
</tr>
<tr>
<td>安徽省农村信用社联合社</td>
<td>14473600</td>
</tr>
<tr>
<td>海南省农村信用社联合社</td>
<td>14486400</td>
</tr>
<tr>
<td>青海省农村信用社联合社</td>
<td>14498500</td>
</tr>
<tr>
<td>广东省农村信用社联合社</td>
<td>14505800</td>
</tr>
<tr>
<td>内蒙古自治区农村信用社联合式</td>
<td>14511900</td>
</tr>
<tr>
<td>四川省农村信用社联合社</td>
<td>14526500</td>
</tr>
<tr>
<td>甘肃省农村信用社联合社</td>
<td>14538200</td>
</tr>
<tr>
<td>辽宁省农村信用社联合社</td>
<td>14542200</td>
</tr>
<tr>
<td>山西省农村信用社联合社</td>
<td>14551600</td>
</tr>
<tr>
<td>天津滨海农村银行</td>
<td>14561100</td>
</tr>
<tr>
<td>黑龙江省农村信用社联合社</td>
<td>14572600</td>
</tr>
<tr>
<td>武汉农村银行</td>
<td>14595210</td>
</tr>
<tr>
<td>江南农村银行</td>
<td>14603040</td>
</tr>
<tr>
<td>大邑交银兴民村镇银行</td>
<td>15006518</td>
</tr>
<tr>
<td>湖北嘉鱼吴江村镇银行</td>
<td>15015363</td>
</tr>
<tr>
<td>青岛即墨北农商村镇银行</td>
<td>15024521</td>
</tr>
<tr>
<td>湖北仙桃北农商村镇银行</td>
<td>15025371</td>
</tr>
<tr>
<td>海口苏南村镇银行</td>
<td>15036410</td>
</tr>
<tr>
<td>双流诚民村镇银行</td>
<td>15036512</td>
</tr>
<tr>
<td>宣汉诚民村镇银行</td>
<td>15036753</td>
</tr>
<tr>
<td>福建建瓯石狮村镇银行</td>
<td>15044015</td>
</tr>
<tr>
<td>恩施常农商村镇银行</td>
<td>15055411</td>
</tr>
<tr>
<td>咸丰常农商村镇银行</td>
<td>15055416</td>
</tr>
<tr>
<td>浙江长兴联合村镇银行</td>
<td>15083362</td>
</tr>
<tr>
<td>浙江平湖工银村镇银行</td>
<td>15103352</td>
</tr>
<tr>
<td>重庆璧山工银村镇银行</td>
<td>15106919</td>
</tr>
<tr>
<td>北京密云汇丰村镇银行</td>
<td>15111027</td>
</tr>
<tr>
<td>湖北随州曾都汇丰村镇银行</td>
<td>15115270</td>
</tr>
<tr>
<td>重庆大足汇丰村镇银行有限责任公司</td>
<td>15116917</td>
</tr>
<tr>
<td>江苏沭阳东吴村镇银行</td>
<td>15123181</td>
</tr>
<tr>
<td>重庆农村银行</td>
<td>15136900</td>
</tr>
<tr>
<td>方大村镇银行</td>
<td>15142080</td>
</tr>
<tr>
<td>深圳龙岗鼎业村镇银行</td>
<td>15145840</td>
</tr>
<tr>
<td>中山小榄村镇银行</td>
<td>15156030</td>
</tr>
<tr>
<td>江苏邗江民泰村镇银行</td>
<td>15173120</td>
</tr>
<tr>
<td>安徽当涂新华村镇银行</td>
<td>15183651</td>
</tr>
<tr>
<td>广州番禹新华村镇银行</td>
<td>15185810</td>
</tr>
<tr>
<td>沂水中银富登村镇银行</td>
<td>15194737</td>
</tr>
<tr>
<td>京山中银富登村镇银行</td>
<td>15195321</td>
</tr>
<tr>
<td>蕲春中银富登村镇银行</td>
<td>15195338</td>
</tr>
<tr>
<td>北京顺义银座村镇银行</td>
<td>15201000</td>
</tr>
<tr>
<td>江西赣州银座村镇银行</td>
<td>15204280</td>
</tr>
<tr>
<td>深圳福田银座村镇银行</td>
<td>15205840</td>
</tr>
<tr>
<td>北京怀柔融兴村镇银行</td>
<td>15211000</td>
</tr>
<tr>
<td>深圳宝安融兴村镇银行</td>
<td>15215840</td>
</tr>
<tr>
<td>南阳村镇银行</td>
<td>15265130</td>
</tr>
<tr>
<td>重庆三峡银行</td>
<td>05426900</td>
</tr>
<tr>
<td>晋中银行</td>
<td>05591750</td>
</tr>
<tr>
<td>宁波通商银行</td>
<td>05803320</td>
</tr>
<tr>
<td>江苏银行</td>
<td>05083000</td>
</tr>
<tr>
<td>渤海银行</td>
<td>03170000</td>
</tr>
<tr>
<td>上海农商行</td>
<td>14012900</td>
</tr>
<tr>
<td>包商银行</td>
<td>04791920</td>
</tr>
<tr>
<td>福建海峡银行</td>
<td>04053910</td>
</tr>
<tr>
<td>富滇银行</td>
<td>64667310</td>
</tr>
<tr>
<td>广东南粤银行</td>
<td>64895910</td>
</tr>
<tr>
<td>汉口银行（武汉银行）</td>
<td>04145210</td>
</tr>
<tr>
<td>江西银行（南昌银行）</td>
<td>04484210</td>
</tr>
<tr>
<td>昆山农村商业银行</td>
<td>14023052</td>
</tr>
<tr>
<td>吴江农商行</td>
<td>14283054</td>
</tr>
</tbody>
</table>
<p> 
 </p>
<h3 id="page_1013">101.3 银行卡认证应答码</h3>
<table>
<thead>
<tr>
<th>返回码</th>
<th>返回信息说明</th>
<th>认证结论</th>
</tr>
</thead>
<tbody>
<tr>
<td>00</td>
<td>认证一致，成功</td>
<td>一致</td>
</tr>
<tr>
<td>01</td>
<td>发卡行不支持此笔交易</td>
<td>交易异常</td>
</tr>
<tr>
<td>03</td>
<td>认证不一致，发卡行不支持此笔交易</td>
<td>认证不一致</td>
</tr>
<tr>
<td>04</td>
<td>认证不一致，此卡被没收</td>
<td>认证不一致</td>
</tr>
<tr>
<td>05</td>
<td>认证不一致，持卡人信息有误</td>
<td>认证不一致</td>
</tr>
<tr>
<td>06</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>07</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>12</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>13</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>33</td>
<td>认证不一致，此卡已过期</td>
<td>认证不一致</td>
</tr>
<tr>
<td>34</td>
<td>认证不一致，作弊卡、吞卡</td>
<td>认证不一致</td>
</tr>
<tr>
<td>35</td>
<td>认证不一致,卡状态异常</td>
<td>认证不一致</td>
</tr>
<tr>
<td>36</td>
<td>认证不一致，受限制的卡</td>
<td>认证不一致</td>
</tr>
<tr>
<td>38</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>40</td>
<td>认证不一致，未开通无卡支付</td>
<td>认证不一致</td>
</tr>
<tr>
<td>41</td>
<td>认证不一致,此卡已挂失</td>
<td>认证不一致</td>
</tr>
<tr>
<td>42</td>
<td>认证不一致，无此账户</td>
<td>认证不一致</td>
</tr>
<tr>
<td>43</td>
<td>认证不一致，被窃卡</td>
<td>认证不一致</td>
</tr>
<tr>
<td>45</td>
<td>认证不一致,发卡行不支持此笔交易</td>
<td>认证不一致</td>
</tr>
<tr>
<td>51</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>54</td>
<td>认证不一致，此卡已过期</td>
<td>认证不一致</td>
</tr>
<tr>
<td>55</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>56</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>57</td>
<td>认证不一致,发卡行不支持此交易</td>
<td>认证不一致</td>
</tr>
<tr>
<td>59</td>
<td>认证不一致,认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>61</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>62</td>
<td>认证不一致,受限制的卡</td>
<td>认证不一致</td>
</tr>
<tr>
<td>64</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>65</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>67</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>75</td>
<td>认证不一致,密码错误次数超限</td>
<td>认证不一致</td>
</tr>
<tr>
<td>A1</td>
<td>您的银行卡暂不支持手机号验证</td>
<td>认证不一致</td>
</tr>
<tr>
<td>IL</td>
<td>认证不一致，无法识别的卡</td>
<td>认证不一致</td>
</tr>
<tr>
<td>LM</td>
<td>交易次数超限或操作过频</td>
<td>交易异常</td>
</tr>
<tr>
<td>ER</td>
<td>交易异常</td>
<td>交易异常</td>
</tr>
<tr>
<td>FL</td>
<td>认证不一致，认证未通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>OT</td>
<td>交易超时</td>
<td>交易异常</td>
</tr>
<tr>
<td>MP</td>
<td>您的银行卡未预留手机号</td>
<td>认证不一致</td>
</tr>
<tr>
<td>CT</td>
<td>无效证件类型</td>
<td>认证不一致</td>
</tr>
<tr>
<td>ID</td>
<td>认证不一致，身份证号错误</td>
<td>认证不一致</td>
</tr>
<tr>
<td>NM</td>
<td>认证不一致，姓名校验不通过</td>
<td>认证不一致</td>
</tr>
<tr>
<td>CD</td>
<td>认证不一致，银行卡号码有误</td>
<td>认证不一致</td>
</tr>
<tr>
<td>SJ</td>
<td>认证不一致，手机号码不合法</td>
<td>认证不一致</td>
</tr>
<tr>
<td>DE</td>
<td>请求数据有误</td>
<td>交易异常</td>
</tr>
<tr>
<td>SF</td>
<td>验签失败</td>
<td>交易异常</td>
</tr>
<tr>
<td>PD</td>
<td>权限不足</td>
<td>交易异常</td>
</tr>
<tr>
<td>AR</td>
<td>账户余额不足</td>
<td>交易异常</td>
</tr>
<tr>
<td>NS</td>
<td>该卡暂不支持认证</td>
<td>交易异常</td>
</tr>
<tr>
<td>SE</td>
<td>系统异常</td>
<td>交易异常</td>
</tr>
<tr>
<td>81</td>
<td>手机号与银行预留信息不一致</td>
<td>认证不一致</td>
</tr>
<tr>
<td>82</td>
<td>证件号与银行预留信息不一致</td>
<td>认证不一致</td>
</tr>
<tr>
<td>83</td>
<td>姓名与银行卡预留信息不一致</td>
<td>认证不一致</td>
</tr>
<tr>
<td>84</td>
<td>身份证号错误</td>
<td>认证不一致</td>
</tr>
<tr>
<td>85</td>
<td>银行卡号错误</td>
<td>认证不一致</td>
</tr>
</tbody>
</table>
    </div>

        <nav>
        <ul class="Pager">
            <li class=Pager--prev><a href="//merchant.Site.com/docs/index">Previous</a></li>            <li class=Pager--next><a href="//merchant.Site.com/docs/NovoxPay_PHP_Demos">Next</a></li>        </ul>
    </nav>
    </article>

            </div>
        </div>
    </div>
</div>

    
    <!-- jQuery -->
    <script src="js/jquery-1.11.3.min_1.js"></script>

    <!-- hightlight.js -->
    <script src="js/highlight.pack_1.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

    <!-- JS -->
    
    <script src="js/daux_1.js"></script>

    
</body>
</html>
