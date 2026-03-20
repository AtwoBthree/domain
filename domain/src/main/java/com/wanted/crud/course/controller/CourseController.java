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

    public boolean deleteCourseById(Long courseId, Long instructorId) {
        return service.deleteCourseById(courseId, instructorId);
    }

    // 특정 강좌에 속한 강의(Section) 목록 조회
    public List<SectionDTO> selectSectionsByCourseId(Long courseId) {
        // 컨트롤러는 서비스에게 일을 시킵니다.
        return service.findSectionsByCourseId(courseId);
    }

    public List<Long> findCoursesId(Long instructorId) {
        return service.findCoursesId(instructorId);
    }

    public boolean updateCourse(Long courseId, String title, String description, Long price, Long instructorId) {
        CourseDTO updateCourse = new CourseDTO(courseId, title, description, price, null, null, instructorId);
        return service.updateCourse(updateCourse);
    }

    // 내 강의 수정
    public boolean updateSection(Long sectionId, String title, String videoUrl, String materialUrl, Long instructorId) {
        // SectionDTO에는 courseId를 굳이 안 넣어도 됩니다 (DAO에서 JOIN으로 체크하니까요)
        SectionDTO section = new SectionDTO(sectionId, title, videoUrl, materialUrl, null, null);
        return service.updateSection(section, instructorId);
    }
}
