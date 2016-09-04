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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;

import entities.act.Akt;
import entities.users.Users;
import sessions.act.ActDaoLocal;

@Path("/act")
public class ActService {
	
	private final Logger log = LoggerFactory.getLogger(ActService.class);
	
	@EJB
	private ActDaoLocal actDao;
	
	@PUT
	@Path("/upload/{username}")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_PLAIN)
    public String uploadAct(@PathParam("username") String userName, String xml) {

		Unmarshaller jaxbUnmarshaller;
		try {
		    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("schemas/act.xsd"));
		    Schema schema = factory.newSchema(schemaFile);
		    Validator validator = schema.newValidator();
		    validator.validate(new StreamSource(new StringReader(xml)));
		    
		    JAXBContext jaxbContext = JAXBContext.newInstance(Akt.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			Akt act = (Akt) jaxbUnmarshaller.unmarshal(reader);
			act.setKorisnik(userName);

			actDao.persist(act, "/act/inprocedure/", true);
			
			return "Upload of an XML file succeed.";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "Upload of an XML file failed.";
		}
    }
	
	@GET
	@Path("/getInProcedureActs")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInProcedureActs() {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = qb.collection("/act/inprocedure/");
		List<Akt> list = actDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Akt a : list) {
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
	@Path("/getInProcedureActsU/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInProcedureActsU(@PathParam("username") String userName) {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = qb.collection("/act/inprocedure/");
		List<Akt> list = actDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Akt a : list) {
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
		Akt akt = actDao.findById(fname);
		actDao.persist(akt, "/act/" + fname + ".xml", false);
		return actDao.executeXQuery("xdmp:document-set-collections(\"/act/" + fname + ".xml\", (\"/act/accepted/\"))");
    }
	
	@GET
	@Path("/setRefused/{fname}")
    @Produces(MediaType.APPLICATION_JSON)
    public String setRefused(@PathParam("fname") String fname) {

		return actDao.executeXQuery("xdmp:document-delete(\"/act/" + fname + ".xml\")");
    }	
	
	@GET
	@Path("/search/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public String search(@PathParam("term") String term) {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = 
			    qb.and(qb.term(term), 
			    		qb.or(qb.collection("/act/accepted/"), qb.collection("/act/inprocedure/")));
		List<Akt> list = actDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Akt a : list) {
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
	@Path("/getAllActs")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllActs() {
		
		StructuredQueryBuilder qb = new StructuredQueryBuilder();
		StructuredQueryDefinition query = 
			    		qb.or(qb.collection("/act/accepted/"), qb.collection("/act/inprocedure/"));
		List<Akt> list = actDao.executeQuery(query);
		JSONArray res = new JSONArray();
		for (Akt a : list) {
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
