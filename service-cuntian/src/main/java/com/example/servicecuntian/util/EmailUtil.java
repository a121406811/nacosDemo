package com.example.servicecuntian.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender jms;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.profiles.active}")
    private String active;

    //    @Value("${custom.parameters}")
//    private List<String> receivers;
    private String title = "村田对接系统环境出现异常！";

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

//    public void sendTemplateEmail(String receiveMail, String msg, String log) {
//        MimeMessage message = null;
//        try {
//            message = jms.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(receiveMail); // 接收地址
//            helper.setSubject(title); // 标题
//            // 处理邮件模板
//            Context context = new Context();
//            context.setVariable("msg", msg);
//            context.setVariable("log", log);
//            String template = templateEngine.process("emailTemplate.html", context);
//            helper.setText(template, true);
//            jms.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("----------------------邮件发送出现异常--------------");
//            logger.error(e.toString());
//            // 企业微信提醒
////            WeixinServerForSendTouserUtil.send(receivers, title);
//        }
//    }

    public void sendTemplateEmail(List<String> receiveMail, String msg, String log) {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(title); // 标题
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("msg", "在" + active + "环境中，" + msg);
            context.setVariable("log", log);

            String template = templateEngine.process("emailTemplate.html", context);
            helper.setText(template, true);

            for (String email : receiveMail) {
                helper.setTo(email); // 接收地址
                jms.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------邮件发送出现异常--------------");
            logger.error(e.toString());
            // 企业微信提醒
//            WeixinServerForSendTouserUtil.send(receivers, title);
            //log作为附件发送？
        }
    }
}
