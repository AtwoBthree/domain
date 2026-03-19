package com.wanted.crud.user.model.service;

import com.wanted.crud.user.model.dao.UserDAO;
import com.wanted.crud.user.model.dao.StudentDAO;
import com.wanted.crud.user.model.dao.InstructorDAO;

import com.wanted.crud.user.model.dto.UserDTO;
import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.dto.InstructorDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final InstructorDAO instructorDAO;
    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAO(connection);
        this.studentDAO = new StudentDAO(connection);
        this.instructorDAO = new InstructorDAO(connection);
    }

    // ===== User =====
    public List<UserDTO> selectAllUsers() {
        try {
            return userDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("유저 전체 조회 중 Error 발생!! /UserService2");
        }
    }



    public String findId(String name, String phone_number) {
        try {
            return userDAO.findId(name, phone_number);
        } catch (SQLException e) {
            throw new RuntimeException("유저 아이디를 찾는 중 Error 발생!!");
        }
    }

    public String findPassord(String userid, String userphonenumber ) {
        try {
            return userDAO.findPassword(userid, userphonenumber);
        } catch (SQLException e) {
            throw new RuntimeException("비밀번호 찾는 중 에러 발생 !!");
        }
    }

    public UserDTO login(String id, String password) {
        try {
            return userDAO.login(id, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long saveUser(UserDTO newUser) {
        try {
            return userDAO.save(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("회원가입 중 Error 발생!!! 🚨");
        }
    }

    // ===== Student =====
    public List<StudentDTO> selectAllStudents() {
        try {
            return studentDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("학생 전체 조회 중 Error 발생!! /UserService2");
        }
    }

    public UserDTO findNo(Long userNo) {
        try {
            return userDAO.findNo(userNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentDTO findPassword(long id) throws SQLException {
        return studentDAO.findById(id);
    }



    // 회원 탈퇴
    public boolean dropUser(String id, String password) {
        try {
            // DAO에서 boolean 반환
            return userDAO.dropUser(id, password);
        } catch (SQLException e) {
            throw new RuntimeException("회원 탈퇴 중 오류 발생", e);
        }
    }

    public boolean updateStudent(UserDTO userDTO) {
        try {
            return studentDAO.updateStudent(userDTO) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // ===== Instructor =====
    public List<InstructorDTO> selectAllInstructors() {
        try {
            return instructorDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("강사 전체 조회 중 Error 발생!! /UserService2");
        }
    }

    // 강사 번호 리턴
    public Long findInstructorId(Long userno) {
        try {
            if(instructorDAO.findId(userno) != null) {
                return instructorDAO.findId(userno);
            } else System.out.println("조회된 강사번호가 없음");
        } catch (SQLException e) {
            throw new RuntimeException("유저번호를 통한 강사번호 조회 중 오류발생!!");
        }
        return null;
    }


}