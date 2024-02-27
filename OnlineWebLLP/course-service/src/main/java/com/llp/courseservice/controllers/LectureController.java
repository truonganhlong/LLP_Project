package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Lecture.LectureCreateRequest;
import com.llp.courseservice.dtos.Section.SectionCreateRequest;
import com.llp.courseservice.services.LectureService;
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
@RequestMapping("/api/course/lecture")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @Operation(summary = "Api 75: create lecture in section")
    @RequestMapping(value = "/teacher", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestBody LectureCreateRequest request, @RequestParam int sectionId){
        try {
            lectureService.create(request, sectionId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 116: get lecture by id")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id, @RequestHeader(name = "Authorization") String authorizationHeader, @RequestParam String courseId){
        try {
            var data = lectureService.getById(id,authorizationHeader,courseId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 117: get lecture studied in homepage after login")
    @RequestMapping(value = "/user/getAllLectureStudied", method = RequestMethod.GET)
    public ResponseEntity<?> getAllLectureStudied(@RequestHeader(name = "Authorization") String authorizationHeader){
        try {
            var data = lectureService.getAllLectureStudied(authorizationHeader);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
