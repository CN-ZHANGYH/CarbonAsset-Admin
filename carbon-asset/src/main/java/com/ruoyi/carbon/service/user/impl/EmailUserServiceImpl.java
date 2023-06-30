package com.ruoyi.carbon.service.user.impl;


import com.ruoyi.carbon.service.user.EmailUserService;
import com.ruoyi.carbon.utils.VerificationCodeGeneratorUtil;
import com.ruoyi.common.core.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 张宇豪
 * @date 2023/4/8 11:35
 * @desc 发送邮箱验证码接口实现
 */
@Slf4j
@Service
public class EmailUserServiceImpl implements EmailUserService {

    /**
     * 发送邮箱验证码
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailForm;

    @Autowired
    private RedisCache redisCache;


    /**
     * 手动创建线程
     */
    private static ExecutorService pool = Executors.newCachedThreadPool();


    @Override
    public String sendEmailCode(String emailTo) {
        // 生成随机验证码
        String code = VerificationCodeGeneratorUtil.generate();
        // 存储Redis缓存数据库
        redisCache.setCacheObject("CODE",code,5, TimeUnit.MINUTES);
        pool.execute(() -> sendMessage(emailTo, code));
        return code;
    }

    /**
     * 发送邮箱验证码
     * @param emailTo
     * @param code
     */
    private void sendMessage(String emailTo, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailForm);
        mailMessage.setTo(emailTo);
        mailMessage.setSentDate(new Date());
        mailMessage.setSubject("【🤖数字碳链🤖】注册邮箱验证");
        mailMessage.setText("您本次注册的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露");
        mailSender.send(mailMessage);
    }
}
