package com.twitter.demo.controllers;

import com.twitter.demo.entities.Comments;
import com.twitter.demo.entities.User;
import com.twitter.demo.entities.dto.CommentsDto;
import com.twitter.demo.entities.dto.CreateCommentDto;
import com.twitter.demo.entities.dto.UserDto;
import com.twitter.demo.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentDto request){
        commentService.createComment(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Comments>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentsDto>> getCommentsByUserId(@PathVariable UUID userId) {

            List<CommentsDto> comments = commentService.getUserComments(userId);
            return ResponseEntity.ok(comments);

    }

}
