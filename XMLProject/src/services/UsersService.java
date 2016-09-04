package services;

import java.util.List;

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
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;

import entities.amendment.Amandman;
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
//    	Users entity = new Users();
//    	Users retVal = null;
//    	Users.User a = new Users.User();
//    	Users.User b = new Users.User();
//    	Users.User c = new Users.User();
//    	Users.User d = new Users.User();
//    	a.setUsername("a");
//    	a.setPassword("a");
//    	a.setRole("president");
//    	b.setUsername("b");
//    	b.setPassword("b");
//    	b.setRole("alderman");
//    	c.setUsername("c");
//    	c.setPassword("c");
//    	c.setRole("alderman");
//    	d.setUsername("d");
//    	d.setPassword("d");
//    	d.setRole("alderman");
//    	entity.getUser().add(a);
//    	entity.getUser().add(b);
//    	entity.getUser().add(c);
//    	entity.getUser().add(d);
//		try {
//			retVal = usersDao.persist(entity, "/users/", true);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
		
//		usersDao.executeXQuery("xdmp:collection-delete(\"/amendment/inprocedure/\")");

		return null;
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
			retVal = usersDao.findById("13403408109757831043");
			for (Users.User u : retVal.getUser()) {
				if (u.getUsername().equals(loginUser.getUsername()) && 
						u.getPassword().equals(loginUser.getPassword()) &&
						u.getRole().equals(loginUser.getRole())) {
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
