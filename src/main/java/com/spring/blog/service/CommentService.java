package com.spring.blog.service;

import com.spring.blog.entity.Comment;
import com.spring.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
      CommentDto  createComment (Long postId , CommentDto commentDto);

      List<CommentDto> getCommentsByPostId (Long postId);

      CommentDto getCommentById(Long postId, Long commentId);

      CommentDto updateComment(Long postId, Long commentId,CommentDto commentRequest);

     void deleteComment (Long postId, Long commentId);


}
