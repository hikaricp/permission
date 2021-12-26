SysDept -> 对应数据库的部门实体类

DeptLevelDto 部门层级数据传输对象，继承了部门实体类

* 将SysDept转换为DeptLevelDto。
  * 遍历dto，将level为0的节点存入rootList
  * 创建一个Map，key为层级level，value为List<SysDept>，存放相同level的部门信息
  * 组装后的数据格式为 level -> [dept1, dept2, ...]
  
* 将部门列表转换为树形结构。
  * 传递参数：根节点列表 -> rootList、根level -> 0、层级Map<String, List<Dept>>
  * 遍历获取某一个根节点，组装下一层级nextLevel = 根level(0) + "." + 当前根节点节点id。 0.1、0.11、0.11.25
  * 从Map中根据下一层级key（nextLevel）获取该层级列表List<DeptLevelDto>
  * 设置下一层数据
  * 递归生成，传递参数：要处理的当前层级List<DeptLevelDto>、nextLevel、Map<String, List<Dept>>
