package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Course.CourseCreateRequest;
import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.services.SectionService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course/section")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;
    @Operation(summary = "Api 74: create section in course")
    @RequestMapping(value = "/teacher", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestBody SectionCreateRequest request, @RequestParam String courseId){
        try {
            var data = sectionService.create(request, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
