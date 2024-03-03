package mukkak.example.counter.api.model;

import mukkak.example.counter.db.entity.CounterEntity;
import mukkak.example.counter.db.model.EntityStatus;

public record Counter(Long id, String name, Long count, EntityStatus status) {
    public static Counter of(CounterEntity e) {
        return new Counter(e.getId(), e.getName(), e.getCount(), e.getStatus());
    }
}
