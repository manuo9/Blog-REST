package com.spring.nscl.entity;

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
