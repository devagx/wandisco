/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

import com.wandisco.oneui.refactor.exceptions.CreateCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.DeleteCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.LoadCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.UpdateCustomerDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerDataSyncTest {
    @Mock
    private LocalCustomerDatabaseData localCustomerDbData;

    @InjectMocks
    private CustomerDataSync customerDataSync;

    @Nested
    @DisplayName("Generic account tests")
    class GenericAccountTests {
        @Test
        @DisplayName("Local customer data does not exist and sync has not ran for 4 days, so record is created")
        void syncCustomerData_daysSinceLastActiveIs4_createMethodIsCalled() throws LoadCustomerDataException, CreateCustomerDataException, UpdateCustomerDataException, DeleteCustomerDataException {
            RemoteData remoteData = new RemoteData(
                    "1",
                    "johnsmith",
                    null,
                    "john",
                    "smith",
                    "mr",
                    4,
                    "1/2/76",
                    "a@b.com",
                    "",
                    false,
                    "");

            when(localCustomerDbData.loadCustomerData(remoteData.id())).thenReturn(null);

            customerDataSync.syncCustomerData(remoteData);

            verify(localCustomerDbData, times(1)).createCustomerData(any(LocalCustomer.class));
        }

        @Test
        @DisplayName("Local customer data exists and sync has not ran for 4 days, so record is updated")
        void syncCustomerData_daysSinceLastActiveIs4_updateMethodIsCalled() throws LoadCustomerDataException, UpdateCustomerDataException, DeleteCustomerDataException, CreateCustomerDataException {
            RemoteData remoteData = new RemoteData(
                    "1",
                    "johnsmith",
                    null,
                    "john",
                    "smith",
                    "mr",
                    4,
                    "1/2/76",
                    "a@b.com",
                    "",
                    false,
                    "");

            LocalCustomer localCustomer = new LocalCustomer
                    .LocalCustomerBuilder()
                    .newInstance()
                    .setExternalId("1")
                    .setName("johnsmith")
                    .setCompanyName(null)
                    .setFirstName("john")
                    .setLast("smith")
                    .setTitle("mr")
                    .setStatusEnum(Account.Status.ACTIVE)
                    .setDateOfBirth("1/2/76")
                    .setEmail("a@b.com")
                    .setDescription("")
                    .setIsCompanyAccount(false)
                    .setNotes("")
                    .build();

            when(localCustomerDbData.loadCustomerData(remoteData.id())).thenReturn(localCustomer);

            customerDataSync.syncCustomerData(remoteData);

            verify(localCustomerDbData, times(1)).updateCustomerData(any(LocalCustomer.class), eq(remoteData.id()));
        }

        @Test
        @DisplayName("Local customer data does not exist and sync has not ran for 10 days, so exception is thrown due to the data not existing for deletion")
        void syncCustomerData_remoteDataNameIsBlank_crudMethodsNotCalled() throws LoadCustomerDataException, CreateCustomerDataException, UpdateCustomerDataException, DeleteCustomerDataException {
            RemoteData remoteData = new RemoteData(
                    "1",
                    "",
                    null,
                    "john",
                    "smith",
                    "mr",
                    4,
                    "1/2/76",
                    "a@b.com",
                    "",
                    false,
                    "");

            when(localCustomerDbData.loadCustomerData(remoteData.id())).thenReturn(null);

            customerDataSync.syncCustomerData(remoteData);

            verify(localCustomerDbData, times(0)).createCustomerData(any(LocalCustomer.class));
            verify(localCustomerDbData, times(0)).updateCustomerData(any(LocalCustomer.class), anyString());
            verify(localCustomerDbData, times(0)).deleteCustomerData(anyString());
        }
    }

    @Nested
    @DisplayName("Personal account tests")
    class PersonalAccounts {
        @Test
        @DisplayName("Local customer data exists and sync has not ran for 10 days, so record is deleted")
        void syncCustomerData_daysSinceLastActiveIs10_deleteMethodIsCalled() throws LoadCustomerDataException, DeleteCustomerDataException, UpdateCustomerDataException, CreateCustomerDataException {
            RemoteData remoteData = new RemoteData(
                    "1",
                    "johnsmith",
                    null,
                    "john",
                    "smith",
                    "mr",
                    10,
                    "1/2/76",
                    "a@b.com",
                    "",
                    false,
                    "");

            LocalCustomer localCustomer = new LocalCustomer
                    .LocalCustomerBuilder()
                    .newInstance()
                    .setExternalId("1")
                    .setName("johnsmith")
                    .setCompanyName(null)
                    .setFirstName("john")
                    .setLast("smith")
                    .setTitle("mr")
                    .setStatusEnum(Account.Status.ACTIVE)
                    .setDateOfBirth("1/2/76")
                    .setEmail("a@b.com")
                    .setDescription("")
                    .setIsCompanyAccount(false)
                    .setNotes("")
                    .build();

            when(localCustomerDbData.loadCustomerData(remoteData.id())).thenReturn(localCustomer);

            customerDataSync.syncCustomerData(remoteData);

            verify(localCustomerDbData, times(1)).deleteCustomerData("johnsmith");
        }
    }

    @Nested
    @DisplayName("Business account tests")
    class BusinessAccounts {
        @Test
        @DisplayName("Local customer data exists")
        void syncCustomerData_daysSinceLastActiveIs10_deleteMethodIsCalled() throws LoadCustomerDataException, DeleteCustomerDataException, UpdateCustomerDataException, CreateCustomerDataException {
            RemoteData remoteData = new RemoteData(
                    "1",
                    "johnsmith",
                    "test company",
                    "john",
                    "smith",
                    "mr",
                    10,
                    "1/2/76",
                    "a@b.com",
                    "",
                    true,
                    "");

            LocalCustomer localCustomer = new LocalCustomer
                    .LocalCustomerBuilder()
                    .newInstance()
                    .setExternalId("1")
                    .setName("johnsmith")
                    .setCompanyName("test company")
                    .setFirstName("john")
                    .setLast("smith")
                    .setTitle("mr")
                    .setStatusEnum(Account.Status.ACTIVE)
                    .setDateOfBirth("1/2/76")
                    .setEmail("a@b.com")
                    .setDescription("")
                    .setIsCompanyAccount(true)
                    .setNotes("")
                    .build();

            when(localCustomerDbData.loadCustomerData(remoteData.id())).thenReturn(localCustomer);

            customerDataSync.syncCustomerData(remoteData);

            verify(localCustomerDbData, times(0)).deleteCustomerData("johnsmith");
        }
    }
}
