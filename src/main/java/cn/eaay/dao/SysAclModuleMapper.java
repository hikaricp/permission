package cn.eaay.dao;

import cn.eaay.model.SysAclModule;

import java.util.List;

/**
 * 权限模块持久层接口定义
 */
public interface SysAclModuleMapper {

    /**
     * 获取全部权限模块列表
     * @return
     */
    List<SysAclModule> getAll();
}
