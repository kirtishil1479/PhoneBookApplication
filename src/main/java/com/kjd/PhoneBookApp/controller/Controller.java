package com.kjd.PhoneBookApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kjd.PhoneBookApp.exception.NoContactFoundException;
import com.kjd.PhoneBookApp.model.Contact;
import com.kjd.PhoneBookApp.service.ContactServiceI;


@RestController
public class Controller {
	
	@Autowired
	private ContactServiceI contactServiceI;
	
	@PostMapping(value="/saveContact", consumes = "APPLICATION/JSON")
	public ResponseEntity<String> saveContact( @RequestBody Contact contact)
	{
		boolean save = contactServiceI.savecontact(contact);
		
		if(save==true) {
			String msg="contact save successfully";
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}else {
			String msg="contact not save successfully";
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value="/getAllContact",produces = "application/json")
	public ResponseEntity<List<Contact>> getAllContact(){
		
		List<Contact> contact = contactServiceI.allcontact();
		if(!contact.isEmpty()) {
			return new ResponseEntity<List<Contact>>(contact,HttpStatus.OK);
		}else {
			throw new NoContactFoundException("Contact list Not found"+contact);
		}
	}
	
	@GetMapping(value="/getContactByName/{contactName}",produces = "application/json")
	public ResponseEntity<List<Contact>>getContactByName(@PathVariable  String contactName){
		
		 List<Contact> name = contactServiceI.getContactByName(contactName);
		 if(!name.isEmpty()) {
			 return new ResponseEntity<List<Contact>>(name,HttpStatus.OK);
		 }else {
			 throw new NoContactFoundException("Given name not found in the list:"+contactName);
		 }
		}
	
	@GetMapping("/getContactById/{contactId}")
	public ResponseEntity<Contact>getContactById(@PathVariable int contactId){
		Contact byId = contactServiceI.getById(contactId);
		if(byId!=null) {
			return new ResponseEntity(byId,HttpStatus.OK);
		}else {
			
			throw new NoContactFoundException("Given contact Id not found:"+contactId);
		}
		
	}
	
	
	@PutMapping("/editContact")
	public ResponseEntity<String> updatecontact(Contact contact)
	{
		boolean updateContact = this.contactServiceI.updateContact(contact);
		if(updateContact==true)
		{
			return new ResponseEntity<String>("Contact updated successfully",HttpStatus.OK);
		}else {
			throw new NoContactFoundException("Given contact is not Updated");
		}
	}
	
	
}
	
