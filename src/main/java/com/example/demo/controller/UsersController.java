package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UserService userService;



    @Operation(
            summary = "Register a new user",
            description = "Creates a new user in the system"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error or email already exists")
    })
    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request
    ) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users fetched successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }
}

