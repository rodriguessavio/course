package br.com.saviorodrigues.main.modules.course.useCases;

import br.com.saviorodrigues.main.exceptions.CourseFoundException;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        System.out.println("Entrou no execute");
        System.out.println(courseEntity);
        courseRepository.findByNameAndCategory(courseEntity.getName(),  courseEntity.getCategory())
                .ifPresent(
                        courseOne -> {
                            throw new CourseFoundException();
                        }
                );

        return this.courseRepository.save(courseEntity);
    }

}
