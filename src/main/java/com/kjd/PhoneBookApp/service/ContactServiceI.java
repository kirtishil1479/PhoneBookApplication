package com.kjd.PhoneBookApp.service;

import java.util.List;


import com.kjd.PhoneBookApp.model.Contact;



public interface ContactServiceI {
	
	public boolean savecontact(Contact contact);
	public List<Contact> allcontact();
	public List<Contact> getContactByName(String contactName);
    public Contact getById(int contactId);
    public boolean updateContact(Contact contact);
	
	
   

}
