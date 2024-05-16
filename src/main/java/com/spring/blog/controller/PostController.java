package com.spring.blog.controller;

import com.spring.blog.payload.PostDto;
import com.spring.blog.payload.PostResponse;
import com.spring.blog.service.PostService;
import com.spring.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST API for Post resources"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation (
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost( @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation (
            summary = "Get ALL Posts REST API",
            description = "Get All Posts REST API is used to fetch all the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getallPost(
            @RequestParam (value = "pageNo" ,required = false,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam (value = "pageSize" ,required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam (value = "sortBy" ,required = false ,defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam (value = "sortDir" ,required = false ,defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir

    ) {
        PostResponse p = postService.getallPost(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(p);
    }

    @Operation (
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity <PostDto> getById( @PathVariable (name ="id") Long id ){
      return  ResponseEntity.ok( postService.getPostById(id));
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
    @PutMapping("/{id}")
    public ResponseEntity  <PostDto> updatePost (@Valid @RequestBody PostDto postDto , @PathVariable (name = "id") Long id ){
     return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.CREATED);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable (name="id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post Deleted",HttpStatus.CREATED);
    }


}
