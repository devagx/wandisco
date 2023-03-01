/**
 * Copyright 2019 WANDisco
 */
package com.wandisco.oneui.refactor;


/**
 * Object to store local customer state. A Local customer is a customer retrieved from the DB.
 */
public class LocalCustomer {
    private String externalId;
    private final String name;
    private final String companyName;
    private final String firstName;
    private final String last;
    private final String title;
    private final Enum statusEnum;
    private final String dateOfBirth;
    private final String email;
    private final String description;
    private final boolean isCompanyAccount;
    private final String notes;


    public LocalCustomer(LocalCustomerBuilder builder) {
        this.externalId = builder.externalId;
        this.isCompanyAccount = builder.isCompanyAccount;
        this.companyName = builder.companyName;
        this.dateOfBirth = builder.dateOfBirth;
        this.description = builder.description;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.last = builder.last;
        this.name = builder.name;
        this.statusEnum = builder.statusEnum;
        this.title = builder.title;
        this.notes = builder.notes;
    }

    public String getexternalId() {
        return externalId;
    }

    @Override
    public String toString() {
        if (isCompanyAccount) {
            return String.format(
                    "Company account user [name=%s, companyname=%s, firstname=%s, last=%s, title=%s, statusEnum=%s, date_of_birth=%s, email=%s, description=%s, companyOrPersonal=%s, notes=%s]",
                    name, companyName, firstName, last, title, statusEnum, dateOfBirth, email, description, isCompanyAccount, notes);
        } else {
            return String.format(
                    "Personal account user [name=%s, companyname=%s, firstname=%s, last=%s, title=%s, statusEnum=%s, date_of_birth=%s, email=%s, description=%s, companyOrPersonal=%s, notes=%s]",
                    name, companyName, firstName, last, title, statusEnum, dateOfBirth, email, description, isCompanyAccount, notes);
        }
    }

    /**
     * Byilder is introduced as a local customer can have two states, one for DB inserts or one for DB updates.
     * The builder also reduces the complexity of the object creation.
     */
    public static class LocalCustomerBuilder {
        private String externalId;
        private String name;
        private String companyName;
        private String firstName;
        private String last;
        private String title;
        private Enum statusEnum;
        private String dateOfBirth;
        private String email;
        private String description;
        private boolean isCompanyAccount;
        private String notes;

        public LocalCustomerBuilder newInstance() {
            return new LocalCustomerBuilder();
        }

        public LocalCustomerBuilder setExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public LocalCustomerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LocalCustomerBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public LocalCustomerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LocalCustomerBuilder setLast(String last) {
            this.last = last;
            return this;
        }

        public LocalCustomerBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public LocalCustomerBuilder setStatusEnum(Enum statusEnum) {
            this.statusEnum = statusEnum;
            return this;
        }

        public LocalCustomerBuilder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public LocalCustomerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public LocalCustomerBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public LocalCustomerBuilder setIsCompanyAccount(boolean isCompanyAccount) {
            this.isCompanyAccount = isCompanyAccount;
            return this;
        }

        public LocalCustomerBuilder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public LocalCustomer build() {
            return new LocalCustomer(this);
        }
    }
}
