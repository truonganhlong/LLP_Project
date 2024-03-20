package com.llp.courseservice.controllers;

import com.llp.courseservice.services.VisitCourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/visitCourse")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VisitCourseController {
    private final VisitCourseService visitCourseService;
    @Operation(summary = "Api 137: get number of visits in your course")
    @RequestMapping(value = "/teacher/getAllVisitByCourse", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVisitByCourse(@RequestParam String courseId,
                                                 @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now().toString()}")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String day){
        try {
            var data = visitCourseService.getAllVisitByCourse(courseId,day);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
