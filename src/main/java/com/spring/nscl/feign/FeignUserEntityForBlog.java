package com.spring.nscl.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeignUserEntityForBlog {


    private Long id;
    private String password;
    private String gender;
    private String email;
    private String profilePictureUrl;
    private String firstName;
    private String lastName;
    public FeignUserEntityForBlog(String email, String gender, String lastName,String firstName) {
        this.email=email;
        this.lastName=lastName;
        this.gender=gender;
        this.firstName=firstName;
    }
}
