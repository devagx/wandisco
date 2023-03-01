package com.wandisco.oneui.refactor;

import com.wandisco.oneui.refactor.exceptions.CreateCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.DeleteCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.LoadCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.UpdateCustomerDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalCustomerDatabaseDataTests {
    @Nested
    @DisplayName("LocalCustomerDatabaseData and DataFactory level tests where no custom exceptions are thrown")
    class NoCustomExceptionsThrown {
        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for loading a customer and no exceptions are thrown")
        void loadCustomerData_withRandomId_loadCustomerDataIsCalledAndReturnsACustomer() {
            LocalCustomerDatabaseData localCustomerDatabaseData = new LocalCustomerDatabaseData(DataFactory.getDatabase());

            assertDoesNotThrow(() -> localCustomerDatabaseData.loadCustomerData("1"));
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for deleting a customer and no exceptions are thrown")
        void deleteCustomerData_withRandomName_deleteCustomerDataIsCalled() {
            LocalCustomerDatabaseData localCustomerDatabaseData = new LocalCustomerDatabaseData(DataFactory.getDatabase());

            assertDoesNotThrow(() -> localCustomerDatabaseData.deleteCustomerData("name"));
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for updating a customer and no exceptions are thrown")
        void updateCustomerData_withRandomIdAndCustomer_updateCustomerDataIsCalled() {
            LocalCustomerDatabaseData localCustomerDatabaseData = new LocalCustomerDatabaseData(DataFactory.getDatabase());

            LocalCustomer customer = Mockito.mock(LocalCustomer.class);
            assertDoesNotThrow(() -> localCustomerDatabaseData.updateCustomerData(customer, "1"));
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for creating a customer and no exceptions are thrown")
        void createCustomerData_withRandomCustomer_createCustomerDataIsCalled() {
            LocalCustomerDatabaseData localCustomerDatabaseData = new LocalCustomerDatabaseData(DataFactory.getDatabase());
            LocalCustomer customer = Mockito.mock(LocalCustomer.class);

            assertDoesNotThrow(() -> localCustomerDatabaseData.createCustomerData(customer));
        }
    }

    @Nested
    @DisplayName("LocalCustomerDatabaseData and DataFactory level tests where custom exceptions are thrown")
    class CustomExceptionsThrown {
        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for custom LoadCustomerDataException handling")
        void loadCustomerData_exceptionCreated_createCustomerDataExceptionThrown() throws LoadCustomerDataException {
            LocalCustomerDatabaseData localCustomerDatabaseData = Mockito.mock(LocalCustomerDatabaseData.class);

            when(localCustomerDatabaseData.loadCustomerData(anyString())).thenThrow(new LoadCustomerDataException("Error", new Throwable()));

            Throwable exception = assertThrows(LoadCustomerDataException.class, () -> {
                localCustomerDatabaseData.loadCustomerData(anyString());
            });

            assertEquals("Error", exception.getMessage());
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for custom DeleteCustomerDataException handling")
        void deleteCustomerData_exceptionCreated_deleteCustomerDataExceptionThrown() throws DeleteCustomerDataException {
            LocalCustomerDatabaseData localCustomerDatabaseData = Mockito.mock(LocalCustomerDatabaseData.class);

            doThrow(new DeleteCustomerDataException("Error", new Throwable())).when(localCustomerDatabaseData).deleteCustomerData(anyString());

            Throwable exception = assertThrows(DeleteCustomerDataException.class, () -> {
                localCustomerDatabaseData.deleteCustomerData(anyString());
            });

            assertEquals("Error", exception.getMessage());
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for custom UpdateCustomerDataException handling")
        void updateCustomerData_exceptionCreated_updateCustomerDataExceptionThrown() throws UpdateCustomerDataException {
            LocalCustomerDatabaseData localCustomerDatabaseData = Mockito.mock(LocalCustomerDatabaseData.class);

            doThrow(new UpdateCustomerDataException("Error", new Throwable())).when(localCustomerDatabaseData).updateCustomerData(any(), anyString());

            Throwable exception = assertThrows(UpdateCustomerDataException.class, () -> {
                localCustomerDatabaseData.updateCustomerData(any(), anyString());
            });

            assertEquals("Error", exception.getMessage());
        }

        @Test
        @DisplayName("Test to ensure the LocalCustomerDatabaseData and DataFactory levels are tested for custom CreateCustomerDataException handling")
        void createCustomerData_exceptionCreated_createCustomerDataExceptionThrown() throws CreateCustomerDataException {
            LocalCustomerDatabaseData localCustomerDatabaseData = Mockito.mock(LocalCustomerDatabaseData.class);

            doThrow(new CreateCustomerDataException("Error", new Throwable())).when(localCustomerDatabaseData).createCustomerData(any());

            Throwable exception = assertThrows(CreateCustomerDataException.class, () -> {
                localCustomerDatabaseData.createCustomerData(any());
            });

            assertEquals("Error", exception.getMessage());
        }
    }
}
