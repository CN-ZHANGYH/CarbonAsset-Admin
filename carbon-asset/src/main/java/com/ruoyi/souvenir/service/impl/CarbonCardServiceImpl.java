package com.ruoyi.souvenir.service.impl;

import java.math.BigInteger;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.carbon.utils.TCOSUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.souvenir.model.bo.SouvenirCardRegisterCardInputBO;
import com.ruoyi.souvenir.service.souvenir.SouvenirCardService;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.souvenir.mapper.CarbonCardMapper;
import com.ruoyi.souvenir.domain.CarbonCard;
import com.ruoyi.souvenir.service.card.ICarbonCardService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 纪念卡数据Service业务层处理
 *
 * @author 张宇豪
 * @date 2023-07-28
 */
@Service
public class CarbonCardServiceImpl implements ICarbonCardService
{
    @Autowired
    private CarbonCardMapper carbonCardMapper;

    @Autowired
    private TCOSUtil tcosUtil;

    @Autowired
    private SouvenirCardService souvenirCardService;

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
    public int insertCarbonCard(CarbonCard carbonCard)
    {

        int code = carbonCardMapper.insertCarbonCard(carbonCard);
        if (code > 0)
        {
            CarbonCard card = carbonCardMapper.selectAllByNameCard(carbonCard.getName());
            SouvenirCardRegisterCardInputBO cardInputBO = new SouvenirCardRegisterCardInputBO();
            cardInputBO.set_cardId(BigInteger.valueOf(card.getId()));
            cardInputBO.set_cardName(card.getName());
            cardInputBO.set_cardDesc(card.getDescription());
            cardInputBO.set_cardUrl(card.getUrl());
            cardInputBO.set_level(BigInteger.valueOf(card.getLevel()));
            cardInputBO.set_categoty(card.getCategory());
            try
            {
                TransactionResponse transactionResponse = souvenirCardService.RegisterCard(cardInputBO);
                if (transactionResponse.getReturnMessage().equals("Success"))
                {
                    return code;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
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
}
