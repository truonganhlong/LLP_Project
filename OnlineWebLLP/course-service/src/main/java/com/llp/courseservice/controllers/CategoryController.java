package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Category.CategoryCreateRequest;
import com.llp.courseservice.dtos.Category.CategoryUpdateRequest;
import com.llp.courseservice.services.CategoryService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "Api 9: get all category in admin side")
    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try {
            var data = categoryService.getAll();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 10: get name of all category in categories filter in homepage before login")
    @RequestMapping(value = "/public/allNameByUser", method = RequestMethod.GET)
    public ResponseEntity<?> getNameByUser(){
        try {
            var data = categoryService.getNameByUser();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 11: get all category in top categories in homepage")
    @RequestMapping(value = "/public/allByUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(){
        try {
            var data = categoryService.getAllByUser();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 12: get category by id")
    @RequestMapping(value = "/public/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            var data = categoryService.getById(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 13: create category")
    @RequestMapping(value = "/admin", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request){
        try {
            categoryService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 14: update category")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CategoryUpdateRequest request){
        try {
            categoryService.update(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 15: update category's status. if status = true then user can see")
    @RequestMapping(value = "/admin/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable int id){
        try {
            categoryService.updateStatus(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        }  catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 16: delete category")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 65: get from category to topic in homepage filter")
    @RequestMapping(value = "/public/allCategoryToTopic", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryToTopic(){
        try {
            var data = categoryService.getCategoryToTopic();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
