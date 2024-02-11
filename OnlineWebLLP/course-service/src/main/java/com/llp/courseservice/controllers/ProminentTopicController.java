package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicCreateRequest;
import com.llp.courseservice.dtos.ProminentTopic.ProminentTopicUpdateRequest;
import com.llp.courseservice.services.ProminentTopicService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/prominentTopic")
@RequiredArgsConstructor
public class ProminentTopicController {
    private final ProminentTopicService prominentTopicService;
    @Operation(summary = "Api 32: get all prominent topics in admin side")
    @RequestMapping(value = "/admin/getAllByAdmin", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByAdmin(){
        try {
            var data = prominentTopicService.getAllByAdmin();
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 33: get all prominent topics in user side", description = "result also return all prominent course in that topic. courses are also pageable")
    @RequestMapping(value = "/public/getAllByUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(@RequestParam(defaultValue = "0") Integer pageNo,
                                          @RequestParam(defaultValue = "5") Integer pageSize)
    {
        try {
            var data = prominentTopicService.getAllByUser(pageNo,pageSize);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 34: get prominent topic by id")
    @RequestMapping(value = "/admin/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            var data = prominentTopicService.getById(id);
            return ResponseEntity.ok(data);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 35: create prominent topic")
    @RequestMapping(value="/admin",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody ProminentTopicCreateRequest request){
        try {
            prominentTopicService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 36: update prominent topic")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ProminentTopicUpdateRequest request){
        try {
            prominentTopicService.update(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update successfully");
        } catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Operation(summary = "Api 37: delete prominent topic")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            prominentTopicService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
