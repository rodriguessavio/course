package br.com.saviorodrigues.main.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseDTO {
    @NotBlank(message = "O nome do curso é obrigatório")
    @Length(min=10, max=60, message = "O campo [nome] deve possuir entre 10 e 30 caracteres")
    private String name;

    @NotBlank(message = "A categoria do curso é obrigatório")
    @Length(min=5, max=10, message = "O campo [categoria] deve possuir entre 5 e 10 caracteres")
    private String category;

    @NotBlank(message = "O professor do curso é obrigatório")
    @Length(min=10, max=30, message = "O campo [Professor] deve possuir entre 10 e 30 caracteres")
    private String teacher;
}
