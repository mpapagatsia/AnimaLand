package gr.mpapagatsia.animaland.animal.dto;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public record PagedDto(
        List<AnimalDto> animals,
        int pageIndex,
        long pageSize,
        long totalElements,
        long totalPages
) implements Serializable {

    public static PagedDto fromPage(Page<AnimalDto> page) {
        return new PagedDto(page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}
