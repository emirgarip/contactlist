package com.kn.contactlist.controller;

import com.kn.contactlist.model.Contact;
import com.kn.contactlist.service.ContactListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImportExcelComponentTest {

    @InjectMocks
    ImportExcelComponent importExcelComponent;

    @Mock
    private ContactListService contactListService;

    @Mock
    private Environment environment;

    private List<Contact> contactList;

    @Before
    public void init() {
        contactList = new ArrayList<>();
        contactList.add(CONTACT1);
        contactList.add(CONTACT2);

        when(contactListService.importExcelWithoutDB()).thenReturn(contactList);
    }

    @Test
    public void importExcelToDB_shouldNotThrowException() {
        when(environment.getProperty(any())).thenReturn("true");
        Throwable thrown = catchThrowable(() -> {
            importExcelComponent.importExcel();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    public void importExcelToList_shouldNotThrowException() {
        when(environment.getProperty(any())).thenReturn("false");
        Throwable thrown = catchThrowable(() -> {
            importExcelComponent.importExcel();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    private final static Contact CONTACT1 = new Contact("name1", "url1");
    private final static Contact CONTACT2 = new Contact("name2", "url2");


}
