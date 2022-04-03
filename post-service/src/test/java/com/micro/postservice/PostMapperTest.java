package com.micro.postservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {

    PostMapper mapper = new PostMapperImpl();

    @Test
    void postToPostDto() {
        // arrange
        var post = Post.builder().id(1L).description("a").build();
        // act
        var dto = mapper.postToPostDto(post);
        // assert
        assertEquals(post.getId(), dto.getId());
        assertEquals(post.getDescription(), dto.getDescription());
    }

    @Test
    void postDtoToPost() {
        // a
        var dto = PostDto.builder().id(1L).description("a").build();
        // a
        var post = mapper.postDtoToPost(dto);
        // a
        assertEquals(dto.getId(), post.getId());
        assertEquals(dto.getDescription(), post.getDescription());
    }

}