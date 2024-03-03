package mukkak.bank.db;

import lombok.extern.slf4j.Slf4j;
import mukkak.bank.db.repo.TransactionRepo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionDbOps {
    private final TransactionRepo transactionRepo;

    public TransactionDbOps(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }
}
