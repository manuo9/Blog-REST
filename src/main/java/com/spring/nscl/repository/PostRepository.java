package com.spring.nscl.repository;

import com.spring.nscl.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository <PostEntity,Long > {

}
