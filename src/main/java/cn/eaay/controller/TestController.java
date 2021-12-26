package cn.eaay.controller;

import cn.eaay.common.JsonData;
import cn.eaay.dto.AclModuleDto;
import cn.eaay.model.SysAclModule;
import cn.eaay.service.SysAclModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("/acl")
    public JsonData home() {
        List<SysAclModule> aclModules = sysAclModuleService.getAllAclModule();
        return JsonData.success(aclModules, "success");
    }

    @RequestMapping("/trees")
    public JsonData tree() {
        List<AclModuleDto> dtoList = sysAclModuleService.trees();
        return JsonData.success(dtoList, "success");
    }
}
