package cn.eaay.model;

import lombok.*;

import java.util.Date;

/**
 * 部门实体类
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysDept {

    // 部门id
    private Integer id;

    // 部门名称
    private String name;

    // 上级部门id
    private Integer parentId;

    // 部门层级
    private String level;

    // 部门在当前层级下的顺序，从小到大
    private Integer seq;

    // 备注
    private String remark;

    // 操作者
    private String operator;

    // 最后一次操作时间
    private Date operateTime;

    // 最后一次更新操作者ip地址
    private String operateIp;

}
