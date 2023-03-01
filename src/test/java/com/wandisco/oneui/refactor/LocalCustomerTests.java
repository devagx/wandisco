package com.wandisco.oneui.refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalCustomerTests {
    @Nested
    @DisplayName("toString() Tests")
    class toString {
        @Test
        @DisplayName("Test to ensure LocalCustomer toString method returns correct formatted string for a personal account")
        void toString_isPersonalAccount_returnsCompanyFormattedString() {
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

            String expected = "Personal account user [name=johnsmith, companyname=null, firstname=john, last=smith, title=mr, statusEnum=ACTIVE, date_of_birth=1/2/76, email=a@b.com, description=, companyOrPersonal=false, notes=]";

            assertEquals(expected, localCustomer.toString());
        }

        @Test
        @DisplayName("Test to ensure LocalCustomer toString method returns correct formatted string for a company account")
        void toString_isCompanyAccount_returnsCompanyFormattedString() {
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
                    .setIsCompanyAccount(true)
                    .setNotes("")
                    .build();

            String expected = "Company account user [name=johnsmith, companyname=null, firstname=john, last=smith, title=mr, statusEnum=ACTIVE, date_of_birth=1/2/76, email=a@b.com, description=, companyOrPersonal=true, notes=]";

            assertEquals(expected, localCustomer.toString());
        }
    }
}
