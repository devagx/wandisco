/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

import com.wandisco.oneui.refactor.exceptions.CreateCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.DeleteCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.LoadCustomerDataException;
import com.wandisco.oneui.refactor.exceptions.UpdateCustomerDataException;

/**
 * This class is responsible for interactions with the data layer
 */
public class LocalCustomerDatabaseData {
    private DatabaseSystem databaseSystem;

    /**
     * Uses dependency injection to decouple classes
     *
     * @param databaseSystem
     */
    public LocalCustomerDatabaseData(DatabaseSystem databaseSystem) {
        this.databaseSystem = databaseSystem;
    }

    /**
     * Loads customer data from the database
     *
     * @param id
     * @return
     * @throws LoadCustomerDataException
     */
    public LocalCustomer loadCustomerData(String id) throws LoadCustomerDataException {
        try {
            return databaseSystem.getCustomer(id);
        } catch (Exception ex) {
            throw new LoadCustomerDataException("Error loading customer data from the database. ID = " + id, ex);
        }
    }

    /**
     * Deletes customer data from the database
     *
     * @param name
     * @throws DeleteCustomerDataException
     */
    public void deleteCustomerData(String name) throws DeleteCustomerDataException {
        try {
            databaseSystem.deleteData(name);
        } catch (Exception ex) {
            throw new DeleteCustomerDataException("Error deleting customer data from the database. Name = " + name, ex);
        }
    }

    /**
     * Updates customer data in the database
     *
     * @param lc
     * @param id
     * @throws UpdateCustomerDataException
     */
    public void updateCustomerData(LocalCustomer lc, String id) throws UpdateCustomerDataException {
        try {
            databaseSystem.update(lc, id);
        } catch (Exception ex) {
            throw new UpdateCustomerDataException("Error updating customer data in the database. ID = " + id, ex);
        }
    }

    /**
     * Creates customer data in the database
     *
     * @param lc
     * @throws CreateCustomerDataException
     */
    public void createCustomerData(LocalCustomer lc) throws CreateCustomerDataException {
        try {
            databaseSystem.create(lc);
        } catch (Exception ex) {
            throw new CreateCustomerDataException("Error creating customer data in the database.", ex);
        }
    }
}
