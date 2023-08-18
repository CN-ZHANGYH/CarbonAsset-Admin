package com.ruoyi.carbon.service.enterprise.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.carbon.domain.carbon.*;
import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.domain.vo.*;
import com.ruoyi.carbon.factory.RawContractLoaderFactory;
import com.ruoyi.carbon.mapper.CarbonEnterpriseMapper;
import com.ruoyi.carbon.model.bo.*;
import com.ruoyi.carbon.service.carbon.CarbonAssetServiceService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseAssetService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.service.enterprise.ICarbonQualificationService;
import com.ruoyi.carbon.service.transaction.ICarbonTransactionService;
import com.ruoyi.carbon.service.user.UserRegisterService;
import com.ruoyi.carbon.utils.BlockTimestampUtil;
import com.ruoyi.carbon.utils.TCOSUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.RedisContacts;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * 企业信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-07-08
 */
@Service
public class CarbonEnterpriseServiceImpl implements ICarbonEnterpriseService
{
    @Autowired
    private CarbonEnterpriseMapper carbonEnterpriseMapper;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TCOSUtil tcosUtil;

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private RawContractLoaderFactory rawContractLoaderFactory;
    @Autowired
    private ICarbonEnterpriseAssetService enterpriseAssetService;

    @Autowired
    private CarbonAssetServiceService carbonAssetServiceService;

    @Autowired
    private ICarbonTransactionService carbonTransactionService;


    @Autowired
    private ICarbonQualificationService qualificationService;

    /**
     * 手动创建线程
     */
    @Autowired
    private static ThreadPoolTaskExecutor poolTaskExecutor;

    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业信息主键
     * @return 企业信息
     */
    @Override
    public CarbonEnterprise selectCarbonEnterpriseByEnterpriseId(Long enterpriseId)
    {
        CarbonEnterprise carbonEnterprise = carbonEnterpriseMapper.selectCarbonEnterpriseByEnterpriseId(enterpriseId);
        SysUser sysUser = userService.selectUserByNickName(carbonEnterprise.getEnterpriseName());
        carbonEnterprise.setEnterpriseEmail(sysUser.getEmail());
        carbonEnterprise.setEnterpriseName(sysUser.getUserName());
        carbonEnterprise.setEnterpriseNickName(sysUser.getNickName());
        return carbonEnterprise;
    }

    /**
     * 查询企业信息列表
     *
     * @param carbonEnterprise 企业信息
     * @return 企业信息
     */
    @Override
    public List<CarbonEnterprise> selectCarbonEnterpriseList(CarbonEnterprise carbonEnterprise)
    {
        return carbonEnterpriseMapper.selectCarbonEnterpriseList(carbonEnterprise);
    }

