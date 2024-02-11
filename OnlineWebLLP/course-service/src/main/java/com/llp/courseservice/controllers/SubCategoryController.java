package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.SubCategory.SubCategoryCreateRequest;
import com.llp.courseservice.dtos.SubCategory.SubCategoryUpdateRequest;
import com.llp.courseservice.services.SubCategoryService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/subCategory")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @Operation(summary = "Api 38: get all sub category in admin side")
    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try {
            var data = subCategoryService.getAll();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 39: get all sub category filter by categoryId in admin side")
    @RequestMapping(value = "/admin/byAdminFilterByCategory/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getByAdminFilterByCategory(@PathVariable int categoryId){
        try {
            var data = subCategoryService.getByAdminFilterByCategory(categoryId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 40: get all sub category filter by categoryId in categories filter")
    @RequestMapping(value = "/public/byUserFilterByCategory/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getByUserFilterByCategory(@PathVariable int categoryId){
        try {
            var data = subCategoryService.getByUserFilterByCategory(categoryId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 41: get sub category by id")
    @RequestMapping(value = "/admin/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            var data = subCategoryService.getById(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 42: create sub category")
    @RequestMapping(value = "/admin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody SubCategoryCreateRequest request){
        try {
            subCategoryService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 43: update sub category")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody SubCategoryUpdateRequest request){
        try {
            subCategoryService.update(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 44: update status of sub category. When status = true, user can see")
    @RequestMapping(value = "/admin/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable int id){
        try {
            subCategoryService.updateStatus(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        }  catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 45: delete sub category")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            subCategoryService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
