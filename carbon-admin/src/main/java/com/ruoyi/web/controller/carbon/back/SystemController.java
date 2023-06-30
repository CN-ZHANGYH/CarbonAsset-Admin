package com.ruoyi.web.controller.carbon.back;


import com.ruoyi.carbon.service.system.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @date 2023/7/8 18:33
 * @desc IntelliJ IDEA
 */


@RestController
@RequestMapping("carbon")
public class SystemController {

    @Autowired
    private DataViewService dataViewService;


    @GetMapping("blockNumber")
    public void getBlockNumber() {
        dataViewService.GetBlockNumber();
    }

}
