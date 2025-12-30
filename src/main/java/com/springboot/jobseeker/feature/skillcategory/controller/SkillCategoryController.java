package com.springboot.jobseeker.feature.skillcategory.controller;

import com.springboot.jobseeker.feature.skillcategory.dto.CreateSkillCategoryRequest;
import com.springboot.jobseeker.feature.skillcategory.dto.SkillCategoryResponse;
import com.springboot.jobseeker.feature.skillcategory.dto.UpdateSkillCategoryRequest;
import com.springboot.jobseeker.feature.skillcategory.service.SkillCategoryService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import com.springboot.jobseeker.shared.data.dto.ErrorResponse;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skill-categories")
@AllArgsConstructor
public class SkillCategoryController {
    private final SkillCategoryService skillCategoryService;

    @PostMapping
    public ResponseEntity<?> createSkillCategory(
            @RequestBody CreateSkillCategoryRequest request
    ){
        ApiResponse apiResponse;
        try {
            apiResponse = ApiResponse.builder()
                .status(201)
                .data(
                        skillCategoryService.createSkillCategory(request)
                )
                .message("Skill Category Created Successfully.")
                .build();
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
            apiResponse
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkillCategory(
            @PathVariable Long id,
            @RequestBody UpdateSkillCategoryRequest request
            ){
        ApiResponse apiResponse;
        try{apiResponse = ApiResponse.builder()
                .status(201)
                .data(
                        skillCategoryService.updateSkillCategory(id, request)
                )
                .message("Skill Category Updated Successfully.")
                .build();
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieveAllSkillCategories(){
        return ResponseEntity.ok().body(
            ApiResponse.builder()
                .status(200)
                .data(skillCategoryService.retrieveAll())
                .message("Skill Categories Retrieved Successfully.")
            .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveOneSkillCategory(@PathVariable Long id){
        ApiResponse apiResponse;
        try {
            SkillCategoryResponse skillCategory = skillCategoryService.retrieveOne(id);
            apiResponse = ApiResponse.builder()
                    .status(200)
                    .data(skillCategoryService.retrieveOne(id))
                    .message("Skill Category Retrieved Successfully.")
                    .build();
        }catch(NotFoundException e2) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND.value(),
                            e2.getMessage()
                    )
            );
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkillCategory(@PathVariable Long id){
        try {
            skillCategoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(NotFoundException e2) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND.value(),
                            e2.getMessage()
                    )
            );
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
    }
}
