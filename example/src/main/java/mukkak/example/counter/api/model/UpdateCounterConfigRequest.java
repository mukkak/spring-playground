package mukkak.example.counter.api.model;

import lombok.Data;

@Data
public class UpdateCounterConfigRequest {
    private Boolean isDefault;
    private Long counterId;
    private Integer incrementSize;
}
