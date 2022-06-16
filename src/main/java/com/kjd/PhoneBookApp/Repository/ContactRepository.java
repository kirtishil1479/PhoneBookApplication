package com.kjd.PhoneBookApp.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kjd.PhoneBookApp.model.Contact;

public interface  ContactRepository extends JpaRepository<Contact, Serializable> {

}
