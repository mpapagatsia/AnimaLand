package gr.mpapagatsia.animaland.trick;

public record TrickDto(String trick) {

    public static TrickDto emptyTrick() {
        return new TrickDto("I am lazy.. No tricks.");
    }
}
