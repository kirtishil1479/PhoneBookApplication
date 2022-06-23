package com.kjd.PhoneBookApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kjd.PhoneBookApp.Props.AppProps;
import com.kjd.PhoneBookApp.model.Contact;
import com.kjd.PhoneBookApp.service.ContactServiceI;


@RestController
public class Controller {
	
	@Autowired
	private ContactServiceI contactServiceI;
	
	@Autowired
	private AppProps appProps;
	
	
	
	@PostMapping(value="/saveContact", consumes = "APPLICATION/JSON")
	public ResponseEntity<String> saveContact( @RequestBody Contact contact)
	{
		Map<String, String> message = appProps.getMessage();
		boolean save = contactServiceI.savecontact(contact);
		if(save==true) {
			String msg=message.get("SaveSuccess");
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}else {
			String msg=message.get("Savefail");
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value="/getAllContact",produces = "application/json")
	public ResponseEntity<List<Contact>> getAllContact(){
		Map<String, String> message = appProps.getMessage();
		List<Contact> contact = contactServiceI.allcontact();
		if(!contact.isEmpty()) {
			return new ResponseEntity<List<Contact>>(contact,HttpStatus.OK);
		}else {
			 return new ResponseEntity(message.get("DataNotFound"),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getActiveContacts",produces = "application/json")
	public ResponseEntity<List<Contact>> getAllActiveContact()
	{
		Map<String, String> message = appProps.getMessage();
		List<Contact> contact = contactServiceI.AllActiveContact();
		if(!contact.isEmpty()) {
			return new ResponseEntity<List<Contact>>(contact,HttpStatus.OK);
		}else {
			 return new ResponseEntity(message.get("DataNotFound"),HttpStatus.BAD_REQUEST);
		}
	}	


	@GetMapping(value="/getContactByName/{contactName}",produces = "application/json")
	public ResponseEntity<List<Contact>>getContactByName(@PathVariable  String contactName)
	{
		Map<String, String> message = appProps.getMessage();
		List<Contact> name = contactServiceI.getContactByName(contactName);
		 if(!name.isEmpty()) {
			 return new ResponseEntity<List<Contact>>(name,HttpStatus.OK);
		 }else {
			 return new ResponseEntity(message.get("DataNotFound"),HttpStatus.BAD_REQUEST);
		 }
		}
	
	
	
	@GetMapping("/getContactById/{contactId}")
	public ResponseEntity<Contact>getContactById(@PathVariable int contactId)
	{
		Map<String, String> message = appProps.getMessage();
		Contact byId = contactServiceI.getById(contactId);
		if(byId!=null) {
			return new ResponseEntity<>(byId,HttpStatus.OK);
		}else {
			return new ResponseEntity(message.get("DataNotFound"),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/editContact")
	public ResponseEntity<String> updatecontact(Contact contact)
	{
		Map<String, String> message = appProps.getMessage();
		boolean updateContact = this.contactServiceI.updateContact(contact);
		if(updateContact==true)
		{
			return new ResponseEntity<String>(message.get("SuccessUpdate"),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(message.get("FailUpdate"),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/hardDeleteContactById/{contactId}")
	public ResponseEntity<String>hardDeleteContactById(@PathVariable int contactId)
	{
		Map<String, String> message = appProps.getMessage();
		boolean deleteId = this.contactServiceI.hardDeleteContactById(contactId);
		if(deleteId==true) {
			return new ResponseEntity<String>(message.get("SuccessDelete"),HttpStatus.OK);
		}
		return new ResponseEntity<String>(message.get("FailDelete"),HttpStatus.BAD_REQUEST);
		
	}
	
	
	@DeleteMapping("/softDeleteContactById/{contactId}")
	public ResponseEntity<String>softDeleteContactById(@PathVariable int contactId)
	{
		Map<String, String> message = appProps.getMessage();
		boolean deleteId = this.contactServiceI.softDeleteContactById(contactId);
		if(deleteId==true) {
			return new ResponseEntity<String>(message.get("SuccessDelete"),HttpStatus.OK);
		}
		return new ResponseEntity<String>(message.get("FailDelete"),HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/DeleteAllContacts")
	public ResponseEntity<String> AllContactDelete()
	{
		Map<String, String> message = appProps.getMessage();
		this.contactServiceI.deleteAllContact();
		return new ResponseEntity<String>(message.get("SuccessDelete"),HttpStatus.OK);
	}
	
	
	
	
	
	
}
	
