package com.llp.courseservice.clients;

import com.llp.courseservice.clients.dtos.InstructorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value= "USERS", url = "http://localhost:8222")
public interface UserClient {
    @RequestMapping(value = "/api/user/user/public/instructorInformation/{userId}", method = RequestMethod.GET)
    InstructorResponse getInstructorInformation(@PathVariable int userId);

    @RequestMapping(value = "/api/user/user/public/userInformation/{userId}", method = RequestMethod.GET)
    InstructorResponse getUserInformation(@PathVariable int userId);

    @RequestMapping(value = "/api/user/user/returnUserId", method = RequestMethod.GET)
    int returnUserId(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(value="/authenticationCourse", method = RequestMethod.GET)
    boolean isHaveAuthenticationCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId);
}
