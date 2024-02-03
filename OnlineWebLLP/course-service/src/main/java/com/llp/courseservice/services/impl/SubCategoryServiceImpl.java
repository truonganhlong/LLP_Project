package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.SubCategory.SubCategoryCreateRequest;
import com.llp.courseservice.dtos.SubCategory.SubCategoryAdminResponse;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUpdateRequest;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUserResponse;
import com.llp.courseservice.entities.SubCategory;
import com.llp.courseservice.mappers.SubCategoryMapper;
import com.llp.courseservice.repositories.CategoryRepository;
import com.llp.courseservice.repositories.SubCategoryRepository;
import com.llp.courseservice.services.SubCategoryService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<SubCategoryAdminResponse> getAll() {
        try {
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            return subCategories.stream().map(SubCategoryMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<SubCategoryAdminResponse> getByAdminFilterByCategory(int categoryId) {
        try {
            List<SubCategory> subCategories = subCategoryRepository.getByAdminFilterByCategory(categoryId);
            return subCategories.stream().map(SubCategoryMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<SubCategoryUserResponse> getByUserFilterByCategory(int categoryId) {
        try {
            List<SubCategoryRepository.SubCategoryByName> subCategories = subCategoryRepository.getByUserFilterByCategory(categoryId);
            return subCategories.stream().map(SubCategoryMapper::convertToUserResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public SubCategoryAdminResponse getById(int id) {
        try {
            SubCategory subCategory = subCategoryRepository.getById(id);
            if(Objects.isNull(subCategory)){
                throw new NotFoundException("Not found in database");
            }
            return SubCategoryMapper.convertToAdminResponse(subCategory);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(SubCategoryCreateRequest request) {
        try {
            SubCategory subCategory = SubCategory.builder()
                    .name(request.getName())
                    .status(true)
                    .category(categoryRepository.getById(request.getCategoryId()))
                    .build();
            subCategoryRepository.save(subCategory);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, SubCategoryUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            SubCategory subCategory = subCategoryRepository.getById(id);
            if(Objects.isNull(subCategory)){
                throw new NotFoundException("Not found in database");
            }
            subCategory.setName(request.getName() != null ? request.getName() : subCategory.getName());
            subCategoryRepository.save(subCategory);
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateStatus(int id) {
        try {
            SubCategory subCategory = subCategoryRepository.getById(id);
            if(Objects.isNull(subCategory)){
                throw new NotFoundException("Not found in database");
            }
            subCategory.setStatus(!subCategory.isStatus());
            subCategoryRepository.save(subCategory);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            SubCategory subCategory = subCategoryRepository.getById(id);
            if(Objects.isNull(subCategory)){
                throw new NotFoundException("Not found in database");
            }
            subCategoryRepository.delete(subCategory);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
