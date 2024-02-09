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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto implements Serializable {

    @JsonProperty(value = "id")
    @NotNull
    private String uuid;
    @NotEmpty
    private String name;
    @NotEmpty
    private String species;

    private List<String> tricks;


    public static AnimalDto fromEntity(Animal animal) {
        return AnimalDto.builder().name(animal.getName())
                .species(animal.getSpecies())
                .uuid(animal.getUuid())
                .tricks(Optional.ofNullable(animal.getTricks())
                                .map(tricks1 -> tricks1.stream().map(Trick::getName).toList())
                        .orElse(null))
                .build();
    }

    public static Animal toEntity(AnimalDto dto) {
        return Animal.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .species(dto.getSpecies())
                .build();
    }


}
