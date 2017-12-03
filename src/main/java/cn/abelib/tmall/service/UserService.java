package cn.abelib.tmall.service;

import cn.abelib.tmall.bean.User;

import java.util.List;

/**
 * Created by abel on 2017/11/12.
 */
public interface UserService {
    /**
     * ͳ��User������
     * @return
     */
    Integer countUser();

    /**
     * �г����е�User
     * @param
     * @return
     */
    List<User> listAllUser();

    /**
     * ����Product�����һ���id
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * ɾ����Ӧid��User
     * @param id
     * @return
     */
    Integer deleteUser(Integer id);

    /**
     *  ����User
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * ͨ��id��ȡUser
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
