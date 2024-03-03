package mukkak.bank.db.repo;

import mukkak.bank.db.entity.WalletEntity;
import mukkak.bank.db.model.WalletStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface WalletRepo extends JpaRepository<WalletEntity, UUID> {
    Set<WalletStatus> DELETED = Set.of(WalletStatus.DELETED);

    Optional<WalletEntity> findByIdAndStatusNotIn(UUID walletId, Set<WalletStatus> status);
    List<WalletEntity> findAllByCustomerIdAndStatusNotIn(UUID customerId, Set<WalletStatus> status);

    default Optional<WalletEntity> fetchWallet(UUID walletId) {
        return findByIdAndStatusNotIn(walletId, DELETED);
    }

    default List<WalletEntity> fetchCustomerWallets(UUID customerId) {
        return findAllByCustomerIdAndStatusNotIn(customerId, DELETED);
    }
}
