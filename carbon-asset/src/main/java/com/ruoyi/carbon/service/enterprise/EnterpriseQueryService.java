package com.ruoyi.carbon.service.enterprise;

import com.ruoyi.common.core.domain.AjaxResult;

public interface EnterpriseQueryService {

    public AjaxResult queryEnterpriseInfo(String address);

    public AjaxResult queryQualificationInfo(String address);


    /**
     *  分页查询企业的交易历史记录
     */
    public AjaxResult queryEnterpriseTxList(String enterpriseName);

    /**
     * 分页查询企业的碳排放资源订单
     */
    public AjaxResult queryEnterpriseErList(String enterpriseName);

    public AjaxResult queryEnterpriseAList(String enterprise);
}
