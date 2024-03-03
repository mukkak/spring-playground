package mukkak.bank.db;

import lombok.extern.slf4j.Slf4j;
import mukkak.bank.db.entity.CustomerEntity;
import mukkak.bank.db.model.CountryCode;
import mukkak.bank.db.model.CustomerStatus;
import mukkak.bank.db.repo.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CustomerDbOps {
    private final CustomerRepo customerRepo;

    public CustomerDbOps(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerEntity> readCustomers() {
        return customerRepo.fetchCustomers();
    }

    public Optional<CustomerEntity> readCustomer(UUID id) {
        return customerRepo.fetchCustomer(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerEntity createCustomer(CountryCode country, String name) {
        return customerRepo.save(new CustomerEntity(country, name));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<CustomerEntity> updateCustomerStatus(UUID id, CustomerStatus status) {
        Optional<CustomerEntity> eo = customerRepo.fetchCustomer(id);
        if (eo.isEmpty()) {
            return Optional.empty();
        }

        CustomerEntity e = eo.get();
        e.setStatus(status);
        return Optional.of(customerRepo.save(e));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<CustomerEntity> updateCustomer(UUID id, String name) {
        Optional<CustomerEntity> eo = customerRepo.fetchCustomer(id);
        if (eo.isEmpty()) {
            return Optional.empty();
        } else {
            CustomerEntity e = eo.get();
            e.setName(name);
            return Optional.of(customerRepo.save(e));
        }
    }
}
