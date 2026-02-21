package br.com.saviorodrigues.main.modules.course.controllers;

import br.com.saviorodrigues.main.modules.course.dto.CreateCourseDTO;
import br.com.saviorodrigues.main.modules.course.dto.ToggleActiveCourseDTO;
import br.com.saviorodrigues.main.modules.course.dto.UpdateCourseDTO;
import br.com.saviorodrigues.main.modules.course.entities.CourseEntity;
import br.com.saviorodrigues.main.modules.course.repositories.CourseRepository;
import br.com.saviorodrigues.main.modules.course.useCases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Curso", description = "Informações do curso")
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

    @Operation(summary = "Criação de um Curso", description = "Essa função é responsável pela criação do curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = CourseEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Curso já existe para essa categoria")

    })
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

    @Operation(summary = "Listar todos os cursos", description = "Essa função é responsável por listar todos os cursos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = CourseEntity.class))
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Curso já existe para essa categoria")

    })
    @GetMapping("/")
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String category){

        List<CourseEntity> result = this.getAllCoursesUseCase.execute(name, category);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Atualização de um Curso", description = "Essa função é responsável por atualizar os dados de um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = CourseEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")

    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        try{
            var result = updateCourseUseCase.execute(id, updateCourseDTO);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "Exclusão de um Curso", description = "Essa função é responsável por excluir um curso existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Curso excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try{
            deleteCourseUseCase.execute(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }


    @Operation(summary = "Atualizar status", description = "Essa função é responsável atualizar o status do curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = CourseEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")

    })
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
