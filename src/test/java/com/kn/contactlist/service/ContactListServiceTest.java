package com.kn.contactlist.service;

import com.kn.contactlist.repository.ContactListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class ContactListServiceTest {

    @InjectMocks
    ContactListService contactListService;

    @Mock
    private ContactListRepository contactListRepository;

    @Test
    public void importExcelWithoutDB_shouldNotThrowException() {
        Throwable thrown = catchThrowable(() -> {
            contactListService.importExcelWithoutDB();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    public void importExcelWithDB_shouldNotThrowException() {
        Throwable thrown = catchThrowable(() -> {
            contactListService.importExcelWithDB();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

}
