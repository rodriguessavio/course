package br.com.saviorodrigues.main.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDTO {
    @Length(min=10, max=60, message = "O campo [nome] deve possuir entre 10 e 30 caracteres")
    private String name;

    @Length(min=5, max=10, message = "O campo [categoria] deve possuir entre 5 e 10 caracteres")
    private String category;
}
