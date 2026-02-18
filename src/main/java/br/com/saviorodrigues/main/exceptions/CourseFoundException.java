package br.com.saviorodrigues.main.exceptions;

public class CourseFoundException extends RuntimeException{
    public CourseFoundException() {
        super("Curso já existe para essa categoria");
    }
}
