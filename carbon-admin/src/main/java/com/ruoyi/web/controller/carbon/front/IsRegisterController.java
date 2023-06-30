package com.ruoyi.web.controller.carbon.front;

import com.ruoyi.carbon.domain.vo.RegisterParam;
import com.ruoyi.carbon.service.user.EmailUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册验证
 *
 */
@Api("用户注册管理")
@RestController
public class IsRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;


    @Autowired
    private EmailUserService emailUserService;


    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterParam user) throws Exception {

        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @ApiOperation("用户验证码")
    @GetMapping("/sendCode")
    public AjaxResult sendCode(@RequestParam String email){
        if (StringUtils.isEmpty(email))
        {
            return error("当前的邮箱不能为空");
        }
        AjaxResult ajax = AjaxResult.success();
        String code = emailUserService.sendEmailCode(email);
        ajax.put("msg","发送成功");
        ajax.put("code",code);
        return ajax;
    }
}
