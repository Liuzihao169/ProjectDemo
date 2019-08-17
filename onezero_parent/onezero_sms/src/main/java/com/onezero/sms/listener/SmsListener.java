package com.onezero.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.onezero.sms.utils.SMSUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hao
 * @create 2019-07-24 ${TIM}
 *
 * 使用方式：@EnableRabbit + @RabbitListener (可以通过containerFactory属性指定工厂)
 *          @RabbitListener（calss）  @RabbitHandler(method)

 *@EnableRabbit:相当于注入一个RabbitListenerAnnotationBeanPostProcessor
 */
@Component
public class SmsListener {

    @Autowired
    private SMSUtils smsUtils;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;


    @RabbitListener(queues = {"onezero.sms"})
    public void receive(Map map)  {
        final String code = (String) map.get("randCode");
        final String mobile = (String) map.get("mobile");
        System.out.println(map);
        //发送短信
        try {
            smsUtils.sendSms(mobile,template_code,sign_name,"{\"code\":\""+code+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
