package cn.eaay.service;

import cn.eaay.dao.SysDeptMapper;
import cn.eaay.dto.DeptLevelDto;
import cn.eaay.model.SysDept;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysTreeService {

    private static final Logger logger = LoggerFactory.getLogger(SysTreeService.class);

    @Resource
    private SysDeptMapper sysDeptMapper;

    public List<DeptLevelDto> deptTree() {
        // 获取部门所有数据
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        // 用于存放转换后的dto列表
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept : deptList) {
            // 将dept转换为部门层级dto
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            // 将dto添加进列表
            dtoList.add(dto);
        }

        // 将部门列表转换为树形结构
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        // 若部门层级列表为空，直接返回一个空列表
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }

        // level -> [dept1, dept2, ...] == Map<String, List<Object>>
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        // 用于存放根列表
        List<DeptLevelDto> rootList = Lists.newArrayList();

        // 遍历处理数据
        for (DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            // 若该部门层级为0，则加入根节点
            if ("0".equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }

        // 递归生成树
        transformDeptTree(rootList, "0", levelDeptMap);

        return rootList;
    }

    /**
     * @param deptLevelList 根节点数据
     * @param level
     * @param levelDeptMap
     */
    public void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // 计算下一层级level，处理当前层级的数据
            String nextLevel = StringUtils.join(level, ".", deptLevelDto.getId());
            // 处理下一层级
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            // 若下一层级不为空
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // TODO: 排序
                // 设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                // 处理下一层
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }
}
