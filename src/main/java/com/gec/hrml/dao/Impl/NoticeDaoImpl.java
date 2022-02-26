package com.gec.hrml.dao.Impl;

import com.gec.hrml.dao.NoticeDao;
import com.gec.hrml.entity.NoticeInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.utils.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl extends BaseDao<NoticeInf> implements NoticeDao {
    /**
     * 保存公告数据
     *
     * @param noticeInf 公告数据
     * @param userInf   创建文件的用户
     * @return
     */
    @Override
    public int save(NoticeInf noticeInf, UserInf userInf) {
        String insertSql="insert into notice_inf (TITLE, CONTENT, CREATE_DATE, USER_ID)values (?,?,?,?)";
        Object[] params=new Object[]{noticeInf.getTitle(),noticeInf.getContent(),noticeInf.getCreateDate(),userInf.getId()};
        return update(insertSql, params);
    }

    /**
     * 删除公告
     *
     * @param noticeTitle 公告题目
     * @return
     */
    @Override
    public int delete(String noticeTitle) {
        String deleteSql="delete  from notice_inf where TITLE=?";
        Object[] params=new Object[]{noticeTitle};
        System.out.println(noticeTitle);
        return update(deleteSql, params);
    }

    /**
     * 统计记录条数
     * @param noticeInf
     * @return
     */
    @Override
    public Long totalUserCount(NoticeInf noticeInf) {
        List<Object> params=new ArrayList<>();

        String sql = "select count(*) from notice_inf where 1=1";


        if(noticeInf.getTitle()!=null && !noticeInf.getTitle().equals(""))
        {
            sql+=" and title like ?";
            params.add(noticeInf.getTitle()+"%");
        }
        return getCountValue(sql,params.toArray());
    }

    /**
     * 实现分页查询
     * @param noticeInf
     * @return
     */
    @Override
    public List<NoticeInf> queryByPage(NoticeInf noticeInf) {
        List<Object> params=new ArrayList<>();


        String sql="select *  from notice_inf where 1=1 ";

        if(noticeInf.getTitle()!=null && !noticeInf.getTitle().equals(""))
        {
            sql+=" and title like ?";
            params.add(noticeInf.getTitle()+"%");
        }

        sql+=" limit ?,?";
        params.add(noticeInf.getStartRowNum());
        params.add(noticeInf.getPageSize());

        return getBeanList(NoticeInf.class,sql,params.toArray());
    }
    /**
     * 修改登陆公告信息
     * @param notice 修改的公告
     * @return
     */
    @Override
    public int update(NoticeInf noticeInf){
        String updateSql="update notice_inf set content =? where title=?";
        Object []params=new Object[]{noticeInf.getContent(),noticeInf.getTitle()};
        return update(updateSql,params);
    }
    /**
     * 实现查询
     * @return
     */
    @Override
    public List<NoticeInf> query() {
        List<Object> params=new ArrayList<>();
        String sql="select *  from notice_inf ";
        return getBeanList(NoticeInf.class,sql,params.toArray());
    }
    @Override
    public List<NoticeInf> queryByTitle(String title) {
        List<Object> params=new ArrayList<>();
        String sql="select *  from notice_inf where title=? ";
        params.add(title);
        return getBeanList(NoticeInf.class,sql,params.toArray());
    }
}
