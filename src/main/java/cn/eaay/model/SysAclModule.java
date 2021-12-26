package cn.eaay.model;

import lombok.*;

import java.util.Date;

/**
 * 访问控制列表实体类
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule {

    // 权限模块id
    private Integer id;

    // 权限模块名称
    private String name;

    // 上级权限模块id
    private Integer parentId;

    // 权限模块层级
    private String level;

    // 当前层级下的顺序
    private Integer seq;

    // 状态：1：正常 0：冻结
    private Integer status;

    // 备注
    private String remark;

    // 操作者
    private String operator;

    // 最后一次操作时间
    private Date operateTime;

    // 最后一次更新操作者的ip
    private String operateIp;


}
