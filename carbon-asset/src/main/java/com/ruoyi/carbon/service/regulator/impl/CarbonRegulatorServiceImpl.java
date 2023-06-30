package com.ruoyi.carbon.service.regulator.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.carbon.domain.carbon.CarbonRegulator;
import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.mapper.CarbonRegulatorMapper;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegisterRegulatorInputBO;
import com.ruoyi.carbon.service.carbon.CarbonAssetServiceService;
import com.ruoyi.carbon.service.regulator.ICarbonRegulatorService;
import com.ruoyi.carbon.service.user.UserKeyService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysUserService;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 监管机构信息Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-08
 */
@Service
public class CarbonRegulatorServiceImpl implements ICarbonRegulatorService
{
    @Autowired
    private CarbonRegulatorMapper carbonRegulatorMapper;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private CarbonAssetServiceService carbonAssetServiceService;

    @Autowired
    private UserKeyService userKeyService;



    /**
     * 查询监管机构信息
     *
     * @param regulatorId 监管机构信息主键
     * @return 监管机构信息
     */
    @Override
    public CarbonRegulator selectCarbonRegulatorByRegulatorId(Long regulatorId)
    {
        CarbonRegulator carbonRegulator = carbonRegulatorMapper.selectCarbonRegulatorByRegulatorId(regulatorId);
        SysUser sysUser = userService.selectUserByNickName(carbonRegulator.getRegulatorName());
        carbonRegulator.setRegulatorUser(sysUser.getUserName());
        return carbonRegulator;
    }

    /**
     * 查询监管机构信息列表
     *
     * @param carbonRegulator 监管机构信息
     * @return 监管机构信息
     */
    @Override
    public List<CarbonRegulator> selectCarbonRegulatorList(CarbonRegulator carbonRegulator)
    {
        return carbonRegulatorMapper.selectCarbonRegulatorList(carbonRegulator);
    }

    /**
     * 新增监管机构信息
     *
     * @param carbonRegulator 监管机构信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCarbonRegulator(CarbonRegulator carbonRegulator) throws Exception {

        // 注册用户
        SysUser sysUser = new SysUser();
        sysUser.setUserName(carbonRegulator.getRegulatorUser());
        sysUser.setNickName(carbonRegulator.getRegulatorName());
        Long[] ids = new Long[]{100L};
        sysUser.setRoleIds(ids);
        sysUser.setPassword(SecurityUtils.encryptPassword(carbonRegulator.getRegulatorPass()));
        boolean unique = userService.checkUserNameUnique(sysUser);
        if (!unique) return 0;
        userService.insertUser(sysUser);
        // 注册链上机构
        UserKey userKey = userKeyService.newUserKeyAndAddress();
        CarbonAssetServiceRegisterRegulatorInputBO regulatorInputBO = new CarbonAssetServiceRegisterRegulatorInputBO();
        regulatorInputBO.set_regulatorAddress(userKey.getAddress());
        regulatorInputBO.set_regulatorName(carbonRegulator.getRegulatorName());

        // 判断该机构是否存在
        CarbonRegulator regulator = carbonRegulatorMapper.selectRegulatorByName(carbonRegulator.getRegulatorName());
        int count = 0;
        if (Objects.isNull(regulator))
        {
            TransactionResponse transactionResponse = carbonAssetServiceService.registerRegulator(regulatorInputBO);
            // 交易回执正常同步到数据库中
            if (transactionResponse.getReceiptMessages().equals("Success")) {
                JSONArray result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(1);
                carbonRegulator.setPrivateKey(userKey.getPrivateKey());
                carbonRegulator.setRegulatorId(result.getIntValue(0));
                carbonRegulator.setRegulatorAddress(result.getString(1));
                carbonRegulator.setRegulatorName(result.getString(2));
                carbonRegulator.setUserType(result.getIntValue(3));
                count = carbonRegulatorMapper.insertCarbonRegulator(carbonRegulator);
                return count;
            }
        }
        return count;
    }

    /**
     * 修改监管机构信息
     *
     * @param carbonRegulator 监管机构信息
     * @return 结果
     */
    @Override
    public int updateCarbonRegulator(CarbonRegulator carbonRegulator)
    {
        SysUser sysUser = userService.selectUserByUserName(carbonRegulator.getRegulatorUser());
        sysUser.setNickName(carbonRegulator.getRegulatorName());
        sysUser.setPassword(SecurityUtils.encryptPassword(carbonRegulator.getRegulatorPass()));
        userService.updateUser(sysUser);
        return carbonRegulatorMapper.updateCarbonRegulator(carbonRegulator);
    }

    /**
     * 批量删除监管机构信息
     *
     * @param regulatorIds 需要删除的监管机构信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonRegulatorByRegulatorIds(Long[] regulatorIds)
    {
        return carbonRegulatorMapper.deleteCarbonRegulatorByRegulatorIds(regulatorIds);
    }

    /**
     * 删除监管机构信息信息
     *
     * @param regulatorId 监管机构信息主键
     * @return 结果
     */
    @Override
    public int deleteCarbonRegulatorByRegulatorId(Long regulatorId)
    {
        return carbonRegulatorMapper.deleteCarbonRegulatorByRegulatorId(regulatorId);
    }

    @Override
    public CarbonRegulator selectRegulatorByAddress(String address) {
        return carbonRegulatorMapper.selectRegulatorByAddress(address);
    }

    @Override
    public CarbonRegulator selectRegulatorByName(String name) {
        return carbonRegulatorMapper.selectRegulatorByName(name);
    }
}
