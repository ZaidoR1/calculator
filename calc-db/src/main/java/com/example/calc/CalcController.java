package com.example.calc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/history")
public class CalcController {

	@Autowired
	private CalcRepository repo;

	@RequestMapping(value = "", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEntries() {
		Iterable<CalcEntry> entries = repo.findAll();
		return ResponseEntity.ok(entries);
	}

	@RequestMapping(value = "", 
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody CalcEntry entry) {
		entry = repo.save(entry);
		
		SessionFactory sessFact = null;
		Session session = null;
		try
		{
			sessFact = HibernateUtil.getSessionFactory();
			session = sessFact.openSession();

			org.hibernate.Transaction tr = session.beginTransaction();
			session.save( entry );
			tr.commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		finally
		{
			if ( session != null && session.isOpen() )
				session.close();
		}
		
		return ResponseEntity.ok(entry);
	}
	
	@RequestMapping(value = "{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		CalcEntry item = repo.findOne(id);
		if ( item != null ) {
			repo.delete(item);
			return ResponseEntity.ok(item);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
