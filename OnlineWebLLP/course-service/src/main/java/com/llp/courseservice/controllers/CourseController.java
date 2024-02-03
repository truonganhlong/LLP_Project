package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Course.CourseFilter;
import com.llp.courseservice.services.CourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/course/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @Operation(summary = "Api 17: get course's overview which is the course's data when user hover in course")
    @RequestMapping(value = "/courseOverviewById/{id}", method = RequestMethod.GET)
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
    @Operation(summary = "Api 59: get all prominent course cards in Learners are viewing in homepage")
    @RequestMapping(value = "/allProminentCourse", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProminentCourse(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "5") Integer pageSize)
    {
        try {
            var data = courseService.getAllProminentCourse(pageNo, pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 60: get all prominent course cards in Featured courses after select a sub category")
    @RequestMapping(value = "/allProminentCourseBySubCategoryId/{subCategoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProminentCourseBySubCategoryId(@PathVariable int subCategoryId,
                                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "1") Integer pageSize)
    {
        try {
            var data = courseService.getAllProminentCourseBySubCategoryId(subCategoryId, pageNo, pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 61: get all prominent course cards in Featured courses after select a category")
    @RequestMapping(value = "/allProminentCourseByCategoryId/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProminentCourseByCategoryId(@PathVariable int categoryId,
                                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                                  @RequestParam(defaultValue = "1") Integer pageSize)
    {
        try {
            var data = courseService.getAllProminentCourseByCategoryId(categoryId, pageNo, pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 62: get all course cards inside topic page", description = "support paging, sorting and filtering")
    @RequestMapping(value = "/allCourseByTopicId/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourseByTopicId(@PathVariable int topicId,
                                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "15") Integer pageSize,
                                                   @RequestParam(defaultValue = "ratingNum") String sortBy,
                                                   @RequestBody CourseFilter filter)
    {
        try {
            var data = courseService.getAllCourseByTopicId(topicId, pageNo, pageSize, sortBy, filter);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 63: get all course cards inside sub category page", description = "support paging, sorting and filtering")
    @RequestMapping(value = "/allCourseBySubCategoryId/{subCategoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourseBySubCategoryId(@PathVariable int subCategoryId,
                                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "15") Integer pageSize,
                                                   @RequestParam(defaultValue = "ratingNum") String sortBy,
                                                   @RequestBody CourseFilter filter)
    {
        try {
            var data = courseService.getAllCourseBySubCategoryId(subCategoryId, pageNo, pageSize, sortBy, filter);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 64: get all course cards inside category page", description = "support paging, sorting and filtering")
    @RequestMapping(value = "/allCourseByCategoryId/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourseByCategoryId(@PathVariable int categoryId,
                                                      @RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(defaultValue = "15") Integer pageSize,
                                                      @RequestParam(defaultValue = "ratingNum") String sortBy,
                                                      @RequestBody CourseFilter filter)
    {
        try {
            var data = courseService.getAllCourseByCategoryId(categoryId, pageNo, pageSize, sortBy, filter);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
