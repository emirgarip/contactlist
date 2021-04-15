package com.kn.contactlist.controller;

import com.kn.contactlist.model.Contact;
import com.kn.contactlist.service.ContactListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class ContactListController implements Serializable {

    private String name;

    private List<Contact> filteredContactList = new ArrayList<>();

    List<Contact> allContactList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getFilteredContactList() {
        return filteredContactList;
    }

    public void setFilteredContactList(List<Contact> filteredContactList) {
        this.filteredContactList = filteredContactList;
    }

    @Autowired
    private ContactListService contactListService;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void importExcel() {
        boolean useDbFlag = Boolean.parseBoolean(environment.getProperty("useDB.flag"));
        if(useDbFlag) {
            contactListService.importExcelWithDB();
            allContactList = contactListService.findAllContacts();
        } else {
            allContactList = contactListService.importExcelWithoutDB();
        }
    }

    public void search() {
        if(name != null && !name.trim().isEmpty() && allContactList != null) {
            filteredContactList = allContactList.stream()
                    .filter(contact -> contact.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        } else {
            filteredContactList = allContactList;
        }
    }

}
