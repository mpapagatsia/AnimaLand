package gr.mpapagatsia.animaland.exception;

import lombok.Data;

@Data
public sealed class ApiException extends RuntimeException permits AnimalNotFoundException, TrickNotFoundException {
    private String description;

    public ApiException (String description) {
        super(description);
        this.description = description;
    }
}
