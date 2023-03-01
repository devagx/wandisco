package com.wandisco.oneui.refactor;

/**
 * Validates customer data before synchronising to the DB
 */
public class ValidateCustomerData {
    private ValidateCustomerData() {
    }

    ;

    /**
     * Returns validated customer data. Returned object will either be an object for creating, updating or deleting
     *
     * @param remoteData
     * @param localCustomerFromDb
     * @return
     */
    public static LocalCustomer getCustomer(RemoteData remoteData, LocalCustomer localCustomerFromDb) {
        LocalCustomer validatedCustomer = null;

        if (remoteData.name().equals("")) {
            return null;
        } else if (remoteData.daysSinceLastActive() < 5) {
            if (localCustomerFromDb != null) {
                validatedCustomer = new LocalCustomer
                        .LocalCustomerBuilder()
                        .newInstance()
                        .setName(remoteData.name())
                        .setCompanyName(remoteData.companyName())
                        .setFirstName(remoteData.firstName())
                        .setLast(remoteData.last())
                        .setTitle(remoteData.title())
                        .setStatusEnum(Account.Status.ACTIVE)
                        .setDateOfBirth(remoteData.dateOfBirth())
                        .setEmail(remoteData.email())
                        .setDescription(remoteData.description())
                        .setIsCompanyAccount(remoteData.isCompanyAccount())
                        .setNotes(remoteData.notes())
                        .build();
            }
            if (localCustomerFromDb == null) {
                validatedCustomer = new LocalCustomer
                        .LocalCustomerBuilder()
                        .newInstance()
                        .setExternalId(remoteData.id())
                        .setName(remoteData.name())
                        .setCompanyName(remoteData.companyName())
                        .setFirstName(remoteData.firstName())
                        .setLast(remoteData.last())
                        .setTitle(remoteData.title())
                        .setStatusEnum(Account.Status.ACTIVE)
                        .setDateOfBirth(remoteData.dateOfBirth())
                        .setEmail(remoteData.email())
                        .setDescription(remoteData.description())
                        .setIsCompanyAccount(remoteData.isCompanyAccount())
                        .setNotes(remoteData.notes())
                        .build();
            }
        } else if (remoteData.daysSinceLastActive() > 5 && !remoteData.isCompanyAccount() && localCustomerFromDb != null) {
            return new LocalCustomer
                    .LocalCustomerBuilder()
                    .newInstance()
                    .setExternalId("FOR_DELETION")
                    .build();
        }
        return validatedCustomer;
    }
}
