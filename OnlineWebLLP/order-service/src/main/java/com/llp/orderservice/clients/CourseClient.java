package com.llp.orderservice.clients;

import com.llp.orderservice.clients.dtos.CourseCardResponse;
import com.llp.orderservice.clients.dtos.CourseTeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value= "COURSES", url = "http://localhost:8222")
public interface CourseClient {
    @RequestMapping(value = "/api/course/course/public/updateSaleNum/{courseId}", method = RequestMethod.PUT)
    void updateSaleNum(@PathVariable String courseId);
    @RequestMapping(value = "/api/course/course/public/getCourseCardById/{courseId}", method = RequestMethod.GET)
    CourseCardResponse getCourseCardById(@PathVariable String courseId);
    @RequestMapping(value = "/api/course/course/public/getByTeacher/{createdBy}", method = RequestMethod.GET)
    List<CourseTeacherResponse> getByTeacher(@PathVariable int createdBy);
}