    /**
     * 新增企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    @Override
    public int insertCarbonEnterprise(CarbonEnterprise carbonEnterprise) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(carbonEnterprise.getEnterpriseName());
        sysUser.setNickName(carbonEnterprise.getEnterpriseNickName());
        sysUser.setPassword(SecurityUtils.encryptPassword(carbonEnterprise.getEnterprisePass()));
        sysUser.setEmail(carbonEnterprise.getEnterpriseEmail());
        boolean unique = userService.checkUserNameUnique(sysUser);
        if (!unique) return 0;
        boolean registered = userService.registerUser(sysUser);
        if (registered){
            CarbonEnterprise enterprise = userRegisterService.registerUser(sysUser);
            if (!Objects.isNull(enterprise)) return 1;
        }
        return 0;
    }

    /**
     * 修改企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    @Override
    public int updateCarbonEnterprise(CarbonEnterprise carbonEnterprise)
    {
        SysUser sysUser = userService.selectUserByUserName(carbonEnterprise.getEnterpriseName());
        sysUser.setUserName(carbonEnterprise.getEnterpriseName());
        sysUser.setNickName(carbonEnterprise.getEnterpriseNickName());
        carbonEnterprise.setEnterpriseName(carbonEnterprise.getEnterpriseNickName());
        int id = userService.updateUser(sysUser);
        if (id > 0) {
            return carbonEnterpriseMapper.updateCarbonEnterprise(carbonEnterprise);
        }
        return id;

    }

    /**
     * 批量删除企业信息
     *
     * @param enterpriseIds 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEnterpriseByEnterpriseIds(Long[] enterpriseIds)
    {
        return carbonEnterpriseMapper.deleteCarbonEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除企业信息信息
     *
     * @param enterpriseId 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return carbonEnterpriseMapper.deleteCarbonEnterpriseByEnterpriseId(enterpriseId);
    }

    @Override
    public CarbonEnterprise selectByAddress(String address) {
        return carbonEnterpriseMapper.selectCarbonEnterpriseByAddress(address);
    }

    @Override
    public CarbonEnterprise selectByEnterpriseName(String enterpriseName) {
        if (!StringUtils.isEmpty(enterpriseName)){
            return carbonEnterpriseMapper.selectCarbonEnterpriseByName(enterpriseName);
        }
        return null;
    }

    /**
     * 企业出售碳额度
     * @param sellVo 入参对象
     * @return 返回结果
     *
     * @desc 1.这里的业务是需要查询该企业是否存在的一个情况，然后需要查看一下当前的企业额度是否充足，条件是充足的情况下
     * 用户可以出售自己的资产，这里会有两个操作，需要实现异步，一个是调用链上接口，然后同步到数据库中 这里需要对
     * enterprise_asset表进行操作，从链上拿数据然后同步到数据库中.
     */
    @Override
    @Transactional
    public AjaxResult sellEnterpriseAsset(SellVo sellVo) {

        // 查询用户是否存在
        CarbonEnterprise carbonEnterprise = this.selectByAddress(sellVo.getUserAddress());
        if (Objects.isNull(carbonEnterprise))
        {
            return AjaxResult.error("当前用户不存在");
        }
        if (carbonEnterprise.getEnterpriseVerified() == 0)
        {
            return AjaxResult.error("当前用户未认证");
        }
        if (StringUtils.isEmpty(sellVo.getImage()))
        {
            return AjaxResult.error("请上传商品封面");
        }
        // 调用链上数据
        CarbonAssetServiceSellEmissionLimitInputBO emissionLimitInputBO = new CarbonAssetServiceSellEmissionLimitInputBO();
        emissionLimitInputBO.set_amount(sellVo.getAmount());
        emissionLimitInputBO.set_emissionLimitCount(sellVo.getQuality());
        List<Object> params = emissionLimitInputBO.toArgs();

        try {
            // 业务上链
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(carbonEnterprise.getPriavateKey(), "sellEmissionLimit", params);
            if (transactionResponse.getReturnMessage().equals("Success")){
                JSONArray result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
                CarbonEnterpriseAsset enterpriseAsset = new CarbonEnterpriseAsset();
                enterpriseAsset.setAssetId(result.getLongValue(0));
                enterpriseAsset.setEnterpriseId(result.getLongValue(1));
                enterpriseAsset.setEnterpriseAddress(result.getString(2));
                enterpriseAsset.setAssetQuantity(result.getBigInteger(3));
                enterpriseAsset.setAssetAmount(result.getBigInteger(4));
                enterpriseAsset.setTime(BlockTimestampUtil.convert(result.getLongValue(5)));
                enterpriseAsset.setStatus(result.getIntValue(6));
                enterpriseAsset.setTitle(sellVo.getTitle());
                enterpriseAsset.setDescription(sellVo.getDescription());
                enterpriseAsset.setImage(sellVo.getImage());
                // 企业需要更新自己的碳额度
                Integer qualificationId = carbonEnterprise.getQualificationId();
                int code = this.insertOrder(enterpriseAsset,qualificationId,sellVo.getQuality());
                if (code > 0){
                    // 添加通告操作
                    LocalDateTime now = LocalDateTime.now();
                    String noticeKey = RedisContacts.NOTICE_USER_KEY + carbonEnterprise.getEnterpriseId();
                    NoticeInfo noticeInfo = new NoticeInfo();
                    noticeInfo.setEnterpriseId(carbonEnterprise.getEnterpriseId());
                    noticeInfo.setNoticeTime(now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
                    noticeInfo.setMsg("出售成功");
                    noticeInfo.setDescription(carbonEnterprise.getEnterpriseName() + "出售了碳额度");
                    noticeInfo.setTitle("出售中");
                    redisCache.setListValue(noticeKey,noticeInfo);
                    redisCache.expire(noticeKey,1, TimeUnit.DAYS);

                    AjaxResult ajax = AjaxResult.success("出售成功");
                    ajax.put("assetOrder",enterpriseAsset);
                    return ajax;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.error("出售失败");
    }

    /**
     * 用户购买企业碳额度
     * @param buyVo 购买入参
     * @return 返回结果
     *
     * @desc 这部分的业务是企业购买碳额度，入参分别是企业地址、出售的企业地址，订单的ID、以及购买的数量，业务执行的是自动扣款费用
     * 需要查询当前的订单ID或者是双方地址的情况，这里不可能同时满足更新全靠链上数据进行同步，当然初步做法，还是实现异步任务的调度
     * 交易的记录需要更新 企业的信息需要更新 出售的资产的订单也需要更新
     */
    @Override
    @Transactional
    public AjaxResult buyEnterpriseAsset(BuyVo buyVo) {
        BigInteger assetId = buyVo.getAssetId();
        String ownerAddress = buyVo.getOwnerAddress();
        String sellerAddress = buyVo.getSellerAddress();

        CarbonEnterprise carbonEnterprise = this.selectByAddress(ownerAddress);

        CarbonAssetServiceBuyEmissionLimitInputBO buyEmissionLimitInputBO = new CarbonAssetServiceBuyEmissionLimitInputBO();
        buyEmissionLimitInputBO.set_enterpriseSeller(sellerAddress);
        buyEmissionLimitInputBO.set_quantity(buyVo.getQuality());
        buyEmissionLimitInputBO.set_eassetId(assetId);
        List<Object> params = buyEmissionLimitInputBO.toArgs();


        // 更新用户的信息 买家 卖家 碳额度
        CarbonEnterprise buyer = carbonEnterpriseMapper.selectCarbonEnterpriseByAddress(ownerAddress);
        CarbonEnterprise seller = carbonEnterpriseMapper.selectCarbonEnterpriseByAddress(sellerAddress);
        CarbonQualification qualification = qualificationService.selectCarbonQualificationByQualificationId(Long.valueOf(buyer.getQualificationId()));
        CarbonEnterpriseAsset enterpriseAsset = enterpriseAssetService.selectCarbonEnterpriseAssetByAssetId(Long.valueOf(String.valueOf(assetId)));

        if (enterpriseAsset.getAssetQuantity() == BigInteger.valueOf(0L))
        {
            return AjaxResult.error("当前商品已售空");
        }

        BigInteger oldBalance = buyer.getEnterpriseBalance();
        buyer.setEnterpriseBalance(oldBalance.subtract(enterpriseAsset.getAssetAmount().multiply(buyVo.getQuality())));
        BigInteger oldEmissionLimit = qualification.getQualificationEmissionLimit();
        qualification.setQualificationEmissionLimit(oldEmissionLimit.add(buyVo.getQuality()));

        BigInteger sellerBalance = seller.getEnterpriseBalance();
        buyer.setEnterpriseBalance(sellerBalance.add(enterpriseAsset.getAssetAmount().multiply(buyVo.getQuality())));

        BigInteger oldQuantity = enterpriseAsset.getAssetQuantity();
        enterpriseAsset.setAssetQuantity(oldQuantity.subtract(buyVo.getQuality()));

        carbonEnterpriseMapper.updateCarbonEnterprise(buyer);
        carbonEnterpriseMapper.updateCarbonEnterprise(seller);
        qualificationService.updateCarbonQualification(qualification);
        enterpriseAssetService.updateCarbonEnterpriseAsset(enterpriseAsset);

        return txTransactionByAsset(carbonEnterprise, params);
    }

    @Async
    public AjaxResult txTransactionByAsset(CarbonEnterprise carbonEnterprise, List<Object> params) {
        try {
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(carbonEnterprise.getPriavateKey(), "buyEmissionLimit", params);

            if (transactionResponse.getReturnMessage().equals("Success")){
                int status = JSON.parseArray(transactionResponse.getValues()).getIntValue(0);
                if (status == 70002)
                {
                    return AjaxResult.error("余额不足");
                }
                if (status == 70004)
                {
                    return AjaxResult.error("资产不存在");
                }
                JSONArray result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
                CarbonTransaction carbonTransaction = new CarbonTransaction();
                carbonTransaction.setTransactionId(result.getLongValue(0));
                carbonTransaction.setBuyerId(result.getLongValue(1));
                carbonTransaction.setSellerId(result.getLongValue(2));
                carbonTransaction.setTransactionOrderName(result.getString(3));
                carbonTransaction.setBuyAddress(result.getString(4));
                carbonTransaction.setSellerAddress(result.getString(5));
                carbonTransaction.setTransactionTime(BlockTimestampUtil.convert(result.getLongValue(6)));
                carbonTransaction.setTransactionQuantity(result.getLongValue(7));
                carbonTransaction.setTxHash(transactionResponse.getTransactionReceipt().getTransactionHash());
                int code = carbonTransactionService.insertCarbonTransaction(carbonTransaction);
                if (code > 0){
                    // 添加通告操作
                    LocalDateTime now = LocalDateTime.now();
                    String noticeKey = RedisContacts.NOTICE_USER_KEY + carbonEnterprise.getEnterpriseId();
                    NoticeInfo noticeInfo = new NoticeInfo();
                    noticeInfo.setEnterpriseId(carbonEnterprise.getEnterpriseId());
                    noticeInfo.setNoticeTime(now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
                    noticeInfo.setMsg("购买成功");
                    noticeInfo.setDescription(carbonEnterprise.getEnterpriseName() + "购买了碳额度");
                    noticeInfo.setTitle("交易成功");
                    redisCache.setListValue(noticeKey,noticeInfo);
                    redisCache.expire(noticeKey,1, TimeUnit.DAYS);

                    AjaxResult ajax = AjaxResult.success("购买成功");
                    ajax.put("transaction",carbonTransaction);
                    return ajax;
                }
                return AjaxResult.error("购买失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("购买失败");
    }

    @Override
    public List<CarbonEnterprise> selectEnterpriseListByAddress(String buyAddress, String sellerAddress) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ownerAddress",buyAddress);
        map.put("sellerAddress",sellerAddress);
        return carbonEnterpriseMapper.selectEnterpriseListByAddress(map);
    }

    @Override
    public int updateCarbonEnterpriseBalance(CarbonEnterprise carbonEnterprise) {
        // 链上更新用户的账户
        UserKey userKey = rawContractLoaderFactory.GetUserPrivateKey(carbonEnterprise.getEnterpriseAddress());
        CarbonAssetServiceUpdateBalanceInputBO inputBO = new CarbonAssetServiceUpdateBalanceInputBO();
        inputBO.set_amount(carbonEnterprise.getEnterpriseBalance());
        List<Object> params = inputBO.toArgs();
        try
        {
            TransactionResponse transactionResponse = rawContractLoaderFactory
                    .GetTransactionResponse(userKey.getPrivateKey(), "updateBalance", params);
            if (transactionResponse.getReturnMessage().equals("Success")){
                return carbonEnterpriseMapper.updateCarbonEnterprise(carbonEnterprise);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public AjaxResult selectEnterpriseInfoByBlockChain(String address) {
        if (StringUtils.isEmpty(address))
        {
            return AjaxResult.success("当前地址无效");
        }
        return this.selectEnterpriseInfoByChain(address);
    }

    /**
     * 更新企业的总需排放量
     * @param address 企业地址
     * @param count 总量
     * @return 返回结果
     */
    @Override
    @Transactional
    public AjaxResult updateTotalEmission(String address, BigInteger count) throws Exception {
        if (StringUtils.isEmpty(address))
        {
            return AjaxResult.error("当前地址为空");
        }

        CarbonEnterprise enterprise = this.selectByAddress(address);
        BigInteger totalEmission = enterprise.getEnterpriseTotalEmission();
        enterprise.setEnterpriseTotalEmission(totalEmission.add(count));

        CarbonAssetServiceUpdateEnterpriseEmissionInputBO emissionInputBO = new CarbonAssetServiceUpdateEnterpriseEmissionInputBO();
        emissionInputBO.set_emmissionsCount(count);
        TransactionResponse transactionResponse = rawContractLoaderFactory
                .GetTransactionResponse(enterprise.getPriavateKey(), "updateEnterpriseEmission", emissionInputBO.toArgs());

        if (transactionResponse.getReturnMessage().equals("Success")){
            int status = carbonEnterpriseMapper.updateCarbonEnterprise(enterprise);
            if (status > 0)
            {
                return AjaxResult.success("更新成功");
            }
        }
        return AjaxResult.error("更新失败");
    }

    @Override
    public AjaxResult updateEnterpriseInfo(EnterpriseVo enterpriseVo) {
        if (StringUtils.isEmpty(enterpriseVo.getEnterprise_name())) {
            return AjaxResult.error("该企业不能为空");
        }
        if (StringUtils.isEmpty(enterpriseVo.getEnterprise_address())) {
            return AjaxResult.error("该企业账户不能为空");
        }
        // 更新平台用户
        SysUser sysUser = userService.selectUserByNickName(enterpriseVo.getEnterprise_name());
        sysUser.setAvatar(enterpriseVo.getAvatar());
        sysUser.setEmail(enterpriseVo.getEmail());
        sysUser.setPhonenumber(enterpriseVo.getPhonenumber());
        int status = userService.updateUserProfile(sysUser);
        if (status > 0)
        {
            return AjaxResult.success("更新成功");
        }
        return AjaxResult.error("更新失败");
    }

    @Override
    public AjaxResult updateAvatar(MultipartFile file) {
        if (file == null)
        {
            return AjaxResult.error("文件格式错误");
        }
        String imgUrl = tcosUtil.uploadFile(file);
        if (StringUtils.isEmpty(imgUrl))
        {
            return AjaxResult.error("上传头像异常");
        }
        AjaxResult ajax = AjaxResult.success("更新头像成功");
        ajax.put("imgUrl",imgUrl);
        return ajax;
    }

    @Override
    public AjaxResult forgetUserPassword(ForgetPassVo forgetPassVo) {
        if (StringUtils.isEmpty(forgetPassVo.getEnterpriseName()))
        {
            return AjaxResult.error("企业名称不能为空");
        }
        SysUser sysUser = userService.selectUserByNickName(forgetPassVo.getEnterpriseName());
        if (Objects.isNull(sysUser))
        {
            return AjaxResult.error("企业未注册");
        }
        if (!forgetPassVo.getFirstPassword().equals(forgetPassVo.getSecondPassword()))
        {
            return AjaxResult.error("两次密码不一致");
        }
        sysUser.setPassword(SecurityUtils.encryptPassword(forgetPassVo.getFirstPassword()));
        int status = userService.updateUserProfile(sysUser);
        if (status > 0)
        {
            return AjaxResult.success("重置密码成功");
        }
        return AjaxResult.error("重置密码失败");


    }

    @Override
    public AjaxResult updateAvatarByName(String enterprise, String avatar) {
        if (StringUtils.isEmpty(enterprise) || StringUtils.isEmpty(avatar))
        {
            return AjaxResult.error("企业和头像不能为空");
        }
        SysUser sysUser = userService.selectUserByNickName(enterprise);
        if (Objects.isNull(sysUser))
        {
            return AjaxResult.error("该企业不存在");
        }
        sysUser.setAvatar(avatar);
        int status = userService.updateUserProfile(sysUser);
        if (status > 0)
        {
            return AjaxResult.success("更新头像成功");
        }
        return AjaxResult.error("更新失败");
    }

    @Override
    public AjaxResult uploadProductImage(MultipartFile file) {
        if (file == null)
        {
            return AjaxResult.error("文件不能为空");
        }
        String imageUrl = tcosUtil.uploadFile(file);
        if (StringUtils.isEmpty(imageUrl))
        {
            return AjaxResult.error("上传失败");
        }
        AjaxResult ajax = AjaxResult.success();
        return ajax.put("imageUrl", imageUrl);
    }

    @Override
    public AjaxResult selectRankingByCredit(Integer page, Integer pageSize) {
        List<RankingCreditVo> rankingCreditVoList = redisCache.getCacheObject("CRanking");
        if (rankingCreditVoList == null)
        {
            List<RankingCreditVo> rankingCreditVos = carbonEnterpriseMapper.selectRankingByCredit(page,pageSize);
            if (rankingCreditVos == null)
            {
                return AjaxResult.success("当前没有企业注册");
            }
            redisCache.setCacheObject("CRanking",rankingCreditVos);
            redisCache.expire("CRanking",7, TimeUnit.DAYS);
            return AjaxResult.success().put("data",rankingCreditVos);
        }
        return AjaxResult.success().put("data",rankingCreditVoList);
    }

    @Override
    public AjaxResult selectEnterpriseRanking(String enterprise) {
        if (enterprise.isEmpty()) return AjaxResult.error("企业不能为空");
        List<String> enterpriseRanking = carbonEnterpriseMapper.selectEnterpriseRanking();
        int ranking = 0;
        for (int i = 0; i < enterpriseRanking.size(); i++) {
            if (enterpriseRanking.get(i).equals(enterprise))
            {
                ranking = i;
                return AjaxResult.success().put("cRanking",ranking + 1);
            }
        }
        return AjaxResult.error("没有当前企业");
    }


    private AjaxResult selectEnterpriseInfoByChain(String address) {

        CarbonAssetServiceSelectEnterpriseInfoInputBO infoInputBO = new CarbonAssetServiceSelectEnterpriseInfoInputBO();
        infoInputBO.set_enterpriseAddress(address);
        CarbonEnterprise carbonEnterprise = selectByAddress(address);

        try {
            CallResponse callResponse = carbonAssetServiceService.selectEnterpriseInfo(infoInputBO);
            if (callResponse.getReturnMessage().equals("Success")){
                System.out.println(callResponse.getReturnObject());
                ArrayList result = (ArrayList) callResponse.getReturnObject().get(0);
                carbonEnterprise.setEnterpriseBalance((BigInteger) result.get(3));
                carbonEnterprise.setEnterpriseTotalEmission((BigInteger) result.get(4));
                carbonEnterprise.setEnterpriseOverEmission((BigInteger) result.get(5));
                carbonEnterprise.setEnterpriseCarbonCredits((BigInteger) result.get(6));
                int code = this.carbonEnterpriseMapper.updateCarbonEnterprise(carbonEnterprise);
                if (code < 0){
                    return AjaxResult.error("同步更新数据失败");
                }

                AjaxResult ajax = AjaxResult.success();
                ajax.put("enterprise",carbonEnterprise);
                return ajax;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("查询失败");
    }


    @Async("taskExecutor")
    public int insertOrder(CarbonEnterpriseAsset enterpriseAsset, Integer qualificationId, BigInteger quality){
        // 查询企业的资质进行更新操作
        CarbonQualification qualification = qualificationService.selectCarbonQualificationByQualificationId(Long.valueOf(qualificationId));
        if(Objects.isNull(qualification)){
            return 0;
        }
        BigInteger oldEmissionLimit = qualification.getQualificationEmissionLimit();
        qualification.setQualificationEmissionLimit(oldEmissionLimit.subtract(quality));
        qualificationService.updateCarbonQualification(qualification);
        return enterpriseAssetService.insertCarbonEnterpriseAsset(enterpriseAsset);
    }


}
