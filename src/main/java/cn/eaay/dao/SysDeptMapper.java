package cn.eaay.dao;

import cn.eaay.model.SysDept;

import java.util.List;

public interface SysDeptMapper {

    /**
     * 获取所有部门列表
     *
     * @return List<SysDept> 部门列表
     */
    List<SysDept> getAllDept();
}
