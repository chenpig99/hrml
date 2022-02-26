package com.gec.hrml.dao.Impl;

import com.gec.hrml.dao.DeptDao;
import com.gec.hrml.entity.DeptInf;
import com.gec.hrml.utils.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl extends BaseDao<DeptInf> implements DeptDao {
    /**
     * 保存员工数据
     * @param dept 员工
     * @return
     */
    public int save(DeptInf dept){
        String insertSql="insert into dept_inf (NAME, REMARK)values (?,?)";
        Object[] params=new Object[]{dept.getName(),dept.getRemark()};
        return update(insertSql,params);

    }
    /**
     * 删除用户数据
     * @param deptName 登陆名
     * @return
     */
    public int delete(String deptName){
        String sql="delete from  dept_inf where name=?";
        Object []params=new Object[]{deptName};
        return update(sql,params);
    }

    /**
     * 修改登陆用户信息
     * @param dept 修改的用户
     * @return
     */
    public int update(DeptInf dept){
        String updateSql="update dept_inf set  NAME=? REMARK=? where NAME=?";
        Object []params=new Object[]{};
        return update(updateSql,params);
    };

    /**
     * 根据用户名查找
     * @param deptName
     * @return
     */
    public DeptInf findUserByName(String deptName){
        String sql="select *  from dept_inf  where NAME=?";
        Object []params=new Object[]{deptName};
        return getBean(DeptInf.class,sql,params);
    };

    /**
     *  统计的记录条数
     */

    public Long totalUserCount(DeptInf dept){
        List<Object> params=new ArrayList<>();

        String sql = "select count(*) from dept_inf where 1=1";


        if(dept.getName()!=null && !dept.getName().equals(""))
        {
            sql+=" and name like ?";
            params.add(dept.getName()+"%");
        }
        return getCountValue(sql,params.toArray());
    };

    /**
     *  实现分页查询
     */

    public List<DeptInf> queryByPage(DeptInf dept){
        List<Object> params=new ArrayList<>();


        String sql="select *  from dept_inf where 1=1 ";

        if(dept.getName()!=null && !dept.getName().equals(""))
        {
            sql+=" and name like ?";
            params.add(dept.getName()+"%");
        }

        sql+=" limit ?,?";
        params.add(dept.getStartRowNum());
        params.add(dept.getPageSize());

        return getBeanList(DeptInf.class,sql,params.toArray());
    };
}
