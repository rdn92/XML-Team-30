package sessions.common;

import java.io.Serializable;

import javax.xml.bind.JAXBContext;

import xmldb.EntityManagerMarkLogic;

public abstract class GenericDao<T, ID extends Serializable> implements GenericDaoLocal<T, ID> {
	
	protected String contextPath;
	
	protected JAXBContext context;
	
	protected EntityManagerMarkLogic<T, ID> em;
	
	public GenericDao(String contextPath, String schemaName) {
		try {
			context = JAXBContext.newInstance(contextPath);
			em = new EntityManagerMarkLogic<T, ID>(schemaName, contextPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T persist(T entity) {
//		Long id = em.getIdentity();
//		entity.setId(123456L);
		System.out.println("pozivam entity manager");
		em.persist(entity, /*id*/ 123456L);
		return entity;
	}
	
	public T findById(ID id) {
		T entity;
		entity = em.find(id);
		return entity;
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