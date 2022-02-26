package com.gec.hrml.dao.Impl;

import com.gec.hrml.dao.DocumentDao;
import com.gec.hrml.entity.DocumentInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.utils.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class DocumentDaoImpl extends BaseDao<DocumentInf> implements DocumentDao {

    /**
     * 保存文件数据
     * @param documentInf 文件数据
     * @param userInf 创建文件的用户
     * @return
     */
    public int save(DocumentInf documentInf, UserInf userInf) {
        String insertSql="insert into document_inf (TITLE, filename, REMARK, CREATE_DATE, USER_ID) values (?,?,?,?,?)";
        Object[] params=new Object[]{documentInf.getTitle(),documentInf.getFilename(),documentInf.getRemark(),documentInf.getCreateDate(),userInf.getId()};
        return update(insertSql,params);
    }
    /**
     * 删除文件数据
     * @param fileName
     * @return
     */
    @Override
    public int delete(String title) {
       String deleteSql="delete from document_inf where title=?";
        Object[] params=new Object[]{title};
        return  update(deleteSql, params);
    }

    /**
     * 统计记录条数
     * @param documentInf
     * @return
     */
    @Override
    public Long totalUserCount(DocumentInf documentInf) {
        List<Object> params=new ArrayList<>();

        String sql = "select count(*) from document_inf where 1=1";

        if(documentInf.getTitle()!=null && !documentInf.getTitle().equals(""))
        {
            sql+=" and title like ?";
            params.add(documentInf.getTitle()+"%");
        }
        return getCountValue(sql,params.toArray());
    }

    /**
     * 实现分页查询
     *
     * @param documentInf@return
     */
    @Override
    public List<DocumentInf> queryByPage(DocumentInf documentInf) {
        List<Object> params=new ArrayList<>();

        String sql = "select * from document_inf where 1=1";

        if(documentInf.getTitle()!=null && !documentInf.getTitle().equals(""))
        {
            sql+=" and title like ?";
            params.add(documentInf.getTitle()+"%");
        }
        sql+=" limit ?,?";
        params.add(documentInf.getStartRowNum());
        params.add(documentInf.getPageSize());
        System.out.println(sql);
        return getBeanList(DocumentInf.class,sql,params.toArray());
    }
}
