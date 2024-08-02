package com.example.lap7.Controller;

import com.example.lap7.Api.ApiResponse;
import com.example.lap7.Model.Student;
import com.example.lap7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        ArrayList<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/post")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id , @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name) {
        Student stu = studentService.getByName(name);
        if(stu == null){
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(stu);
    }

    @GetMapping("/getbymajor/{major}")
    public ResponseEntity getStudentByMajor(@PathVariable String major) {
        ArrayList<Student> stus = studentService.getByMajor(major);
        if(stus.size() == 0){
            return ResponseEntity.status(400).body(new ApiResponse("Students not found"));
        }
        return ResponseEntity.status(200).body(stus);
    }

    @GetMapping("/getgraduated")
    public ResponseEntity getGraduatedStudents() {
        ArrayList<Student> students = studentService.getGraduated();
        return ResponseEntity.status(200).body(students);
    }

    @GetMapping("/checkgraduate/{id}")
    public ResponseEntity checkGraduateStudents(@PathVariable int id) {
        boolean isGraduated = studentService.checkGraduated(id);
        if(isGraduated){
            return ResponseEntity.status(200).body(new ApiResponse("Student graduated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not graduated or not found"));
    }


    @PutMapping("/graduate/{id}")
    public ResponseEntity graduateStudent(@PathVariable int id) {
        boolean isGraduated = studentService.graduateStudent(id);
        if(isGraduated){
            return ResponseEntity.status(200).body(new ApiResponse("Student graduated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }





}
