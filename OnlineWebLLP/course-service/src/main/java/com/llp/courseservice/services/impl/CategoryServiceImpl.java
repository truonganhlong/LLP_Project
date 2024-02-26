package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Category.*;
import com.llp.courseservice.dtos.SubCategory.SubCategoryToTopicResponse;
import com.llp.courseservice.dtos.Topic.TopicNameResponse;
import com.llp.courseservice.entities.Category;
import com.llp.courseservice.mappers.CategoryMapper;
import com.llp.courseservice.mappers.SubCategoryMapper;
import com.llp.courseservice.mappers.TopicMapper;
import com.llp.courseservice.repositories.CategoryRepository;
import com.llp.courseservice.repositories.SubCategoryRepository;
import com.llp.courseservice.repositories.TopicRepository;
import com.llp.courseservice.services.CategoryService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final String root = System.getProperty("user.dir") + "/shared-project/src/main/resources/static/";
    private static final String directory = "images/category/";
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final TopicRepository topicRepository;
    @Override
    public List<CategoryAdminResponse> getAll() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream().map(CategoryMapper::convertToAdminResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CategoryUserResponse> getAllByUser() {
        try {
            List<CategoryRepository.CategoryByUser> categories = categoryRepository.getAllByUser();
            return categories.stream().map(CategoryMapper::convertToUserResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CategoryNameResponse> getNameByUser() {
        try {
            List<CategoryRepository.CategoryByName> categories = categoryRepository.getNameByUser();
            return categories.stream().map(CategoryMapper::convertToNameResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public CategoryAdminResponse getById(int id) {
        try {
            Category category = categoryRepository.getById(id);
            if(Objects.isNull(category)){
                throw new NotFoundException("Not found in database");
            }
            return CategoryMapper.convertToAdminResponse(category);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(CategoryCreateRequest request) {
        try {
            Category category = Category.builder()
                    .name(request.getName())
                    .status(true)
                    .build();
            insertImage(category, request.getImageLink());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, CategoryUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Category category = categoryRepository.getById(id);
            if(Objects.isNull(category)){
                throw new NotFoundException("Not found in database");
            }
            category.setName(request.getName() != null ? request.getName() : category.getName());
            if(!request.getImageLink().isEmpty()){
                String pastPath = category.getImageLink();
                insertImage(category, request.getImageLink());
                deleteImage(pastPath);
            }
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
            Category category = categoryRepository.getById(id);
            if(Objects.isNull(category)){
                throw new NotFoundException("Not found in database");
            }
            category.setStatus(!category.isStatus());
            categoryRepository.save(category);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            Category category = categoryRepository.getById(id);
            if(Objects.isNull(category)){
                throw new NotFoundException("Not found in database");
            }
            deleteImage(category.getImageLink());
            categoryRepository.delete(category);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<CategoryToTopicResponse> getCategoryToTopic() {
        try {
            List<CategoryRepository.CategoryByName> categories = categoryRepository.getNameByUser();
            var result =  categories.stream().map(CategoryMapper::convertToCtTResponse).collect(Collectors.toList());
            for (var category:result) {
                List<SubCategoryRepository.SubCategoryByName> subCategoriesList = subCategoryRepository.getByUserFilterByCategory(category.getId().intValue());
                List<SubCategoryToTopicResponse> subCategories = subCategoriesList.stream().map(SubCategoryMapper::convertToSctTResponse).collect(Collectors.toList());
                for (var subCategory:subCategories) {
                    List<TopicRepository.TopicByName> topicsList = topicRepository.getByUserFilterBySubCategory(subCategory.getId().intValue());
                    List<TopicNameResponse> topics = topicsList.stream().map(TopicMapper::convertToUserResponse).collect(Collectors.toList());
                    subCategory.setTopics(topics);
                }
                category.setSubCategories(subCategories);
            }
            return result;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    private void insertImage(Category category, MultipartFile imageLink) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = imageLink.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = formattedDateTime + randomUUID + fileExtension;
        Path filePath = Paths.get(root, directory, newFilename);
        try {
            imageLink.transferTo(new File(String.valueOf(filePath)));
            category.setImageLink(newFilename);
            categoryRepository.save(category);
        } catch (IOException e) {
            throw new NotFoundException("Not found file");
        }
    }

    private void deleteImage(String pastPath) {
        Path filePath = Paths.get(root, pastPath);
        File file = new File(String.valueOf(filePath));
        file.delete();
    }
}
