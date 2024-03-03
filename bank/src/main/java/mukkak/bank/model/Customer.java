package mukkak.bank.model;

import mukkak.bank.db.entity.CustomerEntity;
import mukkak.bank.db.model.CountryCode;
import mukkak.bank.db.model.CustomerStatus;

import java.util.UUID;

public record Customer(
    UUID id,
    CountryCode country,
    String name,
    CustomerStatus status
) {
    public static Customer from(CustomerEntity e) {
        return new Customer(e.getId(), e.getCountry(), e.getName(), e.getStatus());
    }
}
