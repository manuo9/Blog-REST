//package com.spring.blog.feign;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class BlogFeignService {
//
//    @Autowired
//   private BlogFeign blogFeign;
//
//    public Optional<FeignUserEntityForBlog> getUserById(Long id) {
//        return blogFeign.getById(id);
//    }
//
//}
////