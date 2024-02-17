package com.llp.courseservice.clients;

import com.llp.courseservice.clients.dtos.InstructorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value= "USERS")
public interface UserClient {
    @RequestMapping(value = "/api/user/user/public/instructorInformation/{userId}", method = RequestMethod.GET)
    InstructorResponse getInstructorInformation(@PathVariable int userId);
}
