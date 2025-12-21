package com.springboot.jobseeker.feature.skillcategory.service;

import com.springboot.jobseeker.feature.skillcategory.dto.CreateSkillCategoryRequest;
import com.springboot.jobseeker.feature.skillcategory.dto.SkillCategoryResponse;

import java.util.List;

public interface SkillCategoryService {
    SkillCategoryResponse createSkillCategory(CreateSkillCategoryRequest request);
    List<SkillCategoryResponse> retrieveAll();
    SkillCategoryResponse retrieveOne(Long id);
    void delete(Long id);
}
