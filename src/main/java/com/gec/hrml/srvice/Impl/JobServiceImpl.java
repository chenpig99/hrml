package com.gec.hrml.srvice.Impl;

import com.gec.hrml.dao.Impl.JobDaoImpl;
import com.gec.hrml.dao.JobDao;
import com.gec.hrml.entity.JobInf;
import com.gec.hrml.srvice.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    private JobDao jobDao=new JobDaoImpl();

    /**
     * 保存职位数据
     *
     * @param jobInf 职位
     * @return
     */
    @Override
    public int save(JobInf jobInf) {
        return jobDao.save(jobInf);
    }

    /**
     * 删除职位数据
     * @param jobInfName
     * @return
     */
    @Override
    public int delete(String jobInfName) {
        return jobDao.delete(jobInfName);
    }

    /**
     * 修改职位数据
     *
     * @param jobInf
     * @return
     */
    @Override
    public int update(JobInf jobInf) {

        return jobDao.update(jobInf);
    }

    /**
     * 统计的记录条数
     *
     * @param jobInf
     * @return
     */
    @Override
    public Long totalUserCount(JobInf jobInf) {
        return null;
    }

    /**
     * 实现分页查询
     *
     * @param jobInf
     * @return
     */
    @Override
    public List<JobInf> queryByPage(JobInf jobInf) {

        int totalCount=jobDao.totalUserCount(jobInf).intValue();
        jobInf.setTotalRecordSum(totalCount);
        return jobDao.queryByPage(jobInf);

    }
}
