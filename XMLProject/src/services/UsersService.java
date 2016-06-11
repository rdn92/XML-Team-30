package services;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.users.Users;
import sessions.users.UsersDaoLocal;

@Path("/users")
public class UsersService {

	private final Logger log = LoggerFactory.getLogger(UsersService.class);

	@EJB
	private UsersDaoLocal usersDao;
	
	
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public Users create() {
    	System.out.println("pozvan rest");
    	Users entity = new Users();
    	Users retVal = null;
		try {
			retVal = usersDao.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
    public String login(String user) {
		
    	Users retVal = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Users.User loginUser = mapper.readValue(user, Users.User.class);
			retVal = usersDao.findById(123456L);
			for (Users.User u : retVal.getUser()) {
				if (u.getUsername().equals(loginUser.getUsername()) && 
						u.getPassword().equals(loginUser.getPassword())) { /* TODO Jos provera za role i password*/
					return "Login success!";
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "Server error!";
		}
		return "Wrong username or/and password.";
    }
}
