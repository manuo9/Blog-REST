package com.spring.nscl.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/test")
public class BlogFeignController {
//    @Autowired
//    private BlogFeignService blogFeignService;
    @Autowired
    private BlogFeign blogFeign;

    @GetMapping("/user/{id}")
    public Optional<FeignUserEntityForBlog> getUserById(@PathVariable Long id) {
        return blogFeign.getById(id);
    }
}
