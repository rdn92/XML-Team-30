package sessions.amendment;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.amendment.Amandman;
import sessions.common.GenericDao;

@Stateless
@Local(AmendmentDaoLocal.class)
public class AmendmentDao extends GenericDao<Amandman> implements AmendmentDaoLocal {
	
	public static final String contextPath = "entities.amendment";
	
	public static final String schemaName = "/amendment/";
	
	public AmendmentDao() {
		super(contextPath, schemaName);
	}

}
