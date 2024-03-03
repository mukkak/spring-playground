package mukkak.example.counter.db.repo;

import mukkak.example.counter.db.entity.CounterConfigEntity;
import mukkak.example.counter.db.model.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CounterConfigRepo extends JpaRepository<CounterConfigEntity, Long> {
    Optional<CounterConfigEntity> findByIdAndStatusNotIn(Long id, Set<EntityStatus> statuses);
    List<CounterConfigEntity> findAllByStatusNotIn(Set<EntityStatus> statuses);
    List<CounterConfigEntity> findAllByIsDefaultAndStatusNotIn(Boolean isDefault, Set<EntityStatus> statuses);
    List<CounterConfigEntity> findAllByIsDefaultAndCounterIdAndStatusNotIn(Boolean isDefault, Long counterId, Set<EntityStatus> statuses);

    default Optional<CounterConfigEntity> fetchById(Long id) {
        return findByIdAndStatusNotIn(id, Set.of(EntityStatus.DELETED));
    }

    default List<CounterConfigEntity> fetchAllConfig() {
        return findAllByStatusNotIn(Set.of(EntityStatus.DELETED));
    }

    default List<CounterConfigEntity> fetchDefaultConfig() {
        return findAllByIsDefaultAndStatusNotIn(true, Set.of(EntityStatus.DELETED));
    }

    default List<CounterConfigEntity> fetchCustomConfig() {
        return findAllByIsDefaultAndStatusNotIn(false, Set.of(EntityStatus.DELETED));
    }

    default List<CounterConfigEntity> fetchCustomConfig(Long counterId) {
        return findAllByIsDefaultAndCounterIdAndStatusNotIn(false, counterId, Set.of(EntityStatus.DELETED));
    }
}
