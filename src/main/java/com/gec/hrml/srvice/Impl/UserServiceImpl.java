package com.gec.hrml.srvice.Impl;

import com.gec.hrml.dao.Impl.UserDaoImp;
import com.gec.hrml.dao.UserDao;
import com.gec.hrml.entity.UserInf;
import com.gec.hrml.srvice.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImp();



    @Override
    public int save(UserInf user) {
        return userDao.save(user);
    }

    @Override
    public int delete(String userLoginName) {
        return userDao.delete(userLoginName);
    }

    @Override
    public int update(UserInf user) {
        return userDao.update(user);
    }

    @Override
    public UserInf findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public List<UserInf> query(UserInf user) {
        return userDao.query(user);
    }

    @Override
    public Long totalUserCount(UserInf user) {
        return null;
    }

    @Override
    public List<UserInf> queryByPage(UserInf user) {
        Long totalRecordSum=userDao.totalUserCount(user);
        user.setTotalRecordSum(totalRecordSum.intValue());
        return userDao.queryByPage(user);
    }
}
