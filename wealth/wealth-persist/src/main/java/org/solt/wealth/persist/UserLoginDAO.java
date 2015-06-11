package org.solt.wealth.persist;

import org.solt.wealth.model.UserLogin;
import org.solt.wealth.persist.common.CommonDAO;
import org.springframework.stereotype.Repository;

/**
 * UserLogin DAO Class
 * @author solt
 */
@Repository
public class UserLoginDAO extends CommonDAO<UserLogin> {

	public UserLoginDAO() {
		super(UserLogin.class);
	}

}
