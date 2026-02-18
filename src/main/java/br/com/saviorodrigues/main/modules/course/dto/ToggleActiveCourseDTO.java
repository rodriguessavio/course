package br.com.saviorodrigues.main.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToggleActiveCourseDTO {

    @NotBlank(message = "O active do curso é obrigatório")
    private boolean active;
}
