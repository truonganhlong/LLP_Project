package com.llp.userservice.clients;

import com.llp.userservice.clients.dtos.CourseCardResponse;
import com.llp.userservice.clients.dtos.CourseTeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "COURSES", url = "http://localhost:8222")
public interface CourseClient {
    @RequestMapping(value = "/api/course/course/public/getByTeacher/{createdBy}", method = RequestMethod.GET)
    List<CourseTeacherResponse> getByTeacher(@PathVariable int createdBy);
    @RequestMapping(value = "/api/course/course/public/getCourseCardById/{courseId}", method = RequestMethod.GET)
    CourseCardResponse getCourseCardById(@PathVariable String courseId);
    @RequestMapping(value = "/api/course/specialized/public/getAllTeacherByCategory/{categoryId}", method = RequestMethod.GET)
    List<Integer> getAllTeacherByCategory(@PathVariable int categoryId);
    @RequestMapping(value = "/api/course/specialized/public/getAllTeacherBySubCategory{subCategoryId}", method = RequestMethod.GET)
    List<Integer> getAllTeacherBySubCategory(@PathVariable int subCategoryId);
}
