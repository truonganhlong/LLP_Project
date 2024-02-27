package com.llp.courseservice.clients;

import com.llp.courseservice.clients.dtos.InstructorResponse;
import com.llp.courseservice.clients.dtos.YourLectureResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value= "USERS", url = "http://localhost:8222")
public interface UserClient {
    @RequestMapping(value = "/api/user/user/public/instructorInformation/{userId}", method = RequestMethod.GET)
    InstructorResponse getInstructorInformation(@PathVariable int userId);

    @RequestMapping(value = "/api/user/user/public/userInformation/{userId}", method = RequestMethod.GET)
    InstructorResponse getUserInformation(@PathVariable int userId);

    @RequestMapping(value = "/api/user/user/returnUserId", method = RequestMethod.GET)
    int returnUserId(@RequestHeader("Authorization") String authorizationHeader);

    @RequestMapping(value="/api/user/yourCourse/authenticationCourse", method = RequestMethod.GET)
    boolean isHaveAuthenticationCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId);

    @RequestMapping(value="/api/user/yourLecture/countByUserAndCourse", method = RequestMethod.GET)
    int countByUserAndCourse(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId);

    @RequestMapping(value="/api/user/yourLecture", method = RequestMethod.POST)
    void create(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId, @RequestParam int lectureId);

    @RequestMapping(value="/api/user/yourLecture", method = RequestMethod.PUT)
    void update(@RequestHeader("Authorization") String authorizationHeader, @RequestParam String courseId, @RequestParam int lectureId);

    @RequestMapping(value="/api/user/yourLecture/getAllYourLecture", method = RequestMethod.GET)
    List<YourLectureResponse> getAllYourLecture(@RequestHeader("Authorization") String authorizationHeader);
}
