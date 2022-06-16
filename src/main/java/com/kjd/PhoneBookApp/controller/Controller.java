package com.kjd.PhoneBookApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		if(contact!=null) {
			return new ResponseEntity<List<Contact>>(contact,HttpStatus.OK);
		}else {
			String msg="List is not available";
			return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
	
	
	
	
}