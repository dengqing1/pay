
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--[if lt IE 7]>       <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>          <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>          <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->  <html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <title>MySite PHP Demos - MySite Docs</title>
    <meta name="description" content="The Easiest Way To Document Your Project" />
    <meta name="author" content="No One">
    <meta charset="UTF-8">
    <link rel="icon" href="//merchant.MySite.com/docs/themes/daux/img/favicon-blue.png" type="image/x-icon">
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

<!--         <a class="Brand" href="//merchant.MySite.com/docs/index">Site Docs</a> -->
        <a class="Brand" href="../manager/api.do">Site Docs</a>


        <div class="Collapsible__content">
            <!-- Navigation -->
            <ul class='Nav'><li class='Nav__item '><a href="../manager/api.do">MySite</a></li><li class='Nav__item Nav__item--active'><a href="">MySite PHP Demos</a></li></ul>

            <div class="Links">
                
                
                            </div>
        </div>
    </aside>
    <div class="Columns__right ">
        <div class="Columns__right__content">
            <div class="doc_content">
                <article class="Page">

    <div class="Page__header">
        <h1><a href="../manager/apiphp.do">MySite PHP Demos</a></h1>
                <span style="float: left; font-size: 10px; color: gray;">
                        2018-09-20 18:53:42        </span>
                    </div>


    <div class="s-content">
        <ul class="TableOfContents">
<li>
<p><a href="#page_1">1. 网银支付预下单</a></p>
</li>
<li>
<p><a href="#page_2">2. 扫码支付</a></p>
</li>
<li>
<p><a href="#page_3_H5">3. H5</a></p>
</li>
<li>
<p><a href="#page_41">4.1. 快捷预下单</a></p>
</li>
<li>
<p><a href="#page_42">4.2. 快捷确定支付</a></p>
</li>
<li>
<p><a href="#page_5">5. 银联快捷支付预下单</a></p>
</li>
<li>
<p><a href="#page_100">100. 代付</a></p>
</li>
</ul>
<h2 id="page_1">1. 网银支付预下单</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['backUrl'] = 'http://url.backend';
$params['frontUrl'] = 'http://url.front';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['userId'] = '';
$params['accNo'] = '6222021001080862022';
$params['customerNm'] = '张三';
$params['phoneNo'] = '13012345678';
$params['customerIp'] = '101.95.99.18';

// 支付方式
$params['gateway'] = 'bank';
$params['bankId'] = '01020000';
$params['dcType'] = '0';

// 加密
$paramsNeedSign = site_base64($params, true);
$signature = site_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;
?&gt;

&lt;!DOCTYPE html PUBLIC &quot;-//W3C//DTD XHTML 1.0 Transitional//EN&quot;
        &quot;http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd&quot;&gt;
&lt;html xmlns=&quot;http://www.w3.org/1999/xhtml&quot;&gt;
&lt;head&gt;
    &lt;meta http-equiv=&quot;Content-Type&quot; content=&quot;text/html; charset=utf-8&quot;/&gt;
    &lt;style type=&quot;text/css&quot;&gt;
        html, body {
            height: 98%;
        }

        body {
            background-color: #FFFFFF;
        }

        body, td, div {
            font-family: verdana, arial, sans-serif;
        }
    &lt;/style&gt;
