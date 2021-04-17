package com.micro.postservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.micro.postservice.PostServiceApplication.posts;

@SpringBootApplication
public class PostServiceApplication {
    static Map<Long, Post> posts = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    void populate() {
        posts.put(1L, new Post(1L, "post description"));
    }
}

@RestController
@RequestMapping("/api/posts")
class PostController {

    @GetMapping("/{id}")
    Post findById(@PathVariable Long id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        } else {
            throw new ResourceNotFound("Post with ID " + id + " not found");
        }
    }
}

@Data
@AllArgsConstructor
class Post {
    Long id;
    String description;
}

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}