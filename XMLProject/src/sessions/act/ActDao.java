package sessions.act;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.act.Akt;
import sessions.common.GenericDao;

@Stateless
@Local(ActDaoLocal.class)
public class ActDao extends GenericDao<Akt, Long> implements ActDaoLocal {
	
public static final String contextPath = "entities.act";
	
	public static final String schemaName = "act";
	
	public ActDao() {
		super(contextPath, schemaName);
	}
}
