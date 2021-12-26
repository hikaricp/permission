package cn.eaay.service;

import cn.eaay.dao.SysDeptMapper;
import cn.eaay.model.SysDept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public List<SysDept> getAllDept() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        return deptList;
    }
}
