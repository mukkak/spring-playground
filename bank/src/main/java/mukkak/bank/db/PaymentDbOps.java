package mukkak.bank.db;

import lombok.extern.slf4j.Slf4j;
import mukkak.bank.db.repo.PaymentRepo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentDbOps {
    private final PaymentRepo paymentRepo;

    public PaymentDbOps(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }
}
