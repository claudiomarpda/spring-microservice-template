package com.micro.postservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.micro.postservice.PostServiceApplication.posts;

@EnableDiscoveryClient
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

@RefreshScope
@RestController
@RequestMapping("/api/posts")
class PostController {

    Environment environment;

    public PostController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/{id}")
    Post findById(@PathVariable Long id) {
        if (posts.containsKey(id)) {
            posts.get(id).setDescription("server port " + environment.getProperty("server.port"));
            return posts.get(id);
        } else {
            throw new ResourceNotFound("Post with ID " + id + " not found");
        }
    }

    @GetMapping
    String getMyProperty(@RequestParam String key) {
        return environment.getProperty(key);
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