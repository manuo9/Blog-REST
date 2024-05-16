package com.spring.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {

    private Long id;

    //title shouldn't be null and must be at least  2 words
    @NotEmpty
    @Size(min=2,message = "Post Title attribute have at least 2 chars")
    private String title;

    @NotEmpty
    @Size(min=10,message = "Post description attribute have at least 10 chars")
    private String description;

    @NotEmpty
    private String content;

    private Set <CommentDto> comments;

}
