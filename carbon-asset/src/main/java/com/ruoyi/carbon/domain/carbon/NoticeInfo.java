package com.ruoyi.carbon.domain.carbon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告栏信息通知
 * @author zyh
 * @date 2023/8/13 22:07
 * @desc 公告栏信息通知实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知描述
     */
    private String description;

    /**
     * 通知信息
     */
    private String msg;

    /**
     * 通知时间
     */
    private String noticeTime;
}
