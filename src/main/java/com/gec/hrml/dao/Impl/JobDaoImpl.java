package com.gec.hrml.dao.Impl;

import com.gec.hrml.dao.JobDao;
import com.gec.hrml.entity.JobInf;
import com.gec.hrml.utils.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl extends BaseDao<JobInf> implements JobDao {
    /**
     * 保存职位数据
     *
     * @param jobInf 职位
     * @return
     */
    @Override
    public int save(JobInf jobInf) {
        String insertSql="insert into job_inf ( NAME, REMARK)values (?,?)";
        Object[] params=new Object[]{jobInf.getName(),jobInf.getRemark()};
        return update(insertSql,params);
    }

    /**
     *
     *删除职位数据
     * @param jobInfName
     * @return
     */
    @Override
    public int delete(String jobInfName) {
        String sql="delete from  job_inf where NAME=?";
        Object []params=new Object[]{jobInfName};
        return update(sql,params);
    }

    /**
     * 修改职位数据
     *
     * @param jobInf
     * @return
     */
    @Override
    public int update(JobInf jobInf) {
        String updateSql="update job_inf set  NAME=? REMARK=? where  NAME=?";
        Object []params=new Object[]{jobInf.getName(),jobInf.getRemark(),jobInf.getName()};
        return update(updateSql,params);
    }

    /**
     * 统计的记录条数
     *
     * @param jobInf
     * @return
     */
    @Override
    public Long totalUserCount(JobInf jobInf) {
        List<Object> params=new ArrayList<>();

        String sql = "select count(*) from job_inf where 1=1";

        if(jobInf.getName()!=null && !jobInf.getName().equals(""))
        {
            sql+=" and name like ?";
            params.add(jobInf.getName()+"%");
        }
        return getCountValue(sql,params.toArray());
    }

    /**
     *
     *实现分页查询
     * @param jobInf
     * @return
     */
    @Override
    public List<JobInf> queryByPage(JobInf jobInf) {
        List<Object> params=new ArrayList<>();
        //无数据时可拼接数据sql
        String sql="select *  from job_inf where 1=1 ";
        if(jobInf.getName()!=null && !jobInf.getName().equals(""))
        {
            sql+=" and name like ?";
            params.add(jobInf.getName()+"%");
        }
        sql+=" limit ?,?";
        params.add(jobInf.getStartRowNum());
        params.add(jobInf.getPageSize());
        System.out.println(sql);
        return getBeanList(JobInf.class,sql,params.toArray());
    }
}
