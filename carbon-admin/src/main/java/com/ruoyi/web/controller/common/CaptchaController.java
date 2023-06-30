package com.ruoyi.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysConfigService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 * 
 * @author ruoyi
 */
@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private ISysConfigService configService;
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled)
        {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

//        String capStr = null, code = null;
//        BufferedImage image = null;

        String captchaType = RuoYiConfig.getCaptchaType();
        // 生成验证码
        //验证码EasyCaptcha工具
        Captcha captcha = null;
        if ("math".equals(captchaType)) {//math 算术验证
            //创建算术验证码
            captcha = new ArithmeticCaptcha(115, 42);
        } else if ("chinese".equals(captchaType)) {//chinese 中文验证
            //中文验证
            captcha = new ChineseCaptcha(115, 42);
        } else if ("char".equals(captchaType)) {//char 字符验证
            //创建字符验证码
            captcha = new SpecCaptcha(115,42);
        }
        //得到验证码的值
        String code = captcha.text();



//        if ("math".equals(captchaType))
//        {
//            String capText = captchaProducerMath.createText();
//            capStr = capText.substring(0, capText.lastIndexOf("@"));
//            code = capText.substring(capText.lastIndexOf("@") + 1);
//            image = captchaProducerMath.createImage(capStr);
//        }
//        else if ("char".equals(captchaType))
//        {
//            capStr = code = captchaProducer.createText();
//            image = captchaProducer.createImage(capStr);
//        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
//        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
//        try
//        {
//            ImageIO.write(image, "jpg", os);
//        }
//        catch (IOException e)
//        {
//            return AjaxResult.error(e.getMessage());
//        }

        ajax.put("uuid", uuid);
        ajax.put("img", captcha.toBase64());
        return ajax;
    }
}
