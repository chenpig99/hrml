package com.gec.hrml.srvice.Impl;

import com.gec.hrml.dao.DocumentDao;
import com.gec.hrml.dao.Impl.DocumentDaoImpl;
import com.gec.hrml.entity.DocumentInf;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.DocumentService;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    private DocumentDao documentDao=new DocumentDaoImpl();

    /**
     * 保存文件数据
     *
     * @param documentInf 文件数据
     * @param userInf  创建文件的用户
     * @return
     */
    @Override
    public int save(DocumentInf documentInf, UserInf userInf) {
        return documentDao.save(documentInf, userInf);
    }

    /**
     * 删除文件数据
     *
     * @param fileName
     * @return
     */
    @Override
    public int delete(String fileName) {
        return documentDao.delete(fileName);
    }

    /**
     * 统计记录条数
     *
     * @param documentInf
     * @return
     */
    @Override
    public Long totalUserCount(DocumentInf documentInf) {
        return null;
    }

    /**
     * 实现分页查询
     *
     * @param documentInf
     * @return
     */
    @Override
    public List<DocumentInf> queryByPage(DocumentInf documentInf) {
        Long totalRecordSum=documentDao.totalUserCount(documentInf);
        documentInf.setTotalRecordSum(totalRecordSum.intValue());
        return documentDao.queryByPage(documentInf);
    }
}
