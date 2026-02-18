package br.com.saviorodrigues.main.modules.course.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_course")
    private UUID idCourse;

    @NotBlank(message = "O nome do curso é obrigatório")
    @Length(min=10, max=60, message = "O campo [nome] deve possuir entre 10 e 30 caracteres")
    private String name;

    @NotBlank(message = "A categoria do curso é obrigatório")
    @Length(min=5, max=10, message = "O campo [categoria] deve possuir entre 5 e 10 caracteres")
    private String category;

    @NotBlank(message = "O professor do curso é obrigatório")
    @Length(min=10, max=30, message = "O campo [Professor] deve possuir entre 10 e 30 caracteres")
    private String teacher;


    private boolean active;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
