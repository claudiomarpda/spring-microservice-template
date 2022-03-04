package com.micro.timelineservice;

import feign.FeignException;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class TimelineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineServiceApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class TimelineController {
    PostClient postClient;

    public TimelineController(PostClient pc) {
        this.postClient = pc;
    }

    @PostMapping("/posts/{postId}/process")
    Post processPost(@PathVariable Long postId) {
        return postClient.findById(postId);
    }
}

@FeignClient(name = "post-service", configuration = PostClientConfig.class)
interface PostClient {

    @GetMapping("/api/posts/{postId}")
    Post findById(@PathVariable Long postId);
}

@Data
class Post {
    Long id;
    String description;
}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    String handleFeignClientException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return e.contentUTF8();
    }
}