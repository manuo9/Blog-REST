package com.spring.nscl.service.impl;

import com.spring.nscl.entity.PostEntity;
import com.spring.nscl.exception.ResourceNotFoundException;
import com.spring.nscl.payload.PostDto;
import com.spring.nscl.payload.PostResponse;
import com.spring.nscl.repository.PostRepository;
import com.spring.nscl.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
   // Constructor Autowired
    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

    // fetching the fields from payload to PostDto and setting it to the PostEntity
        // converting DTO to Entity

        PostEntity post =  maptoentity(postDto);

     // saving all the field persisted by db and setting it to a new PostEntity Object
        // schema and data is filled
        PostEntity newPost  = postRepository.save(post);

     // Through db data now creating DTO with fields we want to share to client
       // converting Entity to DTO
        PostDto realDto = maptodto(newPost);


        return realDto;
    }

    @Override
    public PostResponse getallPost(int pageNo, int pageSize,String sortBy,String sortDir) {
Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page <PostEntity> post = postRepository.findAll(pageable);
        // to fetch list of posts from Page object
        List <PostEntity>  newpo= post.getContent();
        List<PostDto> dtos = new ArrayList<>();
        for (PostEntity pos : newpo ){
            dtos.add(maptodto(pos));
        }
        PostResponse postResponse= new PostResponse();
        postResponse.setContent(dtos);
        postResponse.setPageNo(post.getNumber());
        postResponse.setPageSize(post.getSize());
        postResponse.setTotalElements(post.getTotalElements());
        postResponse.setTotalPages(post.getTotalPages());
        postResponse.setLast(post.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post" , "id", id));
        return maptodto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post" , "id", id));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        PostEntity newpost=  postRepository.save(post);
        return   maptodto(newpost);
    }

    @Override
    public void deletePost(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post" , "id", id));
        postRepository.delete(post);
    }


    private PostDto maptodto(PostEntity post){

        PostDto realDto = mapper.map(post,PostDto.class);
//        PostDto realDto = new PostDto ();
//        realDto.setId(post.getId());
//        realDto.setTitle(post.getTitle());
//        realDto.setDescription(post.getDescription());
//        realDto.setContent(post.getContent());
        return realDto;
    }

    private PostEntity maptoentity(PostDto postDto){

//        PostEntity post = new PostEntity();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        PostEntity post = mapper.map(postDto,PostEntity.class);
        return post;
    }

}
