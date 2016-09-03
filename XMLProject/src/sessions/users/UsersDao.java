package sessions.users;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.users.Users;
import sessions.common.GenericDao;

@Stateless
@Local(UsersDaoLocal.class)
public class UsersDao extends GenericDao<Users> implements UsersDaoLocal {
	
public static final String contextPath = "entities.users";
	
	public static final String schemaName = "/users/";
	
	public UsersDao() {
		super(contextPath, schemaName);
	}
}
