package com.llp.userservice.clients;

import com.llp.userservice.clients.dtos.CourseCardResponse;
import com.llp.userservice.clients.dtos.CourseTeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "COURSES", url = "http://localhost:8222")
public interface CourseClient {
    @RequestMapping(value = "/api/course/course/public/getByTeacher/{createdBy}", method = RequestMethod.GET)
    List<CourseTeacherResponse> getByTeacher(@PathVariable int createdBy);
    @RequestMapping(value = "/api/course/course/public/getCourseCardById/{courseId}", method = RequestMethod.GET)
    CourseCardResponse getCourseCardById(@PathVariable String courseId);
}
