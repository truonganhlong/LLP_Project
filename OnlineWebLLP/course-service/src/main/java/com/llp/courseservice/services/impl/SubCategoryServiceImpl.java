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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "subCategory")
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Override
    @Cacheable(cacheNames = "subCategory/getAll")
    public List<SubCategoryAdminResponse> getAll() {
        try {
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            return subCategories.stream().map(SubCategoryMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "subCategory/getByAdminFilterByCategory", key = "#categoryId")
    public List<SubCategoryAdminResponse> getByAdminFilterByCategory(int categoryId) {
        try {
            List<SubCategory> subCategories = subCategoryRepository.getByAdminFilterByCategory(categoryId);
            return subCategories.stream().map(SubCategoryMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "subCategory/getByUserFilterByCategory", key = "#categoryId")
    public List<SubCategoryUserResponse> getByUserFilterByCategory(int categoryId) {
        try {
            List<SubCategoryRepository.SubCategoryByName> subCategories = subCategoryRepository.getByUserFilterByCategory(categoryId);
            return subCategories.stream().map(SubCategoryMapper::convertToUserResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "subCategory/getById", key = "#id")
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
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "subCategory/getAll"),
                    @CacheEvict(cacheNames = "subCategory/getByAdminFilterByCategory", key = "#request.categoryId"),
                    @CacheEvict(cacheNames = "subCategory/getByUserFilterByCategory", key = "#request.categoryId")
            }
    )
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
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "subCategory/getAll"),
                    @CacheEvict(cacheNames = "subCategory/getByAdminFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getByUserFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getById", key = "#id")
            }
    )
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
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "subCategory/getAll"),
                    @CacheEvict(cacheNames = "subCategory/getByAdminFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getByUserFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getById", key = "#id")
            }
    )
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
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "subCategory/getAll"),
                    @CacheEvict(cacheNames = "subCategory/getByAdminFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getByUserFilterByCategory", allEntries = true),
                    @CacheEvict(cacheNames = "subCategory/getById", key = "#id")
            }
    )
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
