package com.kjd.PhoneBookApp.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kjd.PhoneBookApp.model.Contact;

@Repository
public interface  ContactRepository extends JpaRepository<Contact, Serializable> {
	
	
	public List<Contact> findByContactName(String contactName);

}
