package com.llp.courseservice.services.impl;

import com.llp.courseservice.repositories.SpecializedRepository;
import com.llp.courseservice.services.SpecializedService;
import com.llp.sharedproject.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializedServiceImpl implements SpecializedService {
    private final SpecializedRepository specializedRepository;
    @Override
    public List<Integer> getAllTeacherByCategory(int categoryId) {
        try {
            return specializedRepository.getAllTeacherByCategory(categoryId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<Integer> getAllTeacherBySubCategory(int subCategoryId) {
        try {
            return specializedRepository.getAllTeacherBySubCategory(subCategoryId);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
