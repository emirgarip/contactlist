package com.kn.contactlist.controller;

import com.kn.contactlist.model.Contact;
import com.kn.contactlist.service.ContactListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ImportExcelComponent {

    private List<Contact> contactListList;

    public List<Contact> getContactListList() {
        return contactListList;
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
        } else {
            contactListList = contactListService.importExcelWithoutDB();
        }
    }

}
