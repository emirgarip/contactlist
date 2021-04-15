package com.kn.contactlist.service;

import com.kn.contactlist.model.Contact;
import com.kn.contactlist.repository.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactListService implements IContactListService{

    private static final String FILE_NAME = "people.csv";

    @Autowired
    private ContactListRepository contactListRepository;

    private BufferedReader readExcel() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            if (inputStream == null) {
                throw new FileNotFoundException("file not found! " + FILE_NAME);
            }
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new BufferedReader(streamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> importExcelWithoutDB() {
        List<Contact> contactList = new ArrayList<>();
        if(readExcel() != null) {
            contactList = readExcel().lines().skip(1).map(line -> {
                String name = line.split(",")[0].trim();
                String url = line.split(",")[1].trim();
                return new Contact(name, url);}).collect(Collectors.toList());
        }
        return contactList;
    }

    public void importExcelWithDB() {
        if(readExcel() != null) {
            readExcel().lines().skip(1).forEach(line -> {
                String name = line.split(",")[0].trim();
                String url = line.split(",")[1].trim();
                contactListRepository.save(new Contact(name, url));});
        }
    }

    public List<Contact> findAllContacts() {
        return contactListRepository.findAll();
    }
}
