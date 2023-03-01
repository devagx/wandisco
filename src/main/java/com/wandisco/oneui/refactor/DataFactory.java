/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

public class DataFactory {

    //ASSUME THIS WORKS CORRECTLY

    public static DatabaseSystem getDatabase() {
        return new DatabaseSystem() {

            @Override
            public void update(LocalCustomer lc, String id) {
            }

            @Override
            public LocalCustomer getCustomer(String id) {
                return null;
            }

            @Override
            public void deleteData(String name) {
            }

            @Override
            public void create(LocalCustomer lc) {
            }
        };
    }

}
