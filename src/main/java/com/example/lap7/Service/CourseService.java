package com.example.lap7.Service;


import com.example.lap7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList();

    public ArrayList<Course> getAllCourses(){
        return courses ;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean updateCourse(int id , Course course){
        for(int i = 0 ; i < courses.size() ; i++){
            if(courses.get(i).getId() == id){
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int id){
        for(Course course : courses){
            if(course.getId() == id){
                courses.remove(course);
                return true;
            }
        }
        return false;
    }

    public Course getCoursebyId(int id){
        for(Course course : courses){
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }

    public boolean changeCapacity(int id , int newCapacity){
        for(int i = 0 ; i < courses.size() ; i++){
            if(courses.get(i).getId() == id){
                courses.get(i).setCapacity(newCapacity);
                return true;
            }
        }
        return false;
    }

    public boolean changeInstructor(int id , String newInstructor){
        for(int i = 0 ; i < courses.size() ; i++){
            if(courses.get(i).getId() == id){
                courses.get(i).setInstructor(newInstructor);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCoursesByInstructor(String instructor){
        for(int i = 0 ; i < courses.size() ; i++){
            if(courses.get(i).getInstructor().equals(instructor)){
                return courses;
            }
        }
        return null;
    }


}
