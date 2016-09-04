package xmldb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.ResourceNotFoundException;
import com.marklogic.client.document.DocumentPage;
import com.marklogic.client.document.DocumentRecord;
import com.marklogic.client.document.DocumentUriTemplate;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;

import entities.act.Akt;
import entities.amendment.Amandman;
import xmldb.Util.ConnectionProperties;

public class EntityManagerMarkLogic<T> {
	
	private String schemaName;
	
	private String contextPath;
		
	private JAXBContext context;
		
	private ConnectionProperties props;
		
	public EntityManagerMarkLogic(String schemaName, String contextPath) throws JAXBException, IOException {
		setSchemaName(schemaName);
		setContextPath(contextPath);
		
		setProps(Util.loadProperties());
		
		context = JAXBContext.newInstance(contextPath);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T find(String resourceId) {
		
		DatabaseClient client = initializeDatabaseClient();
		
		T entity = null;
		XMLDocumentManager xmlManager = client.newXMLDocumentManager();
		JAXBHandle readHandle = new JAXBHandle(context);
		
		try {
			xmlManager.read(schemaName + resourceId + ".xml", readHandle);
		} catch (ResourceNotFoundException e) {
			client.release();
			return entity;
		}
		
		client.release();
		entity = (T) readHandle.get();
		return entity;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void persist(T entity, String collId, boolean generateUri) {
		
		DatabaseClient client = initializeDatabaseClient();
		XMLDocumentManager xmlManager = client.newXMLDocumentManager();
		DocumentUriTemplate template = null;			
		
		template = xmlManager.newDocumentUriTemplate("xml");
		if (generateUri) {
			template.setDirectory(schemaName);
		} else {
			template.setDirectory("/nsarchive/");
		}
		
		
			
		JAXBHandle writeHandle = new JAXBHandle(context);
		writeHandle.set(entity);
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		metadata.getCollections().add(collId);
		
//		if (generateUri) {
			xmlManager.create(template, metadata, writeHandle);
//		} else {
//			xmlManager.write(schemaName, metadata, writeHandle);
//		}
		
		client.release();
	}
	
	/*
	 * Takes both, XQuery and XUpdate statements.
	 */
//	public InputStream executeQuery(String xQuery, boolean wrap) throws IOException {
//		InputStream result = null;
//		String wrapString = wrap ? "yes" : "no";
//		String wrappedQuery = "<query xmlns='http://basex.org/rest'>" + 
//				"<text><![CDATA[%s]]></text>" + 
//				"<parameter name='wrap' value='" + wrapString + "'/>" +
//			"</query>";
//		wrappedQuery = String.format(wrappedQuery, xQuery);
//
//		url = new URL(REST_URL + schemaName);
//		conn = (HttpURLConnection) url.openConnection();
//		conn.setDoOutput(true);
//		conn.setRequestMethod(RequestMethod.POST);
//		conn.setRequestProperty("Content-Type", "application/query+xml");
//		
//		/*
//		 * Generate HTTP POST body.
//		 */
//		System.out.println(wrappedQuery);
//		OutputStream out = conn.getOutputStream();
//		out.write(wrappedQuery.getBytes("UTF-8"));
//		out.close();
//
//		int responseCode = conn.getResponseCode();
//		String message = conn.getResponseMessage();
//
//		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
//		
//		if (responseCode == HttpURLConnection.HTTP_OK)
//			result = conn.getInputStream();
//		
//		return result;
//	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> executeQuery(StructuredQueryDefinition query) {
		DatabaseClient client = initializeDatabaseClient();
		
//		StructuredQueryBuilder qb = new StructuredQueryBuilder();
//		StructuredQueryDefinition query = qb.and(qb.wordConstraint("buyerAddress", "Nicija"),
//		qb.directory(1, "/invoice/"));
		query.setDirectory(schemaName);
		List<T> results = new ArrayList<T>();
		JAXBHandle readHandle = new JAXBHandle(context);
	    XMLDocumentManager xmlManager = client.newXMLDocumentManager();
		DocumentPage documents = xmlManager.search(query, 1);
		while (documents.hasNext()) {
		    DocumentRecord document = documents.next();
		    document.getContent(readHandle);
		    System.out.println(document.getUri());
		    if (readHandle.get() instanceof Akt) {
		    	Akt a = (Akt) readHandle.get();
		    	a.setFilename(document.getUri());
		    	results.add((T) a);
		    } else if (readHandle.get() instanceof Amandman) {
		    	Amandman a = (Amandman) readHandle.get();
		    	a.setFilename(document.getUri());
		    	results.add((T) a);
		    } else 
		    	results.add((T) readHandle.get());
		}
		client.release();
		return results;
	}
	
	public String executeXQuery(String query) {
		DatabaseClient client = initializeDatabaseClient();
		
		ServerEvaluationCall invoker = client.newServerEval();
		invoker.xquery(query);
		EvalResultIterator response = invoker.eval();
		String retVal = "";
		if (response.hasNext()) {
			for (EvalResult result : response) {
				retVal += result.getString() + "\n";
			}
		} else { 		
			System.out.println("Your query returned an empty sequence.");
		}
		client.release();
		return retVal;
	}
	
	/**
	 * Implements some sort of identity strategy, since it isn't natively supported by XMLDB.
	 * @return the next id value.
	 * @throws IOException
	 */
	public Long getIdentity() throws IOException {
		DatabaseClient client = initializeDatabaseClient();
		QueryManager queryMgr = client.newQueryManager();
		
//		max(//@id)
//		Ovaj query radi nesto
//		StringQueryDefinition stringQry = queryMgr.newStringDefinition();
//		stringQry.setCriteria("max(//@id)");
//		 
//		SearchHandle searchHandle = queryMgr.search(stringQry, new SearchHandle());
//		for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {
//		        System.out.println("document: "+docSum.getUri());
//		        for (MatchLocation docLoc: docSum.getMatchLocations()) {
//		                System.out.println("    location: "+docLoc.getPath());
//		                System.out.println("    matched:  "+docLoc.getAllSnippetText());
//		        }
//		}
//		
		
		
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = qb.wordConstraint("buyerAddress", "Nicija");
		query.setDirectory(schemaName);
//	    SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
//	    StringHandle s = queryMgr.search(query, new StringHandle());
//	    System.out.println("nesto"+s.get().length());
//	    for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {
//	        System.out.println("document: "+docSum.getUri());
//	        for (MatchLocation docLoc: docSum.getMatchLocations()) {
//	                System.out.println("    location: "+docLoc.getPath());
//	                System.out.println("    matched:  "+docLoc.getAllSnippetText());
//	        }
//		}
	    
	    
		JAXBHandle readHandle = new JAXBHandle(context);
	    XMLDocumentManager xmlManager = client.newXMLDocumentManager();
		DocumentPage documents = xmlManager.search(query, 1);
		while (documents.hasNext()) {
			
		    DocumentRecord document = documents.next();
		    System.out.println(document.getUri());
		    document.getContent(readHandle);
//		    Invoice a = (Invoice)readHandle.get();
//		    System.out.println(a.getId());
		    // do something with the contents
		}
	    
	    
//		InputStream input = executeQuery(xQuery, false);
//		BufferedReader br = new BufferedReader(new InputStreamReader(input));
//		
//		String line = br.readLine();
//		if (line != null)
//			return Long.valueOf(line) + 1L;
		return 1L;
	}
	
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public JAXBContext getContext() {
		return context;
	}

	public void setContext(JAXBContext context) {
		this.context = context;
	}

	public ConnectionProperties getProps() {
		return props;
	}

	public void setProps(ConnectionProperties props) {
		this.props = props;
	}
	
	private DatabaseClient initializeDatabaseClient() {
		DatabaseClient client;
		if (props.database.equals("")) {
			client = DatabaseClientFactory.newClient(props.host, props.port, props.user, props.password, props.authType);
		} else {
			client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, props.authType);
		}
		return client;
	}

}
