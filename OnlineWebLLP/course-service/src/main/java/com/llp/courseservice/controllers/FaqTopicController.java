package com.llp.courseservice.controllers;

import com.llp.courseservice.dtos.FaqTopic.FaqTopicRequest;
import com.llp.courseservice.services.FaqTopicService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/faqTopic")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FaqTopicController {
    private final FaqTopicService faqTopicService;

    @Operation(summary = "Api 128: get all faq in topic page")
    @RequestMapping(value = "/public/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getALlByTopic(@PathVariable int topicId){
        try {
            var data = faqTopicService.getAllByTopic(topicId);
            return ResponseEntity.ok(data);
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 129: create faq")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody FaqTopicRequest request){
        try {
            faqTopicService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Api 130: delete faq")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            faqTopicService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
