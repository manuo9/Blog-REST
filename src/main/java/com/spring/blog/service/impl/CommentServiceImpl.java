package com.spring.blog.service.impl;

import com.spring.blog.entity.Comment;
import com.spring.blog.entity.PostEntity;
import com.spring.blog.exception.BlogAPIException;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.payload.CommentDto;
import com.spring.blog.repository.CommentRepository;
import com.spring.blog.repository.PostRepository;
import com.spring.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl (CommentRepository commentRepository,PostRepository postRepository,ModelMapper mapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }
    @Override
    public CommentDto createComment(Long postId ,CommentDto commentDto) {

        Comment comment = maptoEntity(commentDto);
// retrieve post entity by id
        PostEntity post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "id", postId));
// set post to comment entity
        comment.setPost(post);
// comment entity to db
        Comment commm = commentRepository.save(comment);

        return maptoDto(commm);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
// retrieve comment by postid
        List <Comment> comment =commentRepository.findByPostId(postId);

        return comment.stream().map(this::maptoDto).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
      //  retrieve post entity through postId
        PostEntity post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "id", postId));

      // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment" , "id", commentId));

        if(!comment.getPost().getId() .equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment doesn't belong to Post");
        }

        return maptoDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //  retrieve post entity through postId
        PostEntity post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment" , "id", commentId));

        if(!comment.getPost().getId() .equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment doesn't belong to Post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment comm = commentRepository.save(comment);

        return maptoDto(comm);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //  retrieve post entity through postId
        PostEntity post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment" , "id", commentId));

        if(!comment.getPost().getId() .equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment doesn't belong to Post");
        }

        commentRepository.delete(comment);

    }


    private CommentDto maptoDto(Comment comment){

//       CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        CommentDto commentDto = mapper.map(comment,CommentDto.class);
        return commentDto;
    }


    private Comment maptoEntity(CommentDto commentDto){

//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        Comment comment = mapper.map(commentDto,Comment.class);
        return comment;
    }

}
