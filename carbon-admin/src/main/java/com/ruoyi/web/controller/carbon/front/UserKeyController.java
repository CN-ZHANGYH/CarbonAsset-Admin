package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.carbon.domain.user.UserKey;
import com.ruoyi.carbon.service.user.UserKeyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @date 2023/7/10 13:24
 * @desc IntelliJ IDEA
 */

@RestController
public class UserKeyController extends BaseController {

    @Autowired
    private UserKeyService userKeyService;

    @GetMapping("/newKey")
    public AjaxResult newUserKeyAndAddress() {
        AjaxResult ajax = AjaxResult.success();
        UserKey userKey = userKeyService.newUserKeyAndAddress();
        ajax.put("user",userKey);
        return ajax;
    }
}
