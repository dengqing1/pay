package com.myd.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.myd.entity.CodeData;
import com.myd.entity.MerchantIdData;

import java.util.Map;

/**
 * Created by you on 2018/11/22.
 */
public class MapFormat {

    //读取配置文件
    public static ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");

    //获取不同的code对应service
    public static String GetServiceNameByCode(String code){

        //获取注入的Map获取数据
        CodeData codeData = (CodeData) context.getBean("CodeData");
        Map<String, Object> serviceMap = codeData.getServiceMap();
        Object oo = serviceMap.get(code);
        String simpleName = oo.getClass().getSimpleName();//得到数据类型,对应实现类的名字（ArrayList）
        int num = simpleName.indexOf("$");
        String serviceName = simpleName.substring(0, num);
        return serviceName;
    }

  //验证商户ID匹配的code
    public static boolean ValidationMerchantId(String merchantId,String code){

        //获取注入的Map获取数据
        MerchantIdData merchantIdData = (MerchantIdData) context.getBean("MerchantIdData");
        Map<String, Object> merchantIdMap = merchantIdData.getMerchantIdMap();
        String merchantIds = (String) merchantIdMap.get(merchantId);
        if(merchantIds.contains(code)){
            return true;
        }else{
            return false;
        }
    }

    //验证加密是否匹配
    public static boolean ValidationSignature(String code, String merchantId,String signature) {

        //MD5  32位小写加密
        String md5Code = Md5Util.encryption(code);
        String md5MerchantId = Md5Util.encryption(merchantId);
        String newSignature=md5Code+md5MerchantId;
        if(!StringUtils.isEmpty(signature)) {
            if (signature.equals(newSignature)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
