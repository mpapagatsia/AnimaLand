package gr.mpapagatsia.animaland.animal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.trick.Trick;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public record AnimalDto(@JsonProperty(value = "id")
                        @NotNull String uuid,
                        @NotEmpty String name,
                        @NotEmpty String species,
                        List<String> tricks) {

    public static AnimalDto fromEntity(Animal animal) {
        return new AnimalDto(animal.getUuid(),
                animal.getName(), animal.getSpecies(), Optional.ofNullable(animal.getTricks())
                .map(tricks1 -> tricks1.stream().map(Trick::getName).toList())
                .orElse(null));
    }

    public static Animal toEntity(AnimalDto dto) {
        return Animal.builder()
                .uuid(dto.uuid())
                .name(dto.name())
                .species(dto.species())
                .build();
    }
}
