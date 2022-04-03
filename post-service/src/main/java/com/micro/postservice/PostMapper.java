package com.micro.postservice;

import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {

    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto dto);

}
