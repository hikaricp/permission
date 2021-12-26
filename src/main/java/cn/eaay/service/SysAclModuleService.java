package cn.eaay.service;

import cn.eaay.dao.SysAclModuleMapper;
import cn.eaay.dto.AclModuleDto;
import cn.eaay.model.SysAclModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 权限模块业务层
 */

@Service
public class SysAclModuleService {

    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    public List<SysAclModule> getAllAclModule() {
        List<SysAclModule> aclModules = sysAclModuleMapper.getAll();
        return aclModules;
    }

    public List<AclModuleDto> trees() {
        // 获取权限模块全部信息
        List<SysAclModule> aclModules = sysAclModuleMapper.getAll();
        // 用于存储适配后的AclModuleDto列表
        List<AclModuleDto> aclModuleDtoList = Lists.newArrayList();
        // 遍历处理数据
        for (SysAclModule aclModule : aclModules) {
            AclModuleDto dto = AclModuleDto.adapt(aclModule);
            aclModuleDtoList.add(dto);
        }

        return aclModuleToTree(aclModuleDtoList);
    }

    public List<AclModuleDto> aclModuleToTree(List<AclModuleDto> aclModules) {
        // 若dto列表为空，直接返回空列表
        if (CollectionUtils.isEmpty(aclModules)) {
            return Lists.newArrayList();
        }
        // 用于存储根节点
        List<AclModuleDto> rootList = Lists.newArrayList();
        // 用于存储相同层级的权限模块
        // key: level, value: [aclModule1, aclModule2, ...]
        Map<String, List<AclModuleDto>> dtoMap = Maps.newHashMap();
        // 遍历处理数据
        for (AclModuleDto dto : aclModules) {

            // 根据level获取对应的dtoList
            List<AclModuleDto> aclModuleDtoList = dtoMap.get(dto.getLevel());
            // 若当前列表为空
            if (CollectionUtils.isEmpty(aclModuleDtoList)) {
                aclModuleDtoList = Lists.newArrayList();
            }
            aclModuleDtoList.add(dto);
            dtoMap.put(dto.getLevel(), aclModuleDtoList);

            // 存储level相同的key
            // 存储level为0的根节点
            if ("0".equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        transformAclModuleDto(rootList, "0", dtoMap);
        return rootList;
    }

    /**
     * 递归生成树
     *
     * @param aclModuleDtoList dto数据列表
     * @param level            层级
     * @param dtoMap           map
     */
    public void transformAclModuleDto(List<AclModuleDto> aclModuleDtoList, String level, Map<String, List<AclModuleDto>> dtoMap) {
        for (int i = 0; i < aclModuleDtoList.size(); i++) {
            // 获取当前节点
            AclModuleDto dtoLevelList = aclModuleDtoList.get(i);
            // 获取下一层级level
            String nextLevel = StringUtils.join(level, ".", dtoLevelList.getId());
            // 处理下一层级
            List<AclModuleDto> tempAclModuleList = dtoMap.get(nextLevel);
            // 若不为空，则进行递归
            if (CollectionUtils.isNotEmpty(tempAclModuleList)) {
                // 向父级中加入子集列表
                dtoLevelList.setAclModuleDtoList(tempAclModuleList);
                transformAclModuleDto(tempAclModuleList, nextLevel, dtoMap);
            }
        }
    }
}
