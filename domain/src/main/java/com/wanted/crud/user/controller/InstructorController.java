package com.wanted.crud.user.controller;

import com.wanted.crud.user.model.dto.InstructorDTO;
import com.wanted.crud.user.model.service.InstructorService;

import java.util.List;

public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    public List<InstructorDTO> selectAllInstructors() {
        return service.selectAllInstructors();
    }
}