package org.example.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final long EXPIRATION_TIME = 5; // 5分钟有效期
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${spring.mail.username}") // 从配置文件中获取认证用户的邮箱地址
    private String fromEmail;

    @Override
    public void sendVerificationCode(String email) {
        // 生成6位随机验证码
        String code = generateVerificationCode();
        // 存储验证码到 Redis，设置5分钟有效期
        stringRedisTemplate.opsForValue().set(email, code, EXPIRATION_TIME, TimeUnit.MINUTES);
        try {
            // 创建 MimeMessage 对象
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 创建 MimeMessageHelper 对象，第二个参数为 true 表示支持 multipart 类型
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置邮件的发件人
            helper.setFrom(fromEmail);
            // 设置邮件的收件人
            helper.setTo(email);
            // 设置邮件的主题
            helper.setSubject("注册验证码");

            // 构建 HTML 格式的邮件内容
            String htmlContent = "<html>" +
                    "<body>" +
                    "<h2>欢迎来到 Linshiyi's Blog！</h2>" +
                    "<p>您的注册或修改密码验证码是：<strong>" + code + "</strong></p>" +
                    "<p>此验证码有效期为 5 分钟，请尽快使用。</p>" +
                    "<p>如果您没有请求此验证码，请忽略本邮件。</p>" +
                    "<p>感谢您的支持！</p>" +
                    "</body>" +
                    "</html>";

            // 设置邮件内容为 HTML 格式
            helper.setText(htmlContent, true);

            // 发送邮件
            mailSender.send(mimeMessage);
            log.info("验证码邮件已发送至: {}", email);
        } catch (MessagingException e) {
            // 记录发送邮件时的异常信息
            log.error("发送验证码邮件时出错: {}", e.getMessage(), e);
        }
    }

    @Override
    public boolean verifyCode(String email, String code) {
        String storedCode = stringRedisTemplate.opsForValue().get(email);
        if (storedCode != null && storedCode.equals(code)) {
            // 验证成功后移除验证码
            stringRedisTemplate.delete(email);
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        // 定义可能包含在验证码中的字符
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // 随机选择一个字符
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
}