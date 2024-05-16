package com.spring.blog.repository;

import com.spring.blog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository <PostEntity,Long > {

}
