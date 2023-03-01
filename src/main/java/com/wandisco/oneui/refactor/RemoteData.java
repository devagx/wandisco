/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;

/**
 * Remote data record to store immutable object state.
 * A remote object is an incoming customer that needs to be synchronised to the database.
 *
 * @param id
 * @param name
 * @param companyName
 * @param firstName
 * @param last
 * @param title
 * @param daysSinceLastActive
 * @param dateOfBirth
 * @param email
 * @param description
 * @param isCompanyAccount
 * @param notes
 */
public record RemoteData(
        String id,
        String name,
        String companyName,
        String firstName,
        String last,
        String title,
        int daysSinceLastActive,
        String dateOfBirth,
        String email,
        String description,
        boolean isCompanyAccount, String notes
) {
}
