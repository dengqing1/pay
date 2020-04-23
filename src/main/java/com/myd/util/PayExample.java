package com.myd.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static com.google.common.base.Charsets.UTF_8;


/**
 * Pay (支付下单）参数构建示例。参数数据签名与加密。
 *
 * 示例中的密钥均为当前测试使用，不要在生产环境中使用。
 *
 * @author Fuchun
 * @since 1.0
 */
public class PayExample {

    // 商户RSA私钥（请使用工具自行生成）
    private static final String MERCH_PRIVATE_KEY_TEXT = "" +
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK3UNsiU0oyqj2F3pR5QXJYXY56JqGyuMFwCNB" +
            "09gtiB3+B+dXZy/esKzjOEu9gQfbOyvuIcoEl1Oy0TrXJiMmFIX31isrpHoY6gvGG/KpayyeKW/ijfihzbpdvL" +
            "GbdABHfmwea4BoA5HaqgU7ugkeTKItMxqwBwiRi+3EBu5kKHAgMBAAECgYAc3wNeW0GBNY7s7FdTapuGHkpgeZ" +
            "Kswl2r2n3XYXELDr8lZTuTOdQsprXK3r/oSgzmvMhxe7mvmbYDbaJAPkkJkmKh7IssW2p9AruYoSjGH/zNqArw" +
            "1qQ+ToHzmUCS7yFenvjEwk/nzOSFma0zi9PrG2ytqnB5/AwQ60FBB4TUSQJBAPf4yYXi8wM7qvjKx05VjgQDcz" +
            "i8bVvATtU+Ax/0yde+PC7LyNMXgyjy+JosMvILs11hCnrEjlIe/2TsTAwBgXsCQQCzdOyPzzdd00OnXXN55p6A" +
            "d/w/jQq2NdOy6JWuyRs6gAA1YH9sjLmj2yTWUn9GCys1NgjMxOX1ibV104mX+ndlAkEAwxZQNa1fUuPWlvWx58" +
            "cXe8DbHX/g6ZZabxnii3micsGPx6E71hdhcQ322BR94D+kVYrLG9sXvTP16gidq7RjkQJAdzDJjhqLI34WCStA" +
            "b06FJ76Jg4wh+TN3D/T6tcRMmWY6vTkCaQ5uni4HIXJZpiP05rqkzZxZP+Pe6gs97/sjIQJBAO/YRJbNQAT/5R" +
            "Cb/LqaJtPe/KH2lbakwdmVHMP6bAesG492z3LyOfoqIkQnVMXffqmj+zfmjc9vxO2mlQcTGu0=";

    // 商户RSA公钥（请使用工具自行生成，并登录平台商户后台，密钥管理中，添加生成的商户公钥）
    private static final String MERCH_PUBLIC_KEY_TEXT = "" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt1DbIlNKMqo9hd6UeUFyWF2OeiahsrjBcAjQdPYLYgd/gfn" +
            "V2cv3rCs4zhLvYEH2zsr7iHKBJdTstE61yYjJhSF99YrK6R6GOoLxhvyqWssnilv4o34oc26Xbyxm3QAR35sHm" +
            "uAaAOR2qoFO7oJHkyiLTMasAcIkYvtxAbuZChwIDAQAB";

    /* 通信加密密钥（商户需要登录商户后台查看）*/
    private static final String SECRET_KEY = "Hm3fOmh9wjGBJIgHsNqMG2qzKSrnyN2DdWntDnEYXdE=";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36";

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private static final PrivateKey MERCH_PRIVATE_KEY;
    private static final PublicKey MERCH_PUBLIC_KEY;
    private static final PublicKey PLAT_PUBLIC_KEY;
    private static final Key AES_KEY;

