package com.spring.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private long id;
    @NotEmpty( message =  "Name should not be Empty or Null")
    private String name;

    @NotEmpty( message =  "Email should not be Empty or Null")
    @Email
    private String email;

    @Size( min =10 ,message =  "Body should not be Empty or Null")
    private String body;
}
