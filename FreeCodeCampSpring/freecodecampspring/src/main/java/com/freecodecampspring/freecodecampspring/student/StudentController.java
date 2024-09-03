package com.freecodecampspring.freecodecampspring.student;

// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // From Normal class repo

    // @PostMapping("/students")
    // public Student post(@RequestBody Student student) {
    // return repository.save(student);
    // }

    // From Dto
    @PostMapping("/students")
    public StudentResponseDto post(@Valid @RequestBody StudentDto dto) {
        return studentService.post(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent() {
        return studentService.findAllStudent();
    }

    // Normal
    // @GetMapping("/students/{student-id}")
    // public Student findStudentById(@PathVariable("student-id") int id) {
    // return studentService.findStudentById(id);
    // }
    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(@PathVariable("student-id") int id) {
        return studentService.findStudentById(id);
    }

    // @GetMapping("/students/search/{student-name}")
    // public List<Student> findStudentByName(@PathVariable("student-name") String
    // name) {
    // return studentService.findStudentByName(name);
    // }

    // @DeleteMapping("/students/{students-id}")
    // @ResponseStatus(HttpStatus.OK)
    // public void delete(@PathVariable("students-id") int id) {
    // studentService.delete(id);
    // }
    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name) {
        return studentService.findStudentByName(name);
    }

    @DeleteMapping("/students/{students-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("students-id") int id) {
        studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleExceptionMethod(
            MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errMssg = error.getDefaultMessage();
                    errors.put(fieldName, errMssg);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
