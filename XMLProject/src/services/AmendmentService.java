package services;

import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;

import entities.act.Akt;
import entities.amendment.Amandman;
import sessions.amendment.AmendmentDaoLocal;

@Path("/amendment")
public class AmendmentService {
	
	private final Logger log = LoggerFactory.getLogger(AmendmentService.class);
	
	@EJB
	private AmendmentDaoLocal amendDao;
	
	@PUT
	@Path("/upload/{username}/{filename}")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_PLAIN)
    public String uploadAct(@PathParam("username") String userName, @PathParam("filename") String fileName, String xml) {

		Unmarshaller jaxbUnmarshaller;
		try {
//		    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//		    Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("schemas/amendment.xsd"));
//		    Schema schema = factory.newSchema(schemaFile);
//		    Validator validator = schema.newValidator();
//		    validator.validate(new StreamSource(new StringReader(xml)));
		    
		    JAXBContext jaxbContext = JAXBContext.newInstance(Amandman.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			Amandman ama = (Amandman) jaxbUnmarshaller.unmarshal(reader);
			ama.setKorisnik(userName);
			ama.setAktRef(fileName);

			amendDao.persist(ama, "/amendment/inprocedure/", true);
			
			return "Upload of an XML file succeed.";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "Upload of an XML file failed.";
		}
    }
	
	@GET
	@Path("/getInProcedureAmend")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInProcedureAmend() {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = qb.collection("/amendment/inprocedure/");
		List<Amandman> list = amendDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Amandman a : list) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("name", a.getNaslov());
				obj.put("fname", a.getFilename());
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
			res.put(obj);
		}
		
		return res.toString();
    }	
	
	@GET
	@Path("/getInProcedureAmendU/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInProcedureAmendU(@PathParam("username") String userName) {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = qb.collection("/amendment/inprocedure/");
		List<Amandman> list = amendDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Amandman a : list) {
			if (a.getKorisnik().equals(userName)) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("name", a.getNaslov());
					obj.put("fname", a.getFilename());
				} catch (JSONException e) {
					log.error(e.getMessage(), e);
				}
				res.put(obj);
			}
		}
		
		return res.toString();
    }	
	
	@GET
	@Path("/setAccepted/{fname}")
    @Produces(MediaType.APPLICATION_JSON)
    public String setAccepted(@PathParam("fname") String fname) {
		return amendDao.executeXQuery("xdmp:document-set-collections(\"/amendment/" + fname + ".xml\", (\"/amendment/accepted/\"))");
    }
	
	@GET
	@Path("/setRefused/{fname}")
    @Produces(MediaType.APPLICATION_JSON)
    public String setRefused(@PathParam("fname") String fname) {
		return amendDao.executeXQuery("xdmp:document-delete(\"/amendment/" + fname + ".xml\")");
    }	
	
	@GET
	@Path("/search/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public String search(@PathParam("term") String term) {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = 
			    qb.and(qb.term(term), 
			    		qb.or(qb.collection("/amendment/inprocedure/"), qb.collection("/amendment/accepted/")));
		List<Amandman> list = amendDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Amandman a : list) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("name", a.getNaslov());
				obj.put("fname", a.getFilename());
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
			res.put(obj);
			
		}
		
		return res.toString();
    }	
	
	@GET
	@Path("/getAllAmend")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAmend() {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = 
				qb.or(qb.collection("/amendment/inprocedure/"), qb.collection("/amendment/accepted/"));
		List<Amandman> list = amendDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Amandman a : list) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("name", a.getNaslov());
				obj.put("fname", a.getFilename());
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
			res.put(obj);
			
		}
		
		return res.toString();
    }	
}
