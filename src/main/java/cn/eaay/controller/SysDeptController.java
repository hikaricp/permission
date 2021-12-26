package cn.eaay.controller;

import cn.eaay.dto.DeptLevelDto;
import cn.eaay.model.SysDept;
import cn.eaay.service.SysDeptService;
import cn.eaay.service.SysTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class SysDeptController {

    @Resource
    private SysTreeService sysTreeService;

    @Resource
    private SysDeptService deptService;


    @ResponseBody
    @RequestMapping("/trees")
    public List<DeptLevelDto> getAll() {
        return sysTreeService.deptTree();
    }
}
