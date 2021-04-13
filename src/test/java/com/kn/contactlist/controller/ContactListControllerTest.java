package com.kn.contactlist.controller;

import com.kn.contactlist.model.Contact;
import com.kn.contactlist.repository.ContactListRepository;
import com.kn.contactlist.service.ContactListService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class ContactListControllerTest {

    @InjectMocks
    ContactListController contactListController;

    @Mock
    private ContactListService contactListService;

    @Mock
    private Environment environment;

    @Mock
    private ImportExcelComponent importExcelComponent;

    private List<Contact> contactList;

    @Before
    public void init() {
        contactList = new ArrayList<>();
        contactList.add(CONTACT1);
        contactList.add(CONTACT2);

        when(contactListService.findAllContacts()).thenReturn(contactList);
        when(importExcelComponent.getContactListList()).thenReturn(contactList);
    }

    @Test
    public void searchFromDB_shouldNotThrowException_WhenNameIsNotNull() {
        when(environment.getProperty(any())).thenReturn("true");
        contactListController.setName("test");
        Throwable thrown = catchThrowable(() -> {
            contactListController.search();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    public void searchFromDB_shouldNotThrowException_WhenNameIsNull() {
        when(environment.getProperty(any())).thenReturn("true");
        Throwable thrown = catchThrowable(() -> {
            contactListController.search();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    public void searchFromCSV_shouldNotThrowException_WhenNameIsNotNull() {
        when(environment.getProperty(any())).thenReturn("false");
        contactListController.setName("test");
        Throwable thrown = catchThrowable(() -> {
            contactListController.search();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    public void searchFromCSV_shouldNotThrowException_WhenNameIsNull() {
        when(environment.getProperty(any())).thenReturn("false");
        Throwable thrown = catchThrowable(() -> {
            contactListController.search();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    private final static Contact CONTACT1 = new Contact("name1", "url1");
    private final static Contact CONTACT2 = new Contact("name2", "url2");


}
