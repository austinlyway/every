package com.lyways.business.user.test.dao;

import com.lyways.business.user.dao.UserDAO;
import com.lyways.business.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author austin
 * @createDate 2017/11/26.
 */
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;
    public void testInsert()
    {
        User user = new User();
        user.setUserName("Austin");
        user.setPhone("13631231429");
        user.setRealName("王威");
        userDAO.insert(user);
    }
}
