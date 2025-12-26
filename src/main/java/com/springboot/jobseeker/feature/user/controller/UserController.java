package com.springboot.jobseeker.feature.user.controller;

import com.springboot.jobseeker.feature.user.service.UserService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-seeker/users")
@AllArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> retrieveAllUsers(){
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        userService.retrieveAllUsers(),
                        "Users retrieved successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> retrieveUserById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        userService.retrieveUserById(id),
                        "User retrieved successfully"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMapping(
           @PathVariable Long id
    ){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(
                    new ApiResponse(
                            HttpStatus.NOT_FOUND.value(),
                            null,
                            e.getMessage()
                    )
            );
        }
    }

}
