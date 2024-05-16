package com.spring.blog.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JwtResponse {

    private  String jwtToken;
    private  String tokenType="Bearer";

}
