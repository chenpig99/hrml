package com.gec.hrml.dao;

import com.gec.hrml.entity.DocumentInf;
import com.gec.hrml.entity.UserInf;

import java.util.List;

public interface DocumentDao {
    /**
     * 保存文件数据
     * @param documentInf 文件数据
     * @param userInf 创建文件的用户
     * @return
     */
    public int save(DocumentInf documentInf, UserInf userInf);

    /**
     * 删除文件数据
     * @param fileName
     * @return
     */
    public int delete(String fileName);
    /**
     * 统计记录条数
     * @param documentInf
     * @return
     */
    public Long totalUserCount(DocumentInf documentInf);

    /**
     * 实现分页查询
     * @param documentInf
     * @return
     */
    public List<DocumentInf> queryByPage(DocumentInf documentInf);
}
