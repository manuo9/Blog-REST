package com.spring.nscl.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "USER")
public interface BlogFeign {


    @GetMapping("/user/getById")
    public Optional<FeignUserEntityForBlog> getById(@RequestParam Long id);

}