    private static String loadPlatPublicKeyForPEM() {
        InputStream input = PayExample.class.getResourceAsStream("/test_public_key.pem");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder(input.available());
            char[] chars = new char[1024];
            while (reader.read(chars) != -1) {
                sb.append(chars);
            }
            return sb.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {}
            }
        }
    }

    private static PublicKey readPlatPublicKey() {
        String platPublicKeyPem = loadPlatPublicKeyForPEM();
        PEMParser parser = null;
        try {
            parser = new PEMParser(new StringReader(platPublicKeyPem));
            SubjectPublicKeyInfo publicKeyInfo = (SubjectPublicKeyInfo) parser.readObject();
            return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (parser != null) {
                try {
                    parser.close();
                } catch (IOException ignored) {}
            }
        }
    }

    static {
        MERCH_PRIVATE_KEY = RSA.toPrivateKey(MERCH_PRIVATE_KEY_TEXT);
        MERCH_PUBLIC_KEY = RSA.toPublicKey(MERCH_PUBLIC_KEY_TEXT);
        AES_KEY = AES.fromBase64(SECRET_KEY);

        PLAT_PUBLIC_KEY = readPlatPublicKey();
    }

    // 生成业务参数 (biz_data)
    private Map<String, String> buildBizData() {
        TreeMap<String, String> bizMap = new TreeMap<String, String>();
        // 商户生成的订单号
        bizMap.put("out_order_no", UUID.randomUUID().toString().replaceAll("-", ""));
        // 交易金额，单位"分"
        bizMap.put("amount", "10000");
        // 商户前端跳转地址
        bizMap.put("front_url", "http://api.test.net/front.htm");
        // 商户服务端回调通知地址
        bizMap.put("notify_url", "http://api.test.net/notify");
        // 商品名称（标题）
        bizMap.put("subject", "iphoneX");
        // 订单或商品的描述
        bizMap.put("body", "iphoneX 旗舰版");
        // 银行编码，详见银行编码表
        bizMap.put("bank_code", "BOC");
        // 银行账号（银行卡）
//        bizMap.put("acc_no", "6212261001065791540");
        // 银行账户名
//        bizMap.put("acc_name", "张三");
        // 银行账户预留手机号码
//        bizMap.put("mobile", "15000000000");
        // 商户端关联的Id（Optional）
//        bizMap.put("cust_id", "");
        // 终端用户IP
        bizMap.put("terminal_ip", "124.21.65.123");
        return bizMap;
    }

    private Map<String, String> buildPayParams() {

        Map<String, String> params = new TreeMap<String, String>();
        // 平台分配给商户的商户号
        params.put("merch_id", "PAY21100111000071");
        // 网银支付下单
        params.put("biz_code", "P100784");
        // 下单的时间戳：yyyyMMddHHmmss
        params.put("timestamp", "20171112173201");

        Map<String, String> bizMap = buildBizData();

        // 将业务参数生成JSON字符串
        String srcBizData = JSON.toJSONString(bizMap);

        // 先放入原业务 JSON 串，待签名后再加密
        params.put("biz_data", srcBizData);

        // 使用商户私钥对业务参数进行签名
        // 生成待签名的字符串
        StringBuilder sb = new StringBuilder(32);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // remove last &
        sb.deleteCharAt(sb.length() - 1);
        String content = sb.toString();
        System.out.printf("Source biz_data = %s%n", content);

        String sign = RSA.signBase64(content, MERCH_PRIVATE_KEY);

        params.put("sign", sign);

        // 业务参数集合（JSON格式）的加密数据，最大长度不限，商户使用AES算法将其加密并转换为Base64格式，
        // 除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
//        String encryptedBizData = AES.encryptToBase64(srcBizData, AES_KEY);
        String b64BizData = Base64.encodeBase64String(srcBizData.getBytes(UTF_8));
        // 使用密文替换原业务参数数据
        params.put("biz_data", b64BizData);
        return params;
    }

    // 请求示例不能运行成功
    // 需要替换成真实环境参数
    private void requestExample(Map<String, String> params) {

        // 构建一个 Form 表单，使用 Post 提交
        FormBody.Builder fb = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            fb.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = fb.build();
        Request request = new Request.Builder()
                /*.url("http://pay.tengjingshop.com/pay-api/pay")*/
                .url("http://smart-test.yyimingshop.com/pay-api/pay")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", USER_AGENT)
                .post(requestBody)
                .build();

        Call call = HTTP_CLIENT.newCall(request);
        String body = "";
        try {
            Response response = call.execute();

            if (response.isSuccessful()) {
                body = response.body().string();
                // Servlet write body
                System.out.printf("Status %s %s%n%s%n", response.code(), response.message(),
                        body);
            } else {
                System.out.printf("Status %s %s%n%s%n", response.code(), response.message(),
                        response.body().toString());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!body.isEmpty()) {
            // API return error
            if (body.startsWith("{") && body.endsWith("}")) {
                Map<String, String> ret;
                try {
                    ret = JSON.parseObject(body, new TypeReference<Map<String, String>>() {}.getType());

                    String sign = ret.remove("sign");

                    StringBuilder sb = new StringBuilder(body.length());
                    Map<String, String> paramsMap = new TreeMap<String, String>(ret);
                    for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    sb.deleteCharAt(sb.length() - 1);

                    String signContent = sb.toString();

                    System.out.println("content: " + signContent + ", sign: " + sign);
                    boolean verify = RSA.verify(signContent, sign, PLAT_PUBLIC_KEY);
                    System.out.printf("sign verified: %s%n", verify);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                System.out.println(body);
            }
        }

    }

    public static void main(String[] args) {
        PayExample example = new PayExample();

        Map<String, String> params = example.buildPayParams();
        System.out.println("Pay request parameters: ");
        System.out.println(JSON.toJSONString(params, true));

        // 请求示例不能运行成功
        // 需要替换成真实环境参数
        example.requestExample(params);
    }
}
