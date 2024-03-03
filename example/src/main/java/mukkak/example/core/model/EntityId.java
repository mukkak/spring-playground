package mukkak.example.core.model;


public record EntityId<T>(T id) {
    public static <A> EntityId<A> of(A id) {
        return new EntityId<>(id);
    }
}
