package com.gec.hrml.srvice.Impl;

import com.gec.hrml.dao.DeptDao;
import com.gec.hrml.dao.Impl.DeptDaoImpl;
import com.gec.hrml.entity.DeptInf;
import com.gec.hrml.srvice.DeptService;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao =new DeptDaoImpl();



    /**
     * 保存员工数据
     * @param dept 员工
     * @return
     */
    @Override
    public int save(DeptInf dept) {
        return deptDao.save(dept);
    }

    /**
     * 删除用户数据
     *
     * @param deptName 登陆名
     * @return
     */
    @Override
    public int delete(String deptName) {
        return deptDao.delete(deptName);
    }

    /**
     * 修改登陆用户信息
     *
     * @param dept 修改的用户
     * @return
     */
    @Override
    public int update(DeptInf dept) {
        return deptDao.update(dept);
    }

    /**
     * 根据用户名查找
     *
     * @param deptName
     * @return
     */
    @Override
    public DeptInf findUserByName(String deptName) {
        return deptDao.findUserByName(deptName);
    }

    /**
     * 统计的记录条数
     *
     * @param dept
     */
    @Override
    public Long totalUserCount(DeptInf dept) {

        return null;
    }

    /**
     * 实现分页查询
     *
     * @param dept
     */
    @Override
    public List<DeptInf> queryByPage(DeptInf dept) {
        int totalCount=deptDao.totalUserCount(dept).intValue();
        dept.setTotalRecordSum(totalCount);
        return deptDao.queryByPage(dept);
    }
}
