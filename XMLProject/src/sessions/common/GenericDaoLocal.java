package sessions.common;

import java.io.Serializable;
import java.util.List;

import com.marklogic.client.query.StructuredQueryDefinition;

public interface GenericDaoLocal<T> {

	public T findById(String id);

//	public List<T> findAll() throws IOException, JAXBException;

	public T persist(T entity, String collId, boolean generateUri);

//	public T merge(T entity, ID id) throws IOException, JAXBException;
//
//	public void remove(ID id) throws IOException;
//	
//	public InputStream findBy(String xQuery, boolean wrap) throws IOException;
	
	public List<T> executeQuery(StructuredQueryDefinition query);

	public String executeXQuery(String query); 

} 