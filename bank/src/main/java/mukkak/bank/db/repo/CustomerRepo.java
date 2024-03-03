package mukkak.bank.db.repo;

import mukkak.bank.db.entity.CustomerEntity;
import mukkak.bank.db.model.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, UUID> {
    Set<CustomerStatus> DELETED = Set.of(CustomerStatus.DELETED);

    List<CustomerEntity> findAllByStatusNotIn(Set<CustomerStatus> status);
    Optional<CustomerEntity> findByIdAndStatusNotIn(UUID id, Set<CustomerStatus> status);
    Optional<CustomerEntity> findByIdAndStatusIn(UUID id, Set<CustomerStatus> status);

    default Optional<CustomerEntity> fetchCustomer(UUID id) {
        return findByIdAndStatusNotIn(id, DELETED);
    }

    default List<CustomerEntity> fetchCustomers() {
        return findAllByStatusNotIn(DELETED);
    }
}
