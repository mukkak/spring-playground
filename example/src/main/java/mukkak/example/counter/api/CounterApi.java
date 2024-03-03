package mukkak.example.counter.api;

import mukkak.example.core.exception.ApiException;
import mukkak.example.core.exception.ServiceException;
import mukkak.example.core.model.EntityId;
import mukkak.example.counter.api.model.Counter;
import mukkak.example.counter.api.model.CounterConfig;
import mukkak.example.counter.api.model.CreateCounterRequest;
import mukkak.example.counter.api.model.UpdateCounterConfigRequest;
import mukkak.example.counter.service.CounterConfigService;
import mukkak.example.counter.service.CounterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/counters")
public class CounterApi {
    private final CounterService counterService;
    private final CounterConfigService counterConfigService;

    public CounterApi(CounterService counterService, CounterConfigService counterConfigService) {
        this.counterService = counterService;
        this.counterConfigService = counterConfigService;
    }

    @PostMapping("")
    public EntityId<Long> postCounter(@RequestBody CreateCounterRequest request) {
        return EntityId.of(counterService.createCounter(request).getId());
    }

    @GetMapping("")
    public List<Counter> getCounters() {
        return counterService.readCounters()
            .stream()
            .map(Counter::of)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Counter getCounter(@PathVariable(name = "id") Long id) {
        return counterService.readCounter(id)
            .map(Counter::of)
            .orElseThrow(() -> new ApiException(
                ApiException.ENTITY_NOT_FOUND,
                String.format("Counter %d not found", id)
            ));
    }

    @PostMapping("/{id}/activate")
    public Counter activateCounter(@PathVariable(name = "id") Long id) {
        return counterService.activateCounter(id)
            .map(Counter::of)
            .orElseThrow(() -> new ApiException(
                ApiException.ENTITY_NOT_FOUND,
                String.format("Counter %d not found", id)
            ));
    }

    @PostMapping("/{id}/deactivate")
    public Counter deactivateCounter(@PathVariable(name = "id") Long id) {
        return counterService.deactivateCounter(id)
            .map(Counter::of)
            .orElseThrow(() -> new ApiException(
                ApiException.ENTITY_NOT_FOUND,
                String.format("Counter %d not found", id)
            ));
    }

    @PostMapping("/{id}/increment")
    public Counter incrementCounter(@PathVariable(name = "id") Long id) {
        return counterService.incrementCount(id)
            .map(Counter::of)
            .orElseThrow(() -> new ApiException(
                ApiException.ENTITY_NOT_FOUND,
                String.format("Counter %d not found", id)
            ));
    }

    @GetMapping("/config")
    public List<CounterConfig> getCounterConfigs() {
        return counterConfigService.readCounterConfigs()
            .stream()
            .map(CounterConfig::of)
            .toList();
    }

    @PostMapping("/config")
    public EntityId<Long> postCounterConfig(@RequestBody UpdateCounterConfigRequest request) throws ServiceException {
        return EntityId.of(counterConfigService.createOrUpdateCounterConfig(request).getId());
    }
}
