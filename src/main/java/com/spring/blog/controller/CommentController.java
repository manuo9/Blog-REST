package com.spring.blog.controller;

import com.spring.blog.payload.CommentDto;
import com.spring.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class CommentController {

   private CommentService commentService;

   public  CommentController (CommentService commentService){
       this.commentService=commentService;
   }


    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get ALL Posts REST API",
            description = "Get All Posts REST API is used to fetch all the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId (@PathVariable(value = "postId")Long postId){
        return commentService.getCommentsByPostId(postId);
    }


    @Operation(
            summary = "Get Comments by PostID API",
            description = "Get Comment REST API is used to fetch data by postId the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId,commentId), HttpStatus.OK);
    }

    @Operation (
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId,
                                                    @Valid @RequestBody CommentDto commentRequest) {

        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentRequest), HttpStatus.OK);
    }


    @Operation (
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable (value="postId") Long postId, @PathVariable (value="id")  Long commentId) {
        commentService.deleteComment(postId,commentId);
       return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }


}
