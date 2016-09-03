package sessions.common;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBContext;

import com.marklogic.client.query.StructuredQueryDefinition;

import xmldb.EntityManagerMarkLogic;

public abstract class GenericDao<T> implements GenericDaoLocal<T> {
	
	protected String contextPath;
	
	protected JAXBContext context;
	
	protected EntityManagerMarkLogic<T> em;
	
	public GenericDao(String contextPath, String schemaName) {
		try {
			context = JAXBContext.newInstance(contextPath);
			em = new EntityManagerMarkLogic<T>(schemaName, contextPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T persist(T entity, String collId, boolean generateUri) {
		em.persist(entity, collId, generateUri);
		return entity;
	}
	
	public T findById(String id) {
		T entity;
		entity = em.find(id);
		return entity;
	}
	
	public List<T> executeQuery(StructuredQueryDefinition query) {
		return em.executeQuery(query);
	}
	
	public String executeXQuery(String query) {
		return em.executeXQuery(query);
	}

//	public InputStream findBy(String xQuery, boolean wrap) throws IOException {
//		InputStream result;
//		result = em.executeQuery(xQuery, wrap);
//		return result;
//	}
//	
//	public List<T> findAll() throws IOException, JAXBException {
//		List<T> result;
//		result = em.findAll();
//		return result;
//	}
//	
//	public void remove(ID id) throws IOException {
//		em.delete(id);
//	}
//
//	public T merge(T entity, ID id) throws IOException, JAXBException {
//		em.update(entity, id);
//		return entity;
//	}
	
} 