package com.apple.inc.user.client;

import com.apple.inc.user.dto.FieldParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * External Feign client for other services to interact with user-service
 * This jar will be imported by other microservices to call user-service APIs
 */
@FeignClient(name = "user-service", path = "/user")
public interface UserServiceClient {

    @GetMapping("/")
    String sampleMethod();

    @GetMapping("/{id}")
    FieldParam getUserById(@PathVariable("id") Long id);

    @PostMapping("/")
    FieldParam createUser(@RequestBody FieldParam userRequest);

    @PutMapping("/{id}")
    FieldParam updateUser(@PathVariable("id") Long id, @RequestBody FieldParam userRequest);

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id);

    @GetMapping("/search")
    List<FieldParam> searchUsers(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email);
}
