package com.springboot.jobseeker.feature.skillcategory.service.impl;

import com.springboot.jobseeker.feature.skillcategory.dto.CreateSkillCategoryRequest;
import com.springboot.jobseeker.feature.skillcategory.dto.SkillCategoryResponse;
import com.springboot.jobseeker.feature.skillcategory.service.SkillCategoryService;
import com.springboot.jobseeker.shared.data.model.SkillCategory;
import com.springboot.jobseeker.shared.data.repository.jpa.SkillCategoryJpaRepository;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryJpaRepository skillCategoryJpaRepository;

    @Override
    public SkillCategoryResponse createSkillCategory(CreateSkillCategoryRequest request) {
        if (skillCategoryJpaRepository.existsByName(request.name())) {
            throw new BadRequestException("Skill Category Already Exists.");
        }

        SkillCategory skillCategory = SkillCategory.builder()
                .name(request.name())
                .build();

        SkillCategory savedSkillCategory = skillCategoryJpaRepository.save(skillCategory);

        return SkillCategoryResponse.builder()
                .id(savedSkillCategory.getId())
                .name(savedSkillCategory.getName())
                .build();
    }

    @Override
    public List<SkillCategoryResponse> retrieveAll() {
        List<SkillCategory> skillCategories = skillCategoryJpaRepository.findAll();
        return skillCategories.stream()
                .map(skillCategory -> SkillCategoryResponse.builder()
                        .id(skillCategory.getId())
                        .name(skillCategory.getName())
                        .build()
                ).toList();
    }

    @Override
    public SkillCategoryResponse retrieveOne(Long id) {
        SkillCategory skillCategory = skillCategoryJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill Category Not Found."));
        return SkillCategoryResponse.builder()
                .id(skillCategory.getId())
                .name(skillCategory.getName())
                .build();
    }

    @Override
    public void delete(Long id) {
        SkillCategory skillCategory = skillCategoryJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill Category Not Found."));
        skillCategory.delete(1L);
        skillCategoryJpaRepository.save(skillCategory);
    }

}
