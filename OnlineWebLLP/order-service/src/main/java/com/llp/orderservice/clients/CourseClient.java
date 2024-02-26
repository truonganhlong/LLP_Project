package com.llp.orderservice.clients;

import com.llp.orderservice.clients.dtos.CourseCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "COURSES", url = "http://localhost:8222")
public interface CourseClient {
    @RequestMapping(value = "/api/course/course/public/updateSaleNum/{courseId}", method = RequestMethod.PUT)
    void updateSaleNum(@PathVariable String courseId);
    @RequestMapping(value = "/api/course/course/public/getCourseCardById/{courseId}", method = RequestMethod.GET)
    CourseCardResponse getCourseCardById(@PathVariable String courseId);
}
