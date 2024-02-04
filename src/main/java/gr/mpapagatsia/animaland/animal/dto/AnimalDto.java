package gr.mpapagatsia.animaland.animal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.mpapagatsia.animaland.animal.model.Animal;
import gr.mpapagatsia.animaland.animal.model.Trick;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
                .tricks(animal.getTricks().stream().map(Trick::getName).toList())
                .build();
    }

    public static Animal toEntity(AnimalDto dto) {
        return Animal.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .species(dto.getSpecies())
                .tricks(dto.getTricks().stream().map(t-> Trick.builder().name(t).build()).toList()).build();
    }

}
