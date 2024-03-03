package mukkak.bank.db.repo;

import mukkak.bank.db.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByPaymentId(UUID paymentId);
    List<TransactionEntity> findAllByWalletId(UUID walletId);

    default List<TransactionEntity> fetchPaymentTransactions(UUID paymentId) {
        return findAllByPaymentId(paymentId);
    }

    default List<TransactionEntity> fetchWalletTransactions(UUID walletId) {
        return findAllByWalletId(walletId);
    }
}
