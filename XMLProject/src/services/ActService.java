package services;

import java.io.StringReader;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.act.Akt;
import sessions.act.ActDaoLocal;

@Path("/act")
public class ActService {
	
	private final Logger log = LoggerFactory.getLogger(ActService.class);
	
	@EJB
	private ActDaoLocal actDao;
	
	@PUT
	@Path("/upload/{username}")
	@Consumes(MediaType.TEXT_XML)
    public void createasd(@PathParam("username") String userName, String xml) {
    	System.out.println("pozvan rest");
    	System.out.println(userName);
    	
		Unmarshaller jaxbUnmarshaller;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Akt.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			Akt customer = (Akt) jaxbUnmarshaller.unmarshal(reader);
			actDao.persist(customer);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
