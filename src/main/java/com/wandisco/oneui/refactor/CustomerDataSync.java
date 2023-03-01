/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

import com.wandisco.oneui.refactor.exceptions.CreateCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.DeleteCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.LoadCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.UpdateCustomerDataException;

/**
 * This class is responsible for synchronising incoming customer data to the database
 */
public class CustomerDataSync {
    private LocalCustomerDatabaseData localCustomerDatabaseData;

    /**
     * Uses dependency injection to decouple classes
     *
     * @param localCustomerDatabaseData
     */
    public CustomerDataSync(LocalCustomerDatabaseData localCustomerDatabaseData) {
        this.localCustomerDatabaseData = localCustomerDatabaseData;
    }

    /**
     * This method handles the synchronisation process
     *
     * @param remoteCustomer
     * @throws LoadCustomerDataException
     * @throws CreateCustomerDataException
     * @throws DeleteCustomerDataException
     * @throws UpdateCustomerDataException
     */
    public void syncCustomerData(RemoteData remoteCustomer) throws LoadCustomerDataException, CreateCustomerDataException, DeleteCustomerDataException, UpdateCustomerDataException {
        LocalCustomer localCustomerFromDb = localCustomerDatabaseData.loadCustomerData(remoteCustomer.id());

        LocalCustomer localCustomer = ValidateCustomerData.getCustomer(remoteCustomer, localCustomerFromDb);

        if (localCustomer != null) {
            if (localCustomer.getexternalId() == null) {
                localCustomerDatabaseData.updateCustomerData(localCustomer, remoteCustomer.id());
            } else if (localCustomer.getexternalId().equals("FOR_DELETION")) {
                localCustomerDatabaseData.deleteCustomerData(remoteCustomer.name());
            } else if (localCustomer.getexternalId() != null) {
                localCustomerDatabaseData.createCustomerData(localCustomer);
            }
        }
    }
}
