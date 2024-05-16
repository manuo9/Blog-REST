package com.spring.blog.service;

import com.spring.blog.entity.PostEntity;
import com.spring.blog.payload.PostDto;
import com.spring.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getallPost(int pageNo , int pageSize, String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto ,Long id);

    void deletePost(Long id);

}
