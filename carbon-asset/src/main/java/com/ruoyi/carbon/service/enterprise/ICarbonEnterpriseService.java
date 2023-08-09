package com.ruoyi.carbon.service.enterprise;

import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.domain.vo.BuyVo;
import com.ruoyi.carbon.domain.vo.EnterpriseVo;
import com.ruoyi.carbon.domain.vo.ForgetPassVo;
import com.ruoyi.carbon.domain.vo.SellVo;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

/**
 * 企业信息Service接口
 *
 * @author ruoyi
 * @date 2023-07-08
 */
public interface ICarbonEnterpriseService
{
    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业信息主键
     * @return 企业信息
     */
    public CarbonEnterprise selectCarbonEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询企业信息列表
     *
     * @param carbonEnterprise 企业信息
     * @return 企业信息集合
     */
    public List<CarbonEnterprise> selectCarbonEnterpriseList(CarbonEnterprise carbonEnterprise);

    /**
     * 新增企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int insertCarbonEnterprise(CarbonEnterprise carbonEnterprise) throws Exception;

    /**
     * 修改企业信息
     *
     * @param carbonEnterprise 企业信息
     * @return 结果
     */
    public int updateCarbonEnterprise(CarbonEnterprise carbonEnterprise);

    /**
     * 批量删除企业信息
     *
     * @param enterpriseIds 需要删除的企业信息主键集合
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    /**
     * 删除企业信息信息
     *
     * @param enterpriseId 企业信息主键
     * @return 结果
     */
    public int deleteCarbonEnterpriseByEnterpriseId(Long enterpriseId);


    /**
     * 根据企业地址查询企业详细信息
     * @param address 企业的地址
     * @return CarbonEnterprise 企业信息
     */
    public CarbonEnterprise selectByAddress(String address);

    public CarbonEnterprise selectByEnterpriseName(String enterpriseName);

    public AjaxResult sellEnterpriseAsset(SellVo sellVo);

    public AjaxResult buyEnterpriseAsset(BuyVo buyVo);

    public List<CarbonEnterprise> selectEnterpriseListByAddress(String buyAddress,String sellerAddress);

    public int updateCarbonEnterpriseBalance(CarbonEnterprise carbonEnterprise);

    public AjaxResult selectEnterpriseInfoByBlockChain(String address);

    public AjaxResult updateTotalEmission(String address, BigInteger count) throws Exception;

    /**
     * 更新企业的个人信息
     * @param enterpriseVo 企业的个人信息
     * @return 返回结果
     */
    public AjaxResult updateEnterpriseInfo(EnterpriseVo enterpriseVo);

    /**
     * 上传头像
     * @param file 图片
     * @return 返回结果
     */
    public AjaxResult updateAvatar(MultipartFile file);


    /**
     * 重置用户的密码
     * @param forgetPassVo 企业名称和密码
     * @return 返回结果
     */
    public AjaxResult forgetUserPassword(ForgetPassVo forgetPassVo);

    /**
     * 通过企业名称更新用户头像
     * @param enterprise 企业名称
     * @param avatar 用户的头像
     * @return 返回结果
     */
    public AjaxResult updateAvatarByName(String enterprise, String avatar);

    /**
     * 上传商品的图片
     * @param file 图片文件
     * @return 返回结果
     */
    public AjaxResult uploadProductImage(MultipartFile file);

}
