package br.com.saviorodrigues.main.modules.course.controllers;

import br.com.saviorodrigues.main.modules.course.dto.CreateCourseDTO;
import br.com.saviorodrigues.main.modules.course.dto.ToggleActiveCourseDTO;
import br.com.saviorodrigues.main.modules.course.dto.UpdateCourseDTO;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import br.com.saviorodrigues.main.modules.course.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private GetAllCoursesUseCase getAllCoursesUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private ToggleActiveCourseUseCase toggleActiveCourseUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        try{
            var courseEntity = CourseEntity.builder()
                    .category(createCourseDTO.getCategory())
                    .name(createCourseDTO.getName())
                    .teacher(createCourseDTO.getTeacher())
                    .build();
            System.out.println(courseEntity);
            var result = this.createCourseUseCase.execute(courseEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String category){

        List<CourseEntity> result = this.getAllCoursesUseCase.execute(name, category);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        try{
            var result = updateCourseUseCase.execute(id, updateCourseDTO);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try{
            deleteCourseUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable UUID id, @Valid @RequestBody ToggleActiveCourseDTO toggleActiveCourseDTO) {
        try{
            var result = toggleActiveCourseUseCase.execute(id, toggleActiveCourseDTO);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
