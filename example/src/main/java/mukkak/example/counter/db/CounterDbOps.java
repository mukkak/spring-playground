package mukkak.example.counter.db;

import mukkak.example.core.exception.DatabaseException;
import mukkak.example.core.exception.ServiceException;
import mukkak.example.counter.db.entity.CounterConfigEntity;
import mukkak.example.counter.db.entity.CounterEntity;
import mukkak.example.counter.db.model.EntityStatus;
import mukkak.example.counter.db.repo.CounterConfigRepo;
import mukkak.example.counter.db.repo.CounterRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CounterDbOps {
    private final CounterRepo counterRepo;
    private final CounterConfigRepo counterConfigRepo;

    public CounterDbOps(CounterRepo counterRepo, CounterConfigRepo counterConfigRepo) {
        this.counterRepo = counterRepo;
        this.counterConfigRepo = counterConfigRepo;
    }

    /* Counter Config */
    public List<CounterConfigEntity> readAllCounterConfigs() {
        return counterConfigRepo.fetchAllConfig();
    }

    public Optional<CounterConfigEntity> readDefaultCounterConfig() throws ServiceException {
        List<CounterConfigEntity> es = counterConfigRepo.fetchDefaultConfig();
        if (es.size() > 1) {
            throw new ServiceException("Multiple default counter configs found");
        } else {
            return es.stream().findFirst();
        }
    }

    public Optional<CounterConfigEntity> readCounterConfig(Long counterId) throws ServiceException {
        List<CounterConfigEntity> configs = counterConfigRepo.fetchAllConfig();
        List<CounterConfigEntity> defaultConfigs = configs.stream()
            .filter(CounterConfigEntity::getIsDefault)
            .toList();
        if (defaultConfigs.size() > 1) {
            throw new ServiceException("Multiple default counter configs found");
        }

        List<CounterConfigEntity> customConfigs = configs.stream()
            .filter(x -> !x.getIsDefault() && x.getCounterId().equals(counterId))
            .toList();
        if (customConfigs.size() > 1) {
            throw new ServiceException("Multiple custom counter configs found for counter " + counterId);
        }

        if (!customConfigs.isEmpty()) {
            return customConfigs.stream().findFirst();
        } else {
            return defaultConfigs.stream().findFirst();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CounterConfigEntity addOrUpdateDefaultCounterConfig(Integer incrementSIze) throws ServiceException {
        List<CounterConfigEntity> es = counterConfigRepo.fetchDefaultConfig();
        if (es.size() > 1) {
            throw new ServiceException("Multiple default counter configs found");
        } else if (es.size() == 1) {
            CounterConfigEntity e = es.getFirst();
            e.setIncrementSize(incrementSIze);
            return counterConfigRepo.save(e);
        } else {
            return counterConfigRepo.save(new CounterConfigEntity(true, 0L, incrementSIze));
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CounterConfigEntity addOrUpdateCustomCounterConfig(Long counterId, Integer incrementSIze) throws ServiceException {
        List<CounterConfigEntity> es = counterConfigRepo.fetchCustomConfig(counterId);
        if (es.size() > 1) {
            throw new ServiceException("Multiple custom counter configs found for counter " + counterId);
        } else if (es.size() == 1) {
            CounterConfigEntity e = es.getFirst();
            e.setIncrementSize(incrementSIze);
            return counterConfigRepo.save(e);
        } else {
            return counterConfigRepo.save(new CounterConfigEntity(false, counterId, incrementSIze));
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<CounterConfigEntity> updateCounterConfigStatus(Long id, EntityStatus status) {
        Optional<CounterConfigEntity> eo = counterConfigRepo.fetchById(id);
        if (eo.isEmpty()) {
            return Optional.empty();
        }

        CounterConfigEntity e = eo.get();
        e.setStatus(status);
        return Optional.of(counterConfigRepo.save(e));
    }

    /* Counter */
    public List<CounterEntity> readAllCounters() {
        return counterRepo.fetchAll();
    }

    public Optional<CounterEntity> readCounter(Long id) {
        return counterRepo.fetchById(id);
    }

    public Optional<CounterEntity> readCounter(String name) {
        return counterRepo.fetchByName(name);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CounterEntity createCounter(String name) {
        Optional<CounterEntity> eo = readCounter(name);
        if (eo.isPresent()) {
            throw new DatabaseException(String.format(
                "Counter with name '%s' already exists", name
            ));
        }

        return counterRepo.save(new CounterEntity(name));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<CounterEntity> updateCounterStatus(Long id, EntityStatus status) {
        Optional<CounterEntity> eo = counterRepo.fetchById(id);
        if (eo.isEmpty()) {
            return Optional.empty();
        }

        CounterEntity e = eo.get();
        e.setStatus(status);
        return Optional.of(counterRepo.save(e));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<CounterEntity> incrementCounterCount(Long id) {
        Optional<CounterEntity> eo = counterRepo.fetchById(id);
        if (eo.isEmpty()) {
            return Optional.empty();
        }
        CounterEntity e = eo.get();

        try {
            Integer step = readCounterConfig(id).map(CounterConfigEntity::getIncrementSize).orElse(1);
            e.setCount(e.getCount() + step);
            return Optional.of(counterRepo.save(e));
        } catch (ServiceException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }
}
