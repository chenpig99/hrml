package com.gec.hrml.srvice;

import com.gec.hrml.entity.DeptInf;

import java.util.List;

public interface DeptService  {
    /**
     * 保存员工数据
     * @param dept 员工
     * @return
     */
    public int save(DeptInf dept);
    /**
     * 删除用户数据
     * @param deptName 登陆名
     * @return
     */
    public int delete(String deptName);

    /**
     * 修改登陆用户信息
     * @param dept 修改的用户
     * @return
     */
    public int update(DeptInf dept);

    /**
     * 根据用户名查找
     * @param deptName
     * @return
     */
    public DeptInf findUserByName(String deptName);

    /**
     *  统计的记录条数
     */

    public Long totalUserCount(DeptInf dept);
    /**
     *  实现分页查询
     */

    public List<DeptInf> queryByPage(DeptInf dept);
}
