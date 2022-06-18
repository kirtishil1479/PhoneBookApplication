package com.kjd.PhoneBookApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjd.PhoneBookApp.Repository.ContactRepository;
import com.kjd.PhoneBookApp.exception.NoContactFoundException;
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
			throw new NoContactFoundException("Contact not found"+list);
		}
	
	}

	@Override
	public List<Contact> getContactByName(String contactName) {
		List<Contact> name = contactRepository.findByContactName(contactName);
		if(name!=null) {
			return name;
		}
		throw new NoContactFoundException("Given name is not available in contact list:"+contactName);
	}

	@Override
	public Contact getById(int contactId) {
		 Optional<Contact> findById = contactRepository.findById(contactId);
		 if(findById.isPresent()) {
			 Contact contact = findById.get();
			 return contact;
		}
		throw new NoContactFoundException("Given id not found"+contactId);
	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact save = this.contactRepository.save(contact);
		if(save!=null) {
			return true;
		}
		return false;
	}







}
