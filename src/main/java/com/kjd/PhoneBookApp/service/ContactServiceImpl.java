package com.kjd.PhoneBookApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjd.PhoneBookApp.Repository.ContactRepository;
import com.kjd.PhoneBookApp.model.Contact;

@Service
public class ContactServiceImpl implements ContactServiceI{
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean savecontact(Contact contact) {
		
		Contact save = contactRepository.save(contact);
		if(save!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Contact> allcontact() {
		List<Contact> list = contactRepository.findAll();
		
		if(list!=null) {
			return list;
		}
		else
		{
			return null;
		}
	
	}
	
	
	
	

}
