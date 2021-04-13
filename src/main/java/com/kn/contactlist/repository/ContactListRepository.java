package com.kn.contactlist.repository;

import com.kn.contactlist.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactListRepository extends JpaRepository<Contact, Long> {

}
