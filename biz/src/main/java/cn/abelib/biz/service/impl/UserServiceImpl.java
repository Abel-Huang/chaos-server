package cn.abelib.biz.service.impl;

import cn.abelib.biz.constant.BusinessConstant;
import cn.abelib.biz.pojo.User;
import cn.abelib.st.core.data.redis.RedisStringService;
import cn.abelib.st.core.result.Response;
import cn.abelib.biz.constant.StatusConstant;
import cn.abelib.st.core.utils.Md5Util;
import cn.abelib.biz.dao.UserDao;
import cn.abelib.biz.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author abel
 * @date 2018/2/5
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    // cookie name
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisStringService redisService;

    /**
     *  用户登录接口
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    public Response<User> login(String userName, String userPassword){
        Integer resultCount = userDao.checkUserName(userName);
        if (resultCount == 0){
            return Response.failed(StatusConstant.ACCOUNT_NOT_EXISTS);
        }
        //  这里需要进行密码的转换
        User user = userDao.selectLogin(userName);
        String dbPassword = Md5Util.dbPassword(userPassword, user.getSalt());

        if (!dbPassword.equals(user.getUserPassword())){
            return Response.failed(StatusConstant.WRONG_PASS_ERROR);
        }
        user.setUserPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return Response.success(StatusConstant.GENERAL_SUCCESS, user);
    }

    /**
     *  用户注册接口
     * @param user
     * @return
     */
    @Override
    public Response<String> register(User user){
        Integer resultCount = userDao.checkUserName(user.getUserName());
        if (resultCount > 0){
            return Response.failed(StatusConstant.ACCOUNT_ALREADY_EXISTS);
        }
        user.setRole(BusinessConstant.Role.ROLE_CUSTOMER);
        String salt = Md5Util.randSalt(11);
        String dbPassword = Md5Util.dbPassword(user.getUserPassword(), salt);
        user.setSalt(salt);
        user.setUserPassword(dbPassword);
        user.setRole(BusinessConstant.Role.ROLE_CUSTOMER);

        resultCount = userDao.insertUser(user);
        if (resultCount == 0){
            return Response.failed(StatusConstant.INSERT_USER_ERROR);
        }
        return Response.success(StatusConstant.GENERAL_SUCCESS);
    }

    /**
     *  修改用户密码
     * @param originalPass
     * @param newPassword
     * @param user
     * @return
     */
    @Override
    public Response<String> resetPassword(String originalPass, String newPassword, User user){
        int resultCount = userDao.checkPassword(user.getId(), Md5Util.dbPassword(originalPass, user.getSalt()));
        if (resultCount == 0){
            return Response.failed(StatusConstant.WRONG_PASS_ERROR);
        }
        user.setUserPassword(Md5Util.dbPassword(newPassword, user.getSalt()));
        user.setUserPassword(Md5Util.dbPassword(newPassword, user.getSalt()));

        int updateCount = userDao.updateUser(user);
        if (updateCount > 0){
            return Response.success(StatusConstant.GENERAL_SUCCESS);
        }
        return Response.failed(StatusConstant.UPDATE_PASS_ERROR);
    }

    /**
     *  更改用户个人信息
     * @param user
     * @return
     */
    @Override
    public Response<User> updateUserInfo(User user){
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setNickName(user.getNickName());
        updateUser.setPhone(user.getPhone());
        updateUser.setRole(user.getRole());

        int updateCount = userDao.updateUser(user);
        if (updateCount > 0){
            return Response.success(StatusConstant.GENERAL_SUCCESS);
        }
        return Response.failed(StatusConstant.UPDATE_USER_ERROR);
    }

    /**
     *  通过User的Role属性判断是否是管理员
      * @param user
     * @return
     */
    @Override
    public Response<String> checkAdminRole(User user){
        if (user != null && user.getRole().equals(BusinessConstant.Role.ROLE_ADMIN)){
            return Response.success(StatusConstant.GENERAL_SUCCESS);
        }
        return Response.failed(StatusConstant.GENERAL_SERVER_ERROR);
    }
}
