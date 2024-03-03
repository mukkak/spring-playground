package mukkak.example.counter.service;

import lombok.extern.slf4j.Slf4j;
import mukkak.example.core.exception.ServiceException;
import mukkak.example.counter.api.model.UpdateCounterConfigRequest;
import mukkak.example.counter.db.CounterDbOps;
import mukkak.example.counter.db.entity.CounterConfigEntity;
import mukkak.example.counter.db.entity.CounterEntity;
import mukkak.example.counter.db.model.EntityStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CounterConfigService {
    private final CounterDbOps counterDbOps;

    public CounterConfigService(CounterDbOps counterDbOps) {
        this.counterDbOps = counterDbOps;
    }

    public List<CounterConfigEntity> readCounterConfigs() {
        return counterDbOps.readAllCounterConfigs();
    }

    public CounterConfigEntity createOrUpdateCounterConfig(UpdateCounterConfigRequest req) throws ServiceException {
        if (req.getIsDefault()) {
            return counterDbOps.addOrUpdateDefaultCounterConfig(req.getIncrementSize());
        } else {
            return counterDbOps.addOrUpdateCustomCounterConfig(req.getCounterId(), req.getIncrementSize());
        }
    }

    public Optional<CounterEntity> deleteCounterConfig(Long id) {
        return counterDbOps.updateCounterStatus(id, EntityStatus.DELETED);
    }
}
