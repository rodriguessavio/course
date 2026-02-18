package br.com.saviorodrigues.main.modules.course.repositories;

import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    CourseEntity findByIdCourse(UUID idCourse);
    Optional<CourseEntity> findByNameAndCategory(String name, String category);
    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByCategory(String category);
    List<CourseEntity> findAll();
}
