package br.com.saviorodrigues.main.modules.course.useCases;

import br.com.saviorodrigues.main.exceptions.CourseNotFoundException;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id){
        CourseEntity courseEntity = courseRepository.findByIdCourse(id);

        if(courseEntity == null) {
            throw new CourseNotFoundException();
        }

        courseRepository.delete(courseEntity);
    }
}
