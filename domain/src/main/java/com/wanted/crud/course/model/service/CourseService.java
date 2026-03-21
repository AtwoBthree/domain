package com.wanted.crud.course.model.service;

import com.wanted.crud.course.model.dao.CourseDAO;
import com.wanted.crud.course.model.dao.SectionDAO;
import com.wanted.crud.course.model.dto.CourseDTO;
import com.wanted.crud.course.model.dto.CourseMyStudentDTO;
import com.wanted.crud.course.model.dto.SectionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseDAO courseDAO;
    private final SectionDAO sectionDAO;
    private final Connection connection;

    // 강좌 번호 리턴
    public List<Long> findCoursesId(Long instructorId) {
        try {
            // DAO가 List<Long> 줌.
            List<Long> courseList = courseDAO.findcourseid(instructorId);

            if (courseList != null && !courseList.isEmpty()) {
                return courseList; // 강좌 번호들 담긴 리스트 반환
            } else {
                System.out.println("등록된 강좌가 없습니다.");
                return null; // 없으면 null 반환
            }
        } catch (SQLException e) {
            throw new RuntimeException("🚨강좌 목록 조회 중 Error 발생🚨" + e);
        }
    }




    public List<CourseDTO> findAllCourses() {
        try {
            return courseDAO.selectAllCourses();
        } catch (SQLException e) {
            throw new RuntimeException("🚨전체 강좌 조회 중 Error 발생🚨" + e);
        }
    }


    public CourseService(Connection connection) {
        this.courseDAO = new CourseDAO(connection);
        this.sectionDAO = new SectionDAO(connection);
        this.connection = connection;
    }

    public List<CourseDTO> findMyCourse(Long id) {
        try {
            return courseDAO.selectCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException("🚨내 강좌 조회 중 Error 발생🚨" + e);
        }
    }

    public List<SectionDTO> findSectionsByCourseId(Long courseId) {
        try {
            return sectionDAO.selectSectionsByCourseId(courseId);
        } catch (SQLException e) {
            throw new RuntimeException("🚨 강의 목록 조회 중 Error 발생: " + e.getMessage());
        }
    }

    public Long saveCourse(CourseDTO newCourse) {
        // save를 throw 해둠. 그래서 try-catch로 감쌈.
        try {
            return courseDAO.save(newCourse);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 등록 중 Error 발생!!! 🚨");
        }
    }

    public Long saveSection(SectionDTO newSection) {
        // save를 throw 해둠. 그래서 try-catch로 감쌈.
        try {
            return sectionDAO.sectionSave(newSection);
        } catch (SQLException e) {
            throw new RuntimeException("강좌 등록 중 Error 발생!!! 🚨");
        }
    }

    // 내 강좌 삭제
    public boolean deleteCourseById(Long courseId, Long instructorId) {
        try {
            return courseDAO.deleteCourse(courseId, instructorId);

        } catch (SQLException e) {
            System.out.println("🚨 강좌 삭제 중 Error 발생: " + e.getMessage());
            return false;
        }
    }

    // 내 강좌 수정
    public boolean updateCourse(CourseDTO course) {
        try {
            return courseDAO.updateCourse(course) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("🚨 강좌 수정 중 Error 발생: " + e.getMessage());
        }
    }

    // 내 강의 수정
    public boolean updateSection(SectionDTO section, Long instructorId) {
        try {
            return sectionDAO.updateSection(section, instructorId) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("🚨 강의 수정 중 Error 발생: " + e.getMessage());
        }
    }

    // 관리자가 강사 강좌 조회
    public List<CourseDTO> findCoursesByInstructorId(Long instructorId) {
        try {
            List<CourseDTO> courses = courseDAO.selectCoursesByInstructorId(instructorId);

            if (courses == null || courses.isEmpty()) {
                System.out.println("해당 강사가 등록한 강좌가 없습니다.");
            }
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException("🚨 특정 강사 강좌 조회 중 Error 발생: " + e.getMessage());
        }
    }

    // 관리자가 강좌 수정
    public boolean updateCourseByAdmin(CourseDTO course) {
        try {
            return courseDAO.updateCourseByAdmin(course) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("🚨 관리자 강좌 수정 중 오류: " + e.getMessage());
        }
    }

    // 관리자가 강좌 삭제
    public boolean removeCourseByAdmin(Long courseId) {
        try {
            return courseDAO.adminDeleteCourse(courseId);
        } catch (SQLException e) {
            throw new RuntimeException("🚨 관리자 강좌 삭제 중 오류: " + e.getMessage());
        }
    }

    // 내 강좌를 수강 중인 전체 학생 현황 조회
    public List<CourseMyStudentDTO> selectFindMyStudent(Long instructorId) {
        try {
            List<CourseMyStudentDTO> myStudents = courseDAO.selectMyStudent(instructorId);

            if (myStudents == null || myStudents.isEmpty()) {
                System.out.println("해당 강사님의 강좌를 수강 중인 학생이 없습니다.");
            }
            return myStudents;

        } catch (SQLException e) {
            throw new RuntimeException("🚨 수강생 현황 조회 중 Error 발생: " + e.getMessage());
        }
    }

    public Long getPrice(Long courseId) {
        try {
            if(courseDAO.getPrice(courseId) != null) {
                return courseDAO.getPrice(courseId);
            } else System.out.println("잔액이 확인되지않음");
        } catch (SQLException e) {
            throw new RuntimeException("수강번호를 통한 수강 가격확인불가+", e);
        }
        return null;
    }














}