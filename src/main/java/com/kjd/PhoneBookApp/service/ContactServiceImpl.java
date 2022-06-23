package com.kjd.PhoneBookApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjd.PhoneBookApp.Props.AppProps;
import com.kjd.PhoneBookApp.Repository.ContactRepository;
import com.kjd.PhoneBookApp.exception.NoContactFoundException;
import com.kjd.PhoneBookApp.model.Contact;

@Service
public class ContactServiceImpl implements ContactServiceI{
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private AppProps appProps;
	


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
		
		Map<String, String> message = appProps.getMessage();
		
		if(list!=null) {
			return list;
		}
		else
		{
			throw new NoContactFoundException(message.get("DataNotFound")+list);
		}
	
	}

	@Override
	public List<Contact> getContactByName(String contactName) {
		
		Map<String, String> message = appProps.getMessage();
		List<Contact> name = contactRepository.findByContactName(contactName);
		if(name!=null) {
			return name;
		}
		throw new NoContactFoundException(message.get("DataNotFound")+contactName);
	}

	@Override
	public Contact getById(int contactId) {
		
		Map<String, String> message = appProps.getMessage();
		 Optional<Contact> findById = contactRepository.findById(contactId);
		 if(findById.isPresent()) {
			 Contact contact = findById.get();
			 return contact;
		}
		throw new NoContactFoundException("Data Not found SuccessFully"+contactId);
	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact save = this.contactRepository.save(contact);
		if(save!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hardDeleteContactById(Integer contactId) {
		
		Optional<Contact> findById = this.contactRepository.findById(contactId);
		if(findById.isPresent()) {
			this.contactRepository.deleteById(contactId);
			return true;
		}else {
			
			return false;
		}
	}

	@Override
	public List<Contact> AllActiveContact() {
		List<Contact> ActiveContact = this.contactRepository.findAll();
		List<Contact> collect = ActiveContact.stream().filter(contact -> contact.getActiveSw()=='Y').collect(Collectors.toList());
		return collect;
	}

	@Override
	public boolean softDeleteContactById(Integer contactId) {
		Optional<Contact> optional = this.contactRepository.findById(contactId);
		if(optional.isPresent()) {
			Contact contact = optional.get();
			contact.setActiveSw('N');
			this.contactRepository.save(contact);
			return true;
		}
		 return false;
		
	}

	@Override
	public void deleteAllContact() {
		this.contactRepository.deleteAll();
		
	}








}
