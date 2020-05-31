package com.example.controller;

import com.example.model.UserDTO;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public UserDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(path = "/new")
    public UserDTO save(@RequestBody UserDTO dto) {
        return service.save(dto);
    }
}
