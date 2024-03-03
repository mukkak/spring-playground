package mukkak.bank.db.repo;

import mukkak.bank.db.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, UUID> {
    List<PaymentEntity> findAllByWalletId(UUID walletId);

    default Optional<PaymentEntity> fetchPayment(UUID paymentId) {
        return findById(paymentId);
    }

    default List<PaymentEntity> fetchWalletPayments(UUID walletId) {
        return findAllByWalletId(walletId);
    }
}
