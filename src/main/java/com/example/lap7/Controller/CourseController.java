package com.example.lap7.Controller;

import com.example.lap7.Api.ApiResponse;
import com.example.lap7.Model.Course;
import com.example.lap7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        ArrayList<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/post")
    public ResponseEntity addCourse(@Valid @RequestBody Course course , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable int id , @Valid @RequestBody Course course , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = courseService.updateCourse(id, course);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
        return ResponseEntity.status(200).body(new ApiResponse("Course Deleted Successfully"));
    }
    return ResponseEntity.status(404).body(new ApiResponse("Course Not Found"));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity getCourseById(@PathVariable int id) {
        Course course = courseService.getCoursebyId(id);
        if(course == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Course Not Found"));
        }
        return ResponseEntity.status(200).body(course);
    }

    @PutMapping("changecapacity/{id}/{newcapacity}")
    public ResponseEntity changeCapacity(@PathVariable int id, @PathVariable int newcapacity) {
        boolean isUpdated = courseService.changeCapacity(id, newcapacity);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course Not Found"));
    }

    @PutMapping("changeinstructor/{id}/{newinstructor}")
    public ResponseEntity changeInstructor(@PathVariable int id, @PathVariable String newinstructor) {
        boolean change = courseService.changeInstructor(id ,newinstructor);
        if(change) {
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course Not Found"));
    }

    @GetMapping("getbyinstructor/{instructor}")
    public ResponseEntity getCourseByInstructor(@PathVariable String instructor) {
        ArrayList<Course> byInstructor = courseService.getCoursesByInstructor(instructor);
        return ResponseEntity.status(200).body(byInstructor);

    }






}
