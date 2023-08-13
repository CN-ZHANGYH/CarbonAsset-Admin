package com.ruoyi.carbon.service.user.impl;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.carbon.NoticeInfo;
import com.ruoyi.carbon.factory.RawContractLoaderFactory;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSignInInputBO;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.user.SignInService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.RedisContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ICarbonEnterpriseService enterpriseService;

    @Autowired
    private CarbonEnterpriseMapper enterpriseMapper;

    @Autowired
    private RawContractLoaderFactory rawContractLoaderFactory;

    @Autowired
    private ThreadPoolTaskExecutor poolExecutor;


    /**
     * 用户签到功能
     * @param enterprise 用户的企业名称
     * @return 返回结果
     */
    @Override
    public AjaxResult userSignInToCredit(String enterprise)  {
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterprise);
        if (Objects.isNull(carbonEnterprise)) return AjaxResult.error("当前用户不存在");
        CarbonAssetServiceSignInInputBO signAddress = new CarbonAssetServiceSignInInputBO();
        signAddress.set_enterpriseAddress(carbonEnterprise.getEnterpriseAddress());

        // 获取当前时间所在月份的天数
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        String signInTime = now.format(DateTimeFormatter.ofPattern("yyyy:MM"));
        String key = RedisContacts.SIGN_USER_TOKEN + enterprise + ":" + signInTime;

        // 判断是否签到
        Boolean isSign = redisCache.getBigMapValue(key, dayOfMonth - 1);
        if (isSign) return AjaxResult.error("今日已签到");

        // 设置缓存
        poolExecutor.execute(() -> setRedisCache(dayOfMonth, key));

        // 添加通告操作
        Integer enterpriseId = carbonEnterprise.getEnterpriseId();
        String noticeKey = RedisContacts.NOTICE_USER_KEY + enterpriseId;
        NoticeInfo noticeInfo = new NoticeInfo();
        noticeInfo.setEnterpriseId(enterpriseId);
        noticeInfo.setNoticeTime(now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
        noticeInfo.setMsg("签到成功");
        noticeInfo.setDescription(carbonEnterprise.getEnterpriseName() + "完成了签到");
        noticeInfo.setTitle("签到");
        redisTemplate.opsForList().leftPush(noticeKey,noticeInfo);

        // 更新数据库
        BigInteger oldCredit = carbonEnterprise.getEnterpriseCarbonCredits();
        carbonEnterprise.setEnterpriseCarbonCredits(oldCredit.add(new BigInteger(String.valueOf(50))));
        int status = enterpriseMapper.updateCarbonEnterprise(carbonEnterprise);
        if (status > 0)
        {
            AjaxResult ajax = AjaxResult.success("签到成功");
            ajax.put("credit",50);
            poolExecutor.execute(() -> {
                try
                {
                    updateBlockChainSignData(carbonEnterprise, signAddress);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return ajax;
        }
        return AjaxResult.error("签到失败");
    }

    /**
     * 统计用户的签到次数
     * @param enterpriseName 用户的企业名称
     * @return 返回结果
     */
    @Override
    public AjaxResult signCountByAddress(String enterpriseName) {
        // 获取当前的时间
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        String signTime = now.format(DateTimeFormatter.ofPattern("yyyy:MM"));
        String key = RedisContacts.SIGN_USER_TOKEN + enterpriseName  + ":" + signTime;

        // 查询这个月的所有签到次数
        Long totalCount = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(key.getBytes()));

        // 从redis 查询当前的用户签到情况
        List<Long> result = redisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0)
        );
        if (result == null || result.isEmpty())
        {
            return AjaxResult.success("今天还未签到");
        }
        Long num = result.get(0);
        if (num == null || num == 0)
        {
            return AjaxResult.success("今天还未签到");
        }
        int count = 0;
        while (true){
            if ((num & 1) == 0)
            {
                break;
            }else {
                count++;
            }
            num >>>= 1;
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("total",totalCount);
        ajax.put("count",count);
        return ajax;

    }

    @Override
    public AjaxResult getNoticeInfo(Integer id) {
        String key = RedisContacts.NOTICE_USER_KEY + id;
        List<NoticeInfo> noticeInfos = redisTemplate.opsForList().range(key, 0, -1);
        if (noticeInfos.isEmpty())
        {
            return AjaxResult.success();
        }
        return AjaxResult.success().put("nsData",noticeInfos);
    }

    public void updateBlockChainSignData(CarbonEnterprise carbonEnterprise, CarbonAssetServiceSignInInputBO signAddress) throws Exception {
        // 同步链上和数据库
        rawContractLoaderFactory.GetTransactionResponse(carbonEnterprise.getPriavateKey(), "signIn", signAddress.toArgs());
    }

    public void setRedisCache(int dayOfMonth, String key) {
        // 设置redis
        redisCache.setBigMapValue(key, dayOfMonth - 1, true);
    }
}
