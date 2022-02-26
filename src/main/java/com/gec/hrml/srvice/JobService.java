package com.gec.hrml.srvice;

import com.gec.hrml.entity.JobInf;

import java.util.List;

public interface JobService {
    /**
     * 保存职位数据
     * @param jobInf 职位
     * @return
     */
    public int save(JobInf jobInf);
    /**
     * 删除职位数据
     * @param jobInfName
     * @return
     */
    public int delete(String jobInfName);
    /**
     * 修改职位数据
     * @param jobInf
     * @return
     */
    public int update(JobInf jobInf);

    /**
     * 统计的记录条数
     * @param jobInf
     * @return
     */
    public Long totalUserCount(JobInf jobInf);

    /**
     * 实现分页查询
     * @param jobInf
     * @return
     */
    public List<JobInf> queryByPage(JobInf jobInf);
}