&lt;/head&gt;
&lt;body onLoad=&quot;return process();&quot;&gt;
&lt;table cellpadding=&quot;0&quot; width=&quot;100%&quot; height=&quot;100%&quot; cellspacing=&quot;0&quot; style=&quot;border:1px solid #003366;&quot;&gt;
    &lt;tr&gt;
        &lt;td align=&quot;center&quot; style=&quot;height:100%; vertical-align:middle;&quot;&gt;
            &lt;div style=&quot;margin:10px;padding:10px;&quot;&gt;
                &lt;form name=&quot;form&quot; method=&quot;post&quot; action=&quot;&lt;?php echo $url; ?&gt;&quot;&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;merchantId&quot; value=&quot;&lt;?php echo $params['merchantId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;merOrderId&quot; value=&quot;&lt;?php echo $params['merOrderId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;txnAmt&quot; value=&quot;&lt;?php echo $params['txnAmt']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;backUrl&quot; value=&quot;&lt;?php echo $params['backUrl']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;frontUrl&quot; value=&quot;&lt;?php echo $params['frontUrl']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;subject&quot; value=&quot;&lt;?php echo $params['subject']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;body&quot; value=&quot;&lt;?php echo $params['body']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;userId&quot; value=&quot;&lt;?php echo $params['userId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;accNo&quot; value=&quot;&lt;?php echo $params['accNo']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;customerNm&quot; value=&quot;&lt;?php echo $params['customerNm']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;phoneNo&quot; value=&quot;&lt;?php echo $params['phoneNo']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;customerIp&quot; value=&quot;&lt;?php echo $params['customerIp']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;gateway&quot; value=&quot;&lt;?php echo $params['gateway']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;bankId&quot; value=&quot;&lt;?php echo $params['bankId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;dcType&quot; value=&quot;&lt;?php echo $params['dcType']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;signMethod&quot; value=&quot;&lt;?php echo $params['signMethod']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;signature&quot; value=&quot;&lt;?php echo $params['signature']; ?&gt;&quot;/&gt;
                    &lt;button type='submit'&gt;提交请求&lt;/button&gt;
                &lt;/form&gt;
            &lt;/div&gt;
        &lt;/td&gt;
    &lt;/tr&gt;
&lt;/table&gt;
&lt;/body&gt;
&lt;/html&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_2">2. 扫码支付</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

function http_post_request($url, $params, $header = null) {
    $curl = curl_init($url);
    if ($header) {
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
    }
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $params);
    $res = curl_exec($curl);
    
    // curl失败
    if (curl_errno($curl) !== 0) {
        print_r(curl_error($curl));
        curl_close($curl);
        return false;
    }
    
    // curl成功
    curl_close($curl);    
    return $res;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['backUrl'] = 'http://url.backend';
$params['frontUrl'] = 'http://url.front';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['userId'] = '';
$params['merResv1'] = '';

// 支付方式
$params['gateway'] = 'wxpay';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;

// 发送请求
$res = http_post_request($url, http_build_query($params));

print_r($res);

?&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_3_H5">3. H5</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

function http_post_request($url, $params, $header = null) {
    $curl = curl_init($url);
    if ($header) {
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
    }
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $params);
    $res = curl_exec($curl);
    
    // curl失败
    if (curl_errno($curl) !== 0) {
        print_r(curl_error($curl));
        curl_close($curl);
        return false;
    }
    
    // curl成功
    curl_close($curl);    
    return $res;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['backUrl'] = 'http://url.backend';
$params['frontUrl'] = 'http://url.front';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['userId'] = '';
$params['merResv1'] = '';

// 支付方式
$params['gateway'] = 'wxpayh5';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;

// 发送请求
$res = http_post_request($url, http_build_query($params));

print_r($res);

?&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_41">4.1. 快捷预下单</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

function http_post_request($url, $params, $header = null) {
    $curl = curl_init($url);
    if ($header) {
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
    }
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $params);
    $res = curl_exec($curl);
    
    // curl失败
    if (curl_errno($curl) !== 0) {
        print_r(curl_error($curl));
        curl_close($curl);
        return false;
    }
    
    // curl成功
    curl_close($curl);    
    return $res;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['backUrl'] = 'http://url.backend';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['userId'] = '';
$params['merResv1'] = '';
$params['accNo'] = '6222021001080862011';
$params['customerNm'] = '张三';
$params['customerCertifyType'] = '01';
$params['customerCertifyId'] = '310000199901016512';
$params['expired'] = '';
$params['cvv2'] = '';
$params['phoneNo'] = '18812345678';

// 支付方式
$params['gateway'] = 'kuaijie';
$params['subGateway'] = 'prePay';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;

// 发送请求
$res = http_post_request($url, http_build_query($params));

print_r($res);

?&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_42">4.2. 快捷确定支付</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

function http_post_request($url, $params, $header = null) {
    $curl = curl_init($url);
    if ($header) {
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
    }
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $params);
    $res = curl_exec($curl);
    
    // curl失败
    if (curl_errno($curl) !== 0) {
        print_r(curl_error($curl));
        curl_close($curl);
        return false;
    }
    
    // curl成功
    curl_close($curl);    
    return $res;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = '20171225181013';
$params['tn'] = '2017122500181033311508';
$params['smsCode'] = '217751';

// 支付方式
$params['gateway'] = 'kuaijie';
$params['subGateway'] = 'doPay';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;

// 发送请求
$res = http_post_request($url, http_build_query($params));

print_r($res);

?&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_5">5. 银联快捷支付预下单</h2>
<pre><code>&lt;?php

function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['backUrl'] = 'http://url.backend';
$params['frontUrl'] = 'http://url.front';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['userId'] = '';
$params['merResv1'] = '';

// 支付方式
$params['gateway'] = 'kuaijie_unionpay';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;
?&gt;

&lt;!DOCTYPE html PUBLIC &quot;-//W3C//DTD XHTML 1.0 Transitional//EN&quot;
        &quot;http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd&quot;&gt;
&lt;html xmlns=&quot;http://www.w3.org/1999/xhtml&quot;&gt;
&lt;head&gt;
    &lt;meta http-equiv=&quot;Content-Type&quot; content=&quot;text/html; charset=utf-8&quot;/&gt;
    &lt;style type=&quot;text/css&quot;&gt;
        html, body {
            height: 98%;
        }

        body {
            background-color: #FFFFFF;
        }

        body, td, div {
            font-family: verdana, arial, sans-serif;
        }
    &lt;/style&gt;
&lt;/head&gt;
&lt;body onLoad=&quot;return process();&quot;&gt;
&lt;table cellpadding=&quot;0&quot; width=&quot;100%&quot; height=&quot;100%&quot; cellspacing=&quot;0&quot; style=&quot;border:1px solid #003366;&quot;&gt;
    &lt;tr&gt;
        &lt;td align=&quot;center&quot; style=&quot;height:100%; vertical-align:middle;&quot;&gt;
            &lt;div style=&quot;margin:10px;padding:10px;&quot;&gt;
                &lt;form name=&quot;form&quot; method=&quot;post&quot; action=&quot;&lt;?php echo $url; ?&gt;&quot;&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;merchantId&quot; value=&quot;&lt;?php echo $params['merchantId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;merOrderId&quot; value=&quot;&lt;?php echo $params['merOrderId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;txnAmt&quot; value=&quot;&lt;?php echo $params['txnAmt']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;backUrl&quot; value=&quot;&lt;?php echo $params['backUrl']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;frontUrl&quot; value=&quot;&lt;?php echo $params['frontUrl']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;subject&quot; value=&quot;&lt;?php echo $params['subject']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;body&quot; value=&quot;&lt;?php echo $params['body']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;userId&quot; value=&quot;&lt;?php echo $params['userId']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;merResv1&quot; value=&quot;&lt;?php echo $params['merResv1']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;gateway&quot; value=&quot;&lt;?php echo $params['gateway']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;signMethod&quot; value=&quot;&lt;?php echo $params['signMethod']; ?&gt;&quot;/&gt;
                    &lt;input type=&quot;hidden&quot; name=&quot;signature&quot; value=&quot;&lt;?php echo $params['signature']; ?&gt;&quot;/&gt;
                    &lt;button type='submit'&gt;提交请求&lt;/button&gt;
                &lt;/form&gt;
            &lt;/div&gt;
        &lt;/td&gt;
    &lt;/tr&gt;
&lt;/table&gt;
&lt;/body&gt;
&lt;/html&gt;
</code></pre>
<p> </p>
<p> </p>
<h2 id="page_100">100. 代付</h2>
<pre><code>&lt;?php
    
function site_sign($params, $key) {
    ksort($params);
    $uri = urldecode(http_build_query($params));
    $uri = $uri . $key;
    $result = base64_encode(md5($uri, TRUE));
    return $result;
}

function site_base64($params, $decode = true) {
    $need_base64_fields = [
        'subject',
        'body',
    ];
    foreach ($need_base64_fields as $k) {
        if (array_key_exists($k, $params)) {
            if ($decode) {
                $params[$k] = base64_decode($params[$k]);
            } else {
                $params[$k] = base64_encode($params[$k]);
            }
        }
    }
    return $params;
}

function http_post_request($url, $params, $header = null) {
    $curl = curl_init($url);
    if ($header) {
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
    }
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $params);
    $res = curl_exec($curl);
    
    // curl失败
    if (curl_errno($curl) !== 0) {
        print_r(curl_error($curl));
        curl_close($curl);
        return false;
    }
    
    // curl成功
    curl_close($curl);    
    return $res;
}

// 秘钥
$key = '682807c82e2716452bd069aaf72d48dc';

// 请求地址
$url = 'http://epay-testing.MySite.com/pay';

// 参数
$params['version'] = '1.0.0';
$params['txnType'] = '12';
$params['txnSubType'] = '01';
$params['bizType'] = '000401';
$params['accessType'] = '0';
$params['accessMode'] = '01';
$params['merchantId'] = '600000000000002';
$params['merOrderId'] = date('YmdHis');
$params['ppFlag'] = '01';
$params['accNo'] = '6222021001080862011';
$params['customerNm'] = '张三';
$params['phoneNo'] = '13012345678';
$params['issInsName'] = '';
$params['txnTime'] = date('YmdHis');
$params['txnAmt'] = '1';
$params['currency'] = 'CNY';
$params['backUrl'] = 'http://url.backend';
$params['payType'] = '0401';
$params['bankId'] = '01020000';
$params['subject'] = base64_encode('标题');
$params['body'] = base64_encode('内容');
$params['purpose'] = '';
$params['merResv1'] = '';

// 支付方式
$params['gateway'] = 'daifu';

// 加密
$paramsNeedSign = novoxpay_base64($params, true);
$signature = novoxpay_sign($paramsNeedSign, $key);
$params['signMethod'] = 'MD5';
$params['signature'] = $signature;

// 发送请求
$res = http_post_request($url, http_build_query($params));

print_r($res);

?&gt;
</code></pre>
    </div>

        <nav>
        <ul class="Pager">
            <li class=Pager--prev><a href="//merchant.MySite.com/docs/MySite">Previous</a></li>                    </ul>
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
