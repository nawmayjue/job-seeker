package com.springboot.jobseeker.feature.skillcategory.service;

import com.springboot.jobseeker.feature.skillcategory.dto.CreateSkillCategoryRequest;
import com.springboot.jobseeker.feature.skillcategory.dto.SkillCategoryResponse;
import com.springboot.jobseeker.feature.skillcategory.dto.UpdateSkillCategoryRequest;

import java.util.List;

public interface SkillCategoryService {
    SkillCategoryResponse createSkillCategory(CreateSkillCategoryRequest request);
    List<SkillCategoryResponse> retrieveAll();
    SkillCategoryResponse retrieveOne(Long id);
    SkillCategoryResponse updateSkillCategory(Long id, UpdateSkillCategoryRequest request);
    void delete(Long id);
}
