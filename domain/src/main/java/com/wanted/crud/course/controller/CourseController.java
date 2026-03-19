package com.wanted.crud.course.controller;

import com.wanted.crud.course.model.dto.SectionDTO;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.service.CourseService;

import java.util.List;

public class CourseController {

    private final CourseService service;

    public List<CourseDTO> selectAllCourses() {
        return service.findAllCourses();
    }

    public CourseController(CourseService service) {
        this.service = service;
    }

    public List<CourseDTO> selectCourse(long id){
        return service.findMyCourse(id);
    }

    public Long createCourse(String title, String description, long price, long id) {
        CourseDTO newCourse = new CourseDTO(null, title, description, price, "1", null, id);
        return service.saveCourse(newCourse);
    }

    // title, video_url, material_url, created_at, course_id
    public Long createSection(String title, String video_url, String material_url, long id){
        SectionDTO newSection = new SectionDTO(null, title, video_url, material_url, null ,id);
        return service.saveSection(newSection);
    }

    public boolean deleteCourseById(long id) {
        return service.deleteCourse(id) > 0; // 0보다 크면 true
    }

    public List<Long> findCoursesId(Long instructorId) {
        return service.findCoursesId(instructorId);
    }
}
