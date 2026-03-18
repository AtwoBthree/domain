package com.wanted.crud.user.controller;

import com.wanted.crud.user.model.dto.StudentDTO;
import com.wanted.crud.user.model.service.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    public List<StudentDTO> selectAllStudents() {
        return service.selectAllStudents();
    }
}