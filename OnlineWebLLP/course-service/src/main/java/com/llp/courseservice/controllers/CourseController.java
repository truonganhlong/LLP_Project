package com.llp.courseservice.controllers;

import com.llp.courseservice.services.CourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/course/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCourseOverview(@PathVariable String id){
        try {
            var data = courseService.getCourseOverview(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
