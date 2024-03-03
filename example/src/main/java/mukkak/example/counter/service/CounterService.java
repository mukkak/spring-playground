package mukkak.example.counter.service;

import lombok.extern.slf4j.Slf4j;
import mukkak.example.core.exception.ApiException;
import mukkak.example.counter.api.model.CreateCounterRequest;
import mukkak.example.counter.db.CounterDbOps;
import mukkak.example.counter.db.entity.CounterEntity;
import mukkak.example.counter.db.model.EntityStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CounterService {
    private final CounterDbOps counterDbOps;

    public CounterService(CounterDbOps counterDbOps) {
        this.counterDbOps = counterDbOps;
    }

    public List<CounterEntity> readCounters() {
        return counterDbOps.readAllCounters();
    }

    public Optional<CounterEntity> readCounter(Long id) {
        return counterDbOps.readCounter(id);
    }

    public CounterEntity createCounter(CreateCounterRequest request) throws ApiException {
        if (StringUtils.isBlank(request.getName())) {
            throw new ApiException(ApiException.INPUT_VALIDATION_FAILED, "Invalid counter name");
        } else {
            return counterDbOps.createCounter(request.getName().strip());
        }
    }

    public Optional<CounterEntity> activateCounter(Long id) {
        return counterDbOps.updateCounterStatus(id, EntityStatus.ACTIVE);
    }

    public Optional<CounterEntity> deactivateCounter(Long id) {
        return counterDbOps.updateCounterStatus(id, EntityStatus.INACTIVE);
    }

    public Optional<CounterEntity> incrementCount(Long id) {
        return counterDbOps.incrementCounterCount(id);
    }

    public Optional<CounterEntity> deleteCounter(Long id) {
        return counterDbOps.updateCounterStatus(id, EntityStatus.DELETED);
    }
}
