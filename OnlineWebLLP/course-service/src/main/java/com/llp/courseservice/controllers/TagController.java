package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Tag.TagCreateRequest;
import com.llp.courseservice.dtos.Tag.TagUpdateRequest;
import com.llp.courseservice.services.TagService;
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
@RequestMapping("/api/course/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {
    private final TagService tagService;
    @Operation(summary = "Api 46: get all tag in admin side")
    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        try {
            var data = tagService.getAll();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 47: get tag by id")
    @RequestMapping(value = "/public/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            var data = tagService.getById(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 48: create tag")
    @RequestMapping(value = "/admin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody TagCreateRequest request){
        try {
            tagService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 49: update tag")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TagUpdateRequest request){
        try {
            tagService.update(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 50: delete tag")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            tagService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 119: update bestseller tag")
    @RequestMapping(value = "/public/updateBestsellerTag", method = RequestMethod.POST)
    public ResponseEntity<?> updateBestsellerTag(){
        try {
            tagService.updateBestsellerTag();
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 120: update highest rated tag")
    @RequestMapping(value = "/public/updateHighestRatedTag", method = RequestMethod.POST)
    public ResponseEntity<?> updateHighestRatedTag(){
        try {
            tagService.updateHighestRatedTag();
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
