/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

public interface DatabaseSystem {

    LocalCustomer getCustomer(String id);

    void deleteData(String name);

    void update(LocalCustomer lc, String id);

    void create(LocalCustomer lc);

}
