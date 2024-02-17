package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.Course.CourseCreateRequest;
import com.llp.courseservice.dtos.Course.CourseFilter;
import com.llp.courseservice.services.CourseService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @Operation(summary = "Api 17: get course's overview which is the course's data when user hover in course")
    @RequestMapping(value = "/public/courseOverviewById/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/public/allProminentCourse", method = RequestMethod.GET)
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
    @Operation(summary = "Api 66: get all prominent course cards in Featured courses after select a topic")
    @RequestMapping(value = "/public/allProminentCourseByTopic/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProminentCourseByTopicId(@PathVariable int topicId,
                                                               @RequestParam(defaultValue = "0") Integer pageNo,
                                                               @RequestParam(defaultValue = "1") Integer pageSize)
    {
        try {
            var data = courseService.getAllProminentCourseByTopicId(topicId, pageNo, pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 60: get all prominent course cards in Featured courses after select a sub category")
    @RequestMapping(value = "/public/allProminentCourseBySubCategory/{subCategoryId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/public/allProminentCourseByCategory/{categoryId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/public/allCourseByTopic/{topicId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/public/allCourseBySubCategory/{subCategoryId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/public/allCourseByCategory/{categoryId}", method = RequestMethod.GET)
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

    @Operation(summary = "Api 67: update course to prominent")
    @RequestMapping(value = "/admin/prominent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateToProminent(@PathVariable String id){
        try {
            courseService.updateToProminent(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        }  catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 68: update status course")
    @RequestMapping(value = "/admin/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable String id){
        try {
            courseService.updateStatus(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        }  catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 69: delete course")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id){
        try {
            courseService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 70: search course by name", description = "support paging, sorting and filtering")
    @RequestMapping(value = "/public/searchCourseByName/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<?> searchCourseByName(@PathVariable String keyword,
                                                      @RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(defaultValue = "15") Integer pageSize,
                                                      @RequestParam(defaultValue = "ratingNum") String sortBy,
                                                      @RequestBody CourseFilter filter)
    {
        try {
            var data = courseService.searchCourseByName(keyword, pageNo, pageSize, sortBy, filter);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 71: create course")
    @RequestMapping(value = "/teacher", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestBody CourseCreateRequest request){
        try {
            var data = courseService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 85: get course of teacher")
    @RequestMapping(value = "/public/getByTeacher/{createdBy}", method = RequestMethod.GET)
    public ResponseEntity<?> getByTeacher(@PathVariable int createdBy){
        try {
            var data = courseService.getByTeacher(createdBy);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
