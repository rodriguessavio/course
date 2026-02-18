package br.com.saviorodrigues.main.modules.course.useCases;

import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCoursesUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute(String name, String category){
        if(name != null) {
            return this.courseRepository.findByName(name);
        } else if(category != null) {
            return this.courseRepository.findByCategory(category);
        }
        return courseRepository.findAll();
    }
}
