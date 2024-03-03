package mukkak.bank.db;

import lombok.extern.slf4j.Slf4j;
import mukkak.bank.db.entity.CustomerEntity;
import mukkak.bank.db.entity.WalletEntity;
import mukkak.bank.db.model.CurrencyCode;
import mukkak.bank.db.model.CustomerStatus;
import mukkak.bank.db.model.WalletType;
import mukkak.bank.db.repo.CustomerRepo;
import mukkak.bank.db.repo.WalletRepo;
import mukkak.bank.exception.DatabaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class WalletDbOps {
    private final CustomerRepo customerRepo;
    private final WalletRepo walletRepo;

    public WalletDbOps(CustomerRepo customerRepo, WalletRepo walletRepo) {
        this.customerRepo = customerRepo;
        this.walletRepo = walletRepo;
    }

    public List<WalletEntity> readCustomerWallets(UUID customerId) {
        return walletRepo.fetchCustomerWallets(customerId);
    }

    public Optional<WalletEntity> readWallet(UUID walletId) {
        return walletRepo.fetchWallet(walletId);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public WalletEntity createWallet(UUID customerId, WalletType type, String name, CurrencyCode currency) {
        Optional<CustomerEntity> co = customerRepo.fetchCustomer(customerId);
        if (co.isEmpty()) {
            throw new DatabaseException(String.format("Customer(%s): Not found", customerId));
        }

        CustomerEntity c = co.get();
        if (!c.getStatus().equals(CustomerStatus.ACTIVE)) {
            throw new DatabaseException(String.format("Customer(%s): Not active", customerId));
        }

        return walletRepo.save(new WalletEntity(customerId, type, name, currency));
    }
}
