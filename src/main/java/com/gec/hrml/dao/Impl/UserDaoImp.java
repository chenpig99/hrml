package com.gec.hrml.dao.Impl;

import com.gec.hrml.dao.UserDao;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImp extends BaseDao<UserInf> implements UserDao   {
    @Override
    public int save(UserInf user) {
        String insertSql="insert into user_inf ( loginname, PASSWORD, STATUS, createdate, username)values (?,?,?,?,?)";
        Object[] params=new Object[]{user.getLoginname(),user.getPassword(),user.getStatus(),user.getCreatedate(),user.getUsername()};
        return update(insertSql,params);
    }



    @Override
    public int delete(String userLoginName) {
        String sql="delete from  user_inf where loginname=?";
        Object []params=new Object[]{userLoginName};
        return update(sql,params);
    }

    @Override
    public int update(UserInf user) {
        String updateSql="update user_inf set  loginname=? ,`password`=?, `status`=?, username=? where loginname=?";
        Object []params=new Object[]{user.getLoginname(),user.getPassword(),user.getStatus(),user.getUsername(),user.getLoginname()};
        return update(updateSql,params);
    }

    @Override
    public UserInf findUserByName(String username) {
        String sql="select *  from user_inf  where loginname=?";
        Object []params=new Object[]{username};
        return getBean(UserInf.class,sql,params);
    }

    @Override
    public List<UserInf> query(UserInf user) {
        //开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);

        String sql="select *  from user_inf ";

        return getBeanList(UserInf.class,sql);
    }

    /**
     * 模糊查询总条数
     * @param user
     * @return
     */
    @Override
    public Long totalUserCount(UserInf user) {
        List<Object> params = new ArrayList<>();

        String sql = "select count(*)  from user_inf where 1=1 ";

        if (user.getLoginname() != null && !user.getLoginname().equals("")) {
            sql += " and loginname like ?";
            params.add(user.getLoginname() + "%");
        }

        if (user.getUsername() != null && !user.getUsername().equals("")) {
            sql += " and username like ?";
            params.add(user.getUsername() + "%");
        }

        if (  user.getStatus()!=null &&user.getStatus() != ""){
            sql += " and STATUS = ?";
            params.add(user.getStatus());
        }

        return getCountValue(sql, params.toArray());
    }

    /**
     * 模糊查询分页
     * @param user
     * @return
     */
    @Override
    public List<UserInf> queryByPage(UserInf user) {
        List<Object> params=new ArrayList<>();

        String sql="select *  from user_inf where 1=1 ";

        if(user.getLoginname()!=null && !user.getLoginname().equals(""))
        {
            sql+=" and loginname like ?";
            params.add(user.getLoginname()+"%");
        }

        if(user.getUsername()!=null && !user.getUsername().equals(""))
        {
            sql+=" and username like ?";
            params.add(user.getUsername()+"%");
        }

        if (user.getStatus()!=null &&user.getStatus() != "") {
            sql+=" and STATUS = ?";
            params.add(user.getStatus());
        }

        sql+=" limit ?,?";
        params.add(user.getStartRowNum());
        params.add(user.getPageSize());

        System.out.println(sql);

        return getBeanList(UserInf.class,sql,params.toArray());
    }
}

