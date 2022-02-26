package com.gec.hrml.srvice;

import com.gec.hrml.entity.UserInf;

import java.util.List;

public interface UserService {
    /**
     * 保存员工数据
     * @param user 员工
     * @return
     */
    public int save(UserInf user);
    /**
     * 删除用户数据
     * @param userLoginName 登陆名
     * @return
     */
    public int delete(String userLoginName);

    /**
     * 修改登陆用户信息
     * @param user 修改的用户
     * @return
     */
    public int update(UserInf user);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public UserInf findUserByName(String username);

    /**
     *
     * @param user
     * @return
     */
    public List<UserInf> query(UserInf user);

    /**
     *  统计的记录条数
     */

    public Long totalUserCount(UserInf user);
    /**
     *  实现分页查询
     */

    public List<UserInf> queryByPage(UserInf user);
}
