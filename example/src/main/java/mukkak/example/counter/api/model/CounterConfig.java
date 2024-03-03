package mukkak.example.counter.api.model;

import mukkak.example.counter.db.entity.CounterConfigEntity;

public record CounterConfig(Long id, Boolean isDefault, Long counterId, Integer incrementSize) {
    public static CounterConfig of(CounterConfigEntity e) {
        return new CounterConfig(e.getId(), e.getIsDefault(), e.getCounterId(), e.getIncrementSize());
    }
}
