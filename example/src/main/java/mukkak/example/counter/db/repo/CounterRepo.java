package mukkak.example.counter.db.repo;

import mukkak.example.counter.db.entity.CounterEntity;
import mukkak.example.counter.db.model.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CounterRepo extends JpaRepository<CounterEntity, Long> {
    List<CounterEntity> findAllByStatusNotIn(Set<EntityStatus> statuses);
    Optional<CounterEntity> findByIdAndStatusNotIn(Long id, Set<EntityStatus> statuses);
    Optional<CounterEntity> findByNameAndStatusNotIn(String name, Set<EntityStatus> statuses);

    default List<CounterEntity> fetchAll() {
        return findAllByStatusNotIn(Set.of(EntityStatus.DELETED));
    }

    default Optional<CounterEntity> fetchById(Long id) {
        return findByIdAndStatusNotIn(id, Set.of(EntityStatus.DELETED));
    }

    default Optional<CounterEntity> fetchByName(String name) {
        return findByNameAndStatusNotIn(name, Set.of(EntityStatus.DELETED));
    }
}
