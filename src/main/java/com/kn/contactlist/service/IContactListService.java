package com.kn.contactlist.service;

import com.kn.contactlist.model.Contact;

import java.io.BufferedReader;
import java.util.List;

public interface IContactListService {

    List<Contact> importExcelWithoutDB();

    void importExcelWithDB();

    List<Contact> findAllContacts();
}
