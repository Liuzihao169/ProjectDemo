package com.onezero.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author hao
 * @create 2019-07-25 ${TIM}
 */
@Component
public class SMSUtils {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    @Autowired
    private Environment env;

    /**
     * 发送短信
     * @param mobile 手机号
     * @param template_code 模板号
     * @param sign_name 签名
     * @param param 参数
     * @return
     * @throws ClientException
     */
    public CommonResponse sendSms(String mobile, String template_code, String sign_name, String param) throws ClientException {
        String accessKeyId =env.getProperty("aliyun.sms.accessKeyId");
        String accessKeySecret = env.getProperty("aliyun.sms.accessKeySecret");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        CommonRequest request = new CommonRequest();
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.setAction("SendSms");
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        //必填:待发送手机号
        request.putQueryParameter("PhoneNumbers", mobile);
        //必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", sign_name);
        //必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode",template_code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.putQueryParameter("TemplateParam",param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //hint 此处可能会抛出异常，注意catch
        CommonResponse response = acsClient.getCommonResponse(request);
        return  response;
    }
}
