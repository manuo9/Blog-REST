package com.spring.nscl.service;

import com.spring.nscl.payload.PostDto;
import com.spring.nscl.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getallPost(int pageNo , int pageSize, String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto ,Long id);

    void deletePost(Long id);

}
