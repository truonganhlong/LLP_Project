package com.llp.courseservice.controllers;

import com.llp.courseservice.services.SpecializedService;
import com.llp.sharedproject.exceptions.InternalServerException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/specialized")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SpecializedController {
    private final SpecializedService specializedService;
    @Operation(summary = "Api 121: get specialized for user service filter by category")
    @RequestMapping(value = "/public/getAllTeacherByCategory/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTeacherByCategory(@PathVariable int categoryId){
        try {
            var data = specializedService.getAllTeacherByCategory(categoryId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 122: get specialized for user service filter by subCategory")
    @RequestMapping(value = "/public/getAllTeacherBySubCategory/{subCategoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTeacherBySubCategory(@PathVariable int subCategoryId){
        try {
            var data = specializedService.getAllTeacherBySubCategory(subCategoryId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
