package com.ruoyi.web.controller.carbon.front;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisMiaoShaController extends BaseController {


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/getProduct")
    public AjaxResult getProductByRedis(@RequestParam("prodId") String prodId)
    {
        if (StringUtils.isEmpty(prodId))
        {
            return AjaxResult.error("当前的商品ID为空");
        }
        String uuid = UUID.fastUUID().toString();
        String kcKey = "ky:"+ prodId +":kc";
        String userKey = "ky:" + prodId + ":user";

        Object total = redisTemplate.opsForValue().get(kcKey);
        if (total == null)
        {
            return AjaxResult.error("秒杀还没开始");
        }
        if ((Integer) total <= 0)
        {
            return AjaxResult.error("当前活动已经结束");
        }


        if (redisTemplate.opsForSet().isMember(userKey,uuid))
        {
            return AjaxResult.error("当前已秒杀");
        }
        redisTemplate.multi();
        redisTemplate.opsForSet().add(userKey,uuid);
        redisTemplate.opsForValue().increment(kcKey);
        List exec = redisTemplate.exec();
        if (exec == null || exec.size() == 0)
        {
            return AjaxResult.error("秒杀失败");
        }
        return AjaxResult.success("秒杀成功");
    }


}
