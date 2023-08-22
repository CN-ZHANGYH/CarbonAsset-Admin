package com.ruoyi.carbon.domain.carbon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zyh
 * @date 2023/8/21 22:34
 * @desc IntelliJ IDEA
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonCollect {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /** 企业ID */
    @Excel(name = "企业ID")
    private Long enterpriseId;

    /** 纪念卡ID */
    @Excel(name = "纪念卡ID")
    private Long cardId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
