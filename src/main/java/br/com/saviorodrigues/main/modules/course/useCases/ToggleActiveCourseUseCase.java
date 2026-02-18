package br.com.saviorodrigues.main.modules.course.useCases;

import br.com.saviorodrigues.main.exceptions.CourseNotFoundException;
import br.com.saviorodrigues.main.modules.course.dto.ToggleActiveCourseDTO;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ToggleActiveCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, ToggleActiveCourseDTO toggleActiveCourseDTO){
        CourseEntity courseEntity = courseRepository.findByIdCourse(id);

        if(courseEntity == null) {
            throw new CourseNotFoundException();
        }

        courseEntity.setActive(toggleActiveCourseDTO.isActive());
        return courseRepository.save(courseEntity);
    }

}
