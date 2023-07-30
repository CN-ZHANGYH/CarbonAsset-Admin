package com.ruoyi.souvenir.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.carbon.domain.carbon.CarbonEnterprise;
import com.ruoyi.carbon.model.bo.SouvenirCardQueryEnterpriseCardListInputBO;
import com.ruoyi.carbon.model.bo.SouvenirCardRegisterCardInputBO;
import com.ruoyi.carbon.model.bo.SouvenirCardUserBindCardInputBO;
import com.ruoyi.carbon.service.carbon.SouvenirCardService;
import com.ruoyi.carbon.service.enterprise.ICarbonEnterpriseService;
import com.ruoyi.carbon.utils.TCOSUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.souvenir.domain.CarbonCard;
import com.ruoyi.souvenir.domain.vo.CreditVo;
import com.ruoyi.souvenir.mapper.CarbonCardMapper;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 纪念卡数据Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@Service
@Slf4j
public class CarbonCardServiceImpl implements ICarbonCardService
{


    @Autowired
    private TCOSUtil tcosUtil;


    @Autowired
    private CarbonCardMapper carbonCardMapper;


    @Autowired
    private SouvenirCardService souvenirCardService;


    @Autowired
    private ICarbonEnterpriseService enterpriseService;


    /**
     * 查询纪念卡数据
     *
     * @param id 纪念卡数据主键
     * @return 纪念卡数据
     */
    @Override
    public CarbonCard selectCarbonCardById(Long id)
    {
        return carbonCardMapper.selectCarbonCardById(id);
    }

    /**
     * 查询纪念卡数据列表
     *
     * @param carbonCard 纪念卡数据
     * @return 纪念卡数据
     */
    @Override
    public List<CarbonCard> selectCarbonCardList(CarbonCard carbonCard)
    {
        return carbonCardMapper.selectCarbonCardList(carbonCard);
    }

    /**
     * 新增纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertCarbonCard(CarbonCard carbonCard)
    {
        // 插入数据库时 同步上链
        int code = carbonCardMapper.insertCarbonCard(carbonCard);
        if (code > 0)
        {
            CarbonCard card = carbonCardMapper.selectCardByName(carbonCard.getName());
            SouvenirCardRegisterCardInputBO cardInputBO = new SouvenirCardRegisterCardInputBO();
            cardInputBO.set_cardId(BigInteger.valueOf(card.getId()));
            cardInputBO.set_level(BigInteger.valueOf(carbonCard.getLevel()));
            cardInputBO.set_cardName(card.getName());
            cardInputBO.set_cardDesc(card.getDescription());
            cardInputBO.set_cardUrl(card.getUrl());
            cardInputBO.set_level(BigInteger.valueOf(card.getLevel()));
            cardInputBO.set_categoty(card.getCategory());
            cardInputBO.set_credit(carbonCard.getCredit());
            try
            {
                TransactionResponse transactionResponse = souvenirCardService.RegisterCard(cardInputBO);
                if (transactionResponse.getReturnMessage().equals("Success"))
                {
                    return AjaxResult.success("添加成功");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return AjaxResult.error("添加失败");
    }


    /**
     * 修改纪念卡数据
     *
     * @param carbonCard 纪念卡数据
     * @return 结果
     */
    @Override
    public int updateCarbonCard(CarbonCard carbonCard)
    {
        return carbonCardMapper.updateCarbonCard(carbonCard);
    }

    /**
     * 批量删除纪念卡数据
     *
     * @param ids 需要删除的纪念卡数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardByIds(Long[] ids)
    {
        return carbonCardMapper.deleteCarbonCardByIds(ids);
    }

    /**
     * 删除纪念卡数据信息
     *
     * @param id 纪念卡数据主键
     * @return 结果
     */
    @Override
    public int deleteCarbonCardById(Long id)
    {
        return carbonCardMapper.deleteCarbonCardById(id);
    }

    @Override
    public AjaxResult uploadCardImg(MultipartFile file) {
        AjaxResult ajax = AjaxResult.success();
        String imgUrl = tcosUtil.uploadFile(file);
        if (StringUtils.isEmpty(imgUrl)) return AjaxResult.error("上传失败");
        ajax.put("imgUrl",imgUrl);
        return ajax;
    }

    /**
     * 纪念卡兑换
     * @param creditVo 前端入参
     * @return 返回结果
     */
    @Override
    public AjaxResult creditedExchange(CreditVo creditVo) {
        String enterpriseName= creditVo.getUserName();
        String cardName = creditVo.getCardName();
        CarbonEnterprise enterprise = enterpriseService.selectByEnterpriseName(enterpriseName);
        if (Objects.isNull(enterprise))
        {
            return AjaxResult.error("该企业不存在");
        }
        CarbonCard carbonCard = carbonCardMapper.selectCardByName(cardName);
        if (Objects.isNull(carbonCard))
        {
            return AjaxResult.error("该纪念卡不存在");
        }

        SouvenirCardUserBindCardInputBO cardInputBO = new SouvenirCardUserBindCardInputBO();
        cardInputBO.set_cardName(cardName);
        cardInputBO.set_enterpriseName(enterpriseName);
        try
        {
            TransactionResponse transactionResponse = souvenirCardService.UserBindCard(cardInputBO);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                // TODO: 积分扣取
                return AjaxResult.success("兑换成功");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("兑换失败");
    }

    /**
     * 根据企业的名称查询该企业所有的纪念卡
     *
     * @param enterprise 需要查询的企业名称
     * @return 结果
     */
    @Override
    public AjaxResult selectCarbonCardListByEnterprise(String enterprise) {
        if (StringUtils.isEmpty(enterprise))
        {
            return AjaxResult.error("企业名称不能为空");
        }
        CarbonEnterprise carbonEnterprise = enterpriseService.selectByEnterpriseName(enterprise);
        if (Objects.isNull(carbonEnterprise))
        {
            return AjaxResult.error("该企业不存在");
        }
        SouvenirCardQueryEnterpriseCardListInputBO cardListInputBO = new SouvenirCardQueryEnterpriseCardListInputBO();
        cardListInputBO.set_enterpriseName(enterprise);
        try
        {
            TransactionResponse transactionResponse = souvenirCardService.QueryEnterpriseCardList(cardListInputBO);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                Object[] result = JSON.parseArray(transactionResponse.getValues()).getJSONArray(0).toArray();

                List<CarbonCard> cardList = Stream.of(result)
                        .map(Object::toString)
                        .map(JSON::parseArray)
                        .map(row -> {
                            CarbonCard card = new CarbonCard();
                            card.setId(row.getLongValue(0));
                            card.setLevel(row.getLongValue(1));
                            card.setName(row.getString(2));
                            card.setUrl(row.getString(3));
                            card.setDescription(row.getString(4));
                            card.setCategory(row.getString(5));
                            card.setCredit(row.getBigInteger(6));
                            return card;
                        }).collect(Collectors.toList());
                AjaxResult ajax = AjaxResult.success();
                ajax.put("data",cardList);
                return ajax;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("查询失败");
    }

}
