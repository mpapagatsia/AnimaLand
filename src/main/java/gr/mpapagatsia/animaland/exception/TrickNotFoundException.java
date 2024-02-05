package gr.mpapagatsia.animaland.exception;

public final class TrickNotFoundException extends ApiException {
    public TrickNotFoundException(String id) {
        super("Could not locate a trick for animal with id %s".formatted(id));
    }

    public TrickNotFoundException(String name, String message) {
        super("%s %s".formatted(name, message));
    }
}
