package com.gec.hrml.dao;

import com.gec.hrml.entity.NoticeInf;
import com.gec.hrml.entity.UserInf;

import java.util.List;

public interface NoticeDao {
    /**
     * 保存公告数据
     * @param noticeInf 公告数据
     * @param userInf 创建文件的用户
     * @return
     */
    public int save(NoticeInf noticeInf, UserInf userInf);

    /**
     * 删除公告
     * @param noticeTitle 公告题目
     * @return
     */
    public int delete(String noticeTitle);
    /**
     * 统计记录条数
     * @param noticeInf
     * @return
     */
    public Long totalUserCount(NoticeInf noticeInf);

    /**
     * 实现分页查询
     * @param noticeInf
     * @return
     */
    public List<NoticeInf> queryByPage(NoticeInf noticeInf);

    int update(NoticeInf noticeInf);

    List<NoticeInf> query();

    List<NoticeInf> queryByTitle(String title);
}
