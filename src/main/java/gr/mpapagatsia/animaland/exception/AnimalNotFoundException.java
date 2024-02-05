package gr.mpapagatsia.animaland.exception;

public final class AnimalNotFoundException extends ApiException {
    public AnimalNotFoundException(String id) {
        super("Animal not found with id %s".formatted(id));
    }
}
