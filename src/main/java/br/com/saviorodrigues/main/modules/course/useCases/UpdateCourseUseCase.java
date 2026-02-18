package br.com.saviorodrigues.main.modules.course.useCases;

import br.com.saviorodrigues.main.exceptions.CourseFoundException;
import br.com.saviorodrigues.main.exceptions.CourseNotFoundException;
import br.com.saviorodrigues.main.modules.course.dto.UpdateCourseDTO;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, UpdateCourseDTO updateCourseDTO) {
        CourseEntity courseEntity = courseRepository.findByIdCourse(id);


        if(courseEntity == null) {
            throw new CourseNotFoundException();
        }

        courseRepository.findByNameAndCategory(updateCourseDTO.getName(), updateCourseDTO.getCategory())
                .ifPresent(courseEntity1 -> {
                    throw new CourseFoundException();
                });

        if(updateCourseDTO.getName() != null) {
            courseEntity.setName(updateCourseDTO.getName());
        }

        if(updateCourseDTO.getCategory() != null) {
            courseEntity.setCategory(updateCourseDTO.getCategory());
        }

        return courseRepository.save(courseEntity);
    }
}
