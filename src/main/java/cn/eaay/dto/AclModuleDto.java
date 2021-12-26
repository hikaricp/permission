package cn.eaay.dto;

import cn.eaay.model.SysAclModule;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 权限模块层级Dto
 */
@Getter
@Setter
public class AclModuleDto extends SysAclModule {

    private List<AclModuleDto> aclModuleDtoList = Lists.newArrayList();

    /**
     * 将SysAclModule适配为AclModuleDto
     *
     * @param aclModule
     * @return AclModuleDto
     */
    public static AclModuleDto adapt(SysAclModule aclModule) {
        AclModuleDto dto = new AclModuleDto();
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }


}
