package com.gec.hrml.srvice.Impl;

import com.gec.hrml.dao.Impl.NoticeDaoImpl;
import com.gec.hrml.dao.NoticeDao;
import com.gec.hrml.entity.NoticeInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao = new NoticeDaoImpl();

    /**
     * 保存公告数据
     *
     * @param noticeInf 公告数据
     * @param userInf   创建文件的用户
     * @return
     */
    @Override
    public int save(NoticeInf noticeInf, UserInf userInf) {
        return noticeDao.save(noticeInf, userInf);
    }

    /**
     * 删除公告
     *
     * @param noticeTitle 公告题目
     * @return
     */
    @Override
    public int delete(String noticeTitle) {
        return noticeDao.delete(noticeTitle);
    }

    /**
     * 统计记录条数
     *
     * @param noticeInf
     * @return
     */
    @Override
    public Long totalUserCount(NoticeInf noticeInf) {
        return null;
    }

    /**
     * 实现分页查询
     *
     * @param noticeInf
     * @return
     */
    @Override
    public List<NoticeInf> queryByPage(NoticeInf noticeInf) {
        Long totalRecordSum=noticeDao.totalUserCount(noticeInf);
        noticeInf.setTotalRecordSum(totalRecordSum.intValue());
        return noticeDao.queryByPage(noticeInf);
    }
    @Override
    public int update(NoticeInf noticeInf){
        return noticeDao.update(noticeInf);
    }

    @Override
    public List<NoticeInf> query(){
        return noticeDao.query();
    }

    @Override
    public List<NoticeInf> queryByTitle(String title) {
        return noticeDao.queryByTitle(title);
    }
}
